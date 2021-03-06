package edu.gatech.seclass.tourneymanager.playerlist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.tourneymanager.Player;
import edu.gatech.seclass.tourneymanager.Prize;
import edu.gatech.seclass.tourneymanager.R;
import edu.gatech.seclass.tourneymanager.Tournament;
import edu.gatech.seclass.tourneymanager.data.TourneyManagerProvider;

public class PlayerlistActivity extends AppCompatActivity {

    List<Player> players;
    List<Player> playersStaging;

    protected TourneyManagerProvider mProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playerlist);

    }

    @Override
    protected void onStart() {
        super.onStart();

        mProvider = new TourneyManagerProvider(getApplicationContext());
        renderPlayerList();
    }

    protected void renderPlayerList() {

        final ListView playerList = (ListView) findViewById(R.id.playerListView);
        final ArrayAdapter<Player> arrayAdapter;
        final ArrayAdapter[] arrayAdapterNew = new ArrayAdapter[1];

        players = new ArrayList<>();
        playersStaging = fetchPlayers();

        for (Player p : playersStaging) {
            if (p != null && p.getName() != null) {
                players.add(p);
            }
        }

        arrayAdapter = new ArrayAdapter<Player>(this, android.R.layout.simple_list_item_2, android.R.id.text1, players) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(players.get(position).getName());
                text2.setText(players.get(position).getUsername());
                return view;
            }
        };

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
                                deletePlayer(players.get(arg2));
                                playersStaging = fetchPlayers();

                                players.clear();
                                for (Player p : playersStaging) {
                                    if (p.getName() != null) {
                                        players.add(p);
                                    }
                                }

                                renderPlayerList();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                renderPlayerDetail(players.get(arg2), builder, dialogClickListener);

            }

        });

        TextView playerCountTextView = (TextView) findViewById(R.id.playerCountTextView);
        playerCountTextView.setText(String.valueOf(playersStaging.size()));
    }

    protected void renderPlayerDetail(Player player, AlertDialog.Builder builder, DialogInterface.OnClickListener dialogClickListener) {
        String name = player.getName();
        String username = player.getUsername();
        String phoneNumber = player.getPhoneNumber();


        builder.setMessage("Name: " + name + "\nUsername: " + username + "\nPhone Number: " + phoneNumber +"\n\nRemove Player?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mProvider.shutdown();
    }

    protected List<Player> fetchPlayers() {
        return mProvider.fetchPlayers();
    }

    public void addPlayer(View view) {

        Intent mainIntent = new Intent(this, AddPlayerActivity.class);
        startActivity(mainIntent);

    }

    public void deletePlayer(Player player) {
        Tournament tournament = mProvider.fetchCurrentTournament();
        if (tournament != null) {
            for (Player playerInTournament : tournament.getPlayerslist()) {
                if (playerInTournament.getUsername().equals(player.getUsername())) {
                    Toast.makeText(getApplicationContext(), "Cannot delete this player.  Please end or cancel the current tournament first.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
        mProvider.deletePlayer(player);
    }
}
