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

    private TourneyManagerProvider mProvider;

    private Tournament mTournament;

    ArrayList<Match> matches;

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
                if (position==1){
                    theWinner = matchClicked.getPlayer1();
                }else{
                    theWinner = matchClicked.getPlayer2();
                }
                matchClicked.endMatch(theWinner);
                populateGrid();
            }
        });
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
                System.out.println(m.getPlayer1().getName());
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
                public void onItemClick(AdapterView<?> adapterView,
                                        View view, int position, long rowId) {
                    if ( (position+1) %5 == 0)
                    {
                        int index = (position+1)/5-1;
                        Match matchClicked = matches.get(index-1); //Magic numbers
                        if(matchClicked.getStatus()== Status.Setup){
                            matchClicked.setStatus(Status.Ready);
                            populateGrid();
                        }else if (matchClicked.getStatus() == Status.Ready) {
                            matchClicked.startMatch();
                            populateGrid();
                        } else {
                            //TODO start new activity to choose player
                            selectWinner(matchClicked);
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

        mProvider = new TourneyManagerProvider(getApplicationContext());
        Tournament tournament = mProvider.fetchCurrentTournament();
        matches =  ((ArrayList<Match>) tournament.getMatchlist());

        for (Match match: matches){
            System.out.println(match.getMatchId());
        }

//        //TODO remove this part when DB is functional
//        if (matches==null) {
//            ArrayList<Player> players = new ArrayList<>();
//            players.add(new Player("player1", "p1", "", new Deck("")));
//            players.add(new Player("player2", "p2", "", new Deck("")));
//            matches = new ArrayList<>();
//            matches.add(new Match(1, new Tournament(1, 1, 1, players,null), 1, players.get(0), players.get(1), 0, players.get(0)));
//        }



        populateGrid();

    }
}
