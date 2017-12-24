Product Management System
=========================
[![Build Status](https://travis-ci.org/akraskovski/product-management-system.svg?branch=master)](https://travis-ci.org/akraskovski/product-management-system)

Web application produces the system, working with products, stocks and shops resources.

***

Server side technologies stack: 
- Java 8
- Spring Boot (Security, Data Jpa, Web)
- JWT (As a security base point)
- Hibernate Jpa
- PostgreSQL

Client: Angular 4.

Build tools: 
- Gradle
- Webpack

***

Installing the project
===================================
Installing environment (You need Java 8 installed):

2. Download [Gradle](https://gradle.org/install#manually)
    >Unpack and set path in the environment variable Path to foler */bin*.
 
3. Download PostgreSQL from [official site](https://www.postgresql.org/download/windows/) or from docker hub: `docker pull postgres:latest`

4. Download [NodeJS](https://nodejs.org/en/download/) with npm.

Building project
================
1. Clone project from this repository.

2. Start PostgreSQL, docker command: `docker run --name postgres -v /opt/db/postgres:/var/lib/postgresql/data -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=root -e POSTGRES_DB=postgres -p 5432:5432 -d postgres`

3. Change(if needed) in `application.properties` project properties, exactly:
 - URL and port to your database
 - username
 - password
 - secret security key

Run backend:

- Manually: 

1. Go to the server package `cd server/` 
    
2. Run the command `gradle jar`, `java -jar build/libs/product-management-system.jar` or just `gradle bootRun`

- With docker:

1. Go to server directory: `cd server/`
    
2. Build server jar, using gradle command: `gradle clean build`
    
3. Build an image: `docker build -t pms-image .`
    
4. Start docker container: `docker run --name pms-server --link postgres -p 8080:8080 -d pms-image`

Run client:

- In console, go to the project root root.
- `cd client/`
- `npm i`
- `npm start`

Tests
=====
To run server tests use command: `gradle test`

To run client tests go to `client` folder in project and use command: 
* `npm run selen-update`
* `npm run selen`
* `npm run test`

Debug
=====
To debug the client use `npm run server` to run Webpack DevServer (port :3000)

Logging
=======
* Logs save to dir `/server/log/pms/general.YYYY-MM-DD.log`.
