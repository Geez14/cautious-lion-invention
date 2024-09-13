import java.io.IOException;

final public class QueryHandler {
  final private static ProcessBuilder pb;
  static {
    if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
      pb = new ProcessBuilder("cmd", "/c", "cls");
    } else {
      pb = new ProcessBuilder("clear");
    }
  }

  private QueryHandler() {
  }

  public static void help() {
    System.out.println("commands:");
    System.out.println("  /exit          exit application");
    System.out.println("  /clear         clear screen");
    System.out.println("  /help         clear screen");
  }

  public static void clearScreen() {
    try {
      pb.inheritIO().start().waitFor();
    } catch (InterruptedException | IOException e) {
      System.out.println("FAILED TO CREATE PROCESS!");
    }
  }

}
