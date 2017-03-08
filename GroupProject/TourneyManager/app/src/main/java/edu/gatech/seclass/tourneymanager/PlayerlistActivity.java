package edu.gatech.seclass.tourneymanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.gatech.seclass.tourneymanager.data.TourneyManagerProvider;


public class PlayerlistActivity extends AppCompatActivity {

    int mode; // flag for which mode the app is in.  0 = player, 1 = manager.
    int tourneyActive; // flag for whether or not a tournament is active.  0 = inactive, 1 = active.

    ArrayList<Player> players;

    private ApplicationController mController;
    private TourneyManagerProvider mProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playerlist);

    }


    @Override
    protected void onStart() {
        super.onStart();


        mController = new ApplicationController(getApplicationContext());
        mProvider = new TourneyManagerProvider(getApplicationContext());

        mode = 1;
        tourneyActive = 0;

        final ListView playerList = (ListView) findViewById(R.id.playerlist);
        TextView playerListHeader = (TextView) findViewById(R.id.playerListHeader);
        Button addPlayer = (Button) findViewById(R.id.addplayer);
        final ArrayAdapter arrayAdapter;


        players = new ArrayList<>();
        players = mProvider.fetchPlayers();


        if (mode == 0) {
            playerListHeader.setText("Leaderboard");
            addPlayer.setVisibility(View.INVISIBLE);
        } else {
            addPlayer.setVisibility(View.VISIBLE);
        }

        arrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, players);

        playerList.setAdapter(arrayAdapter);
        playerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2,
                                    long arg3) {

                AlertDialog.Builder builder = new AlertDialog.Builder(PlayerlistActivity.this);
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
                                    mController.deletePlayer(players.get(arg2));
                                    arrayAdapter.notifyDataSetChanged();
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
                                    arrayAdapter.notifyDataSetChanged();

                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                        }
                    };

                }

            }

        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        mController.shutdown();
        mProvider.shutdown();
    }

    public void addPlayer(View view) {

        Intent mainIntent = new Intent(this, AddPlayerActivity.class);
        startActivity(mainIntent);

    }
}
