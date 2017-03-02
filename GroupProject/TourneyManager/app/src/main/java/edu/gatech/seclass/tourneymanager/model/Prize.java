package edu.gatech.seclass.tourneymanager.model;

/**
 * Created by Yu on 2/28/2017.
 */

public class Prize {

    private int prize_id;
    private int tournament_id;
    private int place;
    private int prize_amount;
    private String player_name;


    public Prize() {
    }

    public Prize(int prize_id, int tournament_id, int place, int prize_amount, String player_name) {
        this.prize_amount = prize_amount;
        this.tournament_id = tournament_id;
        this.prize_id = prize_id;
        this.player_name = player_name;
        this.place = place;
    }

    public int getPrize_id() {
        return prize_id;
    }

    public void setPrize_id(int prize_id) {
        this.prize_id = prize_id;
    }

    public int getTournament_id() {
        return tournament_id;
    }

    public void setTournament_id(int tournament_id) {
        this.tournament_id = tournament_id;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getPrize_amount() {
        return prize_amount;
    }

    public void setPrize_amount(int prize_amount) {
        this.prize_amount = prize_amount;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }
}
