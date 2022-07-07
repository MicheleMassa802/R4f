package main.java.Controllers;

import java.util.ArrayList;

import main.java.User.CreateUserUseCase;
import main.java.User.LoginUserUseCase;
import main.java.User.UserEntity;

public class UserController {
    // manages communication to the use cases for the User object

    private final CreateUserUseCase userCreator;
    private final LoginUserUseCase userLogger;
    // attributes to be setup upon login
    public String username;
    public String userId;
    public String userBdId;
    public String userCalendarId;


    /**
     * User controller constructor, setting up the use cases
     */
    public UserController(){
        this.userCreator = new CreateUserUseCase();
        this.userLogger = new LoginUserUseCase();

    }


    /**
     * Feeds user creation information to use case, and executes user creation steps
     * @param username  username of user
     * @param pswd      corresponding password
     * @param name      name of user
     * @param lName     last name of user
     * @param notiType  type of notification to be sent to user
     */
    public void executeCreateUser(String username, String pswd, String name, String lName, int notiType){
        this.userId = this.userCreator.runUserCreation(username, pswd, name, lName, notiType);
    }


    /**
     * Execute the login steps for a user using their credentials
     * @param username  username of account to log into
     * @param pswd      password of the account
     * @param firstTime bool representing whether this is the first login or not
     */
    public void executeLoginUser(String username, String pswd, boolean firstTime){
        // user id and username now setup, now must setup calendar id and bd id upon creation of those entities
        this.userId = this.userLogger.login(username, pswd, firstTime);
        this.username = this.userLogger.userInstance.getUsername();

        if (!firstTime){
            // get the other related information
            this.userBdId = this.userLogger.userInstance.getBdId();
            this.userCalendarId = this.userLogger.userInstance.getCalendarId();
        }
    }


    /**
     * To be called after birthday id, sml id, and calendar id are present (in session), used to upload
     * these ids to the database (this should only be setup once)
     * @param username          username you are currently logged into
     * @param bdId,calId,smlId  ids to setup in the table to fill in missing info      
     */
    public void firstLoginFollowUp(String username, String bdId, String calId, String smlId){
        this.userLogger.updateRemainingInfo(username, bdId, calId, smlId);
    }


    /**
     * This method is used to fetch user info given their id (to show account details)
     * @param userId    the id of the user
     * @return          the arraylist with all of the user information (entire row)
     */
    public ArrayList<String> getUserFromId(String userId){
        ArrayList<String> userData = this.userCreator.createUserFromId(userId);
        return userData;

    }


}
