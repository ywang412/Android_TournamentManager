package edu.gatech.seclass.tourneymanager.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.gatech.seclass.tourneymanager.MatchList;
import edu.gatech.seclass.tourneymanager.R;
import edu.gatech.seclass.tourneymanager.data.TourneyManagerProvider;
import edu.gatech.seclass.tourneymanager.playerlist.PlayerlistActivity;
import edu.gatech.seclass.tourneymanager.tournament.CreateTournamentActivity;
import edu.gatech.seclass.tourneymanager.tournament.TournamentDetailsActivity;

public class ManagerDashboardActivity extends AppCompatActivity {

    private TextView mTotalProfitView;
    private TextView mNumTourneyView;
    private Button mCreateTournamentBtn;
    private TourneyManagerProvider mProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mCreateTournamentBtn = (Button) findViewById(R.id.createTournamentBtn);
        mTotalProfitView = (TextView) findViewById(R.id.totalProfit);
        mNumTourneyView = (TextView) findViewById(R.id.numTourneys);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mProvider = new TourneyManagerProvider(getApplicationContext());

        // set total profit value
        int totalProfit = mProvider.fetchTotalProfit();
        mTotalProfitView.setText("$" + String.valueOf(totalProfit));

        // set number of tournaments value
        mNumTourneyView.setText(String.valueOf(mProvider.fetchTournaments().size()));

        // set tournament button behavior
        if (mProvider.fetchCurrentTournament() != null) {
            // there is an active tournament, so prompt to manage it
            final Intent intent = new Intent(this, TournamentDetailsActivity.class);
            mCreateTournamentBtn.setText(getString(R.string.manage_tournament_btn));
            mCreateTournamentBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(intent);
                }
            });
        } else {
            // no active tournament, so prompt to create one
            final Intent intent = new Intent(this, CreateTournamentActivity.class);
            mCreateTournamentBtn.setText(getString(R.string.create_tournament_btn));
            mCreateTournamentBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mProvider.shutdown();
    }

    /**
     * implementation of onClick method for player management button
     * @param view
     */
    public void layoutPlayer(View view) {
        Intent playerListIntent = new Intent(this, PlayerlistActivity.class);
        startActivity(playerListIntent);
    }
}
