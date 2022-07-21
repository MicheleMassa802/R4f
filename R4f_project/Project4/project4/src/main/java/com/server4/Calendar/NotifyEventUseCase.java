package com.server4.Calendar;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import com.server4.Database.ExecuteQuery;
import com.server4.Database.IDBConnectionPoint;

public class NotifyEventUseCase {
    // checking date validity and sending email reminder

    // db constants (table name and columns)
    private final String DB_TABLE = "calendar";
    private final String C_ID = "calendar_id";
    private final String DATE = "date";
    private final String BD_IDS = "birthday_ids";

    private IDBConnectionPoint dbConnection;


    /**
     * Constructor for the calendar use case in charge of notifying the existence of an event
     */
    public NotifyEventUseCase(){
        this.dbConnection = new ExecuteQuery(DB_TABLE, "");  // this use case wont insert columns so its left blank
    }


    /**
     * Return the bdIds the user should be notified of or null
     * @param date          date to check for birthdays (within controller, this is set as today's date using LocalDate.now())
     * @param calendarId    the calendar id used to identify the correct object in the calendar table to monitor
     * @return              the arraylist of bdIds user should be notified of
     */
    public ArrayList<String> notifyBdsPresent(String date, String calendarId){
        
        String bdIdsString = "";
        // fetch specific row
        try {
            bdIdsString = this.dbConnection.executeRetrieve2(C_ID, calendarId, DATE, date, 1, BD_IDS).get(0);
        } catch (SQLException e) {
            System.out.println("Invalid date or calendar id entered, please re-enter the information and try again");
            e.printStackTrace();
        }

        // convert bdIdsString ("[..., ..., ...]") into ArrayList<String>
        // trim '[', ']', then split based on ','
        bdIdsString = bdIdsString.substring(1, bdIdsString.length() - 1);
        
        // check until number is found

        // check trimmed string length is not 0
        if (bdIdsString.length() == 0){
            return new ArrayList<>();  // empty arraylist case
        }

        // else, extract and return those bdIds
        String[] bdIdArray = bdIdsString.split(",");
        ArrayList<String> bdIdsList = new ArrayList<String>(Arrays.asList(bdIdArray));
        return bdIdsList;
    }

}
