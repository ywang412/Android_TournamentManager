package edu.gatech.seclass.tourneymanager.tournament;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.gatech.seclass.tourneymanager.ApplicationController;
import edu.gatech.seclass.tourneymanager.Match;
import edu.gatech.seclass.tourneymanager.Player;
import edu.gatech.seclass.tourneymanager.R;
import edu.gatech.seclass.tourneymanager.Tournament;
import edu.gatech.seclass.tourneymanager.data.TourneyManagerProvider;

public class CreateTournamentActivity extends AppCompatActivity {


    int mode; // flag for which mode the app is in.  0 = player, 1 = manager.
    int tourneyActive; // flag for whether or not a tournament is active.  0 = inactive, 1 = active.

    ArrayList<String> playersmatches;
    ArrayList<Player> players;
    ArrayList<Match> matches;


    private TourneyManagerProvider mProvider;
    private ApplicationController mController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tournament);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    protected void onStart() {
        super.onStart();
        mProvider = new TourneyManagerProvider(getApplicationContext());
        mController = new ApplicationController(getApplicationContext());
        // set tournament button behavior

    }

    @Override
    protected void onStop() {
        super.onStop();
        mProvider.shutdown();
        mController.shutdown();
    }


    public void tourneyCreate(View view) {

        boolean errorInvalid = false;
        int entranceFee = 0, entrants = 0, housePercentage = 0;

        EditText txt = (EditText) findViewById(R.id.entranceFee);
        if (txt.getText().toString().length() == 0) {
            txt.setError("Invalid Fee");
            errorInvalid = true;
        } else {
            entranceFee = Integer.parseInt(txt.getText().toString());
            if (entranceFee < 0) {
                errorInvalid = true;
                txt.setError("Invalid Fee");
            }
        }

        txt = (EditText) findViewById(R.id.housePercentage);
        if (txt.getText().toString().length() == 0) {
            txt.setError("Invalid House Percentage");
            errorInvalid = true;
        } else {
            housePercentage = Integer.parseInt(txt.getText().toString());
            // 0 to 100 inclusive
            if (housePercentage < 0 || housePercentage > 100) {
                errorInvalid = true;
                txt.setError("Invalid House Percentage");
            }
        }

        if (errorInvalid == false) {

            final ArrayList<Player> playerlist = new ArrayList<Player>();
            RadioGroup rg = (RadioGroup) findViewById(R.id.radioentrants);
            int entrantsValue = Integer.parseInt(((RadioButton) findViewById(rg.getCheckedRadioButtonId())).getText().toString());

            EditText name1 = (EditText) findViewById(R.id.name1);
            EditText name2 = (EditText) findViewById(R.id.name2);
            EditText name3 = (EditText) findViewById(R.id.name3);
            EditText name4 = (EditText) findViewById(R.id.name4);
            EditText name5 = (EditText) findViewById(R.id.name5);
            EditText name6 = (EditText) findViewById(R.id.name6);
            EditText name7 = (EditText) findViewById(R.id.name7);
            EditText name8 = (EditText) findViewById(R.id.name8);

            Player player1 = mProvider.fetchPlayer(name1.getText().toString());
            Player player2 = mProvider.fetchPlayer(name2.getText().toString());
            Player player3 = mProvider.fetchPlayer(name3.getText().toString());
            Player player4 = mProvider.fetchPlayer(name4.getText().toString());
            Player player5 = mProvider.fetchPlayer(name5.getText().toString());
            Player player6 = mProvider.fetchPlayer(name6.getText().toString());
            Player player7 = mProvider.fetchPlayer(name7.getText().toString());
            Player player8 = mProvider.fetchPlayer(name8.getText().toString());

            // if one or more players don't exist, need to output error

            playerlist.add(player1);
            playerlist.add(player2);
            playerlist.add(player3);
            playerlist.add(player4);
            playerlist.add(player5);
            playerlist.add(player6);
            playerlist.add(player7);
            playerlist.add(player8);

            mController.registerPlayer("Player1");
            mController.registerPlayer("Player2");
            mController.registerPlayer("Player3");
            mController.registerPlayer("Player4");
            mController.registerPlayer("Player5");
            mController.registerPlayer("Player6");
            mController.registerPlayer("Player7");
            mController.registerPlayer("Player8");
            mController.registerPlayer("TBD");


            Player winner = mProvider.fetchPlayer("TBD");

//            int tourneyID = mProvider.fetchTournaments().size();
            int tourneyID = 1;

            mController.createTournament(tourneyID, housePercentage, entranceFee, playerlist, winner);
            Toast.makeText(getApplicationContext(), "Tournament created!  ID: " + tourneyID, Toast.LENGTH_SHORT).show();

            System.out.println("succeed!!!!!!!!!!");

            Tournament currentT = mController.fetchCurrentTournament();

            for (Match match :currentT.getMatchlist()){
                System.out.println("succeed!!!!!!!!!!"+match.getMatchId());
            }

        }
    }

}
