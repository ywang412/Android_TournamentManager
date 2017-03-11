package edu.gatech.seclass.tourneymanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.gatech.seclass.tourneymanager.Deck;
import edu.gatech.seclass.tourneymanager.Manager;
import edu.gatech.seclass.tourneymanager.Match;
import edu.gatech.seclass.tourneymanager.Player;
import edu.gatech.seclass.tourneymanager.TournamentResult;
import edu.gatech.seclass.tourneymanager.data.TourneyManagerContract.*;
import edu.gatech.seclass.tourneymanager.Prize;
import edu.gatech.seclass.tourneymanager.Status;
import edu.gatech.seclass.tourneymanager.Tournament;

/**
 * class used to execute database queries
 */

public class TourneyManagerProvider {

    private static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";

    private final Context mContext;
    private final TourneyManagerDbHelper dbHelper;
    private final SQLiteDatabase db;

    /**
     * initiates connection to database.  Must invoke {@link #shutdown} when usage is completed.
     * @param mContext
     */
    public TourneyManagerProvider(Context mContext) {
        this.mContext = mContext;
        dbHelper = new TourneyManagerDbHelper(mContext);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * must invoke this method when usage is complete.  Recommended usage in onDestroy method.
     */
    public void shutdown() {
        db.close();
        dbHelper.close();
    }

    /**
     * insert new deck
     * @param deck
     * @return
     */
    public long insertDeck(Deck deck) {
        ContentValues deckValues = new ContentValues();
        deckValues.put(DeckEntry.COLUMN_DECK_NAME, deck.getName());
        return insert(DeckEntry.TABLE_NAME, deckValues);
    }

    /**
     * fetch all decks
     * @return
     */
    public Deck fetchDecks() {
        String tableName = DeckEntry.TABLE_NAME;
        String[] columns = new String[]{
                DeckEntry._ID,
                DeckEntry.COLUMN_DECK_NAME,
        };
        Cursor c = query(tableName, columns, null, null, null, null, null);
        return c.moveToFirst() ? mapToDeck(c) : null;
    }

    /**
     * fetch deck by the name
     * @param deckName
     * @return
     */
    public Deck fetchDeck(String deckName) {
        String tableName = DeckEntry.TABLE_NAME;
        String[] columns = new String[]{
                DeckEntry._ID,
                DeckEntry.COLUMN_DECK_NAME,
        };
        String selection = DeckEntry.COLUMN_DECK_NAME + " = ?";
        String[] selectionArgs = new String[]{deckName};
        Cursor c = query(tableName, columns, selection, selectionArgs, null, null, null);
        return c.moveToFirst() ? mapToDeck(c) : null;
    }

    /**
     * fetch deck by the id
     * @param id
     * @return
     */
    public Deck fetchDeck(int id) {
        String tableName = DeckEntry.TABLE_NAME;
        String[] columns = new String[]{
                DeckEntry._ID,
                DeckEntry.COLUMN_DECK_NAME,
        };
        String selection = DeckEntry._ID + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(id)};
        Cursor c = query(tableName, columns, selection, selectionArgs, null, null, null);
        return c.moveToFirst() ? mapToDeck(c) : null;
    }

    protected Deck mapToDeck(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(DeckEntry._ID);
        int nameIndex = cursor.getColumnIndex(DeckEntry.COLUMN_DECK_NAME);

        Deck deck = new Deck();
        deck.setId(cursor.getInt(idIndex));
        deck.setName(cursor.getString(nameIndex));
        return deck;
    }

    /**
     * inserts match
     * @param match
     * @return
     */
    public long insertMatch(Match match) {
        ContentValues matchValues = new ContentValues();
        matchValues.put(MatchEntry.COLUMN_TOURNAMENT_ID, match.getTournament().getTournamentId());
        matchValues.put(MatchEntry.COLUMN_MATCH_NUMBER, match.getMatchNumber());
        matchValues.put(MatchEntry.COLUMN_STATUS_ID, match.getStatus().statusId);
        matchValues.put(MatchEntry.COLUMN_ROUND, match.getMatchRound());
        if (match.getPlayer1() != null) {
            matchValues.put(MatchEntry.COLUMN_PLAYER_1_USERNAME, match.getPlayer1().getUsername());
        }
        if (match.getPlayer2() != null) {
            matchValues.put(MatchEntry.COLUMN_PLAYER_2_USERNAME, match.getPlayer2().getUsername());
        }
        if (match.getWinner() != null) {
            matchValues.put(MatchEntry.COLUMN_WINNER_USERNAME, match.getWinner().getUsername());
        }
        matchValues.put(MatchEntry.COLUMN_NEXT_MATCH_ID, match.getNextMatch());
        return insert(MatchEntry.TABLE_NAME, matchValues);
    }

