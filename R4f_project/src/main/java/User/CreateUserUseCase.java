package main.java.User;

import java.sql.SQLException;
import java.util.ArrayList;

import main.java.Database.ExecuteQuery;
import main.java.Database.IDBConnectionPoint;

public class CreateUserUseCase {
    // use case for signing up (checks for valid username uniqueness, etc)

    // db constants (table name and columns)
    private final String DB_TABLE = "users";
    private final String U_ID = "user_id";
    private final String USERNAME = "username";
    private final String PASSWORD = "password";
    private final String NAME = "name";
    private final String LAST_NAME = "lastname";
    private final String SML_ID = "sml_id";
    private final String NOTI_TYPE = "notification_type";
    private final String BD_ID = "birthday_id";
    private final String CAL_ID = "calendar_id";
    private final String EMAIL = "email";

    private IDBConnectionPoint dbConnection;


    /**
     * Constructor for user creation use case, in charge of initiating the link to the database connection class to store user info
     */
    public CreateUserUseCase(){
        // table name and columns where we'll insert info
        this.dbConnection = new ExecuteQuery(DB_TABLE, USERNAME, PASSWORD, NAME, LAST_NAME, SML_ID, NOTI_TYPE, BD_ID, CAL_ID, EMAIL);
    }

    /**
     * Stores information in the database, the columns not provided are set to null, and then replaced upon creation (such as ids of other entities)
     * @param username  username of user
     * @param email     the user's email
     * @param pswd      corresponding password
     * @param name      name of user
     * @param lName     last name of user
     * @param notiType  type of notification to be sent to user
     * @return          return the userId of the user that was just created or null
     */
    public String runUserCreation(String username, String email, String pswd, String name, String lName, int notiType){
        
        String userId = "";

        if (this.checkUniqueUsername(username)){
            // upload info to db
            try {
                dbConnection.executeInsert(username, pswd, name, lName, "", Integer.toString(notiType), "", "", email);
                userId = dbConnection.executeRetrieve(USERNAME, username, 1, null).get(0);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return userId;


        }
        // else, do nothing, answer already printed
        return null;
    }

    /**
     * check for the uniqueness of the username provided 
     * @return  whether or not the username provided is unique or not
     */
    public boolean checkUniqueUsername(String usernameProvided){


        ArrayList<String> data = new ArrayList<>();
        try {
            // retrieve all existing usernames
            data = dbConnection.executeRetrieve(null, null, 1, USERNAME);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error in DB, please retry the account creation process");
            return false;
        }

        if (data.contains(usernameProvided)){
            // username not unique
            System.out.println("Username provided already taken, please select another one.");
            return false;
        }
        // else
        return true;
    }


    /**
     * Create and return all the information corresponding to the user their user id
     * @param userId    the user id to use to fetch db info
     * @return          the arraylist with all of the user information (entire row)
     */
    public ArrayList<String> createUserFromId(String userId){
        ArrayList<String> data = new ArrayList<>();

        // fetch the correct row of information
        try {
            data = this.dbConnection.executeRetrieve(U_ID, userId, 10, null);
        } catch (SQLException e) {
            System.out.println("User id entered not recognized, please retry with a valid user id. Class: CreateUserUseCase.java");
            e.printStackTrace();
        }
        return data;
    }
}
