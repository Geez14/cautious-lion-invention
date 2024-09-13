import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Hooks {
  static public Thread closeConnection(Connection conn) {
    Thread t = new Thread(
        () -> {
          try {
            conn.close();
          } catch (SQLException sqe) {
            System.out.println("State: " +
                sqe.getSQLState() +
                ", ErrorCode: " +
                sqe.getErrorCode());
            System.out.println("\033[91mError:\033[0m " + sqe.getMessage());
          }
        });
    return t;
  }

  static public Thread closeConnection(ResultSet rs) {
    Thread t = new Thread(
        () -> {
          try {
            rs.close();
          } catch (SQLException sqe) {
            System.out.println("State: " +
                sqe.getSQLState() +
                ", ErrorCode: " +
                sqe.getErrorCode());
            System.out.println("\033[91mError:\033[0m " + sqe.getMessage());
          }
        });
    return t;
  }

  static public Thread closeHandles(InputStream io) {
    Thread t = new Thread(
        () -> {
          try {
            io.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        });
    return t;
  }
}
