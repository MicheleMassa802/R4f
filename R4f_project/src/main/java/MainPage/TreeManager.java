package main.java.MainPage;

import main.java.Controllers.BirthdayController;
import main.java.Controllers.CalendarController;
import main.java.Controllers.UserController;
import main.java.MainPage.Tree;
import main.java.MainPage.Tree.TreeState;

public class TreeManager {

    // Page name constants
    static final String WELCOME = "welcome.html";
    static final String MAIN = "index.html";
    static final String ACCOUNT = "account.html";
    static final String CALENDAR = "calendar.html";
    static final String ABOUT = "about.html";
    static final String ABOUT_NO_NAV = "aboutNoNav.html";


    private Tree currentNode;
    private BirthdayController bdController;
    private CalendarController calController;
    private UserController userController;

    /** Constructor which starts off the controllers to be used throughout the lifespan of the session */
    public TreeManager(){
        // start off the default build of the tree
        this.currentNode = buildR4fDefault();  // starts off at the welcome parent
        // build the controllers
        this.bdController = new BirthdayController();
        this.userController = new UserController();
    }

    /**
     * setup the calendar controller attribute once logged in
     * @param userId    the id of the user that is logged in to observe/create their calendar
     */
    public void addCalendar(String userId){
        this.calController = new CalendarController(userId);
    }

    /**
     * Helper static method which works regardless of whether or not a Tree instance exists to create a R4F tree
     * @return a tree suited to the layout and structure of the R4F website pages
     */
    public static Tree buildR4fDefault(){
        // stage 0, start
        Tree parent = new Tree(null, TreeState.WELCOME, WELCOME);
        // 1st stage (main and first about us)
        Tree mainPage = parent.addSub(TreeState.HOME, MAIN);
        parent.addSub(TreeState.ABOUT, ABOUT_NO_NAV);  // aboutNoNav
        // 2nd stage (after mainPage: calendar, account, about us)
        mainPage.addSub(TreeState.CALENDAR, CALENDAR);  // calendar
        mainPage.addSub(TreeState.ACCOUNT, ACCOUNT);  // account
        mainPage.addSub(TreeState.ABOUT, ABOUT);  // about
        // to move onto the nav bar elements, the transition would be to move onto the main page 1st stage, then onto the 2nd stage child
        return parent;
    }


    // PAGE SPECIFIC FUNCTIONS  

    // welcome page

    public void logUserIn(){
        // if state is welcome

        // move tree to first stage mainPage
    }

    public void registerUser(){
        // if state is welcome

        // move tree to 0th stage welcome page (so user logs in), so no change in tree state
    }

    public void moveToAboutUsNoNav(){
        // if state is welcome

        // move tree position to first stage about us
    }


    // index page

    public void showCalendar(){
        // if state is home

        // move tree position to second stage calendar

    } 

    public void showAccount(){
        // if state is home

        // move tree position to second stage account

    }

    public void moveToAboutUs(){
        // if state is home

        // move tree position to second stage about us

    } 


    public static void main(String[] args) {

    }

    
}
