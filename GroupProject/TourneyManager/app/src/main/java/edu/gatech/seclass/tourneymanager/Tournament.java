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
    private int prize2nd;
    private int prize1st;
    private int prize3rd;
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
    }

    protected List<Match> generateMatchList(List<Player> players) {
        List<Match> matches = new ArrayList<>();
        int playerCount = players.size();
        int total_round = (int) (Math.log(playerCount)/Math.log(2));
        int match_id = 1;
        int nextMatchCounter = 0;
        int nextMatchId = playerCount/2;

        int match_round;
        for (match_round = 1; match_round<= total_round ; match_round++ ){

            if (match_round ==1 ){
                for (int i = 0; i < players.size()-1; i=i+2) {
                    Player player1 = players.get(i);
                    Player player2 = players.get(i+1);

                    if (nextMatchCounter % 2 == 0) {
                        nextMatchId++;
                    }
                    nextMatchCounter++;

                    Match match = new Match(match_id, this, match_round,player1, player2, nextMatchId);

                    matches.add(match);
                    match_id ++;
                }
            }

            else {
                for (int i = 0; i < playerslist.size()/(int)(Math.pow(2,match_round)); i++) {

                    if (nextMatchCounter % 2 == 0) {
                        nextMatchId++;
                    }
                    nextMatchCounter++;

                    if (match_round >= total_round) {
                        nextMatchId = -1;
                    }
                    Match match = new Match(match_id, this, match_round, nextMatchId);

                    matches.add(match);
                    match_id ++;
                }
            }
        }
        System.out.println("matches_add!");
        Match match = new Match(match_id, this, match_round-1, nextMatchId);
        matches.add(match);
        return matches;
    }


    public void startTournament(){
        setMatchlist(generateMatchList(getPlayerslist()));

        System.out.println("startTournament!");
        this.t_status= Status.InProgress;
        setStartDateTime(new Date());
    }

    public void endTournament(){
        Player player1st = null;
        Player player2nd = null;
        Player player3rd = null;

        for (Match match : getMatchlist()) {
            switch(match.getMatchNumber()){
                case 7:
                    if (player1st == null) {
                        player1st = match.getWinner();
                        player2nd = (match.getPlayer1().getUsername().equals(match.getWinner().getUsername())) ? match.getPlayer2() : match.getPlayer1();
                    }
                    break;
                case 8:
                    if (player3rd == null) {
                        player3rd = match.getWinner();
                    }
                    break;
                case 15:
                    player1st = match.getWinner();
                    player2nd = (match.getPlayer1().getUsername().equals(match.getWinner().getUsername())) ? match.getPlayer2() : match.getPlayer1();
                    break;
                case 16:
                    player3rd = match.getWinner();
                    break;
            }
        }

        List<Prize> prizes = new ArrayList<>();
        prizes.add(new Prize(this, player1st, 1, getPrize1st()));
        prizes.add(new Prize(this, player2nd, 2, getPrize2nd()));
        prizes.add(new Prize(this, player3rd, 3, getPrize3rd()));

        TournamentResult result = new TournamentResult();
        result.setPrizes(prizes);
        result.setTournament(this);
        result.setHouseProfit(getHouseProfit());
        setResult(result);
        this.t_status= Status.Completed;
        setEndDateTime(new Date());
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

    public int getPrize2nd() {
        return prize2nd;
    }

    public void setPrize2nd(int prize2nd) {
        this.prize2nd = prize2nd;
    }

    public int getPrize1st() {
        return prize1st;
    }

    public void setPrize1st(int prize1st) {
        this.prize1st = prize1st;
    }

    public int getPrize3rd() {
        return prize3rd;
    }

    public void setPrize3rd(int prize3rd) {
        this.prize3rd = prize3rd;
    }
}
