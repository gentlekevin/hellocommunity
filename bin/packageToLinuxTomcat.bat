@echo off

echo [INFO] Package the war in target dir.

cd %~dp0

cd ..

call mvn clean package -Dmaven.test.skip=true

echo [INFO] Copy the war to  tomcat which is in linux.

cd target

set warpath=D:\Mywork\hellocommunity\target\

 pscp -l root -pw btcit %warpath%hellocommunity.war 123.56.114.102:/usr/tomcat7/webapps/

cd ..

pause