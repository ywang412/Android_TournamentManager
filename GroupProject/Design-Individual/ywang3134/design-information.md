**Class design specification**
------------------------------

1-The tournament is organized as a single elimination tournament with third place playoff.

The Create_Matchlist() method in Tournament() class implements the single elimination/third place playoff rule in a match list internally.

2-The application has two modes: tournament manager and tournament player. You can assume that the mode is selected when the application starts, with no authentication involved.

Two log-in classes tournament_manager and tournament_player are designed. They are the entries into the system. 

3-The tournament manager uses the system to (1) add players, (2) run tournaments, and (3) display prizes and profits.

The tournament_manager() class can (1) add/delete player() class as operations using add_player()/delete_player() methods, (2) initiate tournament() class for a given list of players whose details are in the system, and (3) display prizes and house profits by TourneyCalc(). 

4-The tournament players use the system to (1) view the match list and (2) view the total player prizes.

The tournament_player class can (1) use MatchList_result() class to display current match list, and (2) use players_total() class to display total player prizes.


5-The app has an underlying database to save persistent information across runs (e.g., players in the system, status of ongoing tournaments).

Create player(), tournament_house_profit() and tournament_winners() classes to represent the information.

6-A player in the system is characterized by the following information:
a.	Name
b.	Unique alphanumeric username
c.	Numeric phone number
d.	A deck choice, from a list of deck options

The player() class is designed with name, username, phone number and deck choice attributes and is uniquely identified using a username. 

7-The tournament manager can add a player to and remove a player from the system.

The tournament_manager() class has methods of add/delete player().

8-There can only be one ongoing tournament at any given time.

The tournament manager can only initiate one tournament. 

9-To start a tournament:
a.	The tournament manager will enter the house cut.
b.	The tournament manager will enter the entry price.
c.	The tournament manager will enter all player usernames. For simplicity, let’s assume that there will be either 8 or 16 players in a tournament.
d.	When the tournament manager has entered the above information, the system will display, in addition to the player names, the potential prizes and profit, as calculated in the TourneyCalc app that you developed for Assignment 4. The tournament manager will then be able to double check the information and start the tournament.

The tournament() class has attributes housecut, entryprice and playerlist. It will use TourneyCalc() class to calculate and display potential prizes and profits.

10-When there is no ongoing tournament, the player mode will show totals for every player in the system as a list sorted by total.

The Tournament_winners is an association class between the tournament and player. Whenever a tournament completes, a tournament_winner class record the winning players and prizes in this year. It then communicates with the players_total() class to calculate and sort prizes totals for every player. The tournament_player() class interacts with players_total() class to see a history of all the prizes earned by a player in past years. 


11-When a tournament is ongoing, the player mode will show a match list. The match list displays a list of players paired with other players representing ongoing matches, matches ready to be played, and results from completed matches.

A MatchList_result() class to display current matches’ information, including matches and results by a view_match_list() method. The tournament_player() class interacts with MatchList_result() to show the match list. 


12-When a tournament is ongoing, the manager mode will also show a match list. In this case, however, the tournament manager will be able to:
a.	Start a match ready to be played by selecting it from the list. The system will then mark the game between the specified players as started.
b.	End an ongoing match and specify a result for it.
c.	End the tournament. If the tournament is ended early, money is refunded.

The tournament_manager() class interacts with MatchList() class to start a match. MatchList() class has start_match() method and end_match() method. The tournament_manager() class interacts with Tournament() class to end the whole tournament and refund money by end_tournament() method. 

13-After a tournament is completed, prizes for the winning player, the second place player, and the third place player (who wins the third place playoff) will be recorded in the underlying database.

After all matches finish, an associated class Tournament_winners() class is designed to record 1st/2nd/3rd winner name, prizes and the tournament year. It then communicates with the players_total() class to calculate totals for every player.
  
14-After a tournament is completed, the house profit will also be recorded in the underlying database.

After all matches finish, Tournament_house_profit() class will record the house profit and tournament year. It then communicates with the past_house_profit() class to list past house profit profits in chorological order.

15-When there is no ongoing tournament, the tournament manager can:
a.	View totals for every player in the system as a list sorted by total. From there, the manager can also view a list of the player’s individual prizes by selecting the player from the list.
b.	View the list of past house profits in chronological order and the total.

The tournament_manager() class use players_total() class to calculate and sort totals for every player. It can also view individual player achievement by individual_player_prizes() method.
The tournament_manager() class use past_house_profit() class to view past house profits in chorological order and total.

16-The User Interface (UI) must be intuitive and responsive.

Not considered because it does not affect the design directly.
 
 
