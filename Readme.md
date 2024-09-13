# A CLI Interface to Connect and Execute Queries to an RDBMS

This project provides a simple command-line interface (CLI) to connect to a relational database management system (RDBMS) and execute SQL queries. It uses the Oracle JDBC driver (ojdbc11) for database connectivity.

## Directory Structure

```graphql
lib/
  └── ojdbc11.jar        # JDBC driver for Oracle DB
  └── mysqlconn.jar        # JDBC driver for MySQL (platform independent)

src/
  ├── Main.java          # Entry point of the application
  ├── QueryHandler.java   # System functionalities.
  └── SQLPLUS.java        # Manages connection and interaction with SQL*Plus
```

The `ojdbc11.jar` is included in the `lib/` folder to handle Oracle database connectivity.

---

## How to Build and Run the Application

### Step 1: Compile the Application

Use the following command to compile the Java source files. The JDBC driver is included in the classpath to ensure proper database connectivity.

```bash
javac -cp "./lib/ojdbc11.jar" -d build src/*.java
```

* `-cp` specifies the classpath for external libraries (in this case, ojdbc11.jar).
* `-d build` specifies the output directory for compiled .class files.

### Step 2: Create a JAR File

Once compiled, you can bundle the application into a JAR file with the following command:

```bash
jar -cfm App.jar Manifest.mf -C build .
```

* `-c` creates the JAR.
* `-f` specifies the name of the output JAR file (App.jar).
* `-m` includes the Manifest.mf file, which should specify the main class and classpath.
* `-C build .` tells the JAR tool to package the compiled files from the build/ directory.

### Step 3: Run the Application

You can run the JAR file by providing the necessary database connection details as system properties.

```bash
java -Durl="jdbc:oracle:thin:@//localhost:1521/xe"
     -Dusername="<user-name>"
     -Dpassword="<your-password>"
     -jar App.jar
```

* `-Durl` specifies the JDBC URL for connecting to the Oracle database.
* `-Dusername` and `-Dpassword` pass the database credentials.
* `-jar` runs the `App.jar` file.

---

### Requirements

* **JDK 11 or higher**
* **Oracle Database
* **Oracle JDBC Driver (already included in the lib/ folder)**

---

### Customization

You can easily modify the application to connect to other databases by replacing the Oracle JDBC driver with the appropriate driver for your database. Be sure to update the JDBC URL, username, and password accordingly.
