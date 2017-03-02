package edu.gatech.seclass.tourneymanager.model;

/**
 * Created by Yu on 2/28/2017.
 */

public class Match {

        private int match_id;
        private int tournament_id;
        private int status_id;
        private String status;
        private int match_round;
        private String winner_name;
        private String player_1_name;
        private String player_2_name;

        public Match() {
        }

        public Match(int match_id,int tournament_id,int match_round,String player1, String player2) {
            this.match_id = match_id;
            this.tournament_id = tournament_id;
            this.match_round = match_round;
            this.player_1_name = player1;
            this.player_2_name = player2;
        }

        public Match(int match_id, int tournament_id, int match_round) {
        this.match_id = match_id;
        this.match_round=match_round;
        this.tournament_id = tournament_id;
        this.status_id = status_id;
        }

    public void startmatch() {
        this.status_id = 1;
        this.status = "InProgress";
//        MatchDA.addmatch(this)
        }

    public void endmatch(String name) {
        this.status_id = 2;
        this.status = "Completed";
        this.winner_name = name;
//      MatchDA.addmatch(this);
    }

    public int getMatch_id() {
        return match_id;
    }

    public void setMatch_id(int match_id) {
        this.match_id = match_id;
    }

    public int getTournament_id() {
        return tournament_id;
    }

    public void setTournament_id(int tournament_id) {
        this.tournament_id = tournament_id;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMatch_round() {
        return match_round;
    }

    public void setMatch_round(int match_round) {
        this.match_round = match_round;
    }

    public String getWinner_name() {
        return winner_name;
    }

    public void setWinner_name(String winner_name) {
        this.winner_name = winner_name;
    }

    public String getPlayer_1_name() {
        return player_1_name;
    }

    public void setPlayer_1_name(String player_1_name) {
        this.player_1_name = player_1_name;
    }

    public String getPlayer_2_name() {
        return player_2_name;
    }

    public void setPlayer_2_name(String player_2_name) {
        this.player_2_name = player_2_name;
    }
}


