package com.server4.Helpers;

import java.time.LocalDate;

public class UseCaseDateFormatter {
    
    /**
     * Function converting a String object from database into a Localdate to be used by the program
     * The converse of this function is just using LocalDate.toString()
     * 
     * @param date  string containing the date from the database ("dd-mm")
     * @return      date string to be used by the program
     */
    public static String formatMonthlyToYearly(String date){
        String dayMonth[] = date.split("-");
        int currentYear = LocalDate.now().getYear();
        LocalDate result = LocalDate.of(currentYear, Integer.parseInt(dayMonth[1]), Integer.parseInt(dayMonth[0]));
        return result.toString();
    }

    /**
     * Function converting a string date of the from "yyyy-mm-dd" to "dd-mm"
     * @param date  date string given in "yyyy-mm-dd" format
     * @return      date string in "dd-mm" format
     */
    public static String formatYearlyToMonthly(String date){
        String yearMonthDate[] = date.split("-");
        String result = yearMonthDate[2] + "-" + yearMonthDate[1];
        return result;

    }

}
