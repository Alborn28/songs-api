# REST API

## Task

The task for this assignment was to create a Spring Boot Web API, and use Thymeleaf to create static HTML-pages and serve them to the browser. The database used was the following: [Chinook](https://www.sqlitetutorial.net/sqlite-sample-database). In the postman_collection.json there are nine calls to the API to test the functionality of the Endpoints. The task was to be completed by utilizing Pair Programming.

## Project Structure

The central part of the project are the three packages in the [assignment2](/src/main/java/se/experis/assignment2) folder, which are: controllers, data_access and models. The controllers-package contains two controllers: CustomerController and ThymeleafController. The CustomerController handles all requests for the Spring Boot Web API, and the ThymeleafController handles all request for the Thymeleaf views. The data_access-package contains all the logic concerning communication with the database. The models-package contains classes used to represent objects in the database. The application is started by running the [Assignment2Application.java](/src/main/java/se/experis/assignment2/Assignment2Application.java).

## Members
**Joakim Österberg & Albin Ljungdahl**
