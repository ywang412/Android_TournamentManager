# Use Case Model

**Author**: Team 37 - Hi-Team

## 1 Use Case Diagram

![component](Supporting Files/UseCase.png)

## 2 Use Case Descriptions


**Use Case: createTournament**  
- **Actors:**  TournamentManager
- **Pre-conditions:**  User logs in with Manager mode.
- **Description:**  Use case "createTournament" includes two sub use cases: enterHouseCut and enterEntryPrice. User clicks on "Creat Tournament" button. And the System will prompt to enter required values, i.e., HouseCut and EntryPrice, respectively. The tournament manager will then submit information. The system will ask for confirmation as well as displaying the potential prizes and profit. The tournament manager will confirm the information or go back if any information needs to be modified. Finally the manager will confirm that new tournament gets started, and the system will return to home page.
- **Post-conditions:**  Home page (main menu) will be displayed.
- **Alternative Flows:**  If there is already an ongoing tournament, the system will not allow user to start a tournament. Suitable  error message will be displayed. Parameters cannot be invalid value or be left black, otherwise the system will display error message accordingly.

**Use Case: managePlayer**  
- **Actors:**  TournamentManager
- **Pre-conditions:**  User logs in with Manager mode.
- **Description:**  Use case "managePlayer" includes two sub use cases: registerPlayer and deletePlayer. User clicks on "Manage Player" button, the system will prompt to either add(register) or remove(delete) players. When "register player" is selected, the tournament manager will enter the player details. The tournament manager will then "Submit" player information, and a success message will be displayed. When "delete player" is selected, the list of players is displayed, and the tournament manager will select the player and then "Remove" player. A success message will be displayed. The user can repeate the two sub use cases multiple times. After player list is complete, the system will return to home page.
- **Post-conditions:**  Home page (main menu) will be displayed.
- **Alternative Flows:**  If the user enters a player that's already in the list, an error message will be displayed. If the user selects to remove a player who's in an ongoing tournament, an error message will be displayed.

**Use Case: conductMatch** 
- **Actors:**  TournamentManager
- **Pre-conditions:**  User logs in with Manager mode.
- **Description:**  The manager selects the option 'Show Matches' The manager then selects a match from the list If manager selects a specific match and clicks on 'Start' The system then prompts to select two players from the player list. If manager selects 'End'. System then prompts to specify result and match ends. If manager selects 'End tournament' the tournament ends. Returns to the home page. The system will return to home page.
- **Post-conditions:**  Home page (main menu) will be displayed.
- **Alternative Flows:**  If manager tries to end tournament and there is any ongoing match the system displays an error message.

**Use Case: displayPlayerPrizes** 
- **Actors:**  TournamentManager
- **Pre-conditions:**  User logs in with Manager mode
- **Description:**  The manager selects the option 'Show Player Totals' The system then displays will show totals for every player in the system as a list sorted by total. If manager selects the player from the list. The system displays a list of the player’s individual prizes Then selects 'Close' Returns to the home page. The system will return to home page.
- **Post-conditions:**  Main menu is displayed
- **Alternative Flows:** If there is any ongoing tournament,the system displays a suitable message.

**Use Case: displayHouseProfits** 
- **Actors:**  TournamentManager
- **Pre-conditions:**   User logs in with Manager mode. 
- **Description:**  The manager selects the option 'Show House Profits' The system then displays list of past house profits in chronological order and the total. Then selects 'Close' Returns to the home page. The system will return to home page.
- **Post-conditions:**  Home page (main menu) will be displayed.
- **Alternative Flows:**  If there is no ongoing tournament, the system displays a suitable message.


**Use Case: viewPlayerList** 
- **Actors:**  TournamentManager,TournamentPlayer
- **Pre-conditions:**  User logs in with Manager mode or Player mode. 
- **Description:**  The user selects the option 'Show Player Totals' The system then displays will show totals for every player in the system as a list sorted by total. Then selects 'Close' Returns to the home page. The system will return to home page.
- **Post-conditions:**  Home page (main menu) will be displayed.
- **Alternative Flows:**  If there is no ongoing tournament, the system will display a suitable message.

**Use Case: viewMatchList** 
- **Actors:**  TournamentManager,TournamentPlayer
- **Pre-conditions:**  User logs in with Manager mode or Player mode.
- **Description:**  The user selects the option "Show Matches". And the system will display a list of matches. The user will select "Return" once finished viwing the list. The system will return to home page.
- **Post-conditions:**  Home page (main menu) will be displayed.
- **Alternative Flows:** If there is no ongoing tournament, the system will display a suitable message.

**Use Case: pickDeck** 
- **Actors:**  TournamentPlayer
- **Pre-conditions:**  User logs in with Player mode. 
- **Description:**  The user selects the option “pickDeck”, then selects from a list of decks. The user will review the select, modify the selection if necessary, or confirm the deck choice. The system will return to home page.  
- **Post-conditions:**  Home page (main menu) will be displayed.
- **Alternative Flows:**  If there is no ongoing tournament, the system will display a suitable message.


