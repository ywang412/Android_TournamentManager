package edu.gatech.seclass.tourneymanager.model;

/**
 * Created by Yu on 2/28/2017.
 */

public class Deck {

    private int deck_name;

    public Deck(int deck_name) {
        this.deck_name = deck_name;
    }

    public int getDeck_name() {
        return deck_name;
    }

    public void setDeck_name(int deck_name) {
        this.deck_name = deck_name;
    }
}
