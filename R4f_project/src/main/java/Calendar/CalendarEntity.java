package main.java.Calendar;

import java.util.HashMap;

public class CalendarEntity {
    // the actual calendar object
    private String calendarId;
    private final String userId;
    private HashMap<String, StringBuilder> year;  // day string to string builder of birthday ids separated by commas


    /**
     * Constructor for initializing the Calendar object with the given User ID (user is created before calendar)
     * 
     * 
     * @param userId        the user ID of the user who this calendar is being created for
     */
    public CalendarEntity(String userId){
        this.userId = userId;
        
        // initiate year calendar with all keys set to empty strings
        this.year = new HashMap<>();

        StringBuilder bdIdsForDate = new StringBuilder();
        bdIdsForDate.append("");  // set to empty string
        // we fill each month with 31 days (from month 1 to 12)
        int i;
        for (i = 1; i < 13; i++){
            int j;
            for (j = 1; j < 32; i++){
                String date = Integer.toString(j) + "-" + Integer.toString(i);  // "<j>-<i>" = "dd-mm"
                this.year.put( date, bdIdsForDate);
            }
        }

        // after its filled we manually remove the dates that dont exist
        // feb 30 and 31
        this.year.remove("30-2");
        this.year.remove("31-2");
        // apr, jun, sep, nov 31
        this.year.remove("31-4");
        this.year.remove("31-6");
        this.year.remove("31-9");
        this.year.remove("31-11");
        // at this point, this.year has all valid days for a leap year with no birthday ids associated

        // other attributes built alongside and set through setter functions

    }

    // no overwrite of toString needed

    // setters and getters
    public String getCalendarId(){return this.calendarId;}
    public void setCalendarId(String calId){this.calendarId = calId;}

}
