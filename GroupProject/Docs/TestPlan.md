# Test Plan


**Author**: \<Team37\>

## 1 Testing Strategy

We test an Android application that manages a Tournament game. The Manager will be able to add and remove players, initialize a tournament match list, view match status and view historical prizes. Multiple users can use the application to view tournament status and prize list.

The application will run on a single Android mobile device. The assumption is made that security is not an important feature to customer; therefore, log-in credential is not required. Local database is used to store users' data.

- Testing Environment: Android version 2.3 and above
- Testing platform: JUnit 5, JDK 1.6, Android SDK
- Android API: Android Studio with ADT plug-in


### 1.1 Overall strategy

The Tournament management Android App will undergo a number of rigorous testing based on unit, integration, system and regression testing strategies. The four testing processes will be performed collaboratively by the team members.

### 1.2 Test Selection

We plan to use black-box testing technique to test the Tournament management App. We will focus on the inputs and outputs based on software requirement and specifications. Both functional testing and non-functional testing (performance, scalability and usability) will be conducted. More regression testing will be done after each bug fix to check that the new code has not affected the existing code. 
The tester will first examine the initial requirement and specifications. Both valid inputs and invalid inputs will be tested for positive test scenario and negative test scenario. The actual output will be compared with expected output. We will fix defects and re-testing the updates. 

### 1.3 Adequacy Criterion

We will perform independent testing from four testers to ensure the quality of the testing process. The results will be combined and re-testing will be performed if necessary. The selection of our test cases are driven from the requirements. In testing the App, we will generate test cases to execute every statement in the “assignment 5: software design” at least once. We will also test cases that we think most likely to cause a problem. Code coverage for unit tests will be measured to ensure a 90% plus coverage. Our testing will complete when all the requirements are met without remaining bug. 

### 1.4 Bug Tracking

The team will use a shared Google Excel sheet to document bug information, severity, and status. The bug will be logged with bug ID. Team members will discuss the status of the bug and strategy.

Bug tracker spreadsheet
https://docs.google.com/spreadsheets/d/18OVkQaXlwxPq3r6HxCRhlBuKU_iwWjTx9jL4NEDbGEE/edit#gid=0

### 1.5 Technology

For unit testing of the App, we will depend on JUnit testing. We will also use JUnit for integration and regression testing to identify any errors, defects or gaps in contrary to the expected results of the application. We may also use Junit parameterized tests for App performance testing. 



## 2 Test Cases

*As the application is still in design phase, to avoid frequent updates to the document, testing steps are omitted and will be updated in next version.*

 
| ID | Test Case | Purpose | Expected result | Actual result | Pass/Fail |
| --- | --- | ---  | --- | --- | --- |
|1|Player log-in	|Player mode selection |		Player Log-in access| | | 		
|2|	Manager log-in	|Manager mode selection |		Manager Log-in access 		| | |
|3|	New tournament	|To ensure tournament creation |		Creation of a new tournament without errors				| | |
|4|	Add player	|New player creation	 |	Player details stored in database				| | |
|4|	Edit player	information | Player information change	 |	Player details changed in database				| | |
|5|	Delete player	|Player deletion	 |	Player details deleted from database			| | |	
|6|	Display prizes and profits |	Total prizes display	 |	Totals for every player and house profits				| | |
|7|	View the match list |	Tournament status display	 |	Match list status				| | |
|8|	Start match	|Allow manager to start a match in list |		Match status "started"			| | |	
|9|	End match	|Allow manager to end a match in list	 |	Match result display			| | |	
|10|	End tournament 	|Allow manager to end a tournament  | 	Session log-out | |  |			
|11|	Default parameters	|To check if default values are enforced	 |	Default values assigned		| | |		
|12|	Log-out	|Session termination	 |	Session log-out		| | | 		
|13|	Session maintenance	|To ensure user is logged on till log-out	|	Session maintained		| | |		
|14| Font size change	|To check if the text font size properly adjusted for different screen resolution |		Font adjusted| | | 		
|15|	Layout change |To check if the Layout properly adjusted for different screen resolution |	Layout adjusted		| | |
|16|	Recovery	|To check if data is kept when app shuts down |	data is not lost			| | |
|17|	Input error	massage | To check if invalid input is handled	|	Input Error massage displayed				| | |
|18|	Exit | Allow user to exit from the app 	|	App exits				| | |
|18|	Delete history | Allow user to delete tournament history record 	|	Correctly delete data after confirmation				| | |
|19|	Hide complete matches | Allow user to filter on-going matches	|	The display will be updated with on-going matches				| | |





