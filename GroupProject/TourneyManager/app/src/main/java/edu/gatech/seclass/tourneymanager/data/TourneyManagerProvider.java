package edu.gatech.seclass.tourneymanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.tourneymanager.model.Deck;
import edu.gatech.seclass.tourneymanager.model.Manager;
import edu.gatech.seclass.tourneymanager.model.Match;
import edu.gatech.seclass.tourneymanager.model.Player;
import edu.gatech.seclass.tourneymanager.data.TourneyManagerContract.*;
import edu.gatech.seclass.tourneymanager.model.Prize;
import edu.gatech.seclass.tourneymanager.model.Status;
import edu.gatech.seclass.tourneymanager.model.Tournament;

/**
 * class used to execute database queries
 */

public class TourneyManagerProvider {

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
        deckValues.put(DeckEntry.COLUMN_DECK_NAME, deck.getDeck_name());
        return insert(DeckEntry.TABLE_NAME, deckValues);
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
        c.moveToFirst();
        return mapToDeck(c);
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
        c.moveToFirst();
        return mapToDeck(c);
    }

    protected Deck mapToDeck(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(DeckEntry._ID);
        int nameIndex = cursor.getColumnIndex(DeckEntry.COLUMN_DECK_NAME);

        Deck deck = new Deck();
        deck.setId(cursor.getInt(idIndex));
        deck.setDeck_name(cursor.getString(nameIndex));
        return deck;
    }

    /**
     * inserts match
     * @param match
     * @return
     */
    public long insertMatch(Match match) {
        // TODO
        return 0;
    }

    /**
     * fetches all the matches for a tournament
     * @param tournament
     * @return
     */
    public List<Match> fetchMatches(Tournament tournament) {
        String tableName = MatchEntry.TABLE_NAME;
        String[] columns = new String[]{
                MatchEntry._ID,
                MatchEntry.COLUMN_TOURNAMENT_ID,
                MatchEntry.COLUMN_STATUS_ID,
                MatchEntry.COLUMN_ROUND,
                MatchEntry.COLUMN_PLAYER_1_USERNAME,
                MatchEntry.COLUMN_PLAYER_2_USERNAME,
                MatchEntry.COLUMN_WINNER_USERNAME
        };
        String selection = MatchEntry.COLUMN_TOURNAMENT_ID + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(tournament.getTournament_id())};
        Cursor c = query(tableName, columns, selection, selectionArgs, null, null, null);
        return mapToMatchList(c, tournament);
    }

    protected List<Match> mapToMatchList(Cursor cursor, Tournament tournament) {
        int idIndex = cursor.getColumnIndex(MatchEntry._ID);
        int statusIdIndex = cursor.getColumnIndex(MatchEntry.COLUMN_STATUS_ID);
        int roundIndex = cursor.getColumnIndex(MatchEntry.COLUMN_ROUND);
        int player1Index = cursor.getColumnIndex(MatchEntry.COLUMN_PLAYER_1_USERNAME);
        int player2Index = cursor.getColumnIndex(MatchEntry.COLUMN_PLAYER_2_USERNAME);
        int winnerIndex = cursor.getColumnIndex(MatchEntry.COLUMN_WINNER_USERNAME);

        List<Match> matchList = new ArrayList<Match>();

        cursor.moveToFirst();
        do {
            Match match = new Match();
            match.setMatch_id(cursor.getInt(idIndex));
            match.setTournament(tournament);
            match.setStatus(fetchStatus(cursor.getInt(statusIdIndex)));
            match.setMatch_round(cursor.getInt(roundIndex));
            match.setPlayer_1(fetchPlayer(cursor.getString(player1Index)));
            match.setPlayer_2(fetchPlayer(cursor.getString(player2Index)));
            match.setWinner(fetchPlayer(cursor.getString(winnerIndex)));

            matchList.add(match);
        }
        while (cursor.moveToNext());

        tournament.setMatchlist(matchList);
        return matchList;
    }

    /**
     * insert player
     * @param player
     * @return
     */
    public String insertPlayer(Player player) {
        ContentValues playerValues = new ContentValues();
        playerValues.put(UserEntry.COLUMN_USERNAME, player.getUsername());
        playerValues.put(UserEntry.COLUMN_NAME, player.getName());
        playerValues.put(UserEntry.COLUMN_PHONE_NUMBER, player.getPhone_number());
        playerValues.put(UserEntry.COLUMN_DECK_ID, player.getDeck().getId());
        playerValues.put(UserEntry.COLUMN_IS_MANAGER, 0);
        insert(UserEntry.TABLE_NAME, playerValues);
        return player.getUsername();
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
        c.moveToFirst();
        return mapToPlayer(c);
    }

    protected Player mapToPlayer(Cursor cursor) {
        int usernameIndex = cursor.getColumnIndex(UserEntry.COLUMN_USERNAME);
        int nameIndex = cursor.getColumnIndex(UserEntry.COLUMN_NAME);
        int phoneNumberIndex = cursor.getColumnIndex(UserEntry.COLUMN_PHONE_NUMBER);
        int deckIdIndex = cursor.getColumnIndex(UserEntry.COLUMN_DECK_ID);

        Player player = new Player();
        player.setUsername(cursor.getString(usernameIndex));
        player.setName(cursor.getString(nameIndex));
        player.setPhone_number(cursor.getString(phoneNumberIndex));

        if (cursor.getString(deckIdIndex) != null) {
            player.setDeck(fetchDeck(cursor.getInt(deckIdIndex)));
        }

        return player;
    }

    /**
     * returns a list of all players
     * @return
     */
    public List<Player> fetchPlayers() {
        // TODO
        return new ArrayList<Player>();
    }

    /**
     * insert prize
     * @param prize
     * @return
     */
    public long insertPrize(Prize prize) {
        // TODO
        return 0;
    }

    /**
     * returns all prizes for a tournament
     * @param tournament
     * @return
     */
    public List<Prize> fetchPrizes(Tournament tournament) {
        // TODO
        return new ArrayList<Prize>();
    }

    /**
     * returns all prizes for a player
     * @param player
     * @return
     */
    public List<Prize> fetchPrizes(Player player) {
        // TODO
        return new ArrayList<Prize>();
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
        c.moveToFirst();
        return mapToStatus(c);
    }

    protected Status mapToStatus(Cursor cursor) {
        int nameIndex = cursor.getColumnIndex(DeckEntry.COLUMN_DECK_NAME);
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
        // TODO
        return 0;
    }

    /**
     * fetch tournament by id
     * @param id
     * @return
     */
    public Tournament fetchTournament(int id) {
        // TODO
        return null;
    }

    /**
     * fetches all tournaments
     * @return
     */
    public List<Tournament> fetchTournaments() {
        // TODO
        return new ArrayList<Tournament>();
    }

    /**
     * validates whether the username, password combination for a manager is correct or not
     * @param manager
     * @param password
     * @return
     */
    public boolean validateManagerAuth(Manager manager, String password) {
        // TODO
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
