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

    /**
     * Helper to return correct month strings given an int (to display months in calendar page)
     * 
     * TO BE CALLED: line before moving onto calendar page (both button and nav)
     * 
     * @param month     the int value of a month (index 1-12)
     * @return          the string value corresponding to the month index
     */
    public static String convertIntToMonth(int month) {
        switch (month) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            default:
                return "December";
        }
    }

}
