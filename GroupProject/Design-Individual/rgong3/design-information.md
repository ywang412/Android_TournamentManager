
1.	The tournament is organized as a single elimination tournament with third place playoff.
	* Single elimination with third place playoff is implemented. The winners of two matches enter into next match. The losers of semi-finals play for the third place. 
2.	The application has two modes: tournament manager and tournament player. You can assume that the mode is selected when the application starts, with no authentication involved.
	* Two classes manager and player are created.
3.	The tournament manager uses the system to (1) add players, (2) run tournaments, and (3) display prizes and profits.
	* The manager can perform the action of adding players, running tournaments, and displaying prizes and profits.
4.	The tournament players use the system to (1) view the match list and (2) view the total player prizes.
	* The players can vies match list and total player prizes.
5.	The app has an underlying database to save persistent information across runs (e.g., players in the system, status of ongoing tournaments).
	* TournamentStatistics is used as the underlying database to save player/match/tournamet information.
6.	A player in the system is characterized by the following information:
a.	Name
b.	Unique alphanumeric username
c.	Numeric phone number
d.	A deck choice, from a list of deck options
	* A player class has four attributes: name, username, phone number, and deck choice.
7.	The tournament manager can add a player to and remove a player from the system.
	* Manager can add or remove a player.
8.	There can only be one ongoing tournament at any given time.
9.	To start a tournament:
a.	The tournament manager will enter the house cut.
b.	The tournament manager will enter the entry price.
c.	The tournament manager will enter all player usernames. For simplicity, let’s assume that there will be either 8 or 16 players in a tournament.
d.	When the tournament manager has entered the above information, the system will display, in addition to the player names, the potential prizes and profit, as calculated in the TourneyCalc app that you developed for Assignment 4. The tournament manager will then be able to double check the information and start the tournament.
	* For each tournament, the manager will enter houseCut, entry price , and all players' usernames.
10.	When there is no ongoing tournament, the player mode will show totals for every player in the system as a list sorted by total.
	* A boolean attribute isOngoing indicates the status of the tournament.
11.	When a tournament is ongoing, the player mode will show a match list. The match list displays a list of players paired with other players representing ongoing matches, matches ready to be played, and results from completed matches.
	* Player list and match list can be viewed by players.
12.	When a tournament is ongoing, the manager mode will also show a match list. In this case, however, the tournament manager will be able to:
a.	Start a match ready to be played by selecting it from the list. The system will then mark the game between the specified players as started.
b.	End an ongoing match and specify a result for it.
c.	End the tournament. If the tournament is ended early, money is refunded.
	* A boolean attributes isStarted indicates the status of a specific match.
13.	After a tournament is completed, prizes for the winning player, the second place player, and the third place player (who wins the third place playoff) will be recorded in the underlying database .  
	* The top 3 winners, first, second, and third place, will be recorded in the database.
14.	After a tournament is completed, the house profit will also be recorded in the underlying database.
	* The profit will be recorded in database.
15.	When there is no ongoing tournament, the tournament manager can:
a.	View totals for every player in the system as a list sorted by total. From there, the manager can also view a list of the player’s individual prizes by selecting the player from the list.
b.	View the list of past house profits in chronological order and the total.
	* Player list and prizes history can be retrieved.
16.	The User Interface (UI) must be intuitive and responsive.
	* Not considered because it does not affect the design directly.