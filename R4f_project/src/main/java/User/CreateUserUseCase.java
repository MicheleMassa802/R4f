package main.java.User;

import java.sql.SQLException;
import java.util.ArrayList;

import main.java.Database.ExecuteQuery;
import main.java.Database.IDBConnectionPoint;

public class CreateUserUseCase {
    // use case for signing up (checks for valid username uniqueness, etc)

    // db constants (table name and columns)
    private final String DB_TABLE = "users";
    // private final String ID = "user_id";
    private final String USERNAME = "username";
    private final String PASSWORD = "password";
    private final String NAME = "name";
    private final String LAST_NAME = "lastname";
    private final String SML_ID = "sml_id";
    private final String NOTI_TYPE = "notification_type";
    private final String BD_ID = "birthday_id";
    private final String CAL_ID = "calendar_id";

    private IDBConnectionPoint dbConnection;


    /**
     * Constructor for user creation use case, in charge of initiating the link to the database connection class to store user info
     */
    public CreateUserUseCase(){
        // table name and columns where we'll insert info
        this.dbConnection = new ExecuteQuery(DB_TABLE, USERNAME, PASSWORD, NAME, LAST_NAME, SML_ID, NOTI_TYPE, BD_ID, CAL_ID);
    }

    /**
     * Stores information in the database, the columns not provided are set to null, and then replaced upon creation (such as ids of other entities)
     * @param username  username of user
     * @param pswd      corresponding password
     * @param name      name of user
     * @param lName     last name of user
     * @param notiType  type of notification to be sent to user
     */
    public void runUserCreation(String username, String pswd, String name, String lName, int notiType){
        
        if (this.checkUniqueUsername(username)){
            // upload info to db
            try {
                dbConnection.executeInsert(username, pswd, name, lName, "", Integer.toString(notiType), "", "");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // else, do nothing, answer already printed
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
}
