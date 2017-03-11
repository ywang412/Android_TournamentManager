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

public class MatchList extends MatchListPublicActivity {

    @Override
    protected void onPause() {
        super.onPause();
        // update matches when we leave this activity
        mProvider.updateTournament(mTournament);
    }

    @Override
    protected void itemClickInteraction(AdapterView<?> adapterView, View view, int position, long rowId) {
        if ( (position+1) %5 == 0)
        {
            int index = (position+1)/5-1;
            Match matchClicked = matches.get(index-1); //Magic numbers
            switch(matchClicked.getStatus()) {
                case Setup:
                    matchClicked.setStatus(Status.Ready);
                    populateGrid();
                    break;
                case Ready:
                    matchClicked.startMatch();
                    populateGrid();
                    break;
                default:
                    //TODO start new activity to choose player
                    selectWinner(matchClicked);
                    break;
            }
        }
    }

    private  void selectWinner(final Match matchClicked) {
        final String[] toShow = new String[3];
        toShow[0] = "Please select winner:";
        toShow[1] = matchClicked.getPlayer1().getName();
        toShow[2] = matchClicked.getPlayer2().getName();
        ArrayAdapter<String> prizeAdapter =
                new ArrayAdapter<String>(this,
                        R.layout.activity_match_list,
                        R.id.reusableTextView,
                        toShow
                );

        GridView matchGrid = new GridView(this);
        setContentView(matchGrid);
        matchGrid.setNumColumns(1);

        matchGrid.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        matchGrid.setGravity(Gravity.CENTER_HORIZONTAL);
        matchGrid.setGravity(Gravity.CENTER_VERTICAL);
        matchGrid.setHorizontalSpacing(10);
        matchGrid.setAdapter(prizeAdapter);
        matchGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long rowId) {
                if (position==0) return;
                Player theWinner;
                Player theLoser;
                if (position==1){
                    theWinner = matchClicked.getPlayer1();
                    theLoser = matchClicked.getPlayer2();
                }else{
                    theLoser = matchClicked.getPlayer1();
                    theWinner = matchClicked.getPlayer2();
                }
                matchClicked.endMatch(theWinner);
                Match nextMatch = null;
                Match consolationMatch = null;
                int totalMatches = matches.size();
                boolean needConsolationMatch = totalMatches - matchClicked.getNextMatch() <= 1;
                for (Match match : matches) {
                    if (match.getMatchId() == matchClicked.getNextMatch()) {
                        nextMatch = match;
                    }

                    if (needConsolationMatch && match.getMatchId() == matchClicked.getNextMatch() + 1) {
                        consolationMatch = match;
                    }

                    if (nextMatch != null) {
                        if (!needConsolationMatch || consolationMatch != null) {
                            break;
                        }
                    }
                }
                if (nextMatch != null) {
                    if (matchClicked.getMatchId()%2 > 0) {
                        nextMatch.setPlayer1(theWinner);
                    }
                    else {
                        nextMatch.setPlayer2(theWinner);
                    }
                }
                if (consolationMatch != null) {
                    if (matchClicked.getMatchId()%2 > 0) {
                        consolationMatch.setPlayer1(theLoser);
                    }
                    else {
                        consolationMatch.setPlayer2(theLoser);
                    }
                }
                populateGrid();
            }
        });
    }
}
