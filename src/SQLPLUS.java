import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SQLPLUS {
  private Connection conn;

  public SQLPLUS(Connection conn) {
    this.conn = conn;
  }

  private void display(ResultSetMetaData tableMetaData, ResultSet rs) throws SQLException {
    int columns = tableMetaData.getColumnCount();
    // Printing column labels
    for (int i = 1; i <= columns; i++) {
      System.out.print("\033[93m" + tableMetaData.getColumnName(i) + "\033[0m");
      if (i < columns) {
        System.out.print(" | ");
      }
    }
    System.out.println();

    // Printing rows
    while (rs.next()) {
      for (int i = 1; i <= columns; i++) {
        System.out.print(rs.getString(i));
        if (i < columns) {
          System.out.print(" | ");
        }
      }
      System.out.println();

      for (int i = 0; i < columns; i++) {
        System.out.print("-----------");
      }
      System.out.println();
    }
  }

  public void runMyQuery(String query) {
    if (conn == null) {
      System.out.println("\033[91mError:\033[0m Connection is null.");
      throw new NullPointerException("Connection object cannot be null!");
    }
    try (Statement smt = conn.createStatement()) {
      boolean isResultSet = smt.execute(query);
      if (isResultSet) {
        try (ResultSet rs = smt.getResultSet()) {
          ResultSetMetaData rsm = rs.getMetaData();
          display(rsm, rs);
        }
      } else {
        System.out.println("Executed Query Success!");
      }
    } catch (SQLException sqe) {
      System.out.println("State: " +
          sqe.getSQLState() +
          ", ErrorCode: " +
          sqe.getErrorCode());
      System.out.println("\033[91mError:\033[0m " + sqe.getMessage());
    }
  }
}