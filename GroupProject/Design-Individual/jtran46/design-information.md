1. The tournament is organized as a single elimination tournament with third place playoff.

Not considered because this will be implemented with the logic of the tournament rather than its design.

2. The application has two modes: tournament manager and tournament player.  You can assume that the mode is selected when the application starts, with no authentication involvedi.

I implemented this by having two separate classes: TournamentPlayer and TournamentManager.  Depending on which mode the application was started in the user will have access to the methods and attributes of that class.

3. The tournament manager uses the system to (1) add players, (2) run tournaments, and (3) display prizes and profits.

The manager has relationships to the Tournament class with the operations: addPlayer, runTournament which will change the tournament's status attribute, and can displayPrizes and displayProfits which will tap into the database Class.

4. The tournament players use the system to (1) view the match list and (2) view the total player prizes.

This requirement is realized by giving players 2 operations: viewMatchList and viewTotalPrizes

5. The app has an underlying database to save persistent information across runs (e.g.,players in the system, status of ongoing tournaments).

This is accomplished by having the Database class keep track of the list of tournaments (tournamentList) and players (playersList).  These are both String arrays.  In order to find the status of ongoing tournaments, the Database pulls the status attribute from each tournament in its tournamentList array.

6. 6. A player in the system is characterized by the following information:
a. Name
b. Unique alphanumeric username
c. Numeric phone number
d. A deck choice, from a list of deck options

Name, username, phoneNumber and deckChoice are added as attributes of the Player class to satisfy this requirement.

7. The tournament manager can add a player to and remove a player from the system.

The manager class has operations of addPlayer and removePlayer in its relationship with the Tournament class.

8.  There can only be one ongoing tournamnent at any given time.

This will be taken care of in the logic of RunTournament operation by the Manager class.  It will check if there are already any tournaments running before allowing a new one to start.

9.  To start a tournament:
a. The tournament manager will enter the house cut.
b. The tournament manager will enter the entry price.
c. The tournament manager will enter all player usernames. For simplicity, let’s
assume that there will be either 8 or 16 players in a tournament.
d. When the tournament manager has entered the above information, the system
will display, in addition to the player names, the potential prizes and profit, as calculated in the TourneyCalc app that you developed for Assignment 4. The
tournament manager will then be able to double check the information and start
the tournament.

Tournaments have the attributes: houseCut, entryPrice, players.  When the Manager executes the runTournament operation, they will need to provide these as parameters to the Tournament class, which will then perform the calculations needed to return the prizes and profits.

10. When there is no ongoing tournament, the player mode will show totals for every player in the system as a list sorted by total

There is a viewTotalPrizes method for the TournamentPlayer class that will be used if the database shows that there are no ongoing tournaments.

11. When a tournament is ongoing, the player mode will show a match list. The match list
displays a list of players paired with other players representing ongoing matches,
matches ready to be played, and results from completed matches.

This is realized through the viewMatchList operation under TournamentPlayer class, which will tap into the database and pull information from the tournamentList which has matchList as an attribute containing all their matches.

12.  When a tournament is ongoing, the manager mode will also show a match list. In this case, however, the tournament manager will be able to:
a. Start a match ready to be played by selecting it from the list. The system will then mark the game between the specified players as started.
b. End an ongoing match and specify a result for it.
c. End the tournament. If the tournament is ended early, money is refunded.

The Manager class can invoke startMatch or endMatch on the Match class, which will affect its status and result.  The Manager class's EndTournament operation will be invoked on a Tournament to handle the money refunding.

13.  After a tournament is completed, prizes for the winning player, the second place player, and the third place player (who wins the third place playoff) will be recorded in the underlying database .

The results of the tournaments will record automatically since the Database keeps track of the list of all past and ongoing tournaments.

14. After a tournament is completed, the house profit will also be recorded in the underlying database.

The Database has a houseProfits attribute that keeps track of all tournament house profits.  It will be added to once a tournament's status has been changed to Completed.

15. When there is no ongoing tournament, the tournament manager can:
a. View totals for every player in the system as a list sorted by total. From there, the
manager can also view a list of the player’s individual prizes by selecting the
player from the list.
b. View the list of past house profits in chronological order and the total.

Since the Database keeps a list of all the past and ongoing tournaments, it is easy to then iterate through that list and view the totals for all players as well as house profits from previous tournaments.

16. The User Interface (UI) must be intuitive and responsive.

This is not considered because it does not affect the design directly.
