package edu.gatech.seclass.tourneymanager;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.tourneymanager.data.TourneyManagerProvider;

/**
 * Created by rulan on 3/1/2017.
 */

public class Player extends User {
    String name;
    String phoneNumber;
    Deck deck;

    public Player(){}

    public Player(String name, String phoneNumber, Deck deck){
        this.name=name;
        this.phoneNumber=phoneNumber;
        this.deck=deck;
    }

    public List<Prize> fetchPrizes(TourneyManagerProvider theProvider){
/*        Tournament dummyTournament = new Tournament();
        ArrayList<Prize> thePrizeList = new ArrayList<Prize>();
        thePrizeList.add(new Prize(dummyTournament, this, 1, 100));
        thePrizeList.add(new Prize(dummyTournament, this, 2, 50));
        thePrizeList.add(new Prize(dummyTournament, this, 3, 20));*/
        return theProvider.fetchPrizes(this);
//        return thePrizeList;
    }

    public void pickDeck(Deck deck){
        this.deck=deck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
