package edu.gatech.seclass.tourneymanager.tournament;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.seclass.tourneymanager.ApplicationController;
import edu.gatech.seclass.tourneymanager.R;
import edu.gatech.seclass.tourneymanager.Tournament;
import edu.gatech.seclass.tourneymanager.data.TourneyManagerProvider;

public class CreateTournamentActivity extends AppCompatActivity {

    private TourneyManagerProvider mProvider;
    private ApplicationController mController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tournament);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mProvider = new TourneyManagerProvider(getApplicationContext());
        mController = new ApplicationController(getApplicationContext());

        // make sure there are no existing tournament.  If there are, then go straight to tournament details
        if (mProvider.fetchCurrentTournament() != null) {
            startActivity(new Intent(this, TournamentDetailsActivity.class));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mProvider.shutdown();
        mController.shutdown();
    }


    public void tourneyCreate(View view) {

        boolean validInputs = true;
        int entranceFee = 0, housePercentage = 0;

        EditText tourneyNameEditText = (EditText) findViewById(R.id.tourneyNameEditText);
        EditText entranceFeeEditText = (EditText) findViewById(R.id.entranceFeeEditText);
        EditText housePercentageEditText = (EditText) findViewById(R.id.housePercentageEditText);

        if (entranceFeeEditText.getText().toString().length() == 0) {
            entranceFeeEditText.setError(getString(R.string.invalid_fee_error));
            validInputs = false;
        } else {
            entranceFee = Integer.parseInt(entranceFeeEditText.getText().toString());
            if (entranceFee < 0) {
                validInputs = false;
                entranceFeeEditText.setError(getString(R.string.invalid_fee_error));
            }
        }

        if (housePercentageEditText.getText().toString().length() == 0) {
            housePercentageEditText.setError(getString(R.string.invalid_house_percentage_error));
            validInputs = false;
        } else {
            housePercentage = Integer.parseInt(housePercentageEditText.getText().toString());
            // 0 to 100 inclusive
            if (housePercentage <= 0 || housePercentage >= 100) {
                validInputs = false;
                housePercentageEditText.setError(getString(R.string.invalid_house_percentage_error));
            }
        }

        if (validInputs) {
            String tourneyName = tourneyNameEditText.getText().toString();
            Tournament tournament = new Tournament(tourneyName, entranceFee, housePercentage);
            mProvider.insertTournament(tournament);

            Toast.makeText(getApplicationContext(), "Tournament " + tourneyName + " created", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this, TournamentDetailsActivity.class));
        }
    }

}
