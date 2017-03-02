package edu.gatech.seclass.tourneymanager;

/**
 * Created by rulan on 3/1/2017.
 */

public class Deck {
    private int id;
    private String name;

    public Deck(){}

    Deck(String deckName){
        name = deckName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
