package com.server4.Calendar;

import java.sql.SQLException;
import java.util.ArrayList;

import com.server4.Database.ExecuteQuery;
import com.server4.Database.IDBConnectionPoint;
import com.server4.Helpers.UseCaseDateFormatter;

public class CreateCalendarUseCase {
    // creation and setup of calendar object

    // db constants (table name and columns)
    private final String DB_TABLE = "calendar";
    private final String C_ID = "calendar_id";
    private final String U_ID = "user_id";
    private final String DATE = "date";
    private final String BD_IDS = "birthday_ids";

    private IDBConnectionPoint dbConnection;
    private String userId;
    public CalendarEntity calendarInstance;
    private int idCount;


    /**
     * Constructor for calendar object creation use case, in charge of initiating the link to the database connection class to store calendar info
     * @param userId    the id of the logged-in user, which is to be used to link a user to their calendar object
     */
    public CreateCalendarUseCase(String userId){
        this.dbConnection = new ExecuteQuery(DB_TABLE, U_ID, C_ID, DATE, BD_IDS);
        this.userId = userId;

        // fetch current calendar_id count
        String countStr = "1001";
        try {
            countStr = this.dbConnection.executeRetrieve(U_ID, "0", 3, null).get(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.idCount = Integer.parseInt(countStr);
        System.out.println("Current calendar_id available: " + countStr);
    }


    /**
     * Stores a newly created (empty in terms of birthday ids) calendar object in the database
     * the calendar entity instance
     * @return  a string representing the id of the calendar object in the table
     */
    public String runCalendarCreation(){

        // initiate calendar object ()
        this.calendarInstance = new CalendarEntity(this.userId);

        // insert row all 365 rows
        for (String date : this.calendarInstance.getYearCal().keySet()){
            String realDate = UseCaseDateFormatter.formatMonthlyToYearly(date);
            try {
                this.dbConnection.executeInsert(this.userId, Integer.toString(this.idCount), realDate, this.calendarInstance.getYearCal().get(date).toString()); 
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        String calId = Integer.toString(this.idCount);
        this.idCount += 1;
        // update idCount in DB
        try {
            this.dbConnection.executeUpdate(U_ID, "0", C_ID, Integer.toString(this.idCount));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // set cal Id in the calendar entity instance
        this.calendarInstance.setCalendarId(calId);
        return calId;

    }

    /**
     * Create and set this.calendarInstance to a working instance of the calendar entity with the information corresponding to the
     * given calendar id
     * @param calId the calendar id to use to fetch db info
     */
    public void createCalendarFromId(String calId){
        ArrayList<String> dates = new ArrayList<>();
        ArrayList<String> bdIds = new ArrayList<>();

        // fetch the entire date and birthday_ids columns corresponding to the given calId
        try {
            dates = this.dbConnection.executeRetrieve(C_ID, calId, 1, DATE);
            bdIds = this.dbConnection.executeRetrieve(C_ID, calId, 1, BD_IDS);
        } catch (SQLException e) {
            System.out.println("Calendar id entered not recognized, please retry with a valid calendar id. Class: CreateCalendarUseCase.java");
            e.printStackTrace();
        }

        // begin constructing calendar entity

        this.calendarInstance = new CalendarEntity(this.userId);
        this.calendarInstance.setCalendarId(calId);

        if (dates.size() != bdIds.size() || dates.size() != this.calendarInstance.getYearCal().keySet().size()){
            // check correct # of rows retrieved
            System.out.println("Error in retrieval, number of date and bdIds rows doesn't match");
        }

        int i;
        for (i = 0; i < dates.size(); i++){
            // format each date
            String formattedDate = UseCaseDateFormatter.formatYearlyToMonthly(dates.get(i));
            if (this.calendarInstance.getYearCal().containsKey(formattedDate)){  // if date at i is contained in hashmap, then fill in the bdIds
                // convert each bdIds string ("[..., ..., ...]") into ArrayList<String>
                // trim '[', ']', then split based on ','
                String currBdIds = bdIds.get(i);
                ArrayList<String> bdIdsList = new ArrayList<>();
                currBdIds = currBdIds.substring(1, currBdIds.length() - 1);
                // copy over values
                String[] bdIdArray = currBdIds.split(", ");

                // check if bdids being added contain 0123456789, else, dont add them
                for (int j = 0; j < bdIdArray.length; j++){
                    if ((bdIdArray[j].matches("\\d+"))){
                        bdIdsList.add(bdIdArray[j]);
                        //System.out.println("CAUGHT YOU");
                    }
                    // else dont add it
                    
                }

                // bdIdsList = new ArrayList<String>(Arrays.asList(bdIdArray));
                
                this.calendarInstance.getYearCal().put(formattedDate, bdIdsList);
            }
        }

        
    }


}
