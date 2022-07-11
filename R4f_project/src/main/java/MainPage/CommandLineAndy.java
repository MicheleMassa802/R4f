package main.java.MainPage;

import java.time.LocalDate;
import java.util.ArrayList;
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
        CommandLineAndy.printSeparator();
        System.out.println("\n\nHello, welcome to the R4F command line access point, where you get to " +
        "experience all of the R4F functionality but through the command line.\n\n");
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
        CommandLineAndy.printSeparator();
        
    }

    public void offerUserCreation(){
        System.out.print("Would you like to create a R4F account (necessary to access R4F features)? [y/n] (single character): ");
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
            System.out.print("Instagram @ (String, no spaces) [print NONE if you dont have one]: ");
            String ig = inputScanner.next();
            System.out.print("Twitter @ (String, no spaces) [print NONE if you dont have one]: ");
            String twt = inputScanner.next();
            System.out.print("Discord Username (String, no spaces) [print NONE if you dont have one]: ");
            String dsc = inputScanner.next();
            System.out.print("How many times would you like to be notified of a birthday? (Single number, 1-3): ");
            String notiType = inputScanner.next();
           
           this.treeManagerClass.registerUser(username, email, pswd, name, lName, birthday, ig, twt, dsc, Integer.parseInt(notiType));
        }
        // else move on to next loop option
        CommandLineAndy.printSeparator();
    }

    public void offerUserLogIn(){
        System.out.print("Would you like to log into your R4F account (necessary to access R4F features)? [y/n] (single character): ");
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
            System.out.println("Welcome to R4F " + this.treeManagerClass.username + " :3");

        }
        // else move on to next loop option
        CommandLineAndy.printSeparator();
    }

    public void welcomeLoop(){
        CommandLineAndy.printSeparator();
        this.welcome();
        while (this.currLoopVar) {
            this.offerUserCreation();
            this.offerUserLogIn();

            // after possible login attempt, check tree state to see if we break and move onto the home loop
            if (this.treeManagerClass.currentNode.stateName == TreeState.HOME){
                // send to main page
                CommandLineAndy.printSeparator();

                if (this.loggedIn){
                    this.mainPageLoop();
                }
            }

            this.offerSubmitSurvey();
            this.offerExitProgram();
        }
        
    }



    //////////////////// SURVEY PAGE


    public void offerSubmitSurvey(){
        System.out.print("Would you like to submit a birthday survey? [y/n] (single character): ");
        char submit = inputScanner.next().charAt(0);
        if(submit == 'y'){
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
            System.out.print("What is your instagram @? (String, no spaces) [print NONE if you dont have one]: ");
            String ig = inputScanner.next();
            System.out.print("What is your twitter @? (String, no spaces) [print NONE if you dont have one]: ");
            String twt = inputScanner.next();
            System.out.print("What is your discord username? (String, no spaces) [print NONE if you dont have one]: ");
            String dsc = inputScanner.next();

            this.treeManagerClass.submitVisitorSurvey(userId, calId, name, lName, birthday, ig, twt, dsc);
            System.out.println("R4F survey submitted, thank you for your time!");

        }
        // else move on to next loop option
        CommandLineAndy.printSeparator();
    }

    public void offerExitProgram(){
        System.out.print("Would you like to exit the program? [y/n] (single character): ");
        char exit = inputScanner.next().charAt(0);
        if(exit == 'y'){
            this.currLoopVar = false;
            System.out.println("Goodbye friend :3! Hope you enjoyed using R4F");
            CommandLineAndy.printSeparator();
            return;
        }
        // else move on to next loop option
        CommandLineAndy.printSeparator();
    }



    //////////////////// HOME PAGE

    public void offerShowCalendar(){
        System.out.print("Would you like to go over to the calendar page? [y/n] (single character): ");
        char calPage = inputScanner.next().charAt(0);
        if (calPage == 'y'){
            
            // move onto calendar page
            this.calendarPageLoop();
        }
        // else move on to next loop option
        CommandLineAndy.printSeparator();

    }

    public void offerAccountView(){
        System.out.print("Would you like to go over to the account page? [y/n] (single character): ");
        char accPage = inputScanner.next().charAt(0);
        if (accPage == 'y'){            
            // move onto account page
            this.accountPageLoop();
        }
        // else move on to next loop option
        CommandLineAndy.printSeparator();
    }

    public void offerShareSurvey(){
        System.out.print("Would you like to share your birthday survey to a friend? [y/n] (single character): ");
        char survey = inputScanner.next().charAt(0);
        if (survey == 'y'){
            System.out.println("Okay, since this is the command line app, for your friend to access the survey they'll have to download it from our github repo: https://github.com/MicheleMassa802/R4f");
            System.out.println("The reference ids your friends will need to complete the survey are the following... \nYour calendar ID: " + this.treeManagerClass.calendarId + ", and your user ID: " + this.treeManagerClass.userId);
            System.out.println("Be sure to communicate those two pieces of information to whoever is filling out the survey and if you like R4F, maybe even recommend it :)))");
        }
        // no matter the answer, move on to next loop option
        CommandLineAndy.printSeparator();
    }


    public void offerShowBirthdays(){
        System.out.print("Would you like to look at the birthdays you have registered for today? [y/n] (single character): ");
        char bds = inputScanner.next().charAt(0);
        if (bds == 'y'){
            System.out.println("Today's birthdays are the following: \n");
            // truncate todays date to only month and day
            String[] todaysDate = LocalDate.now().toString().split("-");
            String date = todaysDate[1] + "-" + todaysDate[2];
            ArrayList<String> todaysBds = this.treeManagerClass.showDateBirthdays(date);
            System.out.println(todaysBds.toString());
        }
        // no matter the answer, move on to next loop option
        CommandLineAndy.printSeparator();
    }

    public void offerLogOut(){
        System.out.print("Would you like to log out of your account? This will cause you to exit back to the welcome page [y/n] (single character): ");
        char logout = inputScanner.next().charAt(0);
        if(logout == 'y'){
            this.currLoopVar = false;
            System.out.println("Logging you out of your account, hope to see you back soon :)");
            CommandLineAndy.printSeparator();
            this.treeManagerClass.moveToParent();  // move to parent state upon logout
            return;
        }
        CommandLineAndy.printSeparator();
    }

    public void mainPageLoop(){
        CommandLineAndy.printSeparator();
        System.out.println("Welcome to the Main Page of R4F, here you can navigate through your account and calendar!");
        CommandLineAndy.printSeparator();

        while(this.currLoopVar){
            this.offerShowCalendar();
            this.offerShareSurvey();
            this.offerShowBirthdays();
            this.offerAccountView();
            this.offerLogOut();
        }

        // once logged out, set this.currLoopVar to true to run welcome loop
        this.currLoopVar = true;
    }



    //////////////////// CALENDAR PAGE

    public void offerDateView(){
        // show calendar and ask for specific date
        System.out.println("This is your calendar view [we apologize if it looks buggy, our CMDLine app cannot handle having too many birthday ids in a single date :(]: \n\n");
        CmdLCalDrawer.printIdCalendar(this.treeManagerClass.showCalendarHashMap(null));
        CommandLineAndy.printSeparator();

        System.out.print("Would you like to look at a specific date's birthdays? [y/n] (single character): ");
        char showSpecBd = inputScanner.next().charAt(0);
        if(showSpecBd == 'y'){
            System.out.println("Please enter the date you want to look at in the following prompts...");
            System.out.print("Enter the month you want to look at [mm] (two digits, a number from 1-12): ");
            String month = inputScanner.next();
            System.out.print("Enter the day you want to look at [dd] (two digits, a number from 1-31): ");
            String day = inputScanner.next();
            // make up date from inputs
            String date = day + "-" + month;
            System.out.println("The birthdays for that date are the following: ");
            ArrayList<String> todaysBds = this.treeManagerClass.showDateBirthdays(date);
            System.out.println(todaysBds.toString());
            
            CommandLineAndy.printSeparator();
            return;
        }
        // else move on to next loop option
        CommandLineAndy.printSeparator();

    }

    public void goMainFromCalendar(){
        System.out.print("Would you like to go back to the Main page? [y/n] (single character): ");
        char calToMain = inputScanner.next().charAt(0);
        if(calToMain == 'y'){
            this.currLoopVar = false;
            System.out.println("Going back to the main page :)");
            CommandLineAndy.printSeparator();
            this.treeManagerClass.moveToParent();  // move to parent state upon going main
            return;
        }
        CommandLineAndy.printSeparator();
    }

    public void calendarPageLoop(){
        CommandLineAndy.printSeparator();
        System.out.println("Welcome to the Calendar Page of R4F, here you can navigate through your entire calendar!");
        CommandLineAndy.printSeparator();

        while(this.currLoopVar){
            this.offerDateView();
            this.goMainFromCalendar();
        }
        // once goMainFromCalendar is called and we exit the loop, we go back to the main loop
        // so we set this.currLoopVar to true again for that loop to work
        this.currLoopVar = true;

    }



    //////////////////// ACCOUNT PAGE

    public void showAccountDetails(){
        System.out.print("Hello there, would you like to go over your account details? [y/n] (single character): ");
        char accDetails = this.inputScanner.next().charAt(0);
        if(accDetails == 'y'){
            System.out.println("The following are your account details: \n\n");
            ArrayList<String> accDetailsArr = this.treeManagerClass.showAccount();
            System.out.println("Username: " + accDetailsArr.get(0) + "\nUser ID: " + accDetailsArr.get(1) + 
            "\nEmail: " + accDetailsArr.get(2) + "\nName: " + accDetailsArr.get(3) + "\nLast Name: " + accDetailsArr.get(4) +
            "\nBirthday: " + accDetailsArr.get(5));
            CommandLineAndy.printSeparator();
            return;
        }
        CommandLineAndy.printSeparator();
    }

    public void goMainFromAccount(){
        System.out.print("Would you like to go back to the Main page? [y/n] (single character): ");
        char accToMain = inputScanner.next().charAt(0);
        if(accToMain == 'y'){
            this.currLoopVar = false;
            System.out.println("Going back to the main page :)");
            CommandLineAndy.printSeparator();
            this.treeManagerClass.moveToParent();  // move to parent state upon going main
            return;
        }
        CommandLineAndy.printSeparator();
    }
    
    public void accountPageLoop(){
        CommandLineAndy.printSeparator();
        System.out.println("Welcome to the Account Page of R4F, here you can navigate through your account's details!");
        CommandLineAndy.printSeparator();

        while(this.currLoopVar){
            this.showAccountDetails();
            this.goMainFromAccount();
        }
        // once goMainFromAccount is called and we exit the loop, we go back to the main loop
        // so we set this.currLoopVar to true again for that loop to work
        this.currLoopVar = true;
    }



    //////////////////// MISC


    public static void printSeparator(){
        System.out.println("\n--------------------------------------------------------------------\n");
    }

}
