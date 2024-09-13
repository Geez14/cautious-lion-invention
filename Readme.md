# A cli interface to connect and execute query to RDBMS

lib -|
     |-ojdbc11.jar

src-|
    |-Main.java
    |-QueryHandler.java
    |-SQLPLUS.java

library comes with ojdbc11 driver in lib folder!

## Make jar file

> compile
javac -cp "./lib/ojdbc11.jar" -d build src/*.java
> build
jar -cfm App.jar Manifest.mf -C build .
> run
java -Durl="jdbc:oracle:thin:@//localhost:1521/xe" -Dusername="mxtylish" -Dpassword="asdf" -jar App.jar
