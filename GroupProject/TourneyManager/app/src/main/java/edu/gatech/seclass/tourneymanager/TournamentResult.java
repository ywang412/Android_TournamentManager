package edu.gatech.seclass.tourneymanager;

import java.util.ArrayList;

/**
 * Created by rulan on 3/1/2017.
 */

public class TournamentResult {
    Tournament tournament;
    ArrayList<Prize> prizes;
    int houseProfit;

    public TournamentResult(){};

    public TournamentResult(Tournament tournament, ArrayList<Prize> prizes, int houseProfit){
        this.tournament=tournament;
        this.prizes=prizes;
        this.houseProfit=houseProfit;
    }

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

    public int getHouseProfit() {
        return houseProfit;
    }

    public void setHouseProfit(int houseProfit) {
        this.houseProfit = houseProfit;
    }
}
