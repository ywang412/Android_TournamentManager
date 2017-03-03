package edu.gatech.seclass.tourneymanager;

/**
 * Created by Yu on 3/3/2017.
 */

public class Profit {
    Tournament tournament;
    int profit;

    public Profit() {}

    public Profit(Tournament tournament, int profit){
        this.tournament=tournament;
        this.profit=profit;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }
}

