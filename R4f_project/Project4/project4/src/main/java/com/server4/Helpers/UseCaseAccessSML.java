package com.server4.Helpers;

import java.sql.SQLException;
import java.util.ArrayList;

import com.server4.Database.ExecuteQuery;
import com.server4.Database.IDBConnectionPoint;

public class UseCaseAccessSML {
    // class for accessing and storing information related to the SML table

    // db constants (table name and columns)
    private final String DB_TABLE = "sml_table";
    private final String ROW = "sml_row";
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
        
        String countStr = "1001";
        try {
            countStr = this.dbConnection.executeRetrieve(ROW, "1", 4, null).get(3);
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
        String currSmlId = Integer.toString(idCount);
        // insert instagram link as the first one
        try {
            this.dbConnection.executeInsert(currSmlId, IG, ig);
            this.dbConnection.executeInsert(currSmlId, TWT, twt);
            this.dbConnection.executeInsert(currSmlId, DSC, dsc);
            this.idCount += 1;
            // update idCount in table
            this.dbConnection.executeUpdate(ID, "0", LINK, Integer.toString(idCount));

        } catch (SQLException e) {
            System.out.println("Insertion unsucessful, please try again. Class: UseCaseAccessSML.java");
            e.printStackTrace();
        }

        System.out.println("Insertion Successful, sml_id: " + currSmlId);
        return currSmlId;
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
