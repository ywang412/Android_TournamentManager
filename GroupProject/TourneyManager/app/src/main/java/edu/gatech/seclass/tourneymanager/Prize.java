package edu.gatech.seclass.tourneymanager;

/**
 * Created by rulan on 3/1/2017.
 */

public class Prize {
    Tournament tournament;
    Player player;
    int place;
    double prizeAmount;
    public Prize(Tournament tournament, Player player, int place, double prizeAmount){
        this.tournament=tournament;
        this.player=player;
        this.place=place;
        this.prizeAmount=prizeAmount;
    }
}
