package edu.gatech.seclass.tourneymanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.gatech.seclass.tourneymanager.data.TourneyManagerProvider;


public class LeaderboardActivity extends AppCompatActivity {

    ArrayList<Player> players;
    ArrayList<Player> playersStaging;

    private ApplicationController mController;
    private TourneyManagerProvider mProvider;

    public class CustomComparator implements Comparator<Player> {
        @Override
        public int compare(Player p1, Player p2) {

            Integer player1Totals = 0, player2Totals = 0;

            List<Prize> p1Prizes = p1.fetchPrizes(mProvider);
            List<Prize> p2Prizes = p2.fetchPrizes(mProvider);

            for (Prize p: p1Prizes) {
                player1Totals += p.getPrizeAmount();
            }

            for (Prize p: p2Prizes) {
                player2Totals += p.getPrizeAmount();
            }

            return player1Totals.compareTo(player2Totals);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

    }

    @Override
    protected void onStart() {
        super.onStart();

        mController = new ApplicationController(getApplicationContext());
        mProvider = new TourneyManagerProvider(getApplicationContext());

        final ListView playerList = (ListView) findViewById(R.id.playerlist);
        final ArrayAdapter arrayAdapter;

        players = new ArrayList<>();
        playersStaging = mProvider.fetchPlayers();

        for (Player p: playersStaging) {
            if (p.getName() != null) {
                players.add(p);
            }
        }

        Collections.sort(players, new CustomComparator());

      /*  arrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_2, android.R.id.text1, players);
        TextView text1 = (TextView) findViewById(android.R.id.text1);
*/


        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, players) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(position + 1 + ". " + players.get(position).getName());

                ArrayList<Prize> prizes = mProvider.fetchPrizes(players.get(position));
                int moneyWon = 0;

                for (Prize p : prizes) {
                    moneyWon += p.getPrizeAmount();
                }

                text2.setText("Total Prizes: $ " + moneyWon);
                return view;
            }
        };


        playerList.setAdapter(arrayAdapter);
        playerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2,
                                    long arg3) {

                AlertDialog.Builder builder = new AlertDialog.Builder(LeaderboardActivity.this);

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

            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        mController.shutdown();
        mProvider.shutdown();
    }



}
