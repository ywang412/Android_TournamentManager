package edu.gatech.seclass.tourneymanager;

import edu.gatech.seclass.tourneymanager.Status;

/**
 * Created by Yu on 2/28/2017.
 */

public class Match {

        private int match_id;
        private int next_match_id;
        private int tournament_id;
        private Status Mstatus;
        private int match_round;
        private Player winner;
        private Player player_1;
        private Player player_2;

        public Match() {
        }

        public Match(int match_id,int tournament_id,int match_round,Player player1, Player player2) {
            this.match_id = match_id;
            this.tournament_id = tournament_id;
            this.match_round = match_round;
            this.player_1 = player1;
            this.player_2 = player2;
        }

        public Match(int match_id, int tournament_id, int match_round) {
        this.match_id = match_id;
        this.match_round=match_round;
        this.tournament_id = tournament_id;

        }

    public void startmatch() {

//        MatchDA.addmatch(this)
        }

    public void endmatch(String name) {


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

    public Status getMstatus() {
        return Mstatus;
    }

    public void setMstatus(Status mstatus) {
        this.Mstatus = mstatus;
    }

    public int getMatch_round() {
        return match_round;
    }

    public void setMatch_round(int match_round) {
        this.match_round = match_round;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Player getPlayer_1() {
        return player_1;
    }

    public void setPlayer_1(Player player_1) {
        this.player_1 = player_1;
    }

    public Player getPlayer_2() {
        return player_2;
    }

    public void setPlayer_2(Player player_2) {
        this.player_2 = player_2;
    }
}


