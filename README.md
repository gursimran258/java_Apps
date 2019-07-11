# Introduction
This repository contains three applications in the package in three sub packages: Java Grep, JDBC Exercise app, and Twitter CLI app.
# Java Grep
## Introduction
Java Grep is an application that scans the files in a directory and sub directories under the given directory to search for a pattern and print the matched lines in files into an output file.
## Usage
The application takes the pattern to be matched, rootpath of directory to check the pattern in the files in the given directory and sub directories. It then write the matched lines to the output file.
``
regex rootpath outputFile
``
## Design and Implementation
Pseudo code:
1. Scan the files in directory and subdirectories to obtain list of files.
2. For all lines present in a file, match the pattern.
3. If pattern is matched, output the matched line to output file.

## Enhancements and Issues
- Testing could be done in order to check the robustness of the application in various cases.

# JDBC Exercise App
## Introduction
JDBC exercise application follows the DAO pattern using the JDBC to perform CRUD operations on the database in the PostgreSQL. 
## Design and Implementation
### Components

 - **JDBC Executer:** This creates the connection with the database in PostgreSQL using database connection manager. It also issues the queries to DAO layer methods to perform operations on database.
 - **DAO Layer:** This formulates the query in proper format to be issues to database in PostgreSQL and parses the response.
 -  **Database Connection Manager:** It uses JDBC driver connect to PostgreSQL database
 - **PostgreSQL database:** Database in stored in PostgreSQL where operations can be performed through database connection manager.
 
## Enhancements
- Thorough testing can be done to check the robustness of the application

# Twitter CLI 
## Introduction
Twitter CLI is a command line Java application to create, read, and delete posts on Twitter. It perform POST and GET requests on Twitter API endpoints to perform the create, read, and delete operations.
### Setup
Create an application on Twitter's Developer account to get access keys and tokens to be used to run this application: Customer key, key secret, access token, and access token secret.  These tokens are necessary to communicate with Twitter REST API. 

Set these credentials in the system environment variables to be used by the application.

## Usage
Twitter CLI uses GET, POST, AND DELETE arguments to retrieve tweets based on their ids, post tweets, and delete tweet based on their id.

GET arguments are passed on command line to retrieve tweet based on their id.

``
GET "1148659969992089602"
``

POST arguments are passed on command line to post a tweet on the timeline passing arguments as status and the coordinates to provide spatial information. 

``
POST "testing a tweet" "-10:10
``

DELETE is used in arguments to delete tweets based on their id.

``
 DELETE "1148659969992089602"
``


## Design and Implementation
### Components
 - ****Twitter CLI**:** This module intializes the components and run the Twitter CLI runner.
 - **Twitter CLI Runner:** This directs the program flow based on the arguments passed by the user.
 -  **Twitter Service:** It includes the business logic that validates the arguments and send it to appropriate methods to the DAO layer.
 -   **Twitter DAO:** This module creates the request to be sent in order to perform GET, POST, DELETE operations.   
 - **Http Helper:** This module is used to perform http methods to perform operations at Twitter REST API endpoint.
 
### Spring Configuration
Dependency management has also been implemented using Spring framework through Spring Bean approach, Spring annotation approach, and SpringBoot approach.
### Workflow
On passing the arguments to the java application, Twitter CLI intializes the components and start the runner. Twitter CLI runner will call the appropriate methods in Twitter service based on arguments. The corresponding method in Twitter service may validate the tweet text and coordinates in case of posting the tweet and form the tweet object to send to Twitter DAO or send the tweet id to get or delete the tweets. Also, it may use the recieved response on performing the methods.  Twitter DAO methods uses the objects sent by Twitter service to generate url or use the id to call methods in https helper module. It also parses the response recieved from http helper to send it back to Twitter service. Http helper uses the request url created by Twitter DAO to send the requests to Twitter API.

### Testing
Unit testing is done using Mockito framework on Twitter service module in isolation. For integration testing, JUnit is used to test the TwitterRestDAO module in integration to the underlying dependency of http helper module.

### Libraries
- OAuth-signpost Library: OAuth client library for authentication
- Jackson: Map java objects to json and vice versa
- JUnit: Integration Testing
- Mockito: Unit Testing
- Spring framework: Creating IOC Container to configure class dependencies

##  Enhancements
- Partial testing is done for this. Thorough integration testing should be done and a thorough system testing for the whole application.
-  There are multitude of methods that can be implemented using Twitter API's end point that can be used to provide additional functionalities to user.
