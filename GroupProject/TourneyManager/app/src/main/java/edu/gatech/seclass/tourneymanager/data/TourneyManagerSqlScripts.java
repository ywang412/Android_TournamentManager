package edu.gatech.seclass.tourneymanager.data;

import edu.gatech.seclass.tourneymanager.data.TourneyManagerContract.*;

/**
 * Create table scripts for Tourney Manager
 */
public class TourneyManagerSqlScripts {

    static final String CREATE_DECK_TABLE = "CREATE TABLE " + DeckEntry.TABLE_NAME + " (" +
            DeckEntry._ID + " INTEGER PRIMARY KEY," +
            DeckEntry.COLUMN_DECK_NAME + " TEXT UNIQUE NOT NULL " +
            " );";

    static final String CREATE_MATCH_TABLE = "CREATE TABLE " + MatchEntry.TABLE_NAME + " (" +
            MatchEntry._ID + " INTEGER PRIMARY KEY," +
            MatchEntry.COLUMN_ROUND + " INTEGER NOT NULL, " +
            MatchEntry.COLUMN_TOURNAMENT_ID + " INTEGER NOT NULL, " +
            MatchEntry.COLUMN_STATUS_ID + " INTEGER, " +
            MatchEntry.COLUMN_PLAYER_1_USERNAME + " TEXT, " +
            MatchEntry.COLUMN_PLAYER_2_USERNAME + " TEXT, " +
            MatchEntry.COLUMN_WINNER_USERNAME + " TEXT, " +
            MatchEntry.COLUMN_NEXT_MATCH_ID + " INTEGER, " +

            " FOREIGN KEY (" + MatchEntry.COLUMN_STATUS_ID + ") REFERENCES " + StatusEntry.TABLE_NAME + " (" + StatusEntry._ID + "), " +
            " FOREIGN KEY (" + MatchEntry.COLUMN_TOURNAMENT_ID + ") REFERENCES " + TournamentEntry.TABLE_NAME + " (" + TournamentEntry._ID + "), " +
            " FOREIGN KEY (" + MatchEntry.COLUMN_WINNER_USERNAME + ") REFERENCES " + UserEntry.TABLE_NAME + " (" + UserEntry.COLUMN_USERNAME + "), " +
            " FOREIGN KEY (" + MatchEntry.COLUMN_PLAYER_1_USERNAME + ") REFERENCES " + UserEntry.TABLE_NAME + " (" + UserEntry.COLUMN_USERNAME + "), " +
            " FOREIGN KEY (" + MatchEntry.COLUMN_PLAYER_2_USERNAME + ") REFERENCES " + UserEntry.TABLE_NAME + " (" + UserEntry.COLUMN_USERNAME + "), " +

            " UNIQUE (" + MatchEntry.COLUMN_TOURNAMENT_ID + ", " + MatchEntry.COLUMN_PLAYER_1_USERNAME + "," + MatchEntry.COLUMN_PLAYER_2_USERNAME + ") ON CONFLICT REPLACE" +
            " );";

    static final String CREATE_PRIZE_TABLE = "CREATE TABLE " + PrizeEntry.TABLE_NAME + " (" +
            PrizeEntry._ID + " INTEGER PRIMARY KEY," +
            PrizeEntry.COLUMN_PLACE + " INTEGER NOT NULL, " +
            PrizeEntry.COLUMN_PRIZE_AMOUNT + " INTEGER NOT NULL, " +
            PrizeEntry.COLUMN_TOURNAMENT_ID + " INTEGER NOT NULL, " +
            PrizeEntry.COLUMN_PLAYER_USERNAME + " TEXT, " +

            " FOREIGN KEY (" + PrizeEntry.COLUMN_TOURNAMENT_ID + ") REFERENCES " + TournamentEntry.TABLE_NAME + " (" + TournamentEntry._ID + "), " +
            " FOREIGN KEY (" + PrizeEntry.COLUMN_PLAYER_USERNAME + ") REFERENCES " + UserEntry.TABLE_NAME + " (" + UserEntry.COLUMN_USERNAME + "), " +

