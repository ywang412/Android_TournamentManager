# Design Document

**Author**: Team 37 - Hi-Team

## 1 Design Considerations

*The subsections below describe the issues that need to be addressed or resolved prior to or while completing the design, as well as issues that may influence the design process.*

### 1.1 Assumptions

*Describe any assumption, background, or dependencies of the software, its use, the operational environment, or significant project issues.*

### 1.2 Constraints

*Describe any constraints on the system that have a significant impact on the design of the system.*

### 1.3 System Environment

*Describe the hardware and software that the system must operate in and interact with.*

## 2 Architectural Design

*The architecture provides the high-level design view of a system and provides a basis for more detailed design work. These subsections describe the top-level components of the system you are building and their relationships.*

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

*Describe the low-level design for each of the system components identified in the previous section. For each component, you should provide details in the following UML diagrams to show its internal structure.*

### 3.1 Class Diagram

![class](Supporting Files/Class Diagram.png)

### 3.2 Other Diagrams

*<u>Optionally</u>, you can decide to describe some dynamic aspects of your system using one or more behavioral diagrams, such as sequence and state diagrams.*

## 4 User Interface Design
*For GUI-based systems, this section should provide the specific format/layout of the user interface of the system (e.g., in the form of graphical mockups).*

