#Information#
1. Tournament format: This information is used to remove the tournament format field from Tournament class
2. Application Modes: The Application class has a logon method which will be used to determine who has logged onto the application.  Then it will determine what actions and information is available to the user.
3. Manager actions:
	a. Adding players:  Managers can use the registerPlayer method in Application class to register new players to the system.  To add them to a tournament, they can use the addPlayer method in Tournament class.
	b. Running tournaments: Managers can use the createTournament method in Application class to create a new tournament.  The Tournament class includes start, end, and cancel methods to manage the tournament's progress.
	c. Display Prizes and Profits: Managers can access prizes and profits using the fetchPrizes and fetchProfits method in Application
4. Player actions: 
	a. View match list: Tournament class includes matches attribute which can be used to obtain the list of matches
	b. View the total player prizes: Player class has fetchPrizesWon method that can be used to obtain the total player prizes
5. Database: Not considered because it does not affect the design directly
6. Player attributes: User class contains the username, name and phone number of each user.  The Player class extends the User class to inherit those attributes and also has the deck attribute.
7. Add/remove player from the system: registerPlayer and deletePlayer methods in Application class can be used by the Manager to add or remove a player from the system.
8. Only one ongoing tournament at a time: Not considered because it does not affect the design directly
9. Starting a tournament:
	a. Tournament class contains the attributes houseCut and entryPrize to allow managers to edit those values once a Tournament class has been instantiated.  These fields can be used to determine the potential prizes and profit.
	b. Manager can add players to the Tournament with the addPlayer method
	c. Player names can be obtained from the Player class that has been added to the playerList
10. Display totals for every player: Application class has fetchPlayers method that returns list of all Players in the system.  Then each Player class has fetchPrizesWon method that can be used to retrieve the totals for each player.
11. Displaying match list: The Tournament class contains the matchList field with all matches related to the Tournament.  The Match class has player1 and player2 fields to represent the participants.  The Match class also has status and winner fields that can be used to display the status or the result of a match.
12. Match actions: Manager actions for a match are defined as methods in Match class.  Available actions include start, end, and cancel.  The cancel method will be used when Tournament is cancelled.
13. Tournament Completion: When a tournament is completed, an instance of TournamentResult class is created.  The TournamentResult class contains the three placement prizes for the tournament and the calculated houseProfit.  Each Prize instance has the Player and Tournament references along with the Player's placement and prizeAmount.  Therefore, the instance of a TournamentResult class contains any headline that a Manager or Player may be interested in for a particular tournament.
14. As specified in 13, the houseProfit is recorded in the TournamentResult class, which should be stored in the database.
15. Manager Actions outside active Tournament:
	a. The totals for every player is addressed by #10.  The detail of a player's individual prize can be obtained the fetchPrizesWon method in the Player class.  This method returns list of Prize class which includes the Player's placement in the tournament and prizeAmount.
	b. To sort profits in chronological order, a dateTime field has been added to the Tournament class.
16. UI: Not considered because it does not affect the design directly