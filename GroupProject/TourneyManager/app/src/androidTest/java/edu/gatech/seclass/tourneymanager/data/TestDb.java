/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.gatech.seclass.tourneymanager.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class TestDb {
    private TourneyManagerDbHelper dbHelper;
    private SQLiteDatabase db;

    @Before
    public void setUp() {
        InstrumentationRegistry.getTargetContext().deleteDatabase(TourneyManagerDbHelper.DATABASE_NAME);
        dbHelper = new TourneyManagerDbHelper(InstrumentationRegistry.getTargetContext());
        db = dbHelper.getWritableDatabase();
    }

    @After
    public void tearDown() {
        if (db != null) {
            db.close();
        }
        if (dbHelper != null) {
            dbHelper.close();
        }
        InstrumentationRegistry.getTargetContext().deleteDatabase(TourneyManagerDbHelper.DATABASE_NAME);
    }

    @Test
    public void testCreateDb() throws Throwable {
        assertEquals(true, db.isOpen());

        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        assertTrue("Error: This means that the database has not been created correctly",
                c.moveToFirst());

        final HashSet<String> tableNameHashSet = new HashSet<String>();
        tableNameHashSet.add(TourneyManagerContract.DeckEntry.TABLE_NAME);
        tableNameHashSet.add(TourneyManagerContract.MatchEntry.TABLE_NAME);
        tableNameHashSet.add(TourneyManagerContract.PrizeEntry.TABLE_NAME);
        tableNameHashSet.add(TourneyManagerContract.StatusEntry.TABLE_NAME);
        tableNameHashSet.add(TourneyManagerContract.TournamentEntry.TABLE_NAME);
        tableNameHashSet.add(TourneyManagerContract.TournamentPlayerLinkEntry.TABLE_NAME);
        tableNameHashSet.add(TourneyManagerContract.UserEntry.TABLE_NAME);

        // verify that the tables have been created
        do {
            tableNameHashSet.remove(c.getString(0));
        } while (c.moveToNext());

        assertTrue("Error: Your database was created without all of the tables",
                tableNameHashSet.isEmpty());

        // now, do our tables contain the correct columns?
        c = db.rawQuery("PRAGMA table_info(" + TourneyManagerContract.DeckEntry.TABLE_NAME + ")",
                null);

        assertTrue("Error: This means that we were unable to query the database for table information.",
                c.moveToFirst());

        // Build a HashSet of all of the column names we want to look for
        final HashSet<String> deckColumnHashSet = new HashSet<String>();
        deckColumnHashSet.add(TourneyManagerContract.DeckEntry._ID);
        deckColumnHashSet.add(TourneyManagerContract.DeckEntry.COLUMN_DECK_NAME);

        int columnNameIndex = c.getColumnIndex("name");
        do {
            String columnName = c.getString(columnNameIndex);
            deckColumnHashSet.remove(columnName);
        } while (c.moveToNext());

        assertTrue("Error: The database doesn't contain all of the required deck entry columns",
                deckColumnHashSet.isEmpty());
    }

    @Test
    public void testDeckTable() throws Throwable {
        testCreateDb();
        insertDeck();
    }

    @Test
    public void testUserTable() throws Throwable {
        testCreateDb();
        long deckRowId = insertDeck();
        assertFalse("Error: Deck Not Inserted Correctly", deckRowId == -1L);

        ContentValues userValues = createUserValues(deckRowId);

        long userRowId = db.insert(TourneyManagerContract.UserEntry.TABLE_NAME, null, userValues);
        assertTrue(userRowId != -1);

        Cursor cursor = db.query(
                TourneyManagerContract.UserEntry.TABLE_NAME,  // Table to Query
                null, // leaving "columns" null just returns all the columns.
                null, // cols for "where" clause
                null, // values for "where" clause
                null, // columns to group by
                null, // columns to filter by row groups
                null  // sort order
        );

        assertTrue("Error: No Records returned from user query", cursor.moveToFirst());
        validateCurrentRecord("testInsertReadDb userEntry failed to validate", cursor, userValues);
        assertFalse("Error: More than one record returned from user query", cursor.moveToNext());

        cursor.close();
    }

    /*
        Students: Use this to create some default user values for your database tests.
     */
    static ContentValues createUserValues(long deckRowId) {
        ContentValues userValues = new ContentValues();
        userValues.put(TourneyManagerContract.UserEntry.COLUMN_DECK_ID, deckRowId);
        userValues.put(TourneyManagerContract.UserEntry.COLUMN_IS_MANAGER, 1);
        userValues.put(TourneyManagerContract.UserEntry.COLUMN_NAME, "Player 1");
        userValues.put(TourneyManagerContract.UserEntry.COLUMN_PASSWORD, "gibberish");
        userValues.put(TourneyManagerContract.UserEntry.COLUMN_PHONE_NUMBER, "1234567890");
        userValues.put(TourneyManagerContract.UserEntry.COLUMN_USERNAME, "player1");

        return userValues;
    }

    public long insertDeck() {

        ContentValues testValues = createSampleDeckValue();

        long deckRowId = db.insert(TourneyManagerContract.DeckEntry.TABLE_NAME, null, testValues);
        assertTrue(deckRowId != -1);

        Cursor cursor = db.query(
                TourneyManagerContract.DeckEntry.TABLE_NAME,  // Table to Query
                null, // all columns
                null, // Columns for the "where" clause
                null, // Values for the "where" clause
                null, // columns to group by
                null, // columns to filter by row groups
                null // sort order
        );

        assertTrue("Error: No Records returned from deck query", cursor.moveToFirst());
        validateCurrentRecord("Error: Deck Query Validation Failed", cursor, testValues);
        assertFalse("Error: More than one record returned from deck query", cursor.moveToNext());
        cursor.close();
        return deckRowId;
    }

    static ContentValues createSampleDeckValue() {
        String TEST_DECK = "Engineer"; //  “Engineer”, “Buzz”, “Sideways”, “Wreck”, “T”, “RAT”

        // Create a new map of values, where column names are the keys
        ContentValues testValues = new ContentValues();
        testValues.put(TourneyManagerContract.DeckEntry.COLUMN_DECK_NAME, TEST_DECK);
        return testValues;
    }

    static void validateCurrentRecord(String error, Cursor valueCursor, ContentValues expectedValues) {
        Set<Map.Entry<String, Object>> valueSet = expectedValues.valueSet();
        for (Map.Entry<String, Object> entry : valueSet) {
            String columnName = entry.getKey();
            int idx = valueCursor.getColumnIndex(columnName);
            assertFalse("Column '" + columnName + "' not found. " + error, idx == -1);
            String expectedValue = entry.getValue().toString();
            assertEquals("Value '" + entry.getValue().toString() +
                    "' did not match the expected value '" +
                    expectedValue + "'. " + error, expectedValue, valueCursor.getString(idx));
        }
    }
}
