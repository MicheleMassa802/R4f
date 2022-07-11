package main.java.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExecuteQuery implements IDBConnectionPoint {
    // the object used to update the R4F database, allowing you to insert and get information from the database based on the selected
    // table and columns that you want to modify. You should only need one of these objects to update an entire table according to the
    // objects modified by the user, the columns attribute is to be filled and used only to execute an insertion
    private String table;
    private ArrayList<String> columns;
    private final String URL = "jdbc:mysql://localhost:3306/r4f_javadb";
    private final String USERNAME = "root";
    private final String PSWD = "Pulpa_rico777";
    

    /**
     * Constructor for initializing a query execution object where some information will be retrieved or sent to the database
     * @param table     the table from which to retrieve or to which we want to upload some information
     * @param columns   indefinite amount of strings representing the columns of the table we are interested in, these should be set up in
     *                  the same order as values are to be inserted according to the executeInsert method. This argument should be set to 
     *                  NULL if an insertion of information is not taking place, but rather just a retrieval
     */
    public ExecuteQuery(String table, String... columns){
        this.table = table;
        this.columns = new ArrayList<>();
        for (String column : columns){
            this.columns.add(column);
        }
    }


    /**
     * Helper for executeInsert creating the first part of the query string without the values to insert
     * @return  the portion of the query string without values
     */
    public String setupInsert(){

        // create query string without values
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ").append(this.table).append(" (");
        // setup columns to be updated
        int i;
        for (i = 0; i < this.columns.size(); i++){
            // last column to be filled case
            if (i == this.columns.size() - 1){
                query.append(this.columns.get(i)).append(")");
            } else {
                // every other case
                query.append(this.columns.get(i)).append(", ");
            }
        }

        query.append(" VALUES (");
        int j;
        for (j = 0; j < this.columns.size(); j++){
            // last "?"" to be filled case
            if (j == this.columns.size() - 1){
                query.append("?").append(")");
            } else {
                // every other case
                query.append("?, ");
            }
        }
        return query.toString();
    }


    /**
     * Function creating and executing a query to insert the information provided as string arguments into the object's table atrribute
     * @param values    the values to be inserted into the database provided in the same order as the columns attribute in this object's constructor
     */
    public void executeInsert(String... values) throws SQLException{
        // check appropriate + of values is being provided
        if(values.length != this.columns.size()){
            System.out.println("Insertion error into " + this.table + " table, number of columns and values don't match! Class: DBConnect.java");
        }

        String query = this.setupInsert();
        
        // connect to db
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PSWD);
            //System.out.println("Connection to database successful!");

            // setup statement with its parameters (values)
            PreparedStatement statement = connection.prepareStatement(query);
            
            int i = 1;
            for (String value : values){
                if(value == ""){
                    // set it as int default 0 (to be overwritten)
                    statement.setInt(i, 0);
                } else {
                    // set it as string
                    statement.setString(i, value);
                }
                
                i++;

            }

            //System.out.println("Statement to execute: " + statement.toString());

            // execute statement and check for updating of database
            statement.executeUpdate();
            // int rowsUpdated = statement.executeUpdate();
            // if (rowsUpdated == this.columns.size()){
            //     System.out.println("All rows have been successfully inserted");
            // } else {
            //     System.out.println("Not all rows have been updated, check database. Class: DBConnect.java");
            // }

            // close statement and connection to DB
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error in the database! Class: DBConnect.java");
            e.printStackTrace();
        }
    }


    /**
     * Function in charge of pulling information from the setup table attribute
     * Note: the columns attribute is ignored when retrieving information since the entire row is retrieved
     * @param idColumn  the name of the column to use to compare objectId to find the correct row
     * @param objectId  the id of the object that is to be retrieved (for identification of the correct row)
     * @param columnNum the number of columns to retrieve from the table, to be put in the resulting row
     * @param colToGet  the specific column to SELECT. If null select all columns, indicating we are retrieving a row;
     *                  if not null, we are selecting the entirety of a column.
     * @return          an arrayList of strings containing the information from all columns of the desired table
     */
    public ArrayList<String> executeRetrieve(String idColumn, String objectId, int columnNum, String colToGet) throws SQLException{
        
        StringBuilder query = new StringBuilder();
        ArrayList<String> row = new ArrayList<>();

        // setup query to retrieve everything from the desired table
        query.append("SELECT ");
        if (colToGet != null){
            query.append(colToGet);
        } else {
            query.append("*");
        }
        query.append(" FROM ").append(this.table);
        
        if (idColumn != null && objectId != null){
            // if row identifiers are not null, append WHERE clause
            query.append(" WHERE ").append(idColumn).append(" = '").append(objectId).append("'");
        }
        
        
        // connect to db
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PSWD);
            //System.out.println("Connection to database successful!");

            // setup statement with its parameters (values)
            PreparedStatement statement = connection.prepareStatement(query.toString());

            //System.out.println("Statement to execute: " + statement.toString());

            // execute statement and check for updating of database
            ResultSet result = statement.executeQuery();

            
            while(result.next()){
                int i;
                for (i = 1; i <= columnNum; i++){
                    row.add(result.getString(i));
                }
            }

            //System.out.println("Row has been successfully retrieved: " + row.toString());

            // close statement and connection to DB
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Connection error in the database! Class: DBConnect.java");
            e.printStackTrace();
        }

        return row;
    }


    /**
     * Function in charge of pulling information from the setup table attribute when there is more than one condition present
     * for the retrieval
     * Note: the columns attribute is ignored when retrieving information since the entire row is retrieved
     * @param idColumn,idColumn2    the name of the columns to use to compare objectId to find the correct row
     * @param objectId,objectId2    the ids of the object that is to be retrieved (for identification of the correct row)
     * @param columnNum             the number of columns to retrieve from the table, to be put in the resulting row
     * @param colToGet              the specific column to SELECT. If null select all columns, indicating we are retrieving a row;
     *                              if not null, we are selecting the entirety of a column.
     * @return                      an arrayList of strings containing the information from all columns of the desired table
     */
    public ArrayList<String> executeRetrieve2(String idColumn, String objectId, String idColumn2, String objectId2,
            int columnNum, String colToGet) throws SQLException{
        
        StringBuilder query = new StringBuilder();
        ArrayList<String> row = new ArrayList<>();

        // setup query to retrieve everything from the desired table
        query.append("SELECT ");
        if (colToGet != null){
            query.append(colToGet);
        } else {
            query.append("*");
        }
        query.append(" FROM ").append(this.table);
        
        if (idColumn != null && objectId != null){
            // if row identifiers are not null, append WHERE clause
            query.append(" WHERE ").append(idColumn).append(" = '").append(objectId).append("'");
            if (idColumn2 != null && objectId2 != null){
                query.append(" AND ").append(idColumn2).append(" = '").append(objectId2).append("'");
            }
        }
        
        
        // connect to db
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PSWD);
            //System.out.println("Connection to database successful!");

            // setup statement with its parameters (values)
            PreparedStatement statement = connection.prepareStatement(query.toString());

            //System.out.println("Statement to execute: " + statement.toString());

            // execute statement and check for updating of database
            ResultSet result = statement.executeQuery();

            
            while(result.next()){
                int i;
                for (i = 1; i <= columnNum; i++){
                    row.add(result.getString(i));
                }
            }

            //System.out.println("Row has been successfully retrieved: " + row.toString());

            // close statement and connection to DB
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Connection error in the database! Class: DBConnect.java");
            e.printStackTrace();
        }

        return row;
    }


    /**
     * Function in charge of inserting and replacing information from the setup table and column attributes in the database
     * @param idColumn          the name of the identifying column
     * @param objectId          the id of the object that is to have information replaced in the database (row identifier)
     * @param columnToUpdate    the name of the column at which we are inserting the new value
     * @param newValue          the value to insert at the columnToUpdate column in the table
     */
    public void executeUpdate(String idColumn, String objectId, String columnToUpdate, String newValue) throws SQLException{

        StringBuilder query = new StringBuilder();
        // setup for a single cell replacement
        query.append("UPDATE ").append(this.table).append(" SET ").append(columnToUpdate).append(" = '");
        query.append(newValue).append("' WHERE ").append(idColumn).append(" = '").append(objectId).append("'");;
        
        // connect to db
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PSWD);
            //System.out.println("Connection to database successful!");

            // setup statement with its parameters (values)
            PreparedStatement statement = connection.prepareStatement(query.toString());
            
            //statement.setString(1, objectId);

            //System.out.println("Statement to execute: " + statement.toString());

            // execute statement and check for updating of database
            statement.executeUpdate();
            
            // int rowsUpdated = statement.executeUpdate();
            // if (rowsUpdated == 1){
            //     System.out.println("Rows value successfully replaced");
            // } else {
            //     System.out.println("Error in replacing of row value, check database. Class: DBConnect.java");
            // }

            // close statement and connection to DB
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Exception raised, error in the database! Class: DBConnect.java");
            e.printStackTrace();
        }
    }

    /**
     * Function in charge of inserting and replacing information from the setup table and column attributes in the database when
     * there is more than one condition present for the insertion
     * @param idColumn,idColumn2    the name of the identifying columns
     * @param objectId,objectId2    the ids of the object that is to have information replaced in the database (row identifier)
     * @param columnToUpdate        the name of the column at which we are inserting the new value
     * @param newValue              the value to insert at the columnToUpdate column in the table
     */
    public void executeUpdate2(String idColumn, String objectId, String idColumn2, String objectId2,
            String columnToUpdate, String newValue) throws SQLException{

        StringBuilder query = new StringBuilder();
        // setup for a single cell replacement
        query.append("UPDATE ").append(this.table).append(" SET ").append(columnToUpdate).append(" = '");
        query.append(newValue).append("' WHERE ").append(idColumn).append(" = '").append(objectId).append("'");
        query.append(" AND ").append(idColumn2).append(" = '").append(objectId2).append("'");
        
        // connect to db
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PSWD);
            System.out.println("Connection to database successful!");

            // setup statement with its parameters (values)
            PreparedStatement statement = connection.prepareStatement(query.toString());
            
            //statement.setString(1, objectId);

            //System.out.println("Statement to execute: " + statement.toString());

            // execute statement and check for updating of database
            statement.executeUpdate();

            // int rowsUpdated = statement.executeUpdate();
            // if (rowsUpdated == 1){
            //     System.out.println("Rows value successfully replaced");
            // } else {
            //     System.out.println("Error in replacing of row value, check database. Class: DBConnect.java");
            // }

            // close statement and connection to DB
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Exception raised, error in the database! Class: DBConnect.java");
            e.printStackTrace();
        }
    }   
}
