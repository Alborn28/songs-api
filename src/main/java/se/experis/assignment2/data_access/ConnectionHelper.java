package se.experis.assignment2.data_access;

import java.sql.Connection;

/**
 * Connection string to the database.
 */
public class ConnectionHelper {
    public static final String CONNECTION_URL = "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";

    public static void closeDatabaseConnection(Connection conn) {
        try {
            conn.close();
        } catch (Exception ex) {
            System.out.println("Something went wrong while closing the connection");
            System.out.println(ex.toString());
        }
    }
}
