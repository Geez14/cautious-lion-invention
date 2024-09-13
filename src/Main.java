import java.util.Properties;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
  static {
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
    } catch (ClassNotFoundException e) {
    }
  }

  public static void main(String[] args) {
    Properties env = System.getProperties();
    System.out.print("Connection...\r");
    try (Connection con = DriverManager.getConnection(env.getProperty("url"), env.getProperty("username"),
        env.getProperty("password"))) {
      QueryHandler.clearScreen();
      System.out.println("Welcome: " + env.getProperty("username"));
      SQLPLUS sqlplus = new SQLPLUS(con);
      try (Scanner input = new Scanner(System.in)) {
        while (true) {

          System.out.print(">>");
          String query = input.nextLine();

          if (query.startsWith("/e")) {
            System.out.println("\033[92mGoodbye\033[0m");
            break;
          }

          if (query.startsWith("/c")) {
            QueryHandler.clearScreen();
            continue;
          }

          if (query.startsWith("/h")) {
            QueryHandler.help();
            continue;
          }
          sqlplus.runMyQuery(query);
        }
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Uexpected error while I/O!");
      }
    } catch (SQLException e) {
      System.out.println("[\033[91mConnection FAILED!\033[0m]");
      System.out.println("\033[91mError:\033[0m " + e.getMessage());
      System.out.println(e.getLocalizedMessage());
      // e.printStackTrace();
    }
  }
}
