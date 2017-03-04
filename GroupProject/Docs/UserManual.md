# User Manual

**Author**: Team 37 - Hi-Team

## Application Overview:

Tourney Manager is a user friendly E-gaming application that allows users to manage or participate in a single elimination tournament, with a payout for the top three winners. The application has two modes: Manager and Player. The tournament can have one manager, and either 8 or 16 players.

## Functionality Overview:

![component](Supporting Files/alpha_select_mode.png)

- **Manager mode: This mode enables the user to**

1.	Add a player to the system

2.	Remove a player from the system

3.	Manage existing tournament or create a new tournament

4.	Display house profit and player prizes

- **Player mode: This mode enables the user to**

1.	View player prizes

2.	View current matches in the existing tournament

## How to use the application:

- **Manager Mode**

![component](Supporting Files/alpha_manager_login.png)  

1.	Add a player to the system
  * User login with Manager mode. The player list will automatically displayed. The user then click "Add Player".  
  * The user will enter all the required information: Name, Phone Number, and Deck. Then click OK.  
![component](Supporting Files/alpha_add_player.png)  
2.	Remove a player from the system
  * User login with Manager mode. The player list will automatically displayed.
  * User clicks on the desired username that he/she wants to remove, then clicks YES or NO to confirm or cancel.    
![component](Supporting Files/alpha_remove_player.png)  
3.	Manage existing tournament or create a new tournament

  * If there is no existing tournament, the Create Tournament button will be available.  
  * User then click on "Create Tournament" and enter the house cut, entry price, number of players, and a list of players participating in the tournament. Please note that the amount of players is restricted to either 8 or 16.  
![component](Supporting Files/alpha_manager_createT2.png)  

  * After all the information has been entered, user clicks on the Display Prizes and Profit button and the application will automatically calculate the house profit amount as well as the first, second, and third prizes based on the user inputs.
  * The house profit is calculated based on the House Cut percentage input of the total amount of money collected, which are user modifiable. For example, the 1st, 2nd, and 3rd prizes can be 50%, 30% and 20% of the prize pool remaining after the house cut. Note that the results are rounded to the nearest integer.  
  * Once the user validates the information, clicks on the Create Tournament button to create a tournament as shown below  
![component](Supporting Files/alpha_manager_createT1.png)  

  * To manage an existing tournament, user clicks on the Manage Tournament button. In this screen, the user can begin or end matches, and assign the results.  
  * Users can end the current tournament by clicking on the End Tournament button. Note that if the tournament is ended before all matches finished, the tournament will be considered invalid and the money will be refunded to players.


4.	Display house profit and player prizes   
  * User login with Manager Mode, and Display Prizes and/or Profits

- **Player Mode**

1.	View player prizes
  * User log with Player Mode. A list of players will be automatically displayed.  
![component](Supporting Files/alpha_player_list.png)  

  * User then clicks on one player, and the prizes of this specific player will be displayed. As shown below.  
![component](Supporting Files/alpha_player_prize.png)  



2.	View current matches in the existing tournament
  * User logon in Player Mode. And view Match list.