            " UNIQUE (" + PrizeEntry.COLUMN_TOURNAMENT_ID + ", " + PrizeEntry.COLUMN_PLACE + ") ON CONFLICT REPLACE" +
            " );";

    static final String CREATE_STATUS_TABLE = "CREATE TABLE " + StatusEntry.TABLE_NAME + " (" +
            StatusEntry._ID + " INTEGER PRIMARY KEY," +
            StatusEntry.COLUMN_STATUS_NAME + " TEXT UNIQUE NOT NULL " +
            " );";

    static final String CREATE_TOURNAMENT_TABLE = "CREATE TABLE " + TournamentEntry.TABLE_NAME + " (" +
            TournamentEntry._ID + " INTEGER PRIMARY KEY," +
            TournamentEntry.COLUMN_TOURNAMENT_NAME + " TEXT UNIQUE NOT NULL, " +
            TournamentEntry.COLUMN_START_DATE + " INTEGER NOT NULL, " +
            TournamentEntry.COLUMN_END_DATE + " INTEGER, " +
            TournamentEntry.COLUMN_ENTRY_PRICE + " INTEGER NOT NULL, " +
            TournamentEntry.COLUMN_HOUSE_CUT + " INTEGER NOT NULL, " +
            TournamentEntry.COLUMN_STATUS_ID + " INTEGER NOT NULL, " +

            " FOREIGN KEY (" + TournamentEntry.COLUMN_STATUS_ID + ") REFERENCES " + StatusEntry.TABLE_NAME + " (" + StatusEntry._ID + ") " +
            " );";

    static final String CREATE_TOURNAMENT_PLAYER_LINK_TABLE = "CREATE TABLE " + TournamentPlayerLinkEntry.TABLE_NAME + " (" +
            TournamentPlayerLinkEntry._ID + " INTEGER PRIMARY KEY," +
            TournamentPlayerLinkEntry.COLUMN_TOURNAMENT_ID + " INTEGER NOT NULL, " +
            TournamentPlayerLinkEntry.COLUMN_PLAYER_USERNAME + " TEXT, " +

            " FOREIGN KEY (" + TournamentPlayerLinkEntry.COLUMN_TOURNAMENT_ID + ") REFERENCES " + TournamentEntry.TABLE_NAME + " (" + TournamentEntry._ID + "), " +
            " FOREIGN KEY (" + TournamentPlayerLinkEntry.COLUMN_PLAYER_USERNAME + ") REFERENCES " + UserEntry.TABLE_NAME + " (" + UserEntry._ID + "), " +

            " UNIQUE (" + TournamentPlayerLinkEntry.COLUMN_TOURNAMENT_ID + ", " + TournamentPlayerLinkEntry.COLUMN_PLAYER_USERNAME + ") ON CONFLICT IGNORE" +
            " );";

    static final String CREATE_USER_TABLE = "CREATE TABLE " + UserEntry.TABLE_NAME + " (" +
            UserEntry._ID + " INTEGER PRIMARY KEY," +
            UserEntry.COLUMN_USERNAME + " TEXT UNIQUE NOT NULL, " +
            UserEntry.COLUMN_NAME + " TEXT, " +
            UserEntry.COLUMN_PHONE_NUMBER + " TEXT, " +
            UserEntry.COLUMN_PASSWORD + " TEXT, " +
            UserEntry.COLUMN_IS_MANAGER + " INTEGER NOT NULL, " +
            UserEntry.COLUMN_DECK_ID + " INTEGER, " +

            " FOREIGN KEY (" + UserEntry.COLUMN_DECK_ID + ") REFERENCES " + DeckEntry.TABLE_NAME + " (" + DeckEntry._ID + ") " +
            " );";
}
