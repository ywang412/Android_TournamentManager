# Use Case Model

**Author**: Team 37 - Hi-Team

## 1 Use Case Diagram

![component](Supporting Files/UseCase.png)

## 2 Use Case Descriptions


**Use Case: createTournament**  
- **Actors:**  TournamentManager
- **Pre-conditions:**  User logs in with Manager mode.
- **Description:**  Use case "createTournament" includes two sub use cases: enterHouseCut and enterEntryPrice. User clicks on 'Creat Tournament' button. And the System will prompt to enter required values, i.e., HouseCut and EntryPrice, respectively. The tournament manager will then submit information. The system will ask for confirmation as well as displaying the potential prizes and profit. The tournament manager will confirm the information or go back if any information need to be modified. Finally the manager will confirm that new tournament gets started, and the system will return to home page.
- **Post-conditions:**  Home page (main menu) will be displayed.
- **Alternative Flows:**  If there is already an ongoing tournament, the system will not allow user to start a tournament. Suitable  error message will be displayed. Parameters cannot be invalid value or be left black, otherwise the system will display error message accordingly.

**Use Case: managePlayer**  
- **Actors:**  TournamentManager
- **Pre-conditions:**  User logs into the system in Manager mode.
- **Description:**  If user clicks on 'Add Player' The system then prompts to enter required values. The tournament manager will enter the player details. The tournament manager will then Submit information. Success message is displayed. If user clicks on 'Remove Player' The list of players is displayed. The tournament manager will select the player. The tournament manager will then 'Remove' player Success message is displayed. If 'Continue' option is selected the updated list of players is displayed. If 'Return' option is selected Returns to the home page.
- **Post-conditions:**  Main menu is displayed
- **Alternative Flows:**  In case the player is already present system doesn't allow user to add the player again and accordingly a message will be displayed. If the player which is selected to be removed is part of any on going tournament, system doesn't allow user to remove the player again and accordingly a message will be displayed.

**Use Case: conductMatch** 
- **Actors:**  TournamentManager
- **Pre-conditions:**  User logs into the system in Manager mode.
- **Description:**  The manager selects the option 'Show Matches' The manager then selects a match from the list If manager selects a specific match and clicks on 'Start' The system then prompts to select two players from the player list. If manager selects 'End'. System then prompts to specify result and match ends. If manager selects 'End tournament' the tournament ends. Returns to the home page.
- **Post-conditions:**  Main menu is displayed
- **Alternative Flows:**  If manager tries to end tournament and there is any ongoing match the system displays an error message.

**Use Case: displayPlayerPrizes** 
- **Actors:**  TournamentManager
- **Pre-conditions:**  User logs into the system in Manager mode
- **Description:**  The manager selects the option 'Show Player Totals' The system then displays will show totals for every player in the system as a list sorted by total. If manager selects the player from the list. The system displays a list of the player’s individual prizes Then selects 'Close' Returns to the home page.
- **Post-conditions:**  Main menu is displayed
- **Alternative Flows:** If there is any ongoing tournament,the system displays a suitable message.

**Use Case: displayHouseProfits** 
- **Actors:**  TournamentManager
- **Pre-conditions:**   User logs into the system in Manager mode. 
- **Description:**  The manager selects the option 'Show House Profits' The system then displays list of past house profits in chronological order and the total. Then selects 'Close' Returns to the home page.
- **Post-conditions:**  Main menu is displayed
- **Alternative Flows:**  If there is no ongoing tournament, the system displays a suitable message.


**Use Case: viewPlayerList** 
- **Actors:**  TournamentManager,TournamentPlayer
- **Pre-conditions:**  User logs into the system in Manager mode or Player mode. 
- **Description:**  The user selects the option 'Show Player Totals' The system then displays will show totals for every player in the system as a list sorted by total. Then selects 'Close' Returns to the home page.
- **Post-conditions:**  Main menu is displayed
- **Alternative Flows:**  If there is no ongoing tournament, the system displays a suitable message.

**Use Case: viewMatchList** 
- **Actors:**  TournamentManager,TournamentPlayer
- **Pre-conditions:**  User logs into the system in Manager mode or Player mode.
- **Description:**  The user selects the option 'Show Matches' The system then displays list of matches Then selects 'Close' Returns to the home page. 
- **Post-conditions:**  Main menu is displayed
- **Alternative Flows:** If there is no ongoing tournament,the system displays a suitable message.

**Use Case: pickDeck** 
- **Actors:**  TournamentPlayer
- **Pre-conditions:**  User logs into the system in Player mode. 
- **Description:**  
- **Post-conditions:**  Main menu is displayed
- **Alternative Flows:**  If there any ongoing tournament,the system displays a suitable message.


