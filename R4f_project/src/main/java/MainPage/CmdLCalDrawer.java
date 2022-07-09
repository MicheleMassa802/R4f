package main.java.MainPage;

import java.util.ArrayList;

public class CmdLCalDrawer {

    // calendar drawing constants
    private static int CELL_WIDTH = 16;  // 2 more than date cell width
    private static int DATE_CELL_WIDTH = 14;  // 2 less than cell width
    private static int CELL_HEIGHT = 5;  // 5 | default
    private static int ROW_WIDTH = 7;  // 5 cells
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


    // starting day is the day at which the cell row will start printing (1, 6, 11, 16, 21, 26, 31)
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
        int i;  // up to 10 each time
        int j;  // up to 6
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


    // starting day is the day at which the row will start printing (1, 6, 11, 16, 21, 26, 31)
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
            rowArray.add(digitOne);
            rowArray.add(digitTwo);

            // add 8 spaces
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
    
}
