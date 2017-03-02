package edu.gatech.seclass.tourneymanager.model;

/**
 * Created by Yu on 2/28/2017.
 */

public class Deck {

    private int id;
    private String deck_name;

    public Deck(){}

    public Deck(String deck_name) {
        this.deck_name = deck_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeck_name() {
        return deck_name;
    }

    public void setDeck_name(String deck_name) {
        this.deck_name = deck_name;
    }
}
