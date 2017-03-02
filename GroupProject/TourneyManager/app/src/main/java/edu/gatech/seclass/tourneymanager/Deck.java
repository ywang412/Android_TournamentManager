package edu.gatech.seclass.tourneymanager;

/**
 * Created by rulan on 3/1/2017.
 */

public class Deck {
    String name;
    Deck(String deckName){
        name = deckName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
