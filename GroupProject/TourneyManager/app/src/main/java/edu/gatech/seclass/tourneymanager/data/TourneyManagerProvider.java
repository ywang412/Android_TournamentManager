package edu.gatech.seclass.tourneymanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import edu.gatech.seclass.tourneymanager.Deck;
import edu.gatech.seclass.tourneymanager.Player;
import edu.gatech.seclass.tourneymanager.data.TourneyManagerContract.*;

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

    public String insertPlayer(Player player) {
        ContentValues playerValues = new ContentValues();
        playerValues.put(UserEntry.COLUMN_USERNAME, player.getUsername());
        playerValues.put(UserEntry.COLUMN_NAME, player.getName());
        playerValues.put(UserEntry.COLUMN_PHONE_NUMBER, player.getPhoneNumber());
        playerValues.put(UserEntry.COLUMN_DECK_ID, player.getDeck().getId());
        insert(UserEntry.TABLE_NAME, playerValues);
        return player.getUsername();
    }

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
        player.setPhoneNumber(cursor.getString(phoneNumberIndex));

        if (cursor.getString(deckIdIndex) != null) {
            player.setDeck(fetchDeck(cursor.getInt(deckIdIndex)));
        }

        return player;
    }

    public long insertDeck(Deck deck) {
        ContentValues deckValues = new ContentValues();
        deckValues.put(DeckEntry.COLUMN_DECK_NAME, deck.getName());
        return insert(DeckEntry.TABLE_NAME, deckValues);
    }

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
        deck.setName(cursor.getString(nameIndex));
        return deck;
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
