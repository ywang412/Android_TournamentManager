package edu.gatech.seclass.tourneymanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    int mode; // flag for which mode the app is in.  0 = player, 1 = manager.
    int tourneyActive; // flag for whether or not a tournament is active.  0 = inactive, 1 = active.

    ArrayList<String> playersmatches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void layoutPlayer(View view) {

        tourneyActive = 0;
        // tourneyActive = dummyClass.getTourneyStatus();

        setContentView(R.layout.activity_playerandmatchlist);
        ListView playerList = (ListView) findViewById(R.id.playerlist);
        TextView playerListHeader = (TextView) findViewById(R.id.playerListHeader);
        Button addPlayer = (Button) findViewById(R.id.addplayer);

        playersmatches = new ArrayList<String>();

        /* If tournament is active, show matchlist.  If tournament is inactive, show players list and totals */
        if (tourneyActive == 1 && mode == 0) {
            playerListHeader.setText("Match List");
            playersmatches.add("John vs Rulan");
            playersmatches.add("Don vs Yu");
            playersmatches.add("Yu vs Rulan");
            playersmatches.add("John vs Don");
            addPlayer.setVisibility(View.INVISIBLE);

            //playersmatches = dummyClass.getMatchList();

        } else {
            if (mode == 0) {
                playerListHeader.setText("Leaderboard");
                addPlayer.setVisibility(View.INVISIBLE);
            } else {
                addPlayer.setVisibility(View.VISIBLE);
            }

            playersmatches.add("John Tran - $1029");
            playersmatches.add("Rulan Gong - $993");
            playersmatches.add("Yu Wang - $970");
            playersmatches.add("Don Lee - $100");
            //playersmatches = dummyClass.getLeaderboard();  // sorted by winnings

        }

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, playersmatches);

        playerList.setAdapter(arrayAdapter);
        playerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                if (tourneyActive == 0 && mode == 0) {

                    //int tourneysWon = dummyClass.getTourneysWon(players.get(arg2));
                    //int moneyWon = dummyClass.getMoneyWon(players.get(arg2));
                    //alertDialog.setMessage("Number of tournaments won: " + tourneysWon + "\nMoney won: $" + moneyWon);
                    AlertDialog alertDialog = builder.create();
                    builder.setTitle("Player Details");
                    alertDialog.setMessage("Number of tournaments won: 43\nMoney won: $29329");
                    Toast.makeText(getApplicationContext(), playersmatches.get(arg2), Toast.LENGTH_SHORT).show();
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
                }

            }

        });

    }

    public void layoutManager(View view) {
        mode = 1;
        setContentView(R.layout.activity_manager);

        // int houseProfits = dummyClass.getProfits();
        // int numberTourneys = dummyClass.getTourneysCount();

        int houseProfits = 650;
        int numberTourneys = 14;

        TextView houseProfitText = (TextView) findViewById(R.id.totalprofit);
        houseProfitText.setText("Total profits:   $" + houseProfits);

        TextView numOfTourneys = (TextView) findViewById(R.id.numtourneys);
        numOfTourneys.setText("# of Tournaments:   " + numberTourneys);

    }

    public void createTournament(View view) {
        setContentView(R.layout.activity_createtournament);

    }
}
