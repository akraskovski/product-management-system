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

Client: Angular 2.

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

##Building project

1. Download or clone project from this repository.

2. In the console, go to the folder with the project and run the command `gradle clean build`(you must have installed npm)

3. Copy file `SpringRestHibernateJpa.war` from folder /build/libs/ to Tomcat's /webapps/

4. Import `classpath:db/migrate/dump.sql` to your database with schema *product*.

To debug the client use `npm run server` to run Webpack DevServer (port :3000)

##Logging

* Logging save to Tomcat `/logs/spring-rest-hibernate-jpa.log`.