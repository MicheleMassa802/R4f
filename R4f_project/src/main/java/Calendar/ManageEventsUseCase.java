package main.java.Calendar;

import java.sql.SQLException;
import java.util.ArrayList;

import main.java.Database.ExecuteQuery;
import main.java.Database.IDBConnectionPoint;

public class ManageEventsUseCase {
    // add or remove birthday event from calendar

    // db constants (table name and columns)
    private final String DB_TABLE = "calendar";
    private final String C_ID = "calendar_id";
    private final String DATE = "date";
    private final String BD_IDS = "birthday_ids";

    private IDBConnectionPoint dbConnection;
    private String calendarId;
    private CalendarEntity calendarToMod;


    /**
     * Constructor for the calendar use case in charge of updating the birthday_ids column in the calendar table
     * @param calId     the calendar id used to identify the correct object in the calendar table to modify
     * @param userId    the use id to be used to create the calendar entity to modify given the calendar id
     */
    public ManageEventsUseCase(String calId, String userId){
        this.calendarId = calId;
        this.dbConnection = new ExecuteQuery(DB_TABLE, "");  // this use case wont insert columns so its left blank
        
        // given the user and calendar ids, fetch the correct calendar entity and build it from the db
        CreateCalendarUseCase calendarGetter = new CreateCalendarUseCase(userId);
        calendarGetter.createCalendarFromId(this.calendarId);
        this.calendarToMod = calendarGetter.calendarInstance;
    }



    /**
     * Method in charge of adding a birthday to the calendar object at the given date
     * @param date  value at which we'll modify the bdId arrayList
     * @param bdId  value to add to the bdId arrayList
     */
    public void addBirthday(String date, String bdId){
        ArrayList<String> bdIdsList = new ArrayList<>();
        
        if (this.calendarToMod.getYearCal().containsKey(date)){
            bdIdsList = this.calendarToMod.getYearCal().get(date);
            bdIdsList.add(bdId);  // bdId added to arraylist
        } else {
            System.out.println("Birthday couldn't be successfully added. Class: ManageEventsUseCase.java");
            return;
        }

        // update db birthday_ids column
        try {
            this.dbConnection.executeUpdate2(C_ID, this.calendarId, DATE, date, BD_IDS, bdIdsList.toString());
        } catch (SQLException e) {
            System.out.println("Update of birthday ids unsuccessful, check database");
            e.printStackTrace();
        }

        System.out.println("Birthday id list successfully updated!");
 
    }


    /**
     * Method in charge of removing a birthday from the calendar object at the given date
     * @param date  value at which we'll modify the bdId arrayList
     * @param bdId  value to remove from the bdId arrayList
     */
    public void removeBirthday(String date, String bdId){
        ArrayList<String> bdIdsList = new ArrayList<>();
        
        if (this.calendarToMod.getYearCal().containsKey(date)){
            bdIdsList = this.calendarToMod.getYearCal().get(date);
            bdIdsList.remove(bdId);  // bdId removed from arraylist
        } else {
            System.out.println("Birthday couldn't be successfully removed. Class: ManageEventsUseCase.java");
            return;
        }

        // update db birthday_ids column
        try {
            this.dbConnection.executeUpdate2(C_ID, this.calendarId, DATE, date, BD_IDS, bdIdsList.toString());
        } catch (SQLException e) {
            System.out.println("Update of birthday ids unsuccessful, check database");
            e.printStackTrace();
        }

        System.out.println("Birthday id list successfully updated!");
    }

}
