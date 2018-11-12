package c01_project.database.selector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import c01_project.database.CSVParser;
import c01_project.database.DatabaseQuery;
import c01_project.database.databaseSetup;


public class DatabaseSelectorTest {
  
  private final static String TEST_ROOT = "src/test/resources/testICareTemplates/";
  private final static String DATABASE_PATH = "test.db";
  private static CSVParser parser;
  private static DatabaseQuery query;

  @BeforeAll
  public static void beforeAll() {
    databaseSetup.createNewDatabase(DATABASE_PATH);
    databaseSetup.initializeNewTables(DATABASE_PATH);
    parser = new CSVParser(DATABASE_PATH);
    com.c01_project.database.PendingDatabaseEntryInterface entry = parser.parseCSVBasicICareTemplate(TEST_ROOT + "normal_multiple_linens.csv");
    entry.dumpIntoDatabase(DATABASE_PATH);
    entry.clear();
    query = new DatabaseQuery(DATABASE_PATH);
  }

  @Test
  @DisplayName("select one column")
  public void testOneColumn() {
    String[] columns = {"name"};
    List<String> expected = new ArrayList<String>();
    List<String> outcome = new ArrayList<String>();
    expected.add("Mohammed Ali");
    expected.add("Terry Suns");
	try {
	  ResultSet result = DatabaseSelector.selectColumnms(query, "basic_data", columns);
	  while (result.next()) {
	    outcome.add(result.getString("name"));
		}
	  result.close();
	  assertEquals(expected, outcome);
	} catch (SQLException sql) {
	  fail("Unexpected SQLException has been thrown");
	} catch (DatabaseNullException dne) {
	  fail("Database is null");
	} catch (NullColumnsException nce) {
	  fail("Columns is null");
	} catch (NullTableException nte) {
	  fail("table is null");
	}
  }

  @Test
  @DisplayName("select two columns")
  public void testTwoColumns() {

  }
  
  @Test
  @DisplayName("select * from a table")
  public void testAllColumns() {

  }
  
  @Test
  @DisplayName("select no columns")
  public void testNoColumns() {

  }
  
  @Test
  @DisplayName("select an incorrect column")
  public void test() {

  }
}
