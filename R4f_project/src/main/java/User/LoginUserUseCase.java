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

    private IDBConnectionPoint dbConnection;
    private String username;
    private String password;
    public UserEntity userInstance;


    /**
     * Constructor for the login use case, setting up the credentials to verify and the db connection
     * @param username  username of account to log into
     * @param pswd      password of the account
     */
    public LoginUserUseCase(String username, String pswd){
        this.username = username;
        this.password = pswd;
        this.dbConnection = new ExecuteQuery(DB_TABLE, "");  // no need to insert so we leave the columns attribute empty
    }

    public boolean login(){
        if (this.checkPassword()){
            // create user entity and validate login
            ArrayList<String> data = new ArrayList<>();
            try {
                // retrieve the 10 columns (in order) from the column with username this.username
                data = dbConnection.executeRetrieve(USERNAME, this.username, 9, null);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Username provided not valid, please enter another one");
            }

            // create user entity instance and store it as an attribute
            this.userInstance = new UserEntity(data.get(1), data.get(2), data.get(9),
                Integer.parseInt(data.get(6)));
            
            System.out.println("Login Successful. Welcome " + this.username);
            return true;
        }
        // else
        System.out.println("Credentials didn't match, please re-enter your username and password");
        return false;

    }


    /**
     * check for the existence and accuracy of the credentials entered in the login
     * @return  whether or not the credentials in the username and password attributes are valid
     */
    public boolean checkPassword(){

        ArrayList<String> data = new ArrayList<>();
        try {
            // retrieve the id, username and password (in that order) from the column with username this.username
            data = dbConnection.executeRetrieve(USERNAME, this.username, 3, null);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Username provided not valid, please enter another one");
            return false;
        }

        if (data.get(2).equals(this.password)){
            // passwords match
            return true;
        }
        // else
        return false;

    }

}
