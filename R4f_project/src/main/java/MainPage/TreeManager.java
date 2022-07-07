package main.java.MainPage;

import main.java.Controllers.BirthdayController;
import main.java.Controllers.CalendarController;
import main.java.Controllers.UserController;
import main.java.MainPage.Tree;
import main.java.MainPage.Tree.TreeState;
import java.util.HashMap;
import java.time.LocalDate;
import java.util.ArrayList;

public class TreeManager {

    // Page name constants
    static final String WELCOME = "welcome.html";
    static final String MAIN = "index.html";
    static final String ACCOUNT = "account.html";
    static final String CALENDAR = "calendar.html";
    static final String ABOUT = "about.html";
    static final String ABOUT_NO_NAV = "aboutNoNav.html";

    // tree  managing
    private Tree currentNode;
    
    // controllers
    private BirthdayController bdController;
    private CalendarController calController;
    private UserController userController;
    
    // user identifying info
    private String userId;
    private String username;
    private String bdId;
    private String calendarId;
    private String smlId;

    // other attributes
    HashMap<String, ArrayList<String>> calendar;

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


    ////////// WELCOME PAGE //////////


    /**
     * Login and setup (or re-setup) all the account related attributes needed as a R4F user
     * 
     * TO BE CALLED: line after submission of login form  
     * 
     * All the parameters below are the information needed to login as an existing user in R4F
     * @param username
     * @param pswd
     * @param firstTime
     */
    public void logUserIn(String username, String pswd, boolean firstTime){
        // if state is welcome
        if (this.currentNode.stateName != TreeState.WELCOME) {
            System.out.println("Tree error: incorrect state");
            return;
        }

        // else

        // login user 
        this.userController.executeLoginUser(username, pswd, false);
        this.userId = this.userController.userId;
        this.username = this.userController.username;
            
        // calendar and bd info setup along with login, as registration process already finished the account creation process
        this.bdId = this.userController.userBdId;
        this.calendarId = this.userController.userCalendarId;


        // move tree to first stage mainPage
        this.moveToChild(TreeState.HOME);
    }


    /**
     * Register and setup the attributes for a new user in R4F
     * 
     * TO BE CALLED: line after submission of registration form  
     * 
     * All the parameters below are the information needed to register as a user in R4F
     * @param username
     * @param email
     * @param pswd
     * @param name
     * @param lName
     * @param birthday
     * @param ig
     * @param twt
     * @param dsc
     * @param notiType
     */
    public void registerUser(String username, String email, String pswd, String name, String lName, 
        String birthday, String ig, String twt, String dsc, int notiType){

        // if state is welcome
        if (this.currentNode.stateName != TreeState.WELCOME) {
            System.out.println("Tree error: incorrect state");
            return;
        }

        // else

        // register user
        this.userController.executeCreateUser(username, pswd, name, lName, notiType);
        this.userId = this.userController.userId;
        
        // create birthday
        this.bdController.executeCreateSML(ig, twt, dsc);
        this.smlId = this.bdController.smlId;
        this.bdController.executeCreateBirthday(birthday, name, lName);
        this.bdId = this.bdController.bdId;

        // create calendar
        this.addCalendar(userId);
        this.calController.executeCreateCalendar();
        this.calendarId = this.calController.calendarId;
        this.calController.setCalendarEventManager(this.calendarId);

        // link related calendar, bd and sml ids to account
        this.userController.firstLoginFollowUp(username, this.bdId, this.calendarId, this.smlId);

        // move tree to 0th stage welcome page (so user logs in), so no change in tree state

    }

    /**
     * Correctly positions the tree when the user moves onto the about us page of the welcome menu
     * 
     * TO BE CALLED: line before moving onto about us no nav page (button)
     */
    public void moveToAboutUsNoNav(){
        // if state is welcome
        if (this.currentNode.stateName != TreeState.WELCOME) {
            System.out.println("Tree error: incorrect state");
            return;
        }

        // move tree position to first stage about us
        this.moveToChild(TreeState.ABOUT);
    }


    ////////// INDEX PAGE //////////


    /**
     * Sets up all the information needed to properly show the index page
     * 
     * TO BE CALLED: line before moving onto home page
     * 
     * @return  an arraylist containing the information to be displayed linked to todays birthdays
     */
    public ArrayList<String> showIndex() {
        // user id and cal id already setup

        // get todays birthdays to display on hover display
        ArrayList<String> bdIds = this.calendar.get(LocalDate.now().toString());
        ArrayList<String> bdInfo = new ArrayList<>();
        for(String bdId: bdIds){
            // fetch bdId corresponding info and build the array to return with all the info setup
            bdInfo.add(this.bdController.getBdFromID(bdId).toString());
        }

        return bdInfo;
    }


