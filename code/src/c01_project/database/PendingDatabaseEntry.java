package c01_project.database;

import javafx.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * A class for adding new row(s) to the ICare database
 * Can either take in a csv ICare template or can be manually set with parameters
 */
public class PendingDatabaseEntry {

  /*
   * Maps each user to all of the entries they need made
   * Each user has a list of insertions that they need to make
   * each insertion contains the table name, Column name,
   * and data to be inserted respectively in that order
   */
  private HashMap<Integer, List<List<String>>> insertions;

  /**
   * Create new entries using an ICare template
   * currently accepts: .csv
   * TODO need to accept: .xls, .xlsx
   * @param filePath
   */
  public PendingDatabaseEntry(String filePath) {
    // get the lines of the file, assumes we have csv of the ICare template
    try {
      List<String> lines = Files.readAllLines(Paths.get(filePath));
      // TODO parse each of these lines and add them to the insertions Hashmap

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /** Reads a line of an ICare template
   *
   * @param ICareTemplateLine
   */
  private void readICareTemplateLine(String ICareTemplateLine) {

  }

  /**
   * Create a row to add to the database from scratch
   */
  public PendingDatabaseEntry() {

  }


  /**
   *  Inserts into the database
   *
   */
  public void addData(int userId, String tableName, String columnName, String data) {
    //TODO check whether this data is even in the database in the first place
    //TODO get a safer method to do this, that checks the type of the data instead of assuming everything is raw text
    if (!insertions.containsKey(userId)) {
      insertions.putIfAbsent(userId, Arrays.asList());
    }
    List insertion = Arrays.asList(tableName, columnName, data);
    insertions.get(userId).add(insertion);

  }


  /**
   * Clears all entries to be added to the database
   */
  public void clear() {
    insertions.clear();
  }


  /**
   *  Once all of the fields you want to add are added, you can run this
   *  method to dump all of the data into the main database
   */
  public void dumpIntoDatabase() {

    // Go through each user, and add their data to the database
    for (Integer userId : insertions.keySet()) {
      for (List<String> data : insertions.get(userId)) {
        String tableName = data.get(0);
        String columnName = data.get(1);
        String insertable = data.get(2);

        String sql =
        "UPDATE " + tableName + "\n" +
        "SET " + columnName + " = '" + insertable + "'\n" +
        "WHERE\n" +
        " id = " + userId.toString() + ";";

      }
    }
  }

}