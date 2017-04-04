Product Management System
=========================
Web application produces the system, working with resources (products, stocks, shops).

***

Server side technologies stack: 
- Java
- Spring
- JWT
- Hibernate
- JPA

Client: Angular 4.

Build tools: 
- Gradle
- Webpack

***

Installing the project
===================================
Installing environment (You need Java 8 installed):

1. Download [Tomcat 9 (Core)](http://tomcat.apache.org/download-90.cgi) and unpack it.

2. Download [Gradle ver. 3.3](https://gradle.org/install#manually)
    >Unpack and set path in the environment variable Path to foler */bin*.
 
3. Download [PostgreSQL](https://www.postgresql.org/download/windows/)

4. Download [NodeJS](https://nodejs.org/en/download/) with npm.

Building project
================
1. Download or clone project from this repository.

2. Copy `conf/pms/pms.properties` to `$CATALINA_HOME`

3. Create schema with random name. As default: `pms`

4. Change(if needed) in `pms.properties` database properties, exactly:
 - URL and port to your database
 - username
 - password
 - current schema

5. In console, go to the folder with the root of the project and run the command `gradle clean war`(you must have installed npm)

6. Copy file `pms.war` from folder /build/libs/ to `$CATALINA_HOME`/webapps/

Tests
=====
To run server tests use command: `gradle test`

To run client tests use command: *will be soon*

Debug
=====
To debug the client use `npm run server` to run Webpack DevServer (port :3000)

Logging
=======
* Logs save to Tomcat base dir `/logs/spring-rest-hibernate-jpa.log`.