package main.java.MainPage;

import java.util.Scanner;

import main.java.MainPage.TreeManager;
import main.java.MainPage.Tree.TreeState;

public class CommandLineAndy {
    
    private TreeManager treeManagerClass;
    private boolean loggedIn;
    private boolean currLoopVar; 
    private Scanner inputScanner;
    
    /**
     * Constructor for the command line application system
     * All the functions below work the same way, prompting the user for an input after giving 
     * them a couple options, and moving along the program based on what the user does.
     */
    public CommandLineAndy(){
        this.loggedIn = false;
        this.treeManagerClass = new TreeManager();
        this.currLoopVar = true;
        this.inputScanner = new Scanner(System.in);
        

    }

    //////////////////// WELCOME PAGE
    
    public void welcome(){
        this.printSeparator();
        System.out.println("\n\nHello, welcome to the R4F command line access point, where you get to " +
        "experience all of the R4F functionality but throughout the command line.\n\n");
        System.out.println("Lets get started then, we will provide prompts and you'll answer with " +
        "y/n standing for yes or no.");
        System.out.println("When we ask for inline input, just type the answer in a single " + 
        "line in the format that is requested.");
        System.out.print("Are you ready to start? [y/n] (single character): ");
        char start = inputScanner.next().charAt(0);
        if(start != 'y'){
            this.offerExitProgram();
        }
        // else, continue according to loop function
        this.printSeparator();
        
    }

    public void offerUserCreation(){
        System.out.println("Would you like to create a R4F account (necessary to access R4F features)? [y/n] (single character): ");
        char create = inputScanner.next().charAt(0);
        if(create == 'y'){
            System.out.println("Okay, to register an account we'll have to ask for some information first, answer all the questions and sit tight :)\n\n");
            System.out.print("Username (String, no spaces): ");
            String username = inputScanner.next();
            System.out.print("Email (String, no spaces): ");
            String email = inputScanner.next();
            System.out.print("Password (String, no spaces): ");
            String pswd = inputScanner.next();
            System.out.print("Name (String, no spaces): ");
            String name = inputScanner.next();
            System.out.print("Last name (String, no spaces): ");
            String lName = inputScanner.next();
            System.out.print("Birthday Date (yyyy-mm-dd): ");
            String birthday = inputScanner.next();
            System.out.print("Instagram @ (String, no spaces): ");
            String ig = inputScanner.next();
            System.out.print("Twitter @ (String, no spaces): ");
            String twt = inputScanner.next();
            System.out.print("Discord Username (String, no spaces): ");
            String dsc = inputScanner.next();
            System.out.print("How many times would you like to be notified of a birthday? (Single number, 1-3): ");
            String notiType = inputScanner.next();
           
           this.treeManagerClass.registerUser(username, email, pswd, name, lName, birthday, ig, twt, dsc, Integer.parseInt(notiType));
        }
        // else move on to next loop option
        this.printSeparator();
    }

    public void offerUserLogIn(){
        System.out.println("Would you like to log into your R4F account (necessary to access R4F features)? [y/n] (single character): ");
        char log = inputScanner.next().charAt(0);
        if(log == 'y'){
            System.out.println("Okay, to log in we'll have to verify your credentials first, fill in the information and sit tight :)\n\n");
            System.out.print("Username (String, no spaces): ");
            String username = inputScanner.next();
            System.out.print("Password (String, no spaces): ");
            String pswd = inputScanner.next();
            System.out.print("Is this your first time logging in? [y/n] (single character): ");
            char firstTimeString = inputScanner.next().charAt(0);

            boolean firstTime = firstTimeString == 'y';
            this.treeManagerClass.logUserIn(username, pswd, firstTime);
            this.treeManagerClass.addCalendar(this.treeManagerClass.userId);
            // if no error comes up in log in
            this.loggedIn = true;
            System.out.println("Welcome to R4F" + this.treeManagerClass.username + ":3");
        }
        // else move on to next loop option
        this.printSeparator();
    }

    public void welcomeLoop(){
        this.welcome();
        while (currLoopVar) {
            this.offerUserCreation();
            this.offerUserLogIn();

            // after possible login attempt, check tree state to see if we break and move onto the home loop
            if (this.treeManagerClass.currentNode.stateName == TreeState.HOME){
                // break out of loop
                break;
            }

            this.offerSubmitSurvey();
            this.offerExitProgram();
        }
        this.printSeparator();
    }



    //////////////////// SURVEY PAGE


    public void offerSubmitSurvey(){
        System.out.println("Would you like to submit a birthday survey? [y/n] (single character): ");
        char exit = inputScanner.next().charAt(0);
        if(exit == 'y'){
            System.out.println("Okay, here are the questions you'll have to answer\n\n");
            System.out.print("What is the user ID of the person inviting you to fill out this survey? (String, no spaces): ");
            String userId = inputScanner.next();
            System.out.print("What is the calendar ID of the person inviting you to fill out this survey? (String, no spaces): ");
            String calId = inputScanner.next();
            System.out.print("What is your name? (String, no spaces): ");
            String name = inputScanner.next();
            System.out.print("What is your last name? (String, no spaces): ");
            String lName = inputScanner.next();
            System.out.print("Birthday Date (yyyy-mm-dd): ");
            String birthday = inputScanner.next();
            System.out.print("What is your instagram @? (String, no spaces): ");
            String ig = inputScanner.next();
            System.out.print("What is your twitter @? (String, no spaces): ");
            String twt = inputScanner.next();
            System.out.print("What is your discord username? (String, no spaces): ");
            String dsc = inputScanner.next();

            this.treeManagerClass.submitVisitorSurvey(userId, calId, name, lName, birthday, ig, twt, dsc);
            System.out.println("R4F survey submitted, thank you for your time!");

        }
        // else move on to next loop option
        this.printSeparator();
    }

    public void offerExitProgram(){
        System.out.println("Would you like to exit the program? [y/n] (single character): ");
        char exit = inputScanner.next().charAt(0);
        if(exit == 'y'){
            this.currLoopVar = false;
            System.out.println("Goodbye friend :3! Hope you enjoyed using R4F");
            this.printSeparator();
            return;
        }
        this.printSeparator();
    }



    //////////////////// HOME PAGE

    public void offerShowCalendar(){

    }

    public void offerShareSurvey(){

    }

    public void offerShowBirthdays(){

    }

    public void offerLogOut(){

    }

    public void mainPageLoop(){

    }



    //////////////////// CALENDAR PAGE

    public void offerDateView(){

    }

    public void goMainFromCalendar(){

    }

    public void calendarPageLoop(){

    }



    //////////////////// ACCOUNT PAGE

    public void offerAccountView(){

    }

    public void goMainFromAccount(){

    }
    
    public void accountPageLoop(){

    }



    //////////////////// MISC


    public void printSeparator(){
        System.out.println("\n--------------------------------------------------------------------\n");
    }


}