    /**
     * updates match row by matchId
     * @param match
     * @return
     */
    public long updateMatch(Match match) {
        ContentValues matchValues = new ContentValues();
        matchValues.put(MatchEntry.COLUMN_TOURNAMENT_ID, match.getTournament().getTournamentId());
        matchValues.put(MatchEntry.COLUMN_MATCH_NUMBER, match.getMatchNumber());
        matchValues.put(MatchEntry.COLUMN_STATUS_ID, match.getStatus().statusId);
        matchValues.put(MatchEntry.COLUMN_ROUND, match.getMatchRound());
        if (match.getPlayer1() != null) {
            matchValues.put(MatchEntry.COLUMN_PLAYER_1_USERNAME, match.getPlayer1().getUsername());
        }
        if (match.getPlayer2() != null) {
            matchValues.put(MatchEntry.COLUMN_PLAYER_2_USERNAME, match.getPlayer2().getUsername());
        }
        if (match.getWinner() != null) {
            matchValues.put(MatchEntry.COLUMN_WINNER_USERNAME, match.getWinner().getUsername());
        }
        matchValues.put(MatchEntry.COLUMN_NEXT_MATCH_ID, match.getNextMatch());

        return update(MatchEntry.TABLE_NAME, matchValues, MatchEntry._ID + " = ?", new String[]{String.valueOf(match.getMatchId())});
    }

    /**
     * fetches the match by its id
     * @param matchId
     * @return
     */
    public Match fetchMatch(int matchId) {
        String tableName = MatchEntry.TABLE_NAME;
        String[] columns = new String[]{
                MatchEntry._ID,
                MatchEntry.COLUMN_TOURNAMENT_ID,
                MatchEntry.COLUMN_MATCH_NUMBER,
                MatchEntry.COLUMN_STATUS_ID,
                MatchEntry.COLUMN_ROUND,
                MatchEntry.COLUMN_PLAYER_1_USERNAME,
                MatchEntry.COLUMN_PLAYER_2_USERNAME,
                MatchEntry.COLUMN_WINNER_USERNAME,
                MatchEntry.COLUMN_NEXT_MATCH_ID
        };
        String selection = MatchEntry._ID + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(matchId)};
        Cursor c = query(tableName, columns, selection, selectionArgs, null, null, null);

