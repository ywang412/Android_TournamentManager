package edu.gatech.seclass.tourneymanager;

import java.util.ArrayList;

/**
 * Created by rulan on 3/1/2017.
 */

public class TournamentResult {
    Tournament tournament;
    ArrayList<Prize> prizes;
    double houseProfit;
    public TournamentResult(Tournament tournament, ArrayList<Prize> prizes, double houseProfit){
        this.tournament=tournament;
        this.prizes=prizes;
        this.houseProfit=houseProfit;
    }
}
