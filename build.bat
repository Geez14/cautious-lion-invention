
@echo off

:: Path to the .env file
set envFile=.env
:: Access the variables
set username=
set password=
set url=
set driver=
set build=
:: Read the .env file line by line
for /f "tokens=1,2 delims==" %%A in (%envFile%) do (
  if "%%A"=="username" set username=%%B
  if "%%A"=="password" set password=%%B
  if "%%A"=="url" set url=%%B
  if "%%A"=="driver" set driver=%%B
  if "%%A"=="build" set build=%%B
)

@Rem compile the application
javac -cp %driver% -d %build% ./src/*.java

if not exist %envFile% (
  echo ".env file not found"
  exit /b -1
)


echo on

@REM java -cp $jdbcDriver;.\; -Djava.security.policy=java.policy -Djava.rmi.server.codebase=file:./ -Djava.rmi.server.hostname=localhost -Djava.rmi.server.useCodebaseOnly=false Server
java -cp "%driver%;%build%" -Dusername=%username% -Dpassword=%password% -Durl=%url% Main