@echo off

:: Start the debugger
java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 -cp "%destPath%\Main.jar" src.java.Main