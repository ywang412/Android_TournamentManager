# Test Plan

**Author**: Team 37 - Hi-Team

## 1 Testing Strategy

We will be testing an Android application that manages a Tournament game. The Manager will be able to add and remove players, initialize a tournament match list, view match status and view historical prizes. Multiple users can use the application to view tournament status and prize list.

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
https://docs.google.com/spreadsheets/d/1tev9KGNxq6pVBH_RUMbR4kNkfWbnWHF4nTWXHk5wzqw/edit#gid=0

### 1.5 Technology

For unit testing of the App, we will depend on JUnit testing. We will also use JUnit for integration and regression testing to identify any errors, defects or gaps in contrary to the expected results of the application. We may also use Junit parameterized tests for App performance testing. 



## 2 Test Cases

**ID**|**Test Case**|**Purpose**|**Steps**|**Expected result**|**Actual result**|**Pass/Fail**
-----|-----|-----|-----|-----|-----|-----
1|Player - View Leaderboard|Player can log-in and view the leaderboard|1. Check for any active tournaments.  If there is an ongoing tournament, cancel it from Manager mode.  <br>2. Press the "Player" button on the main app screen.|The leaderboard is displayed|Leaderboard displays|Pass
2|Player - View Matchlist|Player can log-in and view the matchlist|1. Check for any active tournaments.  If there is none, create one in Manager mode.  <br>2. Press the "Player" button on the main app screen.|The matchlist is displayed|Matchlist displays|Pass
3|Manager - View Playerlist|Manager can log-in and view the playerlist|1. On App main screen, press "Manager" button. <br>2. Press the "Player Management" button.|The playerlist is displayed|Playerlist displays.|Pass
4|Manager - View Matchlist|Manager can log-in and view the matchlist|1. Check for any active tournaments.  If there is none, create one.<br>2.  On App main screen, press "Manager" button. <br>3.  Press the "Match Management" button.|The matchlist is displayed|Matchlist displays|Pass
5|Manager - Create Tournament|Manager can create a tournament|1. On App main screen, press "Manager" button. <br>2. Press the "Create Tournament" button.<br>3. Input the tournament name, entrance fee and house cut. <br> 4.  Press "Create Tournament" button.|Tournament created Toast message and Tournament details screen is displayed.|Toast and tournament details displays|Pass
6|Manager - Add Player To Database|Manager can add a player to the database|1. On App main screen, press "Manager" button. <br>2. Press the "Player Management" button.<br>3.  Press "Add Player" button.<br> 4.  Input all information and then press "Add Player" button.|Player gets added to database and playerlist, as well as a Toast message confirmation.|Playerlist displays the newly added player and Toast confirmation.|Pass
7|Manager - Delete Player From Database|Manager can delete a player from the database|1. On App main screen, press "Manager" button. <br>2. Press the "Player Management" button.<br>3.  Press on an existing player and click Yes when prompted to remove them.|The player gets removed from the list and database.|Player is removed|Pass
8|Manager - View House Profits/Tournaments Hosted|Manager can view the total house profits|1. On App main screen, press "Manager" button. |Total house profit is displayed correctly at the top, alongside total number of tournaments.|Correct profits and tournaments displayed.|Pass
9|Manager - Start Match|Manager can start a match|1. Check for any active tournaments.  If there is none, create one in Manager mode.  <br>2. Press the "Manager" button on the main app screen.<br>3.  Press the "Match Management" button.<br>4.  Set a match to "Ready Status" and start it.|Match status should change from "Setup" to "Ready" and then finally "InProgress"|Status of match changes.|Pass
10|Manager - End Match|Manager can end a match and select the winner|1. On the matchlist, Press the "End" action link on any match with an "InProgress" status.<br>2.  Select the winner of the match.|Match status should change from "InProgress" to "Completed" with a winner listed under "Winner"|Winner is displayed, status is "Completed"|Pass
11|Manager - Cancel Tournament|Manager can cancel a tournament|1. Check for any active tournaments.  If there is none, create one in Manager mode.  <br>2. Press the "Manager" button on the main app screen.<br>3.  Press the "Manage Current Tournament" button.<br>4.  Press the "Cancel" button.|Status of the tournament should display "Cancelled" in the tournament details page.|Status changes to Cancelled.|Pass
12|Leaderboard - Properly Sorted|The player leaderboard is correctly sorted by total prize winnings|1. Check for any active tournaments.  If there is an ongoing tournament, cancel it from Manager mode.  <br>2. Press the "Player" button on the main app screen.|The leaderboard is correctly sorted from highest to lowest|The leaderboard is correctly sorted.|Pass
13|Tournament - Matchmaking|On tournament start, a list of matches are generated with proper matchups|1.  Create a tournament and add 8 or 16 players to it.<br>2.  Start the tournament<br>3.  Press the "Match List" button.|Matches should be generated between the tournament players properly.  Players should not be playing against themselves.|Matchmaking creates unique matches|Pass
14|Tournament - Single-Elimination Progression|Winners of matches should proceed to their next matches while losers are eliminated.|1.  For an ongoing tournament, end some matches and report the match winners.|The winners should be placed against each other in a new match.|Winner moves on to play other winners.|Pass
13|UI - Input Validation|UI will prevent adding players or creating a tournament without all required fields.|1. Add a player without one or more of the following: Username, phone number, name.<br>2.  Create a tournament with invalid house fee percentage or without a name.|A TextError should be displayed and the player fails to add or tournament does not get created.|Fails with error message|Pass
14|UI - Multiple Resolutions Support|Ensure that the app looks and functions normally under different devices|1. Create virtual devices of different screen sizes and resolutions.<br>2.  Launch the application and check for UI problems.|The UI should stay intact.  Buttons, textviews and such are still visible and not cut off.|Application looks fine under different screen sizes.|Pass
15|App - Device Compatibility|Ensure the app will run on the minimal API level and later.|1.  Launch the app on API 19 through 25.|The application should launch without errors.|Application launches.|Pass
16|Database Functionality|The database retains data between app sessions.|1. On App main screen, press "Manager" button. <br>2.  Add a player to the database using the "Player Management" button. <br>3.  Close the application<br>4.  Reload the application<br>5.  Navigate to Manager -> "Player Management"|The player that was added before the app was closed should display on the list.|Database works properly, retains data between sessions|Pass





