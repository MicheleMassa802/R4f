package main.java.MainPage;

import java.util.ArrayList;
import java.util.HashMap;

public class CmdLCalDrawer {

    // calendar drawing constants
    private static int CELL_WIDTH = 16;  // 6 more than date cell width
    private static int DATE_CELL_WIDTH = 10;  // 6 less than cell width (Day:xx)
    private static int CELL_HEIGHT = 5;  // 5 | default
    private static int ROW_WIDTH = 7;  // 7 cells in length X 5 in height by default (to use id printing func)
    private static int[] STARTING_DAYS = {1, 8, 15, 22, 29};  // (1 + ROW_WIDTH, + ROW_WIDTH, ...)



    // empty constructor (all methods are static)
    public CmdLCalDrawer(){
        
    }


    public static void printCalendar(){
        ArrayList<Character> result = new ArrayList<>();

        for(int startingDay : STARTING_DAYS){
            result.addAll(CmdLCalDrawer.printCellRow(startingDay));
        }
        result.addAll(CmdLCalDrawer.printSeparatorRow());
        // once result is full, print it
        for (char calChar : result){
            System.out.print(calChar);
        }
    }


    // starting day is the day at which the cell row will start printing (1, 8, 15, 22, 29)
    public static ArrayList<Character> printCellRow(int startingDay){
        // build array
        ArrayList<Character> cellRowArray = new ArrayList<>();
        // add separator
        cellRowArray.addAll(CmdLCalDrawer.printSeparatorRow());
        // add first row with day values
        cellRowArray.addAll(CmdLCalDrawer.printNumberRow(startingDay));
        // add 4 normal rows
        for (int i = 0; i < CELL_HEIGHT - 1; i++){
            cellRowArray.addAll(CmdLCalDrawer.printNormalRow());
        }
        return cellRowArray;
        

    }

    public static ArrayList<Character> printSeparatorRow(){
        ArrayList<Character> rowArray = new ArrayList<>();
        int i;  // up to 16 each time
        int j;  // up to 7
        for (j = 0; j < ROW_WIDTH; j++){
            rowArray.add('+');
            for (i = 0; i < CELL_WIDTH; i++){
                rowArray.add('-');
            }
        }
        rowArray.add('+');
        rowArray.add('\n');

        return rowArray;
    }


    // starting day is the day at which the row will start printing (1, 8, 15, 22, 29)
    public static ArrayList<Character> printNumberRow(int startingDay){
        ArrayList<Character> rowArray = new ArrayList<>();
        int i;  // up to 8 each time (blank spaces in width) 
        int j;  // up to 5 to draw 5 vertical dividers + 1
        String currDayStr;
        for (j = 0; j < ROW_WIDTH; j++){
            rowArray.add('|');
            currDayStr = String.valueOf(startingDay);
            char digitOne;
            char digitTwo;
            if (startingDay < 10){
                digitOne = '0';
                digitTwo = currDayStr.charAt(0);
            } else if (startingDay < 32){
                // double digit number up to 31
                digitOne = currDayStr.charAt(0);
                digitTwo = currDayStr.charAt(1);
            } else {
                // beyond 31
                digitOne = ' ';
                digitTwo = ' ';
            }
            rowArray.add('D');
            rowArray.add('a');
            rowArray.add('y');
            rowArray.add(':');
            rowArray.add(digitOne);
            rowArray.add(digitTwo);

            // add CELL_WIDTH -  spaces
            for (i = 0; i < DATE_CELL_WIDTH; i++){
                rowArray.add(' ');
            }

            startingDay += 1;
        }

        // at end of loop, add closing '|'
        rowArray.add('|');
        rowArray.add('\n');
        return rowArray;
    }


    public static ArrayList<Character> printNormalRow(){
        ArrayList<Character> rowArray = new ArrayList<>();
        int i;  // up to 10 each time (blank spaces in width) 
        int j;  // up to 5 to draw 5 vertical dividers + 1

        for (j = 0; j < ROW_WIDTH; j++){
            rowArray.add('|');
            // add 10 spaces
            for (i = 0; i < CELL_WIDTH; i++){
                rowArray.add(' ');
            }
        }

        // at end of loop, add closing '|'
        rowArray.add('|');
        rowArray.add('\n');
        return rowArray;
    }

    ///////////////////////////////// Adding Ids to calendar

    // printing rows with the given ids
    public static ArrayList<Character> printIdRow(ArrayList<String> idsForDays){
        ArrayList<Character> rowArray = new ArrayList<>();
        int i;  // blank spaces in width = CELL_WIDTH - lengthOfId  (16 is default cell width)
        int j;  // up to 5 to draw 5 vertical dividers + 1
        for (j = 0; j < ROW_WIDTH; j++){
            rowArray.add('|');
            // add all id chars
            for (char strChar: idsForDays.get(j).toCharArray()){
                rowArray.add(strChar);
            }

            // add DATE_CELL_WIDTH - lengthOfId spaces
            for (i = 0; i < (CELL_WIDTH - idsForDays.get(j).length()); i++){
                rowArray.add(' ');
            }
        }

        // at end of loop, add closing '|'
        rowArray.add('|');
        rowArray.add('\n');
        return rowArray;
    }


