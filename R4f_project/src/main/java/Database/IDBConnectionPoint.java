package main.java.Database;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDBConnectionPoint {

    ArrayList<String> executeRetrieve(String idColumn, String objectId, int columnNum, String colToGet) throws SQLException;
    // retrieve columnNum number of columns from a row based on the objectId value on the specific idColumn
    // OR an entire column of the selected table indicated by colToGet
    // result is returned as an array of Strings

    ArrayList<String> executeRetrieve2(String idColumn, String objectId, String idColumn2, String objectId2, int columnNum, String colToGet) throws SQLException;
    // same as executeRetrieve but with 2 possible conditionals

    void executeUpdate(String idColumn, String objectId, String columnToUpdate, String newValue) throws SQLException;
    // replace the information at the column with name columnToUpdate with the value newValue at the row given by
    // matching the column name idColumn to the value objectId

    void executeUpdate2(String idColumn, String objectId, String idColumn2, String objectId2, String columnToUpdate, String newValue) throws SQLException;
    // same as executeUpdate but with 2 possible conditionals

    void executeInsert(String... values) throws SQLException;
    // insert values (as many values as provided) into the columns attribute provided when the ExecuteQuery class is initiated
}
