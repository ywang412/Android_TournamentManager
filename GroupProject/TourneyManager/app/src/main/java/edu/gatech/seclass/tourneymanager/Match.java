package edu.gatech.seclass.tourneymanager;

import edu.gatech.seclass.tourneymanager.Status;

/**
 * Created by Yu on 2/28/2017.
 */

public class Match {

        private int match_id;
        private Tournament tournament;
        private Status m_status;
        private int match_round;
        private Player winner;
        private int next_match_id;
        private Player player_1;
        private Player player_2;

        public Match() {
        }

        public Match(int match_id,Tournament tournament_id,int match_round,Player player1, Player player2) {
            this.match_id = match_id;
            this.tournament = tournament_id;
            this.match_round = match_round;
            this.player_1 = player1;
            this.player_2 = player2;
            m_status = Status.Setup;
           // System.out.println(match_round+"d"+ match_id+ player1.getName()+ player2.getName()+m_status);
        }

        public Match(int match_id, Tournament tournament_id, int match_round) {
            this.match_id = match_id;
            this.match_round=match_round;
            this.tournament = tournament_id;
            m_status = Status.Setup;
           // System.out.println(match_round+"d"+ match_id+m_status);
        }

    public void startmatch() {

//        MatchDA.addmatch(this)
        }

    public void endmatch(String name) {


//      MatchDA.addmatch(this);
    }


}


