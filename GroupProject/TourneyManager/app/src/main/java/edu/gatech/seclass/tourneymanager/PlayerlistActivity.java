package edu.gatech.seclass.tourneymanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.gatech.seclass.tourneymanager.data.TourneyManagerProvider;

public class PlayerlistActivity extends AppCompatActivity {

    ArrayList<Player> players;
    ArrayList<Player> playersStaging;

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

        final ListView playerList = (ListView) findViewById(R.id.playerlist);
        final ArrayAdapter arrayAdapter;
        final ArrayAdapter[] arrayAdapterNew = new ArrayAdapter[1];

        players = new ArrayList<>();
        playersStaging = new ArrayList<>();
        playersStaging = mProvider.fetchPlayers();

        for (Player p : playersStaging) {
            if (p.getName() != null) {
                players.add(p);
            }
        }

        arrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, players);

        playerList.setAdapter(arrayAdapter);
        playerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2,
                                    long arg3) {

                AlertDialog.Builder builder = new AlertDialog.Builder(PlayerlistActivity.this);


                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                mController.deletePlayer(players.get(arg2));
                                playersStaging = mProvider.fetchPlayers();

                                players.clear();
                                for (Player p : playersStaging) {
                                    if (p.getName() != null) {
                                        players.add(p);
                                    }
                                }

                                arrayAdapterNew[0] =
                                        new ArrayAdapter<Player>(PlayerlistActivity.this, android.R.layout.simple_list_item_1, players);
                                playerList.setAdapter(arrayAdapterNew[0]);

                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                builder.setMessage("Remove Player?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();

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
