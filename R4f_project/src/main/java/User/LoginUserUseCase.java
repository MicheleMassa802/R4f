package main.java.User;

import java.sql.SQLException;
import java.util.ArrayList;

import main.java.Database.ExecuteQuery;
import main.java.Database.IDBConnectionPoint;

public class LoginUserUseCase {
    // use case for login in

    // db constants (table name and columns)
    private final String DB_TABLE = "users";
    private final String USERNAME = "username";
    private final String SML_ID = "sml_id";
    private final String BD_ID = "birthday_id";
    private final String CAL_ID = "calendar_id";

    private IDBConnectionPoint dbConnection;
    public UserEntity userInstance;


    /**
     * Constructor for the login use case, setting up the db connection
     */
    public LoginUserUseCase(){
        this.dbConnection = new ExecuteQuery(DB_TABLE, "");  // no need to insert so we leave the columns attribute empty
    }


    /**
     * Execute the login action for a user using their username and password
     * @param username  username of account to log into
     * @param pswd      password of the account
     * @param firstTime bool representing whether this is the first login or not (only triggered immediatly after user creation branch)
     * @return          the user_id related to this username (which is then used to fetch the related information)
     */
    public String login(String username, String pswd, boolean firstTime){
        if (this.checkPassword(username, pswd)){
            // create user entity and validate login
            ArrayList<String> data = new ArrayList<>();
            try {
                // retrieve the 10 columns (in order) from the column with username this.username
                data = this.dbConnection.executeRetrieve(USERNAME, username, 10, null);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Username provided not valid, please enter another one");
            }

            // create user entity instance and store it as an attribute
            this.userInstance = new UserEntity(data.get(1), data.get(2), data.get(9),
            Integer.parseInt(data.get(6)));

            if (firstTime){
                // first time meaning not all info yet setup
                System.out.println("Login Successful. Welcome " + username);
                return data.get(0);
            } else {
                // not the first time meaning rest of user info is set up, so return instance with everything
                this.userInstance.setUserId(data.get(0));
                this.userInstance.setBdId(data.get(7));
                this.userInstance.setCalendarId(data.get(8));
                System.out.println("Login Successful. Welcome " + username);
                return data.get(0);
            }
            
        }
        // else (pswd and username check failed)
        System.out.println("Credentials didn't match, please re-enter your username and password");
        return null;

    }


    /**
     * check for the existence and accuracy of the credentials entered in the login
     * @param username  username of account to log into
     * @param pswd      password of the account
     * @return          whether or not the credentials in the username and password attributes are valid
     */
    public boolean checkPassword(String username, String pswd){

        ArrayList<String> data = new ArrayList<>();
        try {
            // retrieve the id, username and password (in that order) from the column with username this.username
            data = this.dbConnection.executeRetrieve(USERNAME, username, 3, null);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Username provided not valid, please enter another one");
            return false;
        }

        // check if username existed
        if (data.size() == 0){
            // username entered invalid
            return false;
        }

        if (data.get(2).equals(pswd)){
            // passwords match
            return true;
        }
        // else username exists but pswd doesnt match
        return false;

    }


    /**
     * To be called after birthday id, sml id, and calendar id are present (in session), used to upload
     * these ids to the database (this should only be setup once)
     * @param username          username to update info for
     * @param bdId,calId,smlId  ids to setup in the table to fill in missing info      
     */
    public void updateRemainingInfo(String username, String bdId, String calId, String smlId){
        try {
            this.dbConnection.executeUpdate(USERNAME, username, SML_ID, smlId);
            this.dbConnection.executeUpdate(USERNAME, username, BD_ID, bdId);
            this.dbConnection.executeUpdate(USERNAME, username, CAL_ID, calId);

        } catch (SQLException e) {
            System.out.println("DB error, please check database for further information. Class: LoginUserUseCase.java");
            e.printStackTrace();
        }
    }

}
