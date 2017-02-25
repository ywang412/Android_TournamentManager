# Use Case Model

**Author**: Team 37 - Hi-Team

## 1 Use Case Diagram

![component](Supporting Files/UseCase.png)

## 2 Use Case Descriptions


**Use Case: createTournament**  
- **Actors:**  TournamentManager
- **Pre-conditions:**  User logs in with Manager mode.
- **Description:**  Use case "createTournament" includes two sub use cases: enterHouseCut and enterEntryPrice. User clicks on "Create Tournament" button. And the System will prompt to enter required values, i.e., HouseCut and EntryPrice, respectively. The tournament manager will then submit information. The system will ask for confirmation as well as displaying the potential prizes and profit. The tournament manager will confirm the information or go back if any information needs to be modified. The manager will confirm that new tournament gets started, and the system will return to home page.
- **Post-conditions:**  Home page (main menu) will be displayed.
- **Alternative Flows:**  If there is already an ongoing tournament, the system will not allow user to start a tournament. Suitable  error message will be displayed. Parameters cannot be invalid value or be left black, otherwise the system will display error message accordingly.

**Use Case: managePlayer**  
- **Actors:**  TournamentManager
- **Pre-conditions:**  User logs in with Manager mode.
- **Description:**  Use case "managePlayer" includes two sub use cases: registerPlayer and deletePlayer. User clicks on "Manage Player" button, the system will prompt to either add(register) or remove(delete) players. When "register player" is selected, the tournament manager will enter the player details. The tournament manager will then "Submit" player information, and a success message will be displayed. When "delete player" is selected, the list of players is displayed, and the tournament manager will select the player and then "Remove" player. A success message will be displayed. The user can repeat the two sub use cases multiple times. After player list is complete, the system will return to home page.
- **Post-conditions:**  Home page (main menu) will be displayed.
- **Alternative Flows:**  If the user enters a player that's already in the list, an error message will be displayed. If the user selects to remove a player who's in an ongoing tournament, an error message will be displayed.

**Use Case: conductMatch** 
- **Actors:**  TournamentManager
- **Pre-conditions:**  User logs in with Manager mode.
- **Description:**  Use case "conductMatch" includes three sub use cases: start match, end match, and record winner. When the user selects "conductMatch", a match list will be displayed and the user selects one match from the list. The system will then ask user to select two players from the player list. The user then selects "start Match" to start a match. If user selects 'End Match'. System will prompt to "record winner" and the match ends. When all matches are finished, the user can select 'End tournament' and the tournament ends. The system will return to home page.
- **Post-conditions:**  Home page (main menu) will be displayed.
- **Alternative Flows:**  If manager tries to end tournament when there are ongoing matches, an error message will be displayed.

**Use Case: displayPlayerPrizes** 
- **Actors:**  TournamentManager
- **Pre-conditions:**  User logs in with Manager mode
- **Description:**  The manager selects the option "Show Player Prizes", and the system will show total prizes for each player in the system as a list, and sorted by total prizes. If manager selects the player from the list, the system will display this player’s individual prizes. The manager can select "Close" and the system will return to home page.
- **Post-conditions:**  Home page (main menu) is displayed,
- **Alternative Flows:** If there is any ongoing tournament, a suitable message will be displayed.

**Use Case: displayHouseProfits** 
- **Actors:**  TournamentManager
- **Pre-conditions:**   User logs in with Manager mode. 
- **Description:**  The manager selects the option "Show House Profits", and the system will show a list of past house profits in chronological order and the total. The manager can select "Close" and the system will return to home page.
- **Post-conditions:**  Home page (main menu) will be displayed.
- **Alternative Flows:**  If there is any ongoing tournament, a suitable message will be displayed.


**Use Case: viewPlayerList** 
- **Actors:**  TournamentManager,TournamentPlayer
- **Pre-conditions:**  User logs in with Manager mode or Player mode. 
- **Description:**  The user selects the option "Show Player List", and the system will display totals for every player in the system as a list sorted by total. The user can select "close" and the system will return to home page.
- **Post-conditions:**  Home page (main menu) will be displayed.
- **Alternative Flows:**  If there is any ongoing tournament, the system will display a suitable message.

**Use Case: viewMatchList** 
- **Actors:**  TournamentManager,TournamentPlayer
- **Pre-conditions:**  User logs in with Manager mode or Player mode.
- **Description:**  The user selects the option "Show Matches". And the system will display a list of matches. The user will select "Return" once finished viewing the list, and the system will return to home page.
- **Post-conditions:**  Home page (main menu) will be displayed.
- **Alternative Flows:** If there is any ongoing tournament, the system will display a suitable message.

**Use Case: pickDeck** 
- **Actors:**  TournamentPlayer
- **Pre-conditions:**  User logs in with Player mode. 
- **Description:**  The user selects the option “pickDeck”, then selects from a list of decks. The user will review the selection, modify the selection if necessary, or confirm the deck choice. The system will return to home page.  
- **Post-conditions:**  Home page (main menu) will be displayed.
- **Alternative Flows:**  If there is any ongoing tournament, the system will display a suitable message.


