package edu.gatech.seclass.tourneymanager;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;

import edu.gatech.seclass.tourneymanager.data.TourneyManagerProvider;

public class MatchList extends AppCompatActivity {

    ArrayList<Match> matches;

    private void populateGrid(){
        ArrayList<String> stringsList = new ArrayList<String>();
        stringsList.add("Player1");
        stringsList.add("Player2");
        stringsList.add("Status");
        stringsList.add("Winner");
        stringsList.add("Action");
        for (Match m : matches){
            stringsList.add(m.getPlayer1().getName());
            stringsList.add(m.getPlayer2().getName());
            stringsList.add(m.getStatus().toString());
            if (m.getStatus() == Status.Completed) {
                stringsList.add(m.getWinner().getName());
            } else {
                stringsList.add("");
            }
            stringsList.add(m.getActionString());

        }
        final String[] toShow = new String[stringsList.size()];
        stringsList.toArray(toShow);

        ArrayAdapter<String> prizeAdapter =
                new ArrayAdapter<String>(this,
                        R.layout.activity_match_list,
                        R.id.reusableTextView,
                        toShow
                );

        GridView matchGrid = new GridView(this);
        setContentView(matchGrid);
        matchGrid.setNumColumns(5);

        matchGrid.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        matchGrid.setGravity(Gravity.CENTER_HORIZONTAL);
        matchGrid.setGravity(Gravity.CENTER_VERTICAL);
        matchGrid.setHorizontalSpacing(10);
        matchGrid.setAdapter(prizeAdapter);

        if(matches.size() > 0) {
            matchGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView,
                                        View view, int position, long rowId) {

                    // Generate a message based on the position
                    String message = "You clicked on " + toShow[position];
                    Snackbar.make(adapterView, message, Snackbar.LENGTH_LONG)
                            .show();

                    if ( (position+1) %5 == 0)
                    {
                        int index = (position+1)/5-1;
                        Match matchClicked = matches.get(index-1); //Magic numbers
                        if (matchClicked.getStatus() == Status.Ready) {
                            matchClicked.setStatus(Status.InProgress);
                            populateGrid();
                        } else {
                            //TODO start new activity to choose player
                        }
                    }
                }
            });
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_list);

        //TODO remove this part when DB is functional
        if (matches==null) {
            ArrayList<Player> players = new ArrayList<>();
            players.add(new Player("player1", "p1", "", new Deck("")));
            players.add(new Player("player2", "p2", "", new Deck("")));
            matches = new ArrayList<>();
            matches.add(new Match(1, new Tournament(1, 1, 1, players), 1, players.get(0), players.get(1)));
        }

        populateGrid();

    }
}
