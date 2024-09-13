@echo off
setlocal enabledelayedexpansion

:: Path to the .env file
set envFile=.env
set value=
:: Read the .env file line by line
for /f "tokens=1,2 delims==" %%A in (%envFile%) do (
)

:: Access the variables
set user=
set password=

for /f "tokens=1,2 delims==" %%A in (%envFile%) do (
    if "%%A"=="user" set user=%%B
    if "%%A"=="password" set password=%%B
)

echo User: %user%
echo Password: %password%

endlocal