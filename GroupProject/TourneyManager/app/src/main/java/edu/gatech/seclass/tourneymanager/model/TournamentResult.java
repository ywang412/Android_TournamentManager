package edu.gatech.seclass.tourneymanager.model;

import java.util.ArrayList;

/**
 * Created by Yu on 3/1/2017.
 */

public class TournamentResult {
    private Tournament tournament;
    private ArrayList<Prize> prizes;
    private int house_profit;

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public ArrayList<Prize> getPrizes() {
        return prizes;
    }

    public void setPrizes(ArrayList<Prize> prizes) {
        this.prizes = prizes;
    }

    public int getHouse_profit() {
        return house_profit;
    }

    public void setHouse_profit(int house_profit) {
        this.house_profit = house_profit;
    }
}