        ArrayList<Match> matchList = new ArrayList<Match>();
        return c.moveToFirst() ? mapToMatch(c) : null;
    }

    protected Match mapToMatch(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(MatchEntry._ID);
        int tournamentIdIndex = cursor.getColumnIndex(MatchEntry.COLUMN_TOURNAMENT_ID);
        int matchNumberIndex = cursor.getColumnIndex(MatchEntry.COLUMN_MATCH_NUMBER);
        int statusIdIndex = cursor.getColumnIndex(MatchEntry.COLUMN_STATUS_ID);
        int roundIndex = cursor.getColumnIndex(MatchEntry.COLUMN_ROUND);
        int player1Index = cursor.getColumnIndex(MatchEntry.COLUMN_PLAYER_1_USERNAME);
        int player2Index = cursor.getColumnIndex(MatchEntry.COLUMN_PLAYER_2_USERNAME);
        int winnerIndex = cursor.getColumnIndex(MatchEntry.COLUMN_WINNER_USERNAME);
        int nextMatchIndex = cursor.getColumnIndex(MatchEntry.COLUMN_NEXT_MATCH_ID);

        Match match = new Match();
        match.setMatchId(cursor.getInt(idIndex));
        match.setMatchNumber(cursor.getInt(matchNumberIndex));
        match.setTournament(fetchTournament(cursor.getInt(tournamentIdIndex)));
        match.setStatus(fetchStatus(cursor.getInt(statusIdIndex)));
        match.setMatchRound(cursor.getInt(roundIndex));
        match.setPlayer1(fetchPlayer(cursor.getString(player1Index)));
        match.setPlayer2(fetchPlayer(cursor.getString(player2Index)));
        match.setWinner(fetchPlayer(cursor.getString(winnerIndex)));
        match.setNextMatch(cursor.getInt(nextMatchIndex));

        return match;
    }

    /**
     * fetches all the matches for a tournament
     * @param tournament
     * @return
     */
    public ArrayList<Match> fetchMatches(Tournament tournament) {
        String tableName = MatchEntry.TABLE_NAME;
        String[] columns = new String[]{
                MatchEntry._ID,
                MatchEntry.COLUMN_TOURNAMENT_ID,
                MatchEntry.COLUMN_MATCH_NUMBER,
                MatchEntry.COLUMN_STATUS_ID,
                MatchEntry.COLUMN_ROUND,
                MatchEntry.COLUMN_PLAYER_1_USERNAME,
                MatchEntry.COLUMN_PLAYER_2_USERNAME,
                MatchEntry.COLUMN_WINNER_USERNAME,
                MatchEntry.COLUMN_NEXT_MATCH_ID
        };
        String selection = MatchEntry.COLUMN_TOURNAMENT_ID + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(tournament.getTournamentId())};
        Cursor c = query(tableName, columns, selection, selectionArgs, null, null, null);

        ArrayList<Match> matchList = new ArrayList<Match>();
        if (c.moveToFirst()) {
            do {
                matchList.add(mapToMatch(c, tournament));
            }
            while (c.moveToNext());
        }
        tournament.setMatchlist(matchList);
        return matchList;
    }

    protected Match mapToMatch(Cursor cursor, Tournament tournament) {
        int idIndex = cursor.getColumnIndex(MatchEntry._ID);
        int matchNumberIndex = cursor.getColumnIndex(MatchEntry.COLUMN_MATCH_NUMBER);
        int statusIdIndex = cursor.getColumnIndex(MatchEntry.COLUMN_STATUS_ID);
        int roundIndex = cursor.getColumnIndex(MatchEntry.COLUMN_ROUND);
        int player1Index = cursor.getColumnIndex(MatchEntry.COLUMN_PLAYER_1_USERNAME);
        int player2Index = cursor.getColumnIndex(MatchEntry.COLUMN_PLAYER_2_USERNAME);
        int winnerIndex = cursor.getColumnIndex(MatchEntry.COLUMN_WINNER_USERNAME);
        int nextMatchIndex = cursor.getColumnIndex(MatchEntry.COLUMN_NEXT_MATCH_ID);

        Match match = new Match();
        match.setMatchId(cursor.getInt(idIndex));
        match.setMatchNumber(cursor.getInt(matchNumberIndex));
        match.setTournament(tournament);
        match.setStatus(fetchStatus(cursor.getInt(statusIdIndex)));
        match.setMatchRound(cursor.getInt(roundIndex));
        if (cursor.getString(player1Index) != null) {
            match.setPlayer1(fetchPlayer(cursor.getString(player1Index)));
        }
        if (cursor.getString(player2Index) != null) {
            match.setPlayer2(fetchPlayer(cursor.getString(player2Index)));
        }
        if (cursor.getString(winnerIndex) != null) {
            match.setWinner(fetchPlayer(cursor.getString(winnerIndex)));
        }
        match.setNextMatch(cursor.getInt(nextMatchIndex));

        return match;
    }

    /**
     * insert player
     * @param player
     * @return
     */
    public long insertPlayer(Player player) {
        ContentValues playerValues = new ContentValues();
        playerValues.put(UserEntry.COLUMN_USERNAME, player.getUsername());
        playerValues.put(UserEntry.COLUMN_NAME, player.getName());
        playerValues.put(UserEntry.COLUMN_PHONE_NUMBER, player.getPhoneNumber());
        if (player.getDeck() != null) {
            playerValues.put(UserEntry.COLUMN_DECK_ID, fetchDeck(player.getDeck().getName()).getId());
        }
        playerValues.put(UserEntry.COLUMN_IS_MANAGER, 0);
        return insert(UserEntry.TABLE_NAME, playerValues);
    }

    /**
     * updates player entry in db by the player's username
     * @param player
     * @return
     */
    public long updatePlayer(Player player) {
        ContentValues playerValues = new ContentValues();
        playerValues.put(UserEntry.COLUMN_USERNAME, player.getUsername());
        playerValues.put(UserEntry.COLUMN_NAME, player.getName());
        playerValues.put(UserEntry.COLUMN_PHONE_NUMBER, player.getPhoneNumber());
        playerValues.put(UserEntry.COLUMN_DECK_ID, fetchDeck(player.getDeck().getName()).getId());
        playerValues.put(UserEntry.COLUMN_IS_MANAGER, 0);
        return update(UserEntry.TABLE_NAME, playerValues, UserEntry.COLUMN_USERNAME + " = ?", new String[]{player.getUsername()});
    }

    public void deletePlayer(Player player){
        delete(TournamentPlayerLinkEntry.TABLE_NAME, TournamentPlayerLinkEntry.COLUMN_PLAYER_USERNAME + " = ?", new String[]{player.getUsername()});

        ContentValues playerValues = new ContentValues();
        playerValues.put(UserEntry.COLUMN_USERNAME, "deleted" + UUID.randomUUID().toString());
        playerValues.put(UserEntry.COLUMN_NAME, "deleted");
        playerValues.putNull(UserEntry.COLUMN_PHONE_NUMBER);
        update(UserEntry.TABLE_NAME, playerValues, UserEntry.COLUMN_USERNAME + " = ?", new String[]{player.getUsername()});
    }

    /**
     * fetch player by username
     * @param username
     * @return
     */
    public Player fetchPlayer(String username) {
        String tableName = UserEntry.TABLE_NAME;
        String[] columns = new String[]{
                UserEntry.COLUMN_USERNAME,
                UserEntry.COLUMN_NAME,
                UserEntry.COLUMN_PHONE_NUMBER,
                UserEntry.COLUMN_DECK_ID
        };
        String selection = UserEntry.COLUMN_USERNAME + " = ?";
        String[] selectionArgs = new String[]{username};
        Cursor c = query(tableName, columns, selection, selectionArgs, null, null, null);
        return c.moveToFirst() ? mapToPlayer(c) : null;
    }

    protected Player mapToPlayer(Cursor cursor) {
        int usernameIndex = cursor.getColumnIndex(UserEntry.COLUMN_USERNAME);
        int nameIndex = cursor.getColumnIndex(UserEntry.COLUMN_NAME);
        int phoneNumberIndex = cursor.getColumnIndex(UserEntry.COLUMN_PHONE_NUMBER);
        int deckIdIndex = cursor.getColumnIndex(UserEntry.COLUMN_DECK_ID);

        Player player = new Player();
        player.setUsername(cursor.getString(usernameIndex));
        player.setName(cursor.getString(nameIndex));
        player.setPhoneNumber(cursor.getString(phoneNumberIndex));

        if (cursor.getString(deckIdIndex) != null) {
            player.setDeck(fetchDeck(cursor.getInt(deckIdIndex)));
        }

        return player;
    }

    /**
     * returns a list of all active players
     * @return
     */
    public ArrayList<Player> fetchPlayers() {
        String tableName = UserEntry.TABLE_NAME;
        String[] columns = new String[]{
                UserEntry.COLUMN_USERNAME,
                UserEntry.COLUMN_NAME,
                UserEntry.COLUMN_PHONE_NUMBER,
                UserEntry.COLUMN_DECK_ID
        };
        String selection = UserEntry.COLUMN_IS_MANAGER + " = 0 AND "
                + "ifnull(length(" + UserEntry.COLUMN_PHONE_NUMBER + "), 0) != 0";
        Cursor c = query(tableName, columns, selection, null, null, null, null);
        ArrayList<Player> players = new ArrayList<Player>();
        if (c.moveToFirst()) {
            do {
                players.add(mapToPlayer(c));
            }
            while (c.moveToNext());
        }
        return players;
    }

    /**
     * returns a list of all players in a tournament
     * @return
     */
    public ArrayList<Player> fetchPlayers(Tournament tournament) {
        String tableName = TournamentPlayerLinkEntry.TABLE_NAME;
        String[] columns = new String[]{
                TournamentPlayerLinkEntry.COLUMN_TOURNAMENT_ID,
                TournamentPlayerLinkEntry.COLUMN_PLAYER_USERNAME,
        };
        String selection = TournamentPlayerLinkEntry.COLUMN_TOURNAMENT_ID + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(tournament.getTournamentId())};
        Cursor c = query(tableName, columns, selection, selectionArgs, null, null, null);

        ArrayList<Player> players = new ArrayList<>();
        int playerUserNameIndex = c.getColumnIndex(TournamentPlayerLinkEntry.COLUMN_PLAYER_USERNAME);
        if (c.moveToFirst()) {
            do {
                players.add(fetchPlayer(c.getString(playerUserNameIndex)));
            }
            while (c.moveToNext());
        }
        return players;
    }

    /**
     * insert prize
     * @param prize
     * @return
     */
    public long insertPrize(Prize prize) {
        ContentValues prizeValues = new ContentValues();
        prizeValues.put(PrizeEntry.COLUMN_PLACE, prize.getPlace());
        prizeValues.put(PrizeEntry.COLUMN_PLAYER_USERNAME, prize.getPlayer().getUsername());
        prizeValues.put(PrizeEntry.COLUMN_PRIZE_AMOUNT, prize.getPrizeAmount());
        prizeValues.put(PrizeEntry.COLUMN_TOURNAMENT_ID, prize.getTournament().getTournamentId());
        return insert(PrizeEntry.TABLE_NAME, prizeValues);
    }

    /**
     * updates prize entry in db by the tournament id and the place
     * @param prize
     * @return
     */
    public long updatePrize(Prize prize) {
        ContentValues prizeValues = new ContentValues();
        prizeValues.put(PrizeEntry.COLUMN_PLACE, prize.getPlace());
        prizeValues.put(PrizeEntry.COLUMN_PLAYER_USERNAME, prize.getPlayer().getUsername());
        prizeValues.put(PrizeEntry.COLUMN_PRIZE_AMOUNT, prize.getPrizeAmount());
        prizeValues.put(PrizeEntry.COLUMN_TOURNAMENT_ID, prize.getTournament().getTournamentId());
        String whereClause = PrizeEntry.COLUMN_TOURNAMENT_ID + " = ? AND"
                + PrizeEntry.COLUMN_PLACE + " = ?";
        String[] whereArgs = new String[]{
                String.valueOf(prize.getTournament().getTournamentId()),
                String.valueOf(prize.getPlace())
        };
        return update(PrizeEntry.TABLE_NAME, prizeValues, whereClause, whereArgs);
    }

    /**
     * returns all prizes for a tournament
     * @param tournament
     * @return
     */
    public ArrayList<Prize> fetchPrizes(Tournament tournament) {
        String tableName = PrizeEntry.TABLE_NAME;
        String[] columns = new String[]{
                PrizeEntry.COLUMN_TOURNAMENT_ID,
                PrizeEntry.COLUMN_PLAYER_USERNAME,
                PrizeEntry.COLUMN_PLACE,
                PrizeEntry.COLUMN_PRIZE_AMOUNT
        };
        String selection = PrizeEntry.COLUMN_TOURNAMENT_ID + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(tournament.getTournamentId())};
        Cursor c = query(tableName, columns, selection, selectionArgs, null, null, null);

        ArrayList<Prize> prizes = new ArrayList<Prize>();

        if (c.moveToFirst()) {
            do {
                prizes.add(mapToPrize(c, tournament));
            }
            while (c.moveToNext());
        }
        return prizes;
    }

    protected Prize mapToPrize(Cursor cursor, Tournament tournament) {
        int playerUsernameIndex = cursor.getColumnIndex(PrizeEntry.COLUMN_PLAYER_USERNAME);
        int placeIndex = cursor.getColumnIndex(PrizeEntry.COLUMN_PLACE);
        int prizeAmountIndex = cursor.getColumnIndex(PrizeEntry.COLUMN_PRIZE_AMOUNT);

        Prize prize = new Prize();
        prize.setTournament(tournament);
        prize.setPlayer(fetchPlayer(cursor.getString(playerUsernameIndex)));
        prize.setPlace(cursor.getInt(placeIndex));
        prize.setPrizeAmount(cursor.getInt(prizeAmountIndex));

        return prize;
    }

    /**
     * returns all prizes for a player
     * @param player
     * @return
     */
    public ArrayList<Prize> fetchPrizes(Player player) {
        String tableName = PrizeEntry.TABLE_NAME;
        String[] columns = new String[]{
                PrizeEntry.COLUMN_TOURNAMENT_ID,
                PrizeEntry.COLUMN_PLAYER_USERNAME,
                PrizeEntry.COLUMN_PLACE,
                PrizeEntry.COLUMN_PRIZE_AMOUNT
        };
        String selection = PrizeEntry.COLUMN_PLAYER_USERNAME + " = ?";
        String[] selectionArgs = new String[]{player.getUsername()};
        Cursor c = query(tableName, columns, selection, selectionArgs, null, null, null);

        ArrayList<Prize> prizes = new ArrayList<Prize>();
        if (c.moveToFirst()) {
            do {
                prizes.add(mapToPrize(c, player));
            }
            while (c.moveToNext());
        }
        return prizes;
    }

    protected Prize mapToPrize(Cursor cursor, Player player) {
        int tournamentIdIndex = cursor.getColumnIndex(PrizeEntry.COLUMN_TOURNAMENT_ID);
        int placeIndex = cursor.getColumnIndex(PrizeEntry.COLUMN_PLACE);
        int prizeAmountIndex = cursor.getColumnIndex(PrizeEntry.COLUMN_PRIZE_AMOUNT);

        Prize prize = new Prize();
        prize.setTournament(fetchTournament(cursor.getInt(tournamentIdIndex)));
        prize.setPlayer(player);
        prize.setPlace(cursor.getInt(placeIndex));
        prize.setPrizeAmount(cursor.getInt(prizeAmountIndex));

        return prize;
    }

    protected Status fetchStatus(int id) {
        String tableName = StatusEntry.TABLE_NAME;
        String[] columns = new String[]{
                StatusEntry._ID,
                StatusEntry.COLUMN_STATUS_NAME,
        };
        String selection = DeckEntry._ID + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(id)};
        Cursor c = query(tableName, columns, selection, selectionArgs, null, null, null);
        return c.moveToFirst() ? mapToStatus(c) : null;
    }

    protected Status mapToStatus(Cursor cursor) {
        int nameIndex = cursor.getColumnIndex(StatusEntry.COLUMN_STATUS_NAME);
        for (Status status : Status.values()) {
            if (status.name().equals(cursor.getString(nameIndex))) {
                return status;
            }
        }
        return null;
    }

    /**
     * insert tournament
     * @param tournament
     * @return
     */
    public long insertTournament(Tournament tournament) {
        ContentValues tournamentValue = new ContentValues();
        tournamentValue.put(TournamentEntry.COLUMN_TOURNAMENT_NAME, tournament.getTournamentName());
        tournamentValue.put(TournamentEntry.COLUMN_ENTRY_PRICE, tournament.getEntryPrice());
        tournamentValue.put(TournamentEntry.COLUMN_HOUSE_CUT, tournament.getHouseCut());
        tournamentValue.put(TournamentEntry.COLUMN_HOUSE_PROFIT, tournament.getHouseProfit());
        tournamentValue.put(TournamentEntry.COLUMN_STATUS_ID, tournament.getStatus().statusId);
        if (tournament.getStartDateTime() != null) {
            tournamentValue.put(TournamentEntry.COLUMN_START_DATE, String.valueOf(DateFormat.format(DATE_FORMAT, tournament.getStartDateTime())));
        }
        if (tournament.getEndDateTime() != null) {
            tournamentValue.put(TournamentEntry.COLUMN_END_DATE, String.valueOf(DateFormat.format(DATE_FORMAT, tournament.getEndDateTime())));
        }

        for (Player player : tournament.getPlayerslist()) {
            addPlayerToTournament(tournament, player);
        }

        for (Match match : tournament.getMatchlist()) {
            insertMatch(match);
        }

        if (tournament.getResult() != null) {
            for (Prize prize : tournament.getResult().getPrizes()) {
                insertPrize(prize);
            }
        }

        return insert(TournamentEntry.TABLE_NAME, tournamentValue);
    }

    /**
     * update tournament based on tournament id
     * @param tournament
     * @return
     */
    public long updateTournament(Tournament tournament) {
        ContentValues tournamentValue = new ContentValues();
        tournamentValue.put(TournamentEntry.COLUMN_TOURNAMENT_NAME, tournament.getTournamentName());
        tournamentValue.put(TournamentEntry.COLUMN_ENTRY_PRICE, tournament.getEntryPrice());
        tournamentValue.put(TournamentEntry.COLUMN_HOUSE_CUT, tournament.getHouseCut());
        tournamentValue.put(TournamentEntry.COLUMN_HOUSE_PROFIT, tournament.getHouseProfit());
        tournamentValue.put(TournamentEntry.COLUMN_STATUS_ID, tournament.getStatus().statusId);
        if (tournament.getStartDateTime() != null) {
            tournamentValue.put(TournamentEntry.COLUMN_START_DATE, String.valueOf(DateFormat.format(DATE_FORMAT, tournament.getStartDateTime())));
        }
        if (tournament.getEndDateTime() != null) {
            tournamentValue.put(TournamentEntry.COLUMN_END_DATE, String.valueOf(DateFormat.format(DATE_FORMAT, tournament.getEndDateTime())));
        }

        updatePlayersInTournament(tournament);

        for (Match match : tournament.getMatchlist()) {
            long result = updateMatch(match);
            if (result <= 0) {
                insertMatch(match);
            }
        }

        if (tournament.getResult() != null) {
            for (Prize prize : tournament.getResult().getPrizes()) {
                insertPrize(prize);
            }
        }

        return update(TournamentEntry.TABLE_NAME, tournamentValue, TournamentEntry._ID + " = ? ", new String[]{String.valueOf(tournament.getTournamentId())});
    }


    public void updatePlayersInTournament(Tournament tournament) {
        List<Player> playersToRemove = new ArrayList<>();
        playersToRemove.addAll(fetchPlayers(tournament));
        List<Player> playersToAdd = new ArrayList<>();
        playersToAdd.addAll(tournament.getPlayerslist());

        List<Player> newPlayersInTournament = new ArrayList<>();
        newPlayersInTournament.addAll(playersToAdd);
        List<Player> playersInTournament = new ArrayList<>();
        playersInTournament.addAll(playersToRemove);

        for (Player newPlayer : newPlayersInTournament) {
            for (Player oldPlayer : playersInTournament) {
                if (newPlayer.getUsername().equals(oldPlayer.getUsername())) {
                    playersToRemove.remove(oldPlayer);
                    playersToAdd.remove(newPlayer);
                    break;
                }
            }
        }

        for (Player player : playersToAdd) {
            addPlayerToTournament(tournament, player);
        }

        for (Player player : playersToRemove) {
            removePlayerFromTournament(tournament, player);
        }
    }

    public long addPlayerToTournament(Tournament tournament, Player player) {
        ContentValues tournamentPlayerLinkValue = new ContentValues();
        tournamentPlayerLinkValue.put(TournamentPlayerLinkEntry.COLUMN_PLAYER_USERNAME, player.getUsername());
        tournamentPlayerLinkValue.put(TournamentPlayerLinkEntry.COLUMN_TOURNAMENT_ID, tournament.getTournamentId());
        return insert(TournamentPlayerLinkEntry.TABLE_NAME, tournamentPlayerLinkValue);
    }

    public long removePlayerFromTournament(Tournament tournament, Player player) {
        String whereClause = TournamentPlayerLinkEntry.COLUMN_TOURNAMENT_ID + " = ? AND "
                + TournamentPlayerLinkEntry.COLUMN_PLAYER_USERNAME + " = ?";
        String[] whereArgs = new String[] {
                String.valueOf(tournament.getTournamentId()),
                player.getUsername()
        };
        return delete(TournamentPlayerLinkEntry.TABLE_NAME, whereClause, whereArgs);
    }

    /**
     * fetches the tournament whose status is not {@link Status#Completed} or {@link Status#Cancelled).  If there are no such tournament, returns null
     * @return
     */
    public Tournament fetchCurrentTournament() {
        String tableName = TournamentEntry.TABLE_NAME;
        String[] columns = new String[]{
                TournamentEntry._ID,
                TournamentEntry.COLUMN_TOURNAMENT_NAME,
                TournamentEntry.COLUMN_STATUS_ID,
                TournamentEntry.COLUMN_ENTRY_PRICE,
                TournamentEntry.COLUMN_HOUSE_CUT,
                TournamentEntry.COLUMN_HOUSE_PROFIT,
                TournamentEntry.COLUMN_START_DATE,
                TournamentEntry.COLUMN_END_DATE
        };
        String selection = TournamentEntry.COLUMN_STATUS_ID + " != ? AND "
                + TournamentEntry.COLUMN_STATUS_ID + " != ?";
        String[] selectionArgs = new String[]{String.valueOf(Status.Cancelled.statusId), String.valueOf(Status.Completed.statusId)};
        Cursor c = query(tableName, columns, selection, selectionArgs, null, null, null);
        return c.moveToFirst() ? mapToTournament(c) : null;
    }

    protected Tournament mapToTournament(Cursor cursor) {
        int tournamentIdIndex = cursor.getColumnIndex(TournamentEntry._ID);
        int tournamentNameIndex = cursor.getColumnIndex(TournamentEntry.COLUMN_TOURNAMENT_NAME);
        int statusIdIndex = cursor.getColumnIndex(TournamentEntry.COLUMN_STATUS_ID);
        int entryPriceIndex = cursor.getColumnIndex(TournamentEntry.COLUMN_ENTRY_PRICE);
        int houseCutIndex = cursor.getColumnIndex(TournamentEntry.COLUMN_HOUSE_CUT);
        int houseProfitIndex = cursor.getColumnIndex(TournamentEntry.COLUMN_HOUSE_PROFIT);
        int startDateIndex = cursor.getColumnIndex(TournamentEntry.COLUMN_START_DATE);
        int endDateIndex = cursor.getColumnIndex(TournamentEntry.COLUMN_END_DATE);

        Tournament tournament = new Tournament(
                cursor.getString(tournamentNameIndex),
                cursor.getInt(entryPriceIndex),
                cursor.getInt(houseCutIndex)

        );
        tournament.setTournamentId(cursor.getInt(tournamentIdIndex));
        tournament.setStatus(Status.getStatus(cursor.getInt(statusIdIndex)));
        tournament.setHouseProfit(cursor.getInt(houseProfitIndex));

        tournament.setPlayerslist(fetchPlayers(tournament));
        tournament.setMatchlist(fetchMatches(tournament));

        TournamentResult result = new TournamentResult();
        result.setTournament(tournament);
        result.setPrizes(fetchPrizes(tournament));
        tournament.setResult(result);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            if (cursor.getString(startDateIndex) != null) {
                tournament.setStartDateTime(sdf.parse(cursor.getString(startDateIndex)));
            }
            if (cursor.getString(endDateIndex) != null) {
                tournament.setEndDateTime((sdf.parse(cursor.getString(endDateIndex))));
            }
        }
        catch (Exception e) {
            // ignore excpetion in date parsing
        }

        return tournament;
    }

    /**
     * fetch tournament by id
     * @param id
     * @return
     */
    public Tournament fetchTournament(int id) {
        String tableName = TournamentEntry.TABLE_NAME;
        String[] columns = new String[]{
                TournamentEntry._ID,
                TournamentEntry.COLUMN_TOURNAMENT_NAME,
                TournamentEntry.COLUMN_STATUS_ID,
                TournamentEntry.COLUMN_ENTRY_PRICE,
                TournamentEntry.COLUMN_HOUSE_CUT,
                TournamentEntry.COLUMN_HOUSE_PROFIT,
                TournamentEntry.COLUMN_START_DATE,
                TournamentEntry.COLUMN_END_DATE
        };
        String selection = TournamentEntry._ID + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(id)};
        Cursor c = query(tableName, columns, selection, selectionArgs, null, null, null);
        return c.moveToFirst() ? mapToTournament(c) : null;
    }

    /**
     * fetches all tournaments
     * @return
     */
    public ArrayList<Tournament> fetchTournaments() {
        String tableName = TournamentEntry.TABLE_NAME;
        String[] columns = new String[]{
                TournamentEntry._ID,
                TournamentEntry.COLUMN_TOURNAMENT_NAME,
                TournamentEntry.COLUMN_STATUS_ID,
                TournamentEntry.COLUMN_ENTRY_PRICE,
                TournamentEntry.COLUMN_HOUSE_CUT,
                TournamentEntry.COLUMN_HOUSE_PROFIT,
                TournamentEntry.COLUMN_START_DATE,
                TournamentEntry.COLUMN_END_DATE
        };
        Cursor c = query(tableName, columns, null, null, null, null, null);

        ArrayList<Tournament> tournaments = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                tournaments.add(mapToTournament(c));
            }
            while (c.moveToNext());
        }
        return tournaments;
    }

    /**
     * fetches all tournaments ordered by date
     * @return
     */
    public ArrayList<Tournament> fetchTournamentsOrderByDate() {
        String tableName = TournamentEntry.TABLE_NAME;
        String[] columns = new String[]{
                TournamentEntry._ID,
                TournamentEntry.COLUMN_TOURNAMENT_NAME,
                TournamentEntry.COLUMN_STATUS_ID,
                TournamentEntry.COLUMN_ENTRY_PRICE,
                TournamentEntry.COLUMN_HOUSE_CUT,
                TournamentEntry.COLUMN_HOUSE_PROFIT,
                TournamentEntry.COLUMN_START_DATE,
                TournamentEntry.COLUMN_END_DATE
        };
        String selection = TournamentEntry.COLUMN_STATUS_ID + " = ?";
        String[] selectionArgs = new String[]{"4"};
        Cursor c = query(tableName, columns, selection, selectionArgs, null, null, TournamentEntry.COLUMN_START_DATE + " ASC");

        ArrayList<Tournament> tournaments = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                tournaments.add(mapToTournament(c));
            }
            while (c.moveToNext());
        }
        return tournaments;
    }

    /**
     * fetches all tournaments ordered by date
     * @return
     */
    public ArrayList<Tournament> fetchTournamentsOrderByProfit() {
        String tableName = TournamentEntry.TABLE_NAME;
        String[] columns = new String[]{
                TournamentEntry._ID,
                TournamentEntry.COLUMN_TOURNAMENT_NAME,
                TournamentEntry.COLUMN_STATUS_ID,
                TournamentEntry.COLUMN_ENTRY_PRICE,
                TournamentEntry.COLUMN_HOUSE_CUT,
                TournamentEntry.COLUMN_HOUSE_PROFIT,
                TournamentEntry.COLUMN_START_DATE,
                TournamentEntry.COLUMN_END_DATE
        };
        String selection = TournamentEntry.COLUMN_STATUS_ID + " = ?";
        String[] selectionArgs = new String[]{"4"};
        Cursor c = query(tableName, columns, selection, selectionArgs, null, null, TournamentEntry.COLUMN_HOUSE_PROFIT + " DESC");

        ArrayList<Tournament> tournaments = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                tournaments.add(mapToTournament(c));
            }
            while (c.moveToNext());
        }
        return tournaments;
    }

    /**
     * returns a list of all tournaments a player participated in
     * @return
     */
    public ArrayList<Tournament> fetchTournaments(Player player) {
        String tableName = TournamentPlayerLinkEntry.TABLE_NAME;
        String[] columns = new String[]{
                TournamentPlayerLinkEntry.COLUMN_TOURNAMENT_ID,
                TournamentPlayerLinkEntry.COLUMN_PLAYER_USERNAME,
        };
        String selection = TournamentPlayerLinkEntry.COLUMN_PLAYER_USERNAME + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(player.getUsername())};
        Cursor c = query(tableName, columns, selection, null, null, null, null);

        ArrayList<Tournament> tournaments = new ArrayList<>();
        int tournamentIdIndex = c.getColumnIndex(TournamentPlayerLinkEntry.COLUMN_TOURNAMENT_ID);
        if (c.moveToFirst()) {
            do {
                tournaments.add(fetchTournament(c.getInt(tournamentIdIndex)));
            }
            while (c.moveToNext());
        }
        return tournaments;
    }

    public int fetchTotalProfit() {
        String sql = "SELECT SUM(" + TournamentEntry.COLUMN_HOUSE_PROFIT + ") FROM " + TournamentEntry.TABLE_NAME + " WHERE " + TournamentEntry.COLUMN_STATUS_ID + " = ?";
        String[] selectionArgs = new String[]{"4"};
        Cursor c = db.rawQuery(sql, selectionArgs);
        return c.moveToFirst() ? c.getInt(0) : 0;
    }

    /**
     * validates whether the username, password combination for a manager is correct or not
     * @param manager
     * @param password
     * @return
     */
    public boolean validateManagerAuth(Manager manager, String password) {
        String tableName = UserEntry.TABLE_NAME;
        String[] columns = new String[]{
                UserEntry.COLUMN_USERNAME,
                UserEntry.COLUMN_PASSWORD,
                UserEntry.COLUMN_IS_MANAGER
        };
        String selection = UserEntry.COLUMN_USERNAME + " = ? AND "
                + UserEntry.COLUMN_IS_MANAGER + " = ?";
        String[] selectionArgs = new String[]{manager.getUsername(), "1"};
        Cursor c = query(tableName, columns, selection, selectionArgs, null, null, null);
        if (c.moveToFirst()) {
            int passwordIndex = c.getColumnIndex(UserEntry.COLUMN_PASSWORD);
            return c.getString(passwordIndex).equals(password);
        }
        return false;
    }

    /**
     * directly invokes
     * {@link SQLiteDatabase#query(String, String[], String, String[], String, String, String)}
     * @param table
     * @param columns
     * @param selection
     * @param selectionArgs
     * @param groupBy
     * @param having
     * @param orderBy
     * @return
     */
    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    /**
     * directly invokes {@link SQLiteDatabase#insert(String, String, ContentValues)}
     * @param table
     * @param values
     * @return
     */
    public long insert(String table, ContentValues values) {
        return db.insert(table, null, values);
    }

    /**
     * directly invokes (@link {@link SQLiteDatabase#update(String, ContentValues, String, String[])}
     * @param table
     * @param values
     * @param whereClause
     * @param whereArgs
     * @return
     */
    public long update(String table, ContentValues values, String whereClause, String[] whereArgs) {
        return db.update(table, values, whereClause, whereArgs);
    }

    private long delete(String table, String whereClause, String[] whereArgs) {
        return db.delete(table, whereClause, whereArgs);
    }

    /**
     * directly invokes {@link SQLiteDatabase#rawQuery(String, String[])}
     * @param sql
     * @param selectionArgs
     * @return
     */
    public Cursor rawQuery(String sql, String[] selectionArgs) {
        return db.rawQuery(sql, selectionArgs);
    }

    /**
     * directly invokes (@link {@link SQLiteDatabase#isOpen()}
     * @return
     */
    public boolean isOpen() {
        return db.isOpen();
    }

}
