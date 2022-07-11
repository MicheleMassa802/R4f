package main.java.Calendar;

import java.util.ArrayList;
import java.util.HashMap;

public class CalendarEntity {
    // the actual calendar object
    private String calendarId;
    private final String userId;
    private HashMap<String, ArrayList<String>> year;  // day string to string builder of birthday ids separated by commas


    /**
     * Constructor for initializing the Calendar object with the given User ID (user is created before calendar)
     * 
     * @param userId        the user ID of the user who this calendar is being created for
     */
    public CalendarEntity(String userId){
        this.userId = userId;
        
        // initiate year calendar with all keys set to empty strings
        this.year = new HashMap<>();

        ArrayList<String> bdIdsForDate = new ArrayList<>();  // no bd Ids when created
        
        // we fill each month with 31 days (from month 1 to 12)
        int i;
        for (i = 1; i < 13; i++){
            int j;
            for (j = 1; j < 32; j++){
                String date;
                // fill in zeros
                if (j < 10 && i < 10){
                    date = "0" + Integer.toString(j) + "-" + "0" + Integer.toString(i);  // "<j>-<i>" = "dd-mm"
                } else if (j < 10){
                    date = "0" +  Integer.toString(j) + "-" + Integer.toString(i);
                } else if (i < 10){
                    date = Integer.toString(j) + "-" + "0" + Integer.toString(i);
                } else {
                    date = Integer.toString(j) + "-" + Integer.toString(i);
                }


                this.year.put(date, bdIdsForDate);
            }
        }

        // after its filled we manually remove the dates that dont exist
        // feb 29, feb 30 and 31
        this.year.remove("29-02");
        this.year.remove("30-02");
        this.year.remove("31-02");
        // apr, jun, sep, nov 31
        this.year.remove("31-04");
        this.year.remove("31-06");
        this.year.remove("31-09");
        this.year.remove("31-11");
        // at this point, this.year has all valid days for a non-leap year with no birthday ids associated

        // other attributes built alongside and set through setter functions

    }

    /**
     * Insert into the year attribute a birthday id into the corresponding date
     * @param date  the key in the year hashmap into which we insert the value bdId
     * @param bdId  the corresponding value to insert into the arraylist at the correct date in the year hashmap
     */
    public void insertBdId(String date, String bdId){
        this.year.get(date).add(bdId);
    }

    // no overwrite of toString needed

    // setters and getters
    public String getCalendarId(){return this.calendarId;}
    public void setCalendarId(String calId){this.calendarId = calId;}
    public HashMap<String, ArrayList<String>> getYearCal(){return this.year;}
    //public void setYearCal(HashMap<String, ArrayList<String>> yearCal){this.year = yearCal;}
    

}