    /**
     * Sets up all the information needed to properly show the calendar page
     * 
     * TO BE CALLED: line before moving onto calendar page (both button and nav)
     * 
     * @param month the month being displayed in the calendar (string in format "mm")  
     * @return      the arraylist of 31 elements (indices 0 - 30) to be sorted in the JS
     */
    public ArrayList<String> showCalendar(String month){
        ArrayList<String> result = new ArrayList<>();
        
        // if state is home
        if (this.currentNode.stateName != TreeState.HOME) {
            System.out.println("Tree error: incorrect state");
            return result;
        }
        // else

        this.calendar = this.calController.getCalFromId(this.calendarId).getYearCal();

        if (month == null){
            // default case starts at current month, so return specific info corresponding to this month
            String monthToDisplay = LocalDate.now().toString().split("-")[1];
            
            for (String date: this.calendar.keySet()){
                if (date.contains("-" + monthToDisplay + "-")){  // when month matches
                    // append to result array the string version of all bdIds related to this date
                    result.add(this.calendar.get(date).toString()); 
                }
            }
        } else {
            // month to display is specified
            for (String date: this.calendar.keySet()){
                if (date.contains("-" + month + "-")){  // when month matches
                    // append to result array the string version of all bdIds related to this date
                    result.add(this.calendar.get(date).toString()); 
                }
            }
        }

        // move tree position to second stage calendar
        this.moveToChild(TreeState.CALENDAR);
        return result;

    } 


    /**
     * Given the date the user wants to look at (in the calendar) returns the information of the birthdays
     * for that specific date (through birthday.toString);
     * 
     * TO BE CALLED: upon the users press to view the information corresponding to a birthday cell
     * 
     * @param date  the String corresponding to the date the user wants to check birthdays for
     * @return      the arrayList of strings where each element is a string containing the info for one of the
     *              bd objects present at that date
     */
    public ArrayList<String> showDateBirthdays(String date){
        ArrayList<String> result = new ArrayList<>();

        // if state is home
        if (this.currentNode.stateName != TreeState.CALENDAR) {
            System.out.println("Tree error: incorrect state");
            return result;
        }
        // else 

        // for each bdId linked to the date in the bdIds arrayList
        for (String bdId : this.calendar.get(date)){
            // fetch the related bdId info and add it to the result
            result.add(this.bdController.getBdFromID(bdId).toStringWS());
        }

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
    public static String convertMonthToInt(int month) {
        // this warning does not work in some versions of java, so we've kept it
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


    /**
     * Sets up all the information needed to properly show the account page
     * 
     * TO BE CALLED: line before moving onto account page (both button and nav)
     * 
     * @return  arraylist containning all user account information to be displayed
     */
    public ArrayList<String> showAccount(){
        ArrayList<String> result = new ArrayList<>();
        // if state is home
        if (this.currentNode.stateName != TreeState.HOME) {
            System.out.println("Tree error: incorrect state");
            return result;
        }
        // else

        ArrayList<String> allUserData = this.userController.getUserFromId(this.userId);
        // move needed info onto result array
        result.add(allUserData.get(1));  // username
        result.add(allUserData.get(0));  // id
        result.add(allUserData.get(9));  // email
        result.add(allUserData.get(3));  // name
        result.add(allUserData.get(4));  // lastname

        // fetch birthday using birhtdayId (allUserData.get(7))
        String bd = this.bdController.getBdFromID(allUserData.get(7)).getBirthdayString();
        result.add(bd);  // birthday

        // move tree position to second stage account
        this.moveToChild(TreeState.ACCOUNT);

        return result;
    }  


    /**
     * Correctly positions the tree when the user moves onto the about us page of the home page
     * 
     * TO BE CALLED: line before moving onto standard about us page (nav bar)
     */
    public void moveToAboutUs(){
        // if state is home
        if (this.currentNode.stateName != TreeState.HOME) {
            System.out.println("Tree error: incorrect state");
            return;
        }
        // else
        

        // move tree position to second stage about us
        this.moveToChild(TreeState.ABOUT);
    } 


    /**
     * Assuming the this.currentNode attribute is accurate, this function allows you to move throughout the tree
     * structure onto any of the children
     * @param childToMoveTo     the child state to move to 
     */
    public void moveToChild(TreeState childToMoveTo){
        for (Tree subtree: this.currentNode.subtrees){
            if (subtree.stateName == childToMoveTo){
                this.currentNode = subtree;
            }
        }
        // child not valid
        System.out.println("State to move to is not a valid child of the current state.");
    }


    /**
     * Assuming the this.currentNode attribute is accurate, this function allows you to move throughout the tree to a
     * sibling node, like when using the nav bar 
     * @param siblingToMoveTo     the sibling state to move to 
     */
    public void moveToSibling(TreeState siblingToMoveTo){
        for (Tree subtree: this.currentNode.parent.subtrees){
            if (subtree.stateName == siblingToMoveTo){
                this.currentNode = subtree;
            }
        }

    }


    public static void main(String[] args) {

    }

    
}
