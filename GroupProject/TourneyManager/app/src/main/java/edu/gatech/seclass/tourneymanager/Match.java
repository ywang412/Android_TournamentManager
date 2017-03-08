package edu.gatech.seclass.tourneymanager;

import edu.gatech.seclass.tourneymanager.Status;

/**
 * Created by Yu on 2/28/2017.
 */

public class Match {

         int match_id;
         Tournament tournament;
         Status m_status;
         int match_round;
         Player winner;
         int nextmatch_id;
         Player player_1;
         Player player_2;

        public Match() {
        }

        public Match(int match_id,Tournament tournament_id,int match_round,Player player1, Player player2, int nextmatch_id) {
            this.match_id = match_id;
            this.tournament = tournament_id;
            this.match_round = match_round;
            this.player_1 = player1;
            this.player_2 = player2;
            this.winner = player1;
            this.nextmatch_id = nextmatch_id;
            m_status = Status.Setup;
           // System.out.println(match_round+"d"+ match_id+ player1.getName()+ player2.getName()+m_status);
        }

        public Match(int match_id, Tournament tournament_id, int match_round, int nextmatch_id) {
            this.match_id = match_id;
            this.match_round=match_round;
            this.tournament = tournament_id;
            this.nextmatch_id = nextmatch_id;
            m_status = Status.Setup;
           // System.out.println(match_round+"d"+ match_id+m_status);
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


    public int getMatch_id() {
        return match_id;
    }

    public void setMatch_id(int match_id) {
        this.match_id = match_id;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Status getM_status() {
        return m_status;
    }

    public void setM_status(Status m_status) {
        this.m_status = m_status;
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

    public int getNextmatch_id() {
        return nextmatch_id;
    }

    public void setNextmatch_id(int nextmatch_id) {
        this.nextmatch_id = nextmatch_id;
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

    @Override
    public String toString() {
        return this.match_id + ". " + this.player_1 + " vs " + this.player_2 + " [" + this.m_status + "]";
    }
}


