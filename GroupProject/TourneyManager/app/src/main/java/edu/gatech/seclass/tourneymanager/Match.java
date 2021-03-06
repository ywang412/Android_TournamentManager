package edu.gatech.seclass.tourneymanager;

/**
 * Created by Yu on 2/28/2017.
 */

public class Match {

        private int matchId;
        private int matchNumber;
        private Tournament tournament;
        private Status m_status;
        private int match_round;
        private Player winner;
        private int nextmatch_id;
        private Player player_1;
        private Player player_2;

        public Match() {
        }

        public Match(int matchNumber, Tournament tournament_id, int match_round, Player player1, Player player2, int nextmatch_id) {
            this.matchNumber = matchNumber;
            this.tournament = tournament_id;
            this.match_round = match_round;
            this.player_1 = player1;
            this.player_2 = player2;
            this.nextmatch_id = nextmatch_id;
            m_status = Status.Setup;
           // System.out.println(match_round+"d"+ matchNumber+ player1.getName()+ player2.getName()+m_status);
        }


    public Match(int matchNumber, Tournament tournament_id, int match_round, Player player1, Player player2, int nextmatch_id, Player winner) {
        this.matchNumber = matchNumber;
        this.tournament = tournament_id;
        this.match_round = match_round;
        this.player_1 = player1;
        this.player_2 = player2;
        this.winner = winner;
        this.nextmatch_id = nextmatch_id;
        m_status = Status.Setup;
        // System.out.println(match_round+"d"+ matchNumber+ player1.getName()+ player2.getName()+m_status);
    }

    public Match(int matchNumber, Tournament tournament_id, int match_round, int nextmatch_id) {
            this.matchNumber = matchNumber;
            this.match_round=match_round;
            this.tournament = tournament_id;
            this.nextmatch_id = nextmatch_id;
            m_status = Status.Setup;
           // System.out.println(match_round+"d"+ matchNumber+m_status);
        }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public void startMatch(){
        this.m_status= Status.InProgress;
    }

    public void endMatch(Player player){
        this.m_status= Status.Completed;
        this.winner = player;
    }

    public void cancelMatch(){
        this.m_status= Status.Cancelled;
    }


    public int getMatchNumber() {
        return matchNumber;
    }

    public void setMatchNumber(int matchNumber) {
        this.matchNumber = matchNumber;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Status getStatus() {
        return m_status;
    }

    public void setStatus(Status m_status) {
        this.m_status = m_status;
    }

    public int getMatchRound() {
        return match_round;
    }

    public void setMatchRound(int match_round) {
        this.match_round = match_round;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextMatch() {
        return nextmatch_id;
    }

    public void setNextMatch(int nextMatch) {
        this.nextmatch_id = nextMatch;
    }

    public Player getPlayer1() {
        return player_1;
    }

    public void setPlayer1(Player player_1) {
        this.player_1 = player_1;
    }

    public Player getPlayer2() {
        return player_2;
    }

    public void setPlayer2(Player player_2) {
        this.player_2 = player_2;
    }

    @Override
    public String toString() {
        return this.matchNumber + ". " + this.player_1 + " vs " + this.player_2 + " [" + this.m_status + "]";
    }

    public String getActionString(){
        switch (m_status){
            case Setup:
                return "[Set Ready]";
            case Ready:
                return "[Start]";
            case InProgress:
                return "[End]";
            default:
                return "";
        }
    }
}


