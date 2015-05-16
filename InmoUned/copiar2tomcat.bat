@echo off

rd /S /Q C:\enviroment\apache\apache-tomcat-6.0.32\webapps\si3_48
del /Q C:\enviroment\apache\apache-tomcat-6.0.32\webapps\si3_48.war

copy  C:\Users\Tony\workspace\InmoUned\target\si3_48.war C:\enviroment\apache\apache-tomcat-6.0.32\webapps\. /y