        // starting day is the day at which the cell row will start printing (1, 8, 15, 22, 29)
        // rowsIds is the arrayList of arrayLists of strings representing the row portion of the calendar on the corresponding dates
        // this arrayList should be in order and agreeing with the starting date being printed
        public static ArrayList<Character> printIdCellRow(int startingDay, ArrayList<ArrayList<String>> rowIds){
            // build array
            ArrayList<Character> cellRowArray = new ArrayList<>();
            // add separator
            cellRowArray.addAll(CmdLCalDrawer.printSeparatorRow());
            // add first row with day values
            cellRowArray.addAll(CmdLCalDrawer.printNumberRow(startingDay));
            
            for (int i = 0; i < CELL_HEIGHT - 1; i++){
                // we make an arrayList with the outer list's current ids at the positions from 0 - CELL_HEIGHT
                ArrayList<String> rowIdsAtIndex = new ArrayList<>();
                for(ArrayList<String> idList : rowIds){
                    if (idList.size() <= i){
                        // add blank to rowIdsAtIndex
                        rowIdsAtIndex.add("");
                    } else {
                        // add id for that row
                        rowIdsAtIndex.add(idList.get(i));
                    }
                }
                // draw row with ids (or not)
                cellRowArray.addAll(CmdLCalDrawer.printIdRow(rowIdsAtIndex));
            }
            return cellRowArray;
            
        }

        // given a month (1-12) prints a row of chars with the month in the middle
        public static ArrayList<Character> printMonthRow(int month){
            int rowCharSize = CmdLCalDrawer.printSeparatorRow().size();
            String monthStr = TreeManager.convertIntToMonth(month);
            int actualrowSize = rowCharSize - monthStr.length() - 4;  // 4 for spacing
            
            ArrayList<Character> monthRow = new ArrayList<>();
            
            // add left side
            for (int i = 0; i < (actualrowSize/2); i++){
                monthRow.add('~');
            }
            // add padding
            monthRow.add('/');
            monthRow.add(' ');
            // add month
            for (char monthChar : monthStr.toCharArray()){
                monthRow.add(monthChar);
            }
            // add padding
            monthRow.add(' ');
            monthRow.add('\\');
            // add right side
            // add left side
            for (int i = 0; i < (actualrowSize/2); i++){
                monthRow.add('~');
            }
            monthRow.add('\n');
            return monthRow;
        }


    
        // given a month calendar in the form of a hashmap of days-arrayList of ids we print the calendar with those ids
        public static void printIdCalendar(HashMap<String, ArrayList<String>> monthCalendar){
            // get hashmap length
            int hashmapLen = monthCalendar.keySet().size();
            
            ArrayList<Character> result = new ArrayList<>();
            
            String dateStr = "";
            // figure out month
            keyLoop: for(String date: monthCalendar.keySet()){
                dateStr = date;
                // break out of this loop in the first iteration after getting the date
                break keyLoop;
            }
            String month = dateStr.split("-")[1];

            
            // add month row 
            result.addAll(CmdLCalDrawer.printMonthRow(Integer.parseInt(month)));

            // create an arraylist of all corresponding values in order (sort hashmap contents based on key)
            ArrayList<ArrayList<String>> idsArrayListArr = new ArrayList<>();
            
            
            String date;
            int day;

            for (day = 1; day < 32; day++){
                if (day < 10){
                    date = "0" + Integer.toString(day) + "-" + month;
                } else {
                    date = Integer.toString(day) + "-" + month;
                }
                idsArrayListArr.add(monthCalendar.get(date));  // in order

            }

            int j = 0;
            for(int startingDay : STARTING_DAYS){
                // for each starting day, get ROW_WIDTH # of arrayLists of ids from the idsArrayListArr
                ArrayList<ArrayList<String>> sevenDayIds = new ArrayList<>();
                for (int i = j; i < j + ROW_WIDTH; i++){
                    if (i < hashmapLen){
                        sevenDayIds.add(idsArrayListArr.get(i));  // can only get up to index 30 for 31 days total
                    } else {
                        // add empty arrayList
                        sevenDayIds.add(new ArrayList<>());
                    }
                    
                }

                // update j
                j += ROW_WIDTH;

                result.addAll(CmdLCalDrawer.printIdCellRow(startingDay, sevenDayIds));
            }
            result.addAll(CmdLCalDrawer.printSeparatorRow());
            // once result is full, print it
            for (char calChar : result){
                System.out.print(calChar);
            }
        }


        // for testing purposes, where month is an int from 1-12 representing a month
        public static HashMap<String, ArrayList<String>> createMonthCalendar(int month){
            // initiate month calendar with all keys set to empty strings
            HashMap<String, ArrayList<String>> monthCal = new HashMap<>();

            ArrayList<String> bdIdsForDate = new ArrayList<>();  // no bd Ids when created
            // for further testing, you might insert some non-empty ids at this point into bdIdsForDate
            bdIdsForDate.add("id69");
            bdIdsForDate.add("id420");

            // we fill the month with 31 days
            
            int j;
            for (j = 1; j < 32; j++){
                String date;
                // fill in zeros
                if (j < 10 && month < 10){
                    date = "0" + Integer.toString(month) + "-"  + "0" + Integer.toString(j);  // "<month>-<j>" = "mm-dd"
                } else if (j < 10){
                    date =  Integer.toString(month) + "-" + "0" +  Integer.toString(j);
                } else if (month < 10){
                    date = "0" + Integer.toString(month) + "-" + Integer.toString(j);
                } else {
                    date = Integer.toString(month) + "-" + Integer.toString(j);
                }

                monthCal.put(date, bdIdsForDate);
            }

            return monthCal;
        }
    
}
