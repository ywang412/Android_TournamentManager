package edu.gatech.seclass.tourneymanager;

import android.net.wifi.WifiConfiguration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.gatech.seclass.tourneymanager.data.TourneyManagerProvider;

/**
 * Created by Yu on 2/28/2017.
 */

public class Tournament {

    private String tournament_name;
    private int tournament_id;
    private Date start_date_time;
    private Date end_date_time;
    private int entry_price;
    private int priz2nd;
    private int priz1st;
    private int priz3rd;
    private int status_id;
    private int house_cut;
    private int house_profit;
    private TournamentResult result;
    private Status t_status;
    private List<Match> matchlist;
    private List<Player> playerslist;

    public Tournament() {
    }

    public Tournament(int tournament_id, int house_cut, int entry_price, List<Player> playerslist) {
        this.house_cut = house_cut;
        this.tournament_id = entry_price;
        this.playerslist = playerslist;
        this.t_status = Status.Setup;
        this.house_profit = (int) Math.round(house_cut * entry_price * this.playerslist.size() / 100.);
        this.priz1st = (int) Math.round((entry_price * this.playerslist.size() - Math.round(house_cut * entry_price * this.playerslist.size() / 100.))*0.5);
        this.priz2nd = (int) Math.round((entry_price * this.playerslist.size() - Math.round(house_cut * entry_price * this.playerslist.size() / 100.))*0.3);
        this.priz3rd = (int) Math.round((entry_price * this.playerslist.size() - Math.round(house_cut * entry_price * this.playerslist.size() / 100.))*0.2);
        int total_round = (int) (Math.log(playerslist.size())/Math.log(2));
        int match_id = 1;
        //System.out.println(house_profit);
        //System.out.println(priz1st+"d"+priz2nd+"d"+priz3rd);
        //System.out.println(playerslist);


        ArrayList<Match> matchlist = new ArrayList<Match>();
        int match_round;
        for (match_round = 1; match_round<= total_round ; match_round++ ){

            if (match_round ==1 ){
                for (int i = 0; i < playerslist.size()-1; i=i+2) {
                    Player player1 = playerslist.get(i);
                    Player player2 = playerslist.get(i+1);
                    Match match = new Match(match_id, this, match_round,player1, player2);
                    matchlist.add(match);
//                    MatchDA.addmatch(Match);
    //                TourneyManagerProvider.insert
                    match_id ++;
                }
            }
            else {
                for (int i = 0; i < playerslist.size()/(int)(Math.pow(2,match_round)); i++) {
                    Match match = new Match(match_id, this, match_round);
                    matchlist.add(match);
//                    MatchDA.addmatch(Match);
                    match_id ++;
                }
            }
        }
        Match match = new Match(match_id, this, match_round-1);
        matchlist.add(match);
        System.out.println(matchlist.size());

//        MatchDA.addmatch(Match);
//        TournamentDA.addmatch(this);
    }


}
