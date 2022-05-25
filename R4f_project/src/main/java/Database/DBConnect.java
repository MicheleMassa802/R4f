package main.java.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    public static void main(String[] args) {
        // connection info
        String url = "jdbc:mysql://localhost:3306/r4f_javadb";
        String username = "root";
        String pswd = "Pulpa_rico777";

        // try to establish connection with the provided info in order to perform queries, catch SQLException
        try {
            Connection connection = DriverManager.getConnection(url, username, pswd);
            System.out.println("Connection to database successful!");

            // code for sql goes here: queries, table creation, row insertion
            // this is normally broken down into multiple functions to provide ease of repetition

            /**
             * Eg:
             * String sql = "INSERT INTO users (name, lastname) VALUES (?, ?)";
             * 
             * // create statement
             * PreparedStatement statement = connection.prepareStatement(sql);
             * statement.setString(1, "Michele");  // <parameter index, value being set at that index>
             * statement.setString(2, "Massa");
             * 
             * int rows = statement.executeUpdate();  // execute the built up statement
             * 
             * // check insertion of new rows
             * if (rows > 0){
             *      System.out.println("A row has been successfully inserted");
             * }
             * 
             * // close statement and connection to DB
             * statement.close();
             * connection.close();
             * 
             */

        } catch (SQLException e) {
            System.out.println("Connection error in the database! Class: DBConnect.java");
            e.printStackTrace();
        }
    }
    
}
