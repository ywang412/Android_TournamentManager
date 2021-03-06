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
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.gatech.seclass.tourneymanager.Deck;
import edu.gatech.seclass.tourneymanager.Player;
import edu.gatech.seclass.tourneymanager.Status;
import edu.gatech.seclass.tourneymanager.Tournament;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class TestDb {
    private static final String TEST_DECK = "Test"; //  “Engineer”, “Buzz”, “Sideways”, “Wreck”, “T”, “RAT”
    private static final String TEST_USERNAME = "username";
    private static final String TEST_PLAYER_NAME = "player1";
    private static final String TEST_PHONE_NUMBER = "0123456789";
    private static final String TEST_PASSWORD = "password";

    private TourneyManagerProvider provider;

    @Before
    public void setUp() {
        InstrumentationRegistry.getTargetContext().deleteDatabase(TourneyManagerDbHelper.DATABASE_NAME);
        provider = new TourneyManagerProvider(InstrumentationRegistry.getTargetContext());
    }

    @After
    public void tearDown() {
        provider.shutdown();
        InstrumentationRegistry.getTargetContext().deleteDatabase(TourneyManagerDbHelper.DATABASE_NAME);
    }

    /**
     * modified from https://github.com/udacity/Sunshine-Version-2
     *
     * @throws Throwable
     */
    @Test
    public void testCreateDb() throws Throwable {
        assertEquals(true, provider.isOpen());

        Cursor c = provider.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
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
        c = provider.rawQuery("PRAGMA table_info(" + TourneyManagerContract.DeckEntry.TABLE_NAME + ")",
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
    public void testAddPlayerToTournament() throws Throwable {
        testCreateDb();
        testInsertPlayer();
        testInsertTournament();

        long res = provider.addPlayerToTournament(provider.fetchCurrentTournament(), provider.fetchPlayer(TEST_USERNAME));
        assertTrue(res != -1);

        Tournament tournament = provider.fetchCurrentTournament();
        List<Player> players = provider.fetchPlayers(tournament);
        assertTrue(players.size() > 0);
        assertTrue(tournament.getPlayerslist().size() > 0);
    }

    @Test
    public void testInsertDeck() throws Throwable {
        testCreateDb();
        Deck deck = new Deck();
        deck.setName(TEST_DECK);
        provider.insertDeck(deck);
        Deck fetchedDeck = provider.fetchDeck(TEST_DECK);

        assertNotNull(fetchedDeck.getId());
        assertEquals(fetchedDeck.getName(), TEST_DECK);

        Deck fetchedDeck1 = provider.fetchDeck(fetchedDeck.getId());
        assertEquals(fetchedDeck1.getName(), TEST_DECK);
    }

    @Test
    public void testInsertPlayer() throws Throwable {
        testCreateDb();
        Player player = new Player();
        player.setUsername(TEST_USERNAME);
        player.setName(TEST_PLAYER_NAME);
        player.setPhoneNumber(TEST_PHONE_NUMBER);
        Deck deck = new Deck();
        deck.setName(TEST_DECK);
        player.setDeck(deck);

        provider.insertDeck(deck);
        provider.insertPlayer(player);
        Player fetchedPlayer = provider.fetchPlayer(TEST_USERNAME);

        assertEquals(fetchedPlayer.getUsername(), TEST_USERNAME);
        assertEquals(fetchedPlayer.getPhoneNumber(), TEST_PHONE_NUMBER);
        assertEquals(fetchedPlayer.getName(), TEST_PLAYER_NAME);
        assertNotNull(fetchedPlayer.getDeck());
        assertEquals(fetchedPlayer.getDeck().getName(), TEST_DECK);
    }

    @Test
    public void testInsertTournament() throws Throwable {
        testCreateDb();
        Tournament tournament = new Tournament("TEST TOURNEY", 10, 5);

        provider.insertTournament(tournament);
        Tournament fetchedTournament = provider.fetchCurrentTournament();

        assertEquals(fetchedTournament.getTournamentName(), tournament.getTournamentName());
        assertEquals(fetchedTournament.getEntryPrice(), tournament.getEntryPrice());
        assertEquals(fetchedTournament.getHouseCut(), tournament.getHouseCut());
        assertEquals(fetchedTournament.getStatus(), Status.Setup);
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

        long userRowId = provider.insert(TourneyManagerContract.UserEntry.TABLE_NAME, userValues);
        assertTrue(userRowId != -1);

        Cursor cursor = provider.query(
                TourneyManagerContract.UserEntry.TABLE_NAME,  // Table to Query
                null, // leaving "columns" null just returns all the columns.
                TourneyManagerContract.UserEntry.COLUMN_USERNAME + " = ?", // cols for "where" clause
                new String[]{TEST_USERNAME}, // values for "where" clause
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
        userValues.put(TourneyManagerContract.UserEntry.COLUMN_NAME, TEST_PLAYER_NAME);
        userValues.put(TourneyManagerContract.UserEntry.COLUMN_PASSWORD, TEST_PASSWORD);
        userValues.put(TourneyManagerContract.UserEntry.COLUMN_PHONE_NUMBER, TEST_PHONE_NUMBER);
        userValues.put(TourneyManagerContract.UserEntry.COLUMN_USERNAME, TEST_USERNAME);

        return userValues;
    }

    public long insertDeck() {

        ContentValues testValues = createSampleDeckValue();

        long deckRowId = provider.insert(TourneyManagerContract.DeckEntry.TABLE_NAME, testValues);
        assertTrue(deckRowId != -1);

        Cursor cursor = provider.query(
                TourneyManagerContract.DeckEntry.TABLE_NAME,  // Table to Query
                null, // all columns
                TourneyManagerContract.DeckEntry.COLUMN_DECK_NAME + " = ?", // Columns for the "where" clause
                new String[]{TEST_DECK}, // Values for the "where" clause
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
        // Create a new map of values, where column names are the keys
        ContentValues testValues = new ContentValues();
        testValues.put(TourneyManagerContract.DeckEntry.COLUMN_DECK_NAME, TEST_DECK);
        return testValues;
    }

    /**
     * copied from https://github.com/udacity/Sunshine-Version-2
     * @param error
     * @param valueCursor
     * @param expectedValues
     */
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
