package database;

import database.*;

import java.sql.*;

public class databaseSetup {
	//creating database called test.db for running the program without a database
	//4 default users representing 4 default type will be added
  public static void main(String[] args) throws SQLException {  
	createNewDatabase("test.db");
    initializeNewTables("test.db");
    //default users added
    UserQuery.addUser("Alice","123",1);
    UserQuery.addUser("Bob","123",2);
    UserQuery.addUser("Cody","123",3);
    UserQuery.addUser("Jeff","123",4);
  }

/**
 * Creating new database with given database path
 * @param databasePath the location and name of newly created database
 */
  public static void createNewDatabase(String databasePath) {

    String url = "jdbc:sqlite:" + databasePath;

    try (Connection conn = DriverManager.getConnection(url)) {
      if (conn != null) {
        DatabaseMetaData meta = conn.getMetaData();
        System.out.println("The driver name is " + meta.getDriverName());
        System.out.println("A new database has been created.");
      }

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
  /**
   * Initializing tables with given database and schema in Schema.java
   * @param databasePath the location and name of database
   */
  public static void initializeNewTables(String databasePath) {
    String url = "jdbc:sqlite:" + databasePath;

    try (Connection conn = DriverManager.getConnection(url);
         Statement stmt = conn.createStatement()) {
      // create a new table
      System.out.println("connected to database");
      stmt.execute(Schema.sqlClientProfile);
      System.out.println("created Client Profile table");
      stmt.execute(Schema.sqlLTClientEnrollNew);
      System.out.println("created unconfirm Client Enroll table");
      stmt.execute(Schema.sqlLTClientExitNew);
      System.out.println("created unconfirm Client Exit table");
      stmt.execute(Schema.sqlCommunityConnections);
      System.out.println("created commnunity connections");
      stmt.execute(Schema.sqlLT_Course_Setup_New);
      System.out.println("created Course setup table");
      stmt.execute(Schema.sqlNeedsAssessmentAndReferrals);
      System.out.println("created Needs Assesment and Referrals table");
      stmt.execute(Schema.sqlEmployment);
      System.out.println("created Employment table");
      stmt.execute(Schema.sqlInfoOrien);
      System.out.println("created InfoOrien table");
      stmt.execute(Schema.sqlUserTable);
      System.out.println("created user table");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
}
