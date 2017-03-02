package edu.gatech.seclass.tourneymanager;

import java.util.ArrayList;

/**
 * Created by Yu on 3/2/2017.
 */

public class ApplicationController {


    public static void logon(User user){};

    public static Player registerPlayer(String player){
        return new Player();
    };

    public static void deletePlayer(Player player){};


    public static Tournament createTournament(int tournament_id, int house_cut, int entry_price, List<Player> playerslist){
        Tournament tournament= new Tournament(tournament_id, house_cut,  entry_price, playerslist);
        return tournament;
    };

    public static Tournament fetchCurrentTournament(){
        return new Tournament();
    };

    public static int fetchProfits(){
        return 0;
    };
    public static ArrayList<Prize> fetchPrizes(){
        return new ArrayList<Prize>();
    };
    public static Tournament fetchPlayers(){
        return new Tournament();
    };


}
