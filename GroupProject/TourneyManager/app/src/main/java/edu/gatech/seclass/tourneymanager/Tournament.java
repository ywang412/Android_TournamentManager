package edu.gatech.seclass.tourneymanager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private int house_cut;
    private int house_profit;
    private TournamentResult result;
    private Status t_status;
    private List<Match> matchlist = new ArrayList<>();;
    private List<Player> playerslist = new ArrayList<>();

    public Tournament(String tournament_name, int entry_price, int house_cut) {
        this.tournament_name = tournament_name;
        this.house_cut = house_cut;
        this.entry_price = entry_price;
        this.t_status = Status.Setup;
    }

    public Tournament(int tournament_id, int house_cut, int entry_price, List<Player> playerslist, Player winner) {
        this(String.valueOf(tournament_id), entry_price, house_cut);
        this.playerslist = playerslist;
        this.t_status = Status.Setup;
        this.result = new TournamentResult(this);
        this.house_profit = (int) Math.round(house_cut * entry_price * this.playerslist.size() / 100.);
        this.priz1st = (int) Math.round((entry_price * this.playerslist.size() - Math.round(house_cut * entry_price * this.playerslist.size() / 100.))*0.5);
        this.priz2nd = (int) Math.round((entry_price * this.playerslist.size() - Math.round(house_cut * entry_price * this.playerslist.size() / 100.))*0.3);
        this.priz3rd = (int) Math.round((entry_price * this.playerslist.size() - Math.round(house_cut * entry_price * this.playerslist.size() / 100.))*0.2);
        int total_round = (int) (Math.log(playerslist.size())/Math.log(2));
        int match_id = 1;
        int nextmatch_id =0;
        //System.out.println(house_profit);
        //System.out.println(priz1st+"d"+priz2nd+"d"+priz3rd);
        //System.out.println(playerslist);


        //ArrayList<Match> matchlist
        //this.matchlist = matchlist

        int match_round;
        for (match_round = 1; match_round<= total_round ; match_round++ ){

            if (match_round ==1 ){
                for (int i = 0; i < playerslist.size()-1; i=i+2) {
                    Player player1 = playerslist.get(i);
                    Player player2 = playerslist.get(i+1);

                    nextmatch_id = match_id+total_round/(int)(Math.pow(2,match_round));

                    Match match = new Match(match_id, this, match_round,player1, player2, nextmatch_id,winner);

                    matchlist.add(match);
                    System.out.println("match_no="+match.getMatchId());
//                    MatchDA.addmatch(Match);
    //                TourneyManagerProvider.insert
                    match_id ++;
                }
            }

            else {
                for (int i = 0; i < playerslist.size()/(int)(Math.pow(2,match_round)); i++) {

                    nextmatch_id = match_id+total_round/(int)(Math.pow(2,match_round));
                    Match match = new Match(match_id, this, match_round,winner, winner, nextmatch_id, winner);

                    matchlist.add(match);
                    System.out.println("match_no="+match.getMatchId());
//                    MatchDA.addmatch(Match);
                    match_id ++;
                }
            }
        }

        Match match = new Match(match_id, this, match_round-1, winner, winner, nextmatch_id, winner);
        matchlist.add(match);

        System.out.println("match_no="+match.getMatchId()+"next"+match.getMatchId());
        System.out.println("matchlist_size="+matchlist.size());


        System.out.println("debug"+ this.getPlayerslist().get(1).getName());


        for (Match matchi: matchlist){
            System.out.println("no."+matchi.getMatchId());
            System.out.println(this.getMatchlist().get(matchi.getMatchId()-1).getMatchId());}


        System.out.println("done+createlist in tournament!");

//        TournamentDA.addmatch(this);
    }


    public void startTournament(){
        this.t_status= Status.InProgress;
    }

    public void endTournament(){
        this.t_status= Status.Completed;
    }

    public void cancelTournament(){
        this.t_status= Status.Cancelled;
    }




    public String getTournamentName() {
        return tournament_name;
    }

    public void setTournamentName(String tournament_name) {
        this.tournament_name = tournament_name;
    }

    public int getTournamentId() {
        return tournament_id;
    }

    public void setTournamentId(int tournament_id) {
        this.tournament_id = tournament_id;
    }

    public Date getStartDateTime() {
        return start_date_time;
    }

    public void setStartDateTime(Date start_date_time) {
        this.start_date_time = start_date_time;
    }

    public Date getEndDateTime() {
        return end_date_time;
    }

    public void setEndDateTime(Date end_date_time) {
        this.end_date_time = end_date_time;
    }

    public int getEntryPrice() {
        return entry_price;
    }

    public void setEntryPrice(int entry_price) {
        this.entry_price = entry_price;
    }

    public Status getStatus() {
        return t_status;
    }

    public void setStatus(Status status) {
        this.t_status = status;
    }

    public int getHouseCut() {
        return house_cut;
    }

    public void setHouseCut(int house_cut) {
        this.house_cut = house_cut;
    }

    public int getHouseProfit() {
        return house_profit;
    }

    public void setHouseProfit(int house_profit) {
        this.house_profit = house_profit;
    }

    public TournamentResult getResult() {
        return result;
    }

    public void setResult(TournamentResult result) {
        this.result = result;
    }

    public List<Match> getMatchlist() {
        return matchlist;
    }

    public void setMatchlist(List<Match> matchlist) {
        this.matchlist = matchlist;
    }

    public List<Player> getPlayerslist() {
        return playerslist;
    }

    public void setPlayerslist(List<Player> playerslist) {
        this.playerslist = playerslist;
    }
}
