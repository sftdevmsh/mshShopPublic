@REM $MAVEN_HOME_PARENT = "$HOME/.m2/wrapper/dists/$distributionUrlNameMain"
@REM if ($env:MAVEN_USER_HOME) {
@REM   $MAVEN_HOME_PARENT = "$env:MAVEN_USER_HOME/wrapper/dists/$distributionUrlNameMain"
@REM }
@REM $MAVEN_HOME_NAME = ([System.Security.Cryptography.MD5]::Create().ComputeHash([byte[]][char[]]$distributionUrl) | ForEach-Object {$_.ToString("x2")}) -join ''
@REM $MAVEN_HOME = "$MAVEN_HOME_PARENT/$MAVEN_HOME_NAME"




@echo off
setlocal enabledelayedexpansion

:: Define new address
set NEW_ADDRESS=shop.online.com

:: Get current version from moduleController/pom.xml
for /f "tokens=1,2 delims=<>" %%i in ('findstr "<artifactId>moduleController</artifactId>" moduleController\pom.xml /n') do (
    set /a lineNumber=%%i + 1
)
for /f "tokens=3 delims=<>" %%i in ('more +%lineNumber% moduleController\pom.xml ^| findstr "<version>"') do (
    set current_version=%%i
)

:: Parse the version into major, minor, and patch
for /f "tokens=1,2,3 delims=.-" %%a in ("%current_version%") do (
    set major=%%a
    set minor=%%b
    set patch=%%c
)

:: Increment patch version
set /a patch+=1

:: Form new version string
set new_version=%major%.%minor%.%patch%-RELEASE

:: Update version in pom.xml
powershell -Command "(gc moduleController\pom.xml) -replace '<version>%current_version%</version>', '<version>%new_version%</version>' | Out-File moduleController\pom.xml -encoding ASCII"
echo Version incremented to: %new_version%

:: List of modules
set modules=moduleCommon moduleRepository moduleService moduleController

:: Loop through each module and execute Maven commands
@REM for %%m in (%modules%) do (
@REM     cd %%m
@REM     "C:\Users\msh\IdeaProjects\maven\apache-maven-3.9.9\bin\mvn" clean compile package install
@REM     cd ..
@REM )

for %%m in (%modules%) do (
    cd %%m
    "C:\Users\Sweetan\.m2\wrapper\dists\apache-maven-3.9.9-bin\4nf9hui3q3djbarqar9g711ggc\apache-maven-3.9.9\bin\mvn" clean compile package install
    cd ..
)

endlocal