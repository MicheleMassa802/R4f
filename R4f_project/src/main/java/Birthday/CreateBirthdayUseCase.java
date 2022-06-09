package main.java.Birthday;

import java.sql.SQLException;
import java.util.ArrayList;

import main.java.Database.ExecuteQuery;
import main.java.Database.IDBConnectionPoint;
import main.java.Helpers.UseCaseAccessSML;

public class CreateBirthdayUseCase {
    // use case for setup and creation of a birthday object

    // db constants (table name and columns)
    private final String DB_TABLE = "birthday";
    private final String ID = "bd_id";
    private final String DATE = "bd_date";
    private final String NAME = "name";
    private final String LAST_NAME = "lastname";
    private final String SML_ID = "sml_id";

    private IDBConnectionPoint dbConnection;
    private String smlId;
    public BirthdayEntity bdInstance;


    /**
     * Constructor for birthday object creation use case, in charge of initiating the link to the database connection class to store bd info
     * @param smlId     the id used to identify corresponding links in the sml_table, this we get after inserting a row with the function
     *                  insertLinks from the class UseCaseAccessSML.java
     */
    public CreateBirthdayUseCase(String smlId){
        // table name and columns where we'll insert info
        this.dbConnection = new ExecuteQuery(DB_TABLE, DATE, NAME, LAST_NAME, SML_ID);
        this.smlId = smlId;
    }


    /**
     * Stores information in the database, the columns not provided are set to null, and then replaced upon creation
     * (such as ids of other entities)
     * @param bd        the string in format yyyy-mm-dd representing the date of birth of a user
     * @param name      the name used to refer to the person with bd birthday
     * @param lName     the last name used to refer to the person with bd birthday
     * @param smlId     the id used to identify corresponding links in the sml_table, this we get after inserting a row with the function
     *                  insertLinks from the class UseCaseAccessSML.java
     * @return          a string representing the id of the bd object in the table
     */
    public String runBdCreation(String bd, String name, String lName){
        String result = "";
        try {
            this.dbConnection.executeInsert(bd, name, lName, this.smlId);
            result = this.dbConnection.executeRetrieve(SML_ID, this.smlId, 1, null).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Birthday object successfully created.");

        return result;
    }


    /**
     * Create and set this.bdInstance to a working instance of the birthday entity with the information corresponding to the
     * given birthday id
     * @param birthdayId    the id of a birthday object as returned by the runBdCreation object and stored in calendars
     */
    public void createBirthdayFromID(String birthdayId){
        ArrayList<String> data = new ArrayList<>();
        
        // fetch birthday object info
        try {
            this.dbConnection.executeRetrieve(ID, birthdayId, 5, null);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Birthday Id not found, please enter correct id");
        }

        // fetch SML link information using smlConnection to fill in birthday object information
        UseCaseAccessSML smlAccess = new UseCaseAccessSML();
        ArrayList<String> links = smlAccess.getLinks(this.smlId);  

        String[] dateArray = data.get(1).split("-");  // index 0 = year, 1 = month, 2 = day

        this.bdInstance = new BirthdayEntity(Integer.parseInt(dateArray[2]), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[0]),
            data.get(2), data.get(3), links.get(0), links.get(1), links.get(2));  


    }

}
