package edu.gatech.seclass.tourneymanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.tourneymanager.data.TourneyManagerProvider;

public class MatchListPublicActivity extends AppCompatActivity {

    protected TourneyManagerProvider mProvider;
    protected Tournament mTournament;
    protected List<Match> matches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_list);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mProvider = new TourneyManagerProvider(getApplicationContext());
        mTournament = mProvider.fetchCurrentTournament();
        matches =  mTournament.getMatchlist();

        populateGrid();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mProvider.shutdown();
    }


    protected void populateGrid(){
        ArrayList<String> stringsList = new ArrayList<String>();
        stringsList.add("Player1");
        stringsList.add("Player2");
        stringsList.add("Status");
        stringsList.add("Winner");
        stringsList.add("Action");
        for (Match m : matches){

            if (m.getPlayer1() !=null) {
                stringsList.add(m.getPlayer1().getName());
                stringsList.add(m.getPlayer2().getName());
                stringsList.add(m.getStatus().toString());
                if (m.getStatus() == Status.Completed) {
                    stringsList.add("");
                } else {
                    stringsList.add("");
                }
            }
            else {
                stringsList.add("");
                stringsList.add("");
                stringsList.add("");
                if (m.getStatus() == Status.Completed) {
                    stringsList.add("");
                } else {
                    stringsList.add("");
                }
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
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long rowId) {
                    itemClickInteraction(adapterView,view,position,rowId);
                }
            });
        }

    }

    protected void itemClickInteraction(AdapterView<?> adapterView, View view, int position, long rowId) {
        // do nothing
    }
}
