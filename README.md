#Spring + Hibernate + Jpa
Simple web application based on gradle.

Version 1.0.0

##Tutorial how to install the project

Installing environment (You need Java 8 to install):

1. Download Tomcat 9 (Core) by [link](http://tomcat.apache.org/download-90.cgi) and unpack it.

2. Download Gradle ver. 3.3 by [link](https://gradle.org/install#manually)
    >Unpack and set path in the environment variable Path to foler */bin*.
 
3. Download and install PostgreSQL by [link](https://www.postgresql.org/download/windows/)

##Building project

1. Download or clone project from this repository.

2. In the console, go to the folder with the project and run the command `gradle clean build`

3. Copy file `SpringRestHibernateJpa.war` from folder /build/libs/ to Tomcat's /webapps/

##Logging

* Logging save to Tomcat `/logs/spring-rest-hibernate-jpa.log`.