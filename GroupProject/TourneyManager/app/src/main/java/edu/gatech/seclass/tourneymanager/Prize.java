package edu.gatech.seclass.tourneymanager;

/**
 * Created by rulan on 3/1/2017.
 */

public class Prize {
    Tournament tournament;
    Player player;
    int place;
    int prizeAmount;

    public Prize() {}

    public Prize(Tournament tournament, Player player, int place, int prizeAmount){
        this.tournament=tournament;
        this.player=player;
        this.place=place;
        this.prizeAmount=prizeAmount;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getPrizeAmount() {
        return prizeAmount;
    }

    public void setPrizeAmount(int prizeAmount) {
        this.prizeAmount = prizeAmount;
    }
}
