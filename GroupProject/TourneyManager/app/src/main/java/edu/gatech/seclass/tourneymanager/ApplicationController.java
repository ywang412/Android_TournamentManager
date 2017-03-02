package edu.gatech.seclass.tourneymanager;

import java.util.ArrayList;

/**
 * Created by Yu on 3/2/2017.
 */

public class ApplicationController {


    public void logon(User user){};

    public Player registerPlayer(String player){
        return new Player();
    };

    public void deletePlayer(Player player){};

    public Tournament createTournament(){
        Tournament tournament= new Tournament();
        return tournament;
    };

    public Tournament fetchCurrentTournament(){
        return new Tournament();
    };

    public int fetchProfits(){
        return 0;
    };
    public ArrayList<Prize> fetchPrizes(){
        return new ArrayList<Prize>();
    };
    public Tournament fetchPlayers(){
        return new Tournament();
    };


}
