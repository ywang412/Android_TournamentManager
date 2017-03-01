package edu.gatech.seclass.tourneymanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<String> players;
    ArrayList<String> tournaments;

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
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, players);

        playerList.setAdapter(arrayAdapter);

    }
    public void layoutManager(View view) {
        setContentView(R.layout.activity_manager);
    }
}
