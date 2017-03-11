package edu.gatech.seclass.tourneymanager.tournament;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import edu.gatech.seclass.tourneymanager.R;
import edu.gatech.seclass.tourneymanager.Tournament;
import edu.gatech.seclass.tourneymanager.data.TourneyManagerProvider;

import static edu.gatech.seclass.tourneymanager.tournament.TournamentHistoryActivity.Sort.DATE;

public class TournamentHistoryActivity extends AppCompatActivity {

    private TourneyManagerProvider mProvider;

    private ListView tournamentHistoryList;
    private TextView sortByTextView;

    private Sort currentSort = DATE;

    protected enum Sort {
        DATE("Sorted by Date"),
        PROFIT("Sorted by Profit");
        String text;
        Sort(String text){
            this.text = text;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_history);

        tournamentHistoryList = (ListView) findViewById(R.id.tournamentListView);
        sortByTextView = (TextView) findViewById(R.id.sortByTextView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mProvider = new TourneyManagerProvider(getApplicationContext());
        displayTournamentList();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mProvider.shutdown();
    }

    public void toggleSort(View v) {
        Sort[] sortList = Sort.values();
        Sort newSort = sortList[0];
        for (int i = 1 ; i < sortList.length; i++) {
            if (sortList[i].equals(currentSort)) {
                break;
            }
            newSort = sortList[i];
        }
        currentSort = newSort;
        displayTournamentList();
    }

    protected void displayTournamentList() {
        sortByTextView.setText(currentSort.text);
        final List<Tournament> tournaments;
        switch(currentSort) {
            case PROFIT:
                tournaments = mProvider.fetchTournamentsOrderByProfit();
                break;
            case DATE:
                tournaments = mProvider.fetchTournamentsOrderByDate();
                break;
            default:
                tournaments = mProvider.fetchTournaments();
                break;
        }
        ArrayAdapter<Tournament> arrayAdapter = new ArrayAdapter<Tournament>(this, android.R.layout.simple_list_item_2, android.R.id.text1, tournaments) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                Tournament tournament = tournaments.get(position);

                text1.setText(position + 1 + ". " + tournament.getTournamentName());
                text2.setText("Profit: $" + tournament.getHouseProfit() + "\nDate: " + tournament.getStartDateTime());
                return view;
            }
        };

        tournamentHistoryList.setAdapter(arrayAdapter);
    }
}
