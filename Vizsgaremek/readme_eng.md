![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![Stack Overflow](https://img.shields.io/badge/-Stackoverflow-FE7A16?style=for-the-badge&logo=stack-overflow&logoColor=white)


## General Information
- It's currently a backend application that models a running log. The program calculates the loaded route in km based on coordinate points.
- This is a project to practice the Java/Spring Boot/SQL database management layer and expand my horizon with other technologies.

## Technologies Used
- Java - version 17
- Spring Boot - version 3.2.1
- MariaDB java client - version 3.0.4.
- Spring Boot starter test - version 2.6.15.
- Flyway-mysql - version 8.5.11.
- Spring data JPA - 2.6.8.
- Maven - version 3.1.0.

## Features
- The app is suitable for recording running workouts
- Calculates the length of the workout, the total number of level climbs
- Download the data into the mariaDb database
- Spring Data JPA persistent layer is used
- Tested with integration (mainly WebClient) tests
- Create dynamic documentation with Swagger UI

## Usage
The application runs on localhost, could be tried out with Postman or **by running tests**.

## Project Status
Project is: _in progress_

## Room for Improvement

Room for improvement:
- Revise testing structure
- Containerisation does not work perfectly

To do:
- Validation
- Authorization and authentication with OAuth 2.0
- Frontend (Vue-vite)
