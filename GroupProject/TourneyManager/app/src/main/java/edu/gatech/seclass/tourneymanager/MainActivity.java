package edu.gatech.seclass.tourneymanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.gatech.seclass.tourneymanager.data.TourneyManagerProvider;


public class MainActivity extends AppCompatActivity {

    int mode; // flag for which mode the app is in.  0 = player, 1 = manager.
    int tourneyActive; // flag for whether or not a tournament is active.  0 = inactive, 1 = active.

    ArrayList<String> playersmatches;
    ArrayList<Player> players;
    ArrayList<Match> matches;

    private ApplicationController mController;
    private TourneyManagerProvider mProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mController = new ApplicationController(getApplicationContext());
        mProvider = new TourneyManagerProvider(getApplicationContext());
    }

    @Override
    protected void onPause() {
        super.onPause();
        mController.shutdown();
        mProvider.shutdown();
    }

    public void layoutPlayer(View view) {

        tourneyActive = 0;
        // tourneyActive = dummyClass.getTourneyStatus();

        setContentView(R.layout.activity_playerandmatchlist);
        final ListView playerList = (ListView) findViewById(R.id.playerlist);
        TextView playerListHeader = (TextView) findViewById(R.id.playerListHeader);
        Button addPlayer = (Button) findViewById(R.id.addplayer);
        ArrayAdapter arrayAdapter;

        playersmatches = new ArrayList<>();

        /* If tournament is active, show matchlist.  If tournament is inactive, show players list and totals */
        if (tourneyActive == 1 && mode == 0) {
            playerListHeader.setText("Match List");

            matches = mProvider.fetchMatches(mController.fetchCurrentTournament());
            addPlayer.setVisibility(View.INVISIBLE);
            arrayAdapter =
                    new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, matches);

        } else {
            if (mode == 0) {
                playerListHeader.setText("Leaderboard");
                addPlayer.setVisibility(View.INVISIBLE);
            } else {
                addPlayer.setVisibility(View.VISIBLE);
            }

            players = mProvider.fetchPlayers();

            arrayAdapter =
                    new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, players);
        }

        playerList.setAdapter(arrayAdapter);
        playerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2,
                                    long arg3) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                if (tourneyActive == 0 && mode == 0) {

                    ArrayList<Prize> prizes = mProvider.fetchPrizes(players.get(arg2));
                    int tourneysWon = 0;
                    int moneyWon = 0;

                    for (Prize p : prizes) {
                        moneyWon += p.getPrizeAmount();
                        if (p.getPlace() == 1) {
                            tourneysWon++;
                        }
                    }

                    AlertDialog alertDialog = builder.create();
                    builder.setTitle("Player Details");
                    alertDialog.setMessage("Number of tournaments won: " + tourneysWon + "\nMoney won: $" + moneyWon);
                    Toast.makeText(getApplicationContext(), players.get(arg2).getName(), Toast.LENGTH_SHORT).show();
                    alertDialog.show();
                } else if (tourneyActive == 0 && mode == 1) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    //dummyClass.removePlayer(players.get(arg2));
                                    // arrayAdapter.NotifyDataSetChanged();
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                        }
                    };

                    builder.setMessage("Remove Player?").setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();
                } else if (tourneyActive == 1 && mode == 1) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    mController.deletePlayer(players.get(arg2));
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                        }
                    };

                    builder.setMessage("End Match?").setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();
                }

            }

        });

    }

    public void layoutManager(View view) {
        mode = 1;
        setContentView(R.layout.activity_manager);

        int houseProfits = 0;
        ArrayList<Tournament> tournaments = mProvider.fetchTournaments();
        int numberTourneys = tournaments.size();

        for (Tournament t : tournaments) {
            houseProfits += t.getHouseProfit();
        }

        TextView houseProfitText = (TextView) findViewById(R.id.totalprofit);
        houseProfitText.setText("Total profits:   $" + houseProfits);

        TextView numOfTourneys = (TextView) findViewById(R.id.numtourneys);
        numOfTourneys.setText("# of Tournaments:   " + numberTourneys);
    }

    public void createTournament(View view) {
        setContentView(R.layout.activity_tourneycreate);
    }

    public void addPlayer(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Player name to add:");


        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mController.registerPlayer(input.getText().toString());
                setContentView(R.layout.activity_main);

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
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


            int tourneyID = mProvider.fetchTournaments().size();
            mController.createTournament(tourneyID, housePercentage, entranceFee, playerlist);
            Toast.makeText(getApplicationContext(), "Tournament created!  ID: " + tourneyID, Toast.LENGTH_SHORT).show();
            tourneyActive = 1;

        }
    }

}
