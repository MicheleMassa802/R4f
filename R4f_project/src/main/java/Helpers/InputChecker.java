package main.java.Helpers;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;


public class InputChecker {
    

    public InputChecker(){
        // empty constructor, there is nothing to initialize
    }

    // prompt printers + input checkers

    /**
     * Checks whether based on a y/n prompt, the answer is valid or a new input must be provided
     * @param promptToDisplay   prompt to be displayed until answer is valid
     * @param inputScanner      the scanner to take in input
     * @return                  the valid string answer input by the user
     */
    public static String ynPrompt(String promptToDisplay, Scanner inputScanner){
        System.out.print(promptToDisplay);
        String input = inputScanner.next();

        // check input validity
        Pattern pt = Pattern.compile("[yn]");
        Matcher mt = pt.matcher(input);
        
        while (!mt.matches()){
            InputChecker.printWrongInput(input);
            System.out.print(promptToDisplay);
            input = inputScanner.next();
            mt = pt.matcher(input);
        }

        return input;

    }


    /**
     * Checks whether based on a pattern given and a prompt, the answer is valid or a new input must be provided
     * @param patternToMatch    the regex pattern denoting a valid input
     * @param promptToDisplay   prompt to be displayed until answer is valid
     * @param inputScanner      the scanner to take in input
     * @return                  the valid string answer input by the user
     */
    public static String accountDetailPrompt(String patternToMatch, String promptToDisplay, Scanner inputScanner){
        System.out.print(promptToDisplay);
        String input = inputScanner.next();

        // check input validity
        Pattern pt = Pattern.compile(patternToMatch);
        Matcher mt = pt.matcher(input);
        
        while (!mt.matches()){
            InputChecker.printWrongInput(input);
            System.out.print(promptToDisplay);
            input = inputScanner.next();
            mt = pt.matcher(input);
        }

        return input;
    }


    // helpers

    public static void printWrongInput(String answer){
        System.out.println("Your previous answer: '" + answer + "' is not valid, please answer this question again considering the limitations mentioned.");
    }

    public static void main(String[] args) {
        InputChecker.accountDetailPrompt("\\d+", "Username (String, no spaces): ", new Scanner(System.in));
    }


}
