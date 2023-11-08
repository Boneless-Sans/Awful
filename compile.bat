@echo off

:: Set the source and destination paths
set sourcePath=.
set destPath=%USERPROFILE%\Desktop

:: Check if /d argument is provided for detailed compilation output
set enableDebugging=false

for %%i in (%*) do (
    if "%%i"=="/d" set enableDebugging=true
)

:: Compile the Java code
if "%enableDebugging%"=="true" (
    javac -d . -Xlint:unchecked src\java\*.java
) else (
    javac -d . src\java\*.java
)

:: Create a manifest file in the META-INF directory
echo Manifest-Version: 1.0 > META-INF\MANIFEST.MF
echo Main-Class: src.java.Main >> META-INF\MANIFEST.MF

:: Create the JAR file
jar cfm Main.jar META-INF\MANIFEST.MF -C . .

:: Move the JAR file to the desktop
move Main.jar "%destPath%"

:: Clean up the directory by deleting .class and .jar files
del /s /q *.class
del /s /q *.jar