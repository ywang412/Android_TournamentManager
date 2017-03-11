package edu.gatech.seclass.tourneymanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import edu.gatech.seclass.tourneymanager.data.TourneyManagerProvider;
import edu.gatech.seclass.tourneymanager.manager.ManagerDashboardActivity;
import edu.gatech.seclass.tourneymanager.playerlist.LeaderboardActivity;


public class MainActivity extends AppCompatActivity {

    private TourneyManagerProvider mProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mProvider = new TourneyManagerProvider(getApplicationContext());
    }

    @Override
    protected void onStop() {
        super.onStop();
        mProvider.shutdown();
    }

    public void layoutPlayer(View view) {

        // check whether there is a current tournament or not
        if (mProvider.fetchCurrentTournament() != null) {
            // if there is a current activity, display matchlist for the tournament
            Intent mainIntent = new Intent(this, MatchListPublicActivity.class);
            startActivity(mainIntent);
        } else {
            // if there's not current tournament, display player list
            Intent mainIntent = new Intent(this, LeaderboardActivity.class);
            startActivity(mainIntent);
        }
    }

    public void layoutManager(View view) {
        startActivity(new Intent(this, ManagerDashboardActivity.class));
    }

    public void createTournament(View view) {
        setContentView(R.layout.activity_tourneycreate);
    }
}
