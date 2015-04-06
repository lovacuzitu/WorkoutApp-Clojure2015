## Workout app

A Clojure web application for listing of equipment, exercises and workout examples from MongoDB and the REST service, and for adding, deleting and updating workout examples, where is enabled user logging and registering. The application is written in Clojure and uses the following libraries: Noir, Enlive, Compojure, Monger, Ring and Compojure libraries (details in technology explanation part).
This application is designed for learning Clojure.

The application calls a REST service from https://wger.de/en/software/api and displays equipment and exercises. Data is obtained in JSON format, and shown on pages "Equipment" and "Exercises".

First the database is filled with some initial data about workout (name, description, equipment and image). After starting application user can see these workout examples on the home page. Also, initially, in database collection named "users" is created with one user inserted, and he can loggin into the application with credentials:

 - username: admin
 - password admin

If user is not logged in, he can just review exercises, equipment and workout examples, and search exercises and equipment by ID calling REST service. When the user logs in, he can also add, delete or update workout in application, on page "Workout". User can add name, description, equipment, and upload a image about the workout that will be displayed on the home page. User can also register, and after that he can log in to the application using saved credentials.

The application consists of 4 pages:

1. Home page - serves as a starting point and displays two workout examples from database.
2. Equipment page - displays one default eqipment (id = 10, name ="Kettlebell") and a search bar where user can search equipment via REST service. User can search for equipment using only id, and values that exists on REST server are from 1 to 10.
3. Exercises page - displays one default exercise (id = 94, name = "Crunches on machine") and a search bar where user can search exercises via REST service. User can search for a exercise using only exercise ID, from value 94, because of REST API restriction. If user, for example, enters ID=1, he will get exercise desribed on German language.
4. Workout page - CRUD functionalities are developed on this page. They are visible only if user logs in to the application. User can add new workout. User can delete workout by name. On this page last added workout is shown, and user can update description and equipment for that workout. He can update that workout by name, so he need to enter old name of workout (shown next to the input field), and new values for description and equipment, and submit changes. User will be redirected to home page after performing any of these actions on this page.

* POSTMAN is great application that helps user to see which results can be obtained by calling REST service (it is developed as Google Chrome Add-on). There is no need for any kind of authorization for communicating with REST service used in this application.

## Usage

Download and install Leingen (https://github.com/technomancy/leiningen), and then download and install MongoDB (http://www.mongodb.org/downloads).

It's necessary to start MongoDB before running the application. Database used in this project is MongoDB 2.6.8. To start database open command line, navigate to mongodb/bin folder, and then execute mongod.exe (on windows). To start MongoDB shell, navigate to the same path and execute mongo.exe (on windows). For more detailed instructions on how to start MongoDB, see http://docs.mongodb.org/manual/installation/.

The application automaticaly inserts initial data in database. If you would like to prevent this comment out the first line in the main method (db/insert-initial-data).

To start the application navigate to the root folder of "Workout" application and enter: lein run. Leiningen will download all dependencies needed for runing this project.

## Technology explanation

Noir is Clojure web framework. He requires Leiningen installed. Noir framework uses Hiccup library that is used for representing HTML in Clojure. Noir is  built on top of Ring and Compojure, which take care of handling HTTP requests and responses. Compojure is a small routing library for Ring that allows web application to be composed of small independent parts. Enlive is a selector-based (CSS) templating library for Clojure. Monger is a Clojure MongoDB client. When we use Monger, Clojure data structures are automaticaly translated into MongoDB/BSON data types.

## References

[Practical Clojure](http://www.amazon.com/Practical-Clojure-Experts-Voice-Source/dp/1430272317), Luke VanderHart and Stuart Sierra,
[Programming Collective Intelligence: Building Smart Web 2.0 Applications](http://www.amazon.com/Programming-Collective-Intelligence-Building-Applications/dp/0596529325), O' Reilly

## License

Distributed under the Eclipse Public License, the same as Clojure.
