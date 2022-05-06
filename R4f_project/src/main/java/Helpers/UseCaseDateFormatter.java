package main.java.Helpers;

import java.time.LocalDate;

public class UseCaseDateFormatter {
    
    /**
     * Function converting a String object from database into a Localdate to be used by the program
     * The converse of this function is just using LocalDate.toString()
     * 
     * @param date string containing the date from the database ("yyyy-mm-dd")
     * @return Localdate to be used by the program
     */
    public static LocalDate formatStringIntoDate(String date){
        String yearMonthDate[] = date.split("-");
        LocalDate result = LocalDate.of(Integer.parseInt(yearMonthDate[0]),
            Integer.parseInt(yearMonthDate[1]), Integer.parseInt(yearMonthDate[2]));
        
            return result;
    }

    /**
     * Function converting a string date of the from "yyyy-mm-dd" to "dd-mm"
     * @param date date string given in "yyyy-mm-dd" format
     * @return date string in "dd-mm" format
     */
    public static String formatYearlyToMonthly(String date){
        String yearMonthDate[] = date.split("-");
        String result = yearMonthDate[2] + yearMonthDate[1];
        return result;

    }

}
