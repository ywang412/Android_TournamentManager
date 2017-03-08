package edu.gatech.seclass.tourneymanager.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Defines table and columns for tournament manager
 */

public class TourneyManagerContract {

    public static final String CONTENT_AUTHORITY = "edu.gatech.seclass.tourneymanager";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_DECK = "deck";
    public static final String PATH_MATCH = "match";
    public static final String PATH_PRIZE = "prize";
    public static final String PATH_STATUS = "status";
    public static final String PATH_TOURNAMENT = "tournament";
    public static final String PATH_TOURNAMENT_PLAYER_LINK = "tournament_player_link";
    public static final String PATH_USER = "user";

    /* Inner class that defines the table contents of the status table */
    public static final class DeckEntry implements BaseColumns {
        // Table name
        public static final String TABLE_NAME = PATH_DECK;

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;

        public static final String COLUMN_DECK_NAME = "deck_name";

        public static Uri buildLocationUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    /* Inner class that defines the table contents of the match table */
    public static final class MatchEntry implements BaseColumns {
        // Table name
        public static final String TABLE_NAME = PATH_MATCH;

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;

        public static final String COLUMN_ROUND = "round";

        // foreign keys
        public static final String COLUMN_TOURNAMENT_ID = "tournament_id";
        public static final String COLUMN_STATUS_ID = "status_id";
        public static final String COLUMN_WINNER_USERNAME = "winner_username";
        public static final String COLUMN_PLAYER_1_USERNAME = "player_1_username";
        public static final String COLUMN_PLAYER_2_USERNAME = "player_2_username";
        public static final String COLUMN_NEXT_MATCH_ID = "next_match_id";

        public static Uri buildLocationUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    /* Inner class that defines the table contents of the prize table */
    public static final class PrizeEntry implements BaseColumns {
        // Table name
        public static final String TABLE_NAME = PATH_PRIZE;

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;

        public static final String COLUMN_PLACE = "place";
        public static final String COLUMN_PRIZE_AMOUNT = "prize_amount";

        // foreign keys
        public static final String COLUMN_TOURNAMENT_ID = "tournament_id";
        public static final String COLUMN_PLAYER_USERNAME = "player_username";

        public static Uri buildLocationUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    /* Inner class that defines the table contents of the status table */
    public static final class StatusEntry implements BaseColumns {
        // Table name
        public static final String TABLE_NAME = PATH_STATUS;

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;

        public static final String COLUMN_STATUS_NAME = "status_name";

        public static Uri buildLocationUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    /* Inner class that defines the table contents of the tournament table */
    public static final class TournamentEntry implements BaseColumns {
        // Table name
        public static final String TABLE_NAME = PATH_TOURNAMENT;

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;

        public static final String COLUMN_TOURNAMENT_NAME = "tournament_name";
        public static final String COLUMN_START_DATE = "start_date";
        public static final String COLUMN_END_DATE = "end_date";
        public static final String COLUMN_ENTRY_PRICE = "entry_price";
        public static final String COLUMN_HOUSE_CUT = "house_cut";
        public static final String COLUMN_HOUSE_PROFIT = "house_profit";

        // foreign keys
        public static final String COLUMN_STATUS_ID = "status_id";

        public static Uri buildLocationUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    /* Inner class that defines the table contents of the tournament player link table */
    public static final class TournamentPlayerLinkEntry implements BaseColumns {
        // Table name
        public static final String TABLE_NAME = PATH_TOURNAMENT_PLAYER_LINK;

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;

        public static final String COLUMN_TOURNAMENT_ID = "tournament_id";
        public static final String COLUMN_PLAYER_USERNAME = "player_username";

        public static Uri buildLocationUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    /* Inner class that defines the table contents of the user table */
    public static final class UserEntry implements BaseColumns {
        // Table name
        public static final String TABLE_NAME = PATH_USER;

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;

        // primary key
        public static final String COLUMN_USERNAME = "username";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PHONE_NUMBER = "phone_number";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_IS_MANAGER = "is_manager";

        // foreign keys
        public static final String COLUMN_DECK_ID = "deck_id";

        public static Uri buildLocationUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
