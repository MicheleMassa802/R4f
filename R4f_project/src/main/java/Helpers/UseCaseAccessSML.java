package main.java.Helpers;

import java.sql.SQLException;
import java.util.ArrayList;

import main.java.Database.ExecuteQuery;
import main.java.Database.IDBConnectionPoint;

public class UseCaseAccessSML {
    // class for accessing and storing information related to the SML table

    // db constants (table name and columns)
    private final String DB_TABLE = "sml_table";
    private final String ID = "sml_id";
    private final String TYPE = "sml_type";
    private final String LINK = "sml";

    // sml types
    private final String IG = "Instagram";
    private final String TWT = "Twitter";
    private final String DSC = "Discord";

    
    private IDBConnectionPoint dbConnection;
    private int idCount;


    /**
     * Constructor for sml table object, in charge of initiating the link to the database connection class to store sml info
     */
    public UseCaseAccessSML(){
        this.dbConnection = new ExecuteQuery(DB_TABLE, ID, TYPE, LINK);
        //this.dbConnection2 = new ExecuteQuery(DB_TABLE, ID, TYPE, LINK);
        String countStr = "1001";
        try {
            countStr = this.dbConnection.executeRetrieve(ID, "0", 3, null).get(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.idCount = Integer.parseInt(countStr);
        System.out.println("Current sml_id available: " + countStr);
        
    }


    /**
     * Inserts the sm links into the sml table and returns a reference to it
     * @param ig    the instagram link
     * @param twt   the twitter link
     * @param dsc   the discord link
     * @return      a string representing the sml_id to access these links through
     */
    public String insertLinks(String ig, String twt, String dsc){

        // insert instagram link as the first one
        try {
            this.dbConnection.executeInsert(Integer.toString(idCount), IG, ig);
            idCount += 1;
            // update idCount in table
            this.dbConnection.executeUpdate(ID, "0", LINK, Integer.toString(idCount));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // fetch the sml_id of this insertion
        String resultId = "";
        try {
            resultId = this.dbConnection.executeRetrieve(LINK, ig, 1, null).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // insert the two other rows under the same id
        try {
            this.dbConnection.executeInsert(resultId, TWT, twt);
            this.dbConnection.executeInsert(resultId, DSC, dsc);
        } catch (SQLException e) {
            System.out.println("Insertion unsucessful, please try again. Class: UseCaseAccessSML.java");
            e.printStackTrace();
        }

        System.out.println("Insertion Successful, sml_id: " + resultId);
        return resultId;
    }


    /**
     * Retrieves the links related to an sml_id in order: instagram, twitter, discord
     * @param smlId the sml table id used to identify the corresponding links
     * @return      an arraylist containing the sm links for a given id
     */
    public ArrayList<String> getLinks(String smlId){

        ArrayList<String> result = new ArrayList<>();
        
        try {
            result = this.dbConnection.executeRetrieve(ID, smlId, 1, LINK);
        } catch (SQLException e) {
            System.out.println("Retrieval unsucessful, please check the sml_id used. Class: UseCaseAccessSML.java");
            e.printStackTrace();
        }

        System.out.println("Retrieval Successful! Links pulled: " + result.toString());
        return result;
    }


}
