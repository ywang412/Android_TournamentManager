package edu.gatech.seclass.tourneymanager;

import java.util.ArrayList;

import edu.gatech.seclass.tourneymanager.Match;
import edu.gatech.seclass.tourneymanager.Status;

/**
 * Created by Yu on 2/28/2017.
 */

public class Tournament {

    private int tournament_name;
    private int tournament_id;
    private String start_date_time;
    private String end_date_time;
    private int entry_price;
    private int priz2nd;
    private int priz1st;
    private int priz3rd;
    private int status_id;
    private int house_cut;
    private int house_profit;
    private TournamentResult result;
    private Status Tstatus;
    private ArrayList<Match> matchlist;
    private ArrayList<Player> playerslist;

    public Tournament() {
    }

    public Tournament(int house_cut, int entry_price, ArrayList<Player> playerslist) {
        this.house_cut = house_cut;
        this.tournament_id = entry_price;
        this.playerslist = playerslist;
        this.house_profit = (int) Math.round(house_cut * entry_price * this.playerslist.size() / 100.);
        this.priz2nd = (int) Math.round((entry_price * this.playerslist.size() - Math.round(house_cut * entry_price * this.playerslist.size() / 100.))*0.3);
        this.priz1st = (int) Math.round((entry_price * this.playerslist.size() - Math.round(house_cut * entry_price * this.playerslist.size() / 100.))*0.5);
        this.priz3rd = (int) Math.round((entry_price * this.playerslist.size() - Math.round(house_cut * entry_price * this.playerslist.size() / 100.))*0.2);
        int total_round = (int) (Math.log(playerslist.size())/Math.log(2));
        int match_id = 1;
        ArrayList<Match> matchlist = new ArrayList<Match>();
        int match_round;
        for (match_round = 1; match_round<= total_round ; match_round++ ){
            if (match_round ==1 ){
                for (int i = 0; i < playerslist.size()-1; i++) {
                    Player player1 = playerslist.get(i);
                    Player player2 = playerslist.get(i+1);
                    Match match = new Match(match_id, tournament_id, match_round,player1, player2);
                    matchlist.add(match);
//                    MatchDA.addmatch(Match);
                    match_id ++;
                }
            }
            else {
                for (int i = 0; i < playerslist.size()/(int)(Math.pow(2,match_round)); i++) {
                    Match match = new Match(match_id, tournament_id,match_round);
                    matchlist.add(match);
//                    MatchDA.addmatch(Match);
                    match_id ++;
                }
            }
        }
        Match match = new Match(match_id, tournament_id, match_round);
        matchlist.add(match);
//        MatchDA.addmatch(Match);
//        TournamentDA.addmatch(this);
    }


}
