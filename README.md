Product Management System
=========================
[![Build Status](https://travis-ci.org/akraskovski/product-management-system.svg?branch=master)](https://travis-ci.org/akraskovski/product-management-system)

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

2. Download [Gradle ver. 3.3](https://gradle.org/install#manually)
    >Unpack and set path in the environment variable Path to foler */bin*.
 
3. Download PostgreSQL from [official site](https://www.postgresql.org/download/windows/) or by docker command: `docker pull postgres:9.4`

4. Download [NodeJS](https://nodejs.org/en/download/) with npm.

Building project
================
1. Download or clone project from this repository.

2. Run db on your system. (use docker command `docker run --name postgres-latest -v /opt/db/postgres-latest:/var/lib/postgresql/data -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=root -e POSTGRES_DB=postgres -p 5432:5432 -d postgres:9.4`)

3. Create schema with random name. As default: `pms`

4. Change(if needed) in `application.properties` project properties, exactly:
 - URL and port to your database
 - username
 - password
 - current schema
 - secret key for security
 - storing images directory on your machine

Run backend:

- In console, go to the project root and run the command `gradle jar`, `java -jar build/libs/product-management-system.jar` or just `gradle bootRun`

Run client:

- In console, go to the project root root.
- `cd client/`
- `npm i`
- `npm run server`

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
* Logs save to Tomcat base dir `/logs/pms/pms.log`.
