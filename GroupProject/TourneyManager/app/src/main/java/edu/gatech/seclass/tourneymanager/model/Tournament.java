package edu.gatech.seclass.tourneymanager.model;

import java.util.ArrayList;

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
    private ArrayList<Match> matchlist;
    private ArrayList<String> playerslist;

    public Tournament() {
    }

    public Tournament(int house_cut, int entry_price, ArrayList<String> playerslist) {
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
                    String player1 = playerslist.get(i);
                    String player2 = playerslist.get(i+1);
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

    public int getTournament_name() {
        return tournament_name;
    }

    public void setTournament_name(int tournament_name) {
        this.tournament_name = tournament_name;
    }

    public int getTournament_id() {
        return tournament_id;
    }

    public void setTournament_id(int tournament_id) {
        this.tournament_id = tournament_id;
    }

    public String getStart_date_time() {
        return start_date_time;
    }

    public void setStart_date_time(String start_date_time) {
        this.start_date_time = start_date_time;
    }

    public String getEnd_date_time() {
        return end_date_time;
    }

    public void setEnd_date_time(String end_date_time) {
        this.end_date_time = end_date_time;
    }

    public int getEntry_price() {
        return entry_price;
    }

    public void setEntry_price(int entry_price) {
        this.entry_price = entry_price;
    }

    public int getPriz2nd() {
        return priz2nd;
    }

    public void setPriz2nd(int priz2nd) {
        this.priz2nd = priz2nd;
    }

    public int getPriz1st() {
        return priz1st;
    }

    public void setPriz1st(int priz1st) {
        this.priz1st = priz1st;
    }

    public int getPriz3rd() {
        return priz3rd;
    }

    public void setPriz3rd(int priz3rd) {
        this.priz3rd = priz3rd;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public int getHouse_cut() {
        return house_cut;
    }

    public void setHouse_cut(int house_cut) {
        this.house_cut = house_cut;
    }

    public int getHouse_profit() {
        return house_profit;
    }

    public void setHouse_profit(int house_profit) {
        this.house_profit = house_profit;
    }

    public ArrayList<Match> getMatchlist() {
        return matchlist;
    }

    public void setMatchlist(ArrayList<Match> matchlist) {
        this.matchlist = matchlist;
    }

    public ArrayList<String> getPlayerslist() {
        return playerslist;
    }

    public void setPlayerslist(ArrayList<String> playerslist) {
        this.playerslist = playerslist;
    }

}
