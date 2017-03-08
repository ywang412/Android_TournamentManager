package edu.gatech.seclass.tourneymanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.gatech.seclass.tourneymanager.data.TourneyManagerContract.*;
import edu.gatech.seclass.tourneymanager.Status;

import static edu.gatech.seclass.tourneymanager.data.TourneyManagerSqlScripts.CREATE_DECK_TABLE;
import static edu.gatech.seclass.tourneymanager.data.TourneyManagerSqlScripts.CREATE_MATCH_TABLE;
import static edu.gatech.seclass.tourneymanager.data.TourneyManagerSqlScripts.CREATE_PRIZE_TABLE;
import static edu.gatech.seclass.tourneymanager.data.TourneyManagerSqlScripts.CREATE_STATUS_TABLE;
import static edu.gatech.seclass.tourneymanager.data.TourneyManagerSqlScripts.CREATE_TOURNAMENT_PLAYER_LINK_TABLE;
import static edu.gatech.seclass.tourneymanager.data.TourneyManagerSqlScripts.CREATE_TOURNAMENT_TABLE;
import static edu.gatech.seclass.tourneymanager.data.TourneyManagerSqlScripts.CREATE_USER_TABLE;

/**
 * Manages the local database for tournament manager
 */

public class TourneyManagerDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;
    private static final String[] DEFAULT_DECK_VALUES = new String[] {
            "Engineer", "Buzz", "Sideways", "Wreck", "T", "RAT"
    };
    private static final String[] MANAGER_AUTH = new String[] {
            "hiteam:team37",
            "testUsername:testPassword"
    };

    static final String DATABASE_NAME = "tourneymanager.db";

    public TourneyManagerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DECK_TABLE);
        db.execSQL(CREATE_MATCH_TABLE);
        db.execSQL(CREATE_PRIZE_TABLE);
        db.execSQL(CREATE_STATUS_TABLE);
        db.execSQL(CREATE_TOURNAMENT_TABLE);
        db.execSQL(CREATE_TOURNAMENT_PLAYER_LINK_TABLE);
        db.execSQL(CREATE_USER_TABLE);

        // insert constant status values
        for (Status status : Status.values()) {
            ContentValues values = new ContentValues();
            values.put(StatusEntry._ID, status.statusId);
            values.put(StatusEntry.COLUMN_STATUS_NAME, status.name());
            db.insert(StatusEntry.TABLE_NAME, null, values);
        }

        // insert given deck values
        for (String deckName : DEFAULT_DECK_VALUES) {
            ContentValues deckValues = new ContentValues();
            deckValues.put(DeckEntry.COLUMN_DECK_NAME, deckName);
            db.insert(DeckEntry.TABLE_NAME, null, deckValues);
        }

        // insert manager information
        for (String auth : MANAGER_AUTH) {
            String[] authArr = auth.split(":");
            ContentValues managerValues = new ContentValues();
            managerValues.put(UserEntry.COLUMN_USERNAME, authArr[0]);
            managerValues.put(UserEntry.COLUMN_PASSWORD, authArr[1]);
            managerValues.put(UserEntry.COLUMN_IS_MANAGER, 1);
            db.insert(UserEntry.TABLE_NAME, null, managerValues);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // currently there's no plan to keep any data when update is necessary.
        db.execSQL("DROP TABLE IF EXISTS " + DeckEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MatchEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PrizeEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + StatusEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TournamentEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TournamentPlayerLinkEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME);
        onCreate(db);
    }
}
