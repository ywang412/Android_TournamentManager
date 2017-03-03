package edu.gatech.seclass.tourneymanager;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.tourneymanager.data.TourneyManagerProvider;

/**
 * Created by Yu on 3/2/2017.
 */

public class ApplicationController {

    private Context mContext;
    private TourneyManagerProvider mProvider;

    /**
     * instantiate in {@link android.support.v7.app.AppCompatActivity#onResume} method
     * @param context
     */
    public ApplicationController(Context context) {
        this.mContext = context;
        this.mProvider = new TourneyManagerProvider(context);
    }

    /**
     * must invoke in {@link android.support.v7.app.AppCompatActivity#onPause} method
     */
    public void shutdown() {
        mProvider.shutdown();
    }

    public void logon(User user){}

    public Player registerPlayer(String player){
        return new Player();
    }

    public void deletePlayer(Player player){}


    public Tournament createTournament(int tournament_id, int house_cut, int entry_price, List<Player> playerslist){
        Tournament tournament= new Tournament(tournament_id, house_cut,  entry_price, playerslist);
        return tournament;
    }

    public Tournament fetchCurrentTournament(){
        return mProvider.fetchCurrentTournament();
    }

    public int fetchProfits(){
        return 0;
    }
    public ArrayList<Prize> fetchPrizes(){
        return new ArrayList<Prize>();
    }
    public Tournament fetchPlayers(){
        return new Tournament();
    }


}
