# Design Document

**Author**: Team 37 - Hi-Team

## 1 Design Considerations

### 1.1 Assumptions

* The application will only run on an Android device with touch screen or equivalent control
* Android API level 19
* No player driven data.  All data entry will be done by the tournament manager
* No webservice interaction
* Single device management - the tournament data is not expected to be available on more than one system
 * The application will use Android's built in SQLite database

### 1.2 Constraints

* requires that only one device is used for the tournament

### 1.3 System Environment

* Android OS
* Android device with touch screen (or equivalent control)

## 2 Architectural Design

### 2.1 Component Diagram
![component](Supporting Files/Component Diagram.png)

UI: 
* The player interaction will consist of access read data to the results, players, deck and other tournament related data in order to present relavent information.  
* The Manager interaction will require write access to almost all components as the Manager is the only role capable of entering data.

Business Component: 
* Match Maker component is the logic that is responsible for generating matches for a given tournament.
* Tournament component will consist of Matches and contain any logic required to allow tournament data to be managed and displayed before, during and after the tournament
* Result component will consist of prizes, revenues, and the results of the matches.  This is separate from the tournament itself as our classes are designed to separate the dynamic tournament data from the static result data.
* Player component will consist of the management of players in the application and tournament along with any relevant player data.
* Although deck was a minor component in the requirements, it is represented as an individual component as deck management should be and will be an independent module.
 
Infrastructure:
* Security is necessary to ensure nobody is able to modify tournament data without proper authentication and audit trail
* Persistence and database are necessary to store the tournament data in a robust way.  Especially when we consider that this will be an Android app, we must make sure our data will not be wiped unintentially by the Android lifecycle.

### 2.2 Deployment Diagram

As a stand-alone Android app, we did not see the necessity of a deployment diagram.  Android Studio provides all the necessary tools to package all the classes and resources into an APK, which can be added to the Play Store for deployment through the Google Play Store.

## 3 Low-Level Design

### 3.1 Class Diagram

![class](Supporting Files/Class Diagram.png)

### 3.2 Other Diagrams

We do not currently have any other diagrams to describe our design.  As of now, as a standalone Android app, we feel that the Component Diagram and the Class Diagram sufficiently describe our application.

## 4 User Interface Design

![class](Supporting Files/UI Mockup.png)

