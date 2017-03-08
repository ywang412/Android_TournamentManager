package edu.gatech.seclass.tourneymanager.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.seclass.tourneymanager.PlayerlistActivity;
import edu.gatech.seclass.tourneymanager.R;
import edu.gatech.seclass.tourneymanager.data.TourneyManagerProvider;

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
            mCreateTournamentBtn.setText(getString(R.string.manage_tournament_btn));
            mCreateTournamentBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO go to matchlist for current tournament
                    Toast.makeText(getApplicationContext(), "manage tournament", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            mCreateTournamentBtn.setText(getString(R.string.create_tournament_btn));
            mCreateTournamentBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO goto tournament creation activity
                    Toast.makeText(getApplicationContext(), "create tournament", Toast.LENGTH_SHORT).show();
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
