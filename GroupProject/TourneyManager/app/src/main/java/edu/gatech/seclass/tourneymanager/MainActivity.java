package edu.gatech.seclass.tourneymanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    int mode; // flag for which mode the app is in.  0 = player, 1 = manager.
    ArrayList<String> players;
    ArrayList<String> tourneys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void layoutPlayer(View view) {

        setContentView(R.layout.activity_playerlist);
        ListView playerList = (ListView) findViewById(R.id.playerlist);

        players = new ArrayList<String>();
        players.add("John Tran");
        players.add("Rulan Gong");
        players.add("Yu Wang");
        players.add("Don Lee");


        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, players);

        playerList.setAdapter(arrayAdapter);
        playerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                Toast.makeText(getApplicationContext(), players.get(arg2), Toast.LENGTH_SHORT).show();
                builder.setTitle("Player Details");
                AlertDialog alertDialog = builder.create();
                alertDialog.setMessage("Number of tournaments won: 43\nMoney won: $29329");
                alertDialog.show();
            }


        });
    }

    public void layoutTourney(View view) {
        setContentView(R.layout.activity_matchlist);
        ListView tourneyList = (ListView) findViewById(R.id.tourneylist);

        tourneys = new ArrayList<String>();
        tourneys.add("John vs Rulan");
        tourneys.add("Yu vs Don");

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tourneys);

        tourneyList.setAdapter(arrayAdapter);
    }

    public void layoutManager(View view) {
        mode = 1;
        setContentView(R.layout.activity_manager);

        // int houseProfits = dummyclass.getProfits();
        // int numberTourneys = dummyclass.getTourneysCount();

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
