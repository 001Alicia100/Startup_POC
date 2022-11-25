Backend of the Startup_Poc project : Java Spring Boot + MySql

# Startup_POC

Enable import maven dependency. After the import create the database with the name startuppoc  then run the command “ mvn clean install “

Verify the applications.properties in the config folder and change database credentials if necessary (already configure)

INSERT INTO roles(name) VALUES('ROLE_ADMIN'); 
INSERT INTO roles(name) VALUES('ROLE_USER_MANAGE');
INSERT INTO roles(name) VALUES('ROLE_VIEW');

Register the user using postman
http://localhost:8080/api/auth/signup
Request body ( for a normal user)