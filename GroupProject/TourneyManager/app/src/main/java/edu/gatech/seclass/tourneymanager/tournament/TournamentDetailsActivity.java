package edu.gatech.seclass.tourneymanager.tournament;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.seclass.tourneymanager.Match;
import edu.gatech.seclass.tourneymanager.MatchList;
import edu.gatech.seclass.tourneymanager.R;
import edu.gatech.seclass.tourneymanager.Status;
import edu.gatech.seclass.tourneymanager.Tournament;
import edu.gatech.seclass.tourneymanager.data.TourneyManagerProvider;
import edu.gatech.seclass.tourneymanager.playerlist.PlayersInTournamentActivity;

public class TournamentDetailsActivity extends AppCompatActivity {
    /**
     * to display a specific tournament, start this activity with this value set as an extra in the intent
     */
    public static final String TOURNAMENT_ID_TO_SHOW = "tournament_id_to_show";

    private TourneyManagerProvider mProvider;

    private Tournament mTournament;

    // get references to all the views for displaying data
    TextView tourneyNameView;
    TextView entranceFeeView;
    TextView houseCutView;
    TextView tournamentStatusView;
    TextView numPlayersView;
    TextView profitView;
    TextView firstPrizeView;
    TextView secondPrizeView;
    TextView thirdPrizeView;


    private Button playerListButton;
    private Button matchListButton;
    private Button cancelButton;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        playerListButton = (Button) findViewById(R.id.playerListBtn);
        matchListButton = (Button) findViewById(R.id.matchListBtn);
        cancelButton = (Button) findViewById(R.id.cancelTournamentBtn);
        startButton = (Button) findViewById(R.id.startTournamentBtn);

        // get references to all the views for displaying data
        tourneyNameView = (TextView) findViewById(R.id.tourneyNameTextView);
        entranceFeeView = (TextView) findViewById(R.id.entranceFeeTextView);
        houseCutView = (TextView) findViewById(R.id.housePercentageTextView);
        tournamentStatusView = (TextView) findViewById(R.id.tournamentStatusTextView);
        numPlayersView = (TextView) findViewById(R.id.numPlayersTextView);
        profitView = (TextView) findViewById(R.id.profitTextView);
        firstPrizeView = (TextView) findViewById(R.id.firstPrizeTextView);
        secondPrizeView = (TextView) findViewById(R.id.secondPrizeTextView);
        thirdPrizeView = (TextView) findViewById(R.id.thirdPrizeTextView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mProvider = new TourneyManagerProvider(getApplicationContext());

        mTournament = fetchTournament();

        if (mTournament == null) {
            Toast.makeText(getApplicationContext(), getString(R.string.no_current_tournament_toast), Toast.LENGTH_SHORT).show();
            finish();
        } else {
            updateLayout();
            addButtonOnClickListeners();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mProvider.shutdown();
    }

    protected Tournament fetchTournament() {
        Tournament tournament = null;
        if (getIntent().getIntExtra(TOURNAMENT_ID_TO_SHOW, -1) >= 0) {
            tournament = mProvider.fetchTournament(getIntent().getIntExtra(TOURNAMENT_ID_TO_SHOW, -1));
        } else {
            tournament = mProvider.fetchCurrentTournament();
        }
        return tournament;
    }

    protected void updateLayout() {

        int playerCount = mTournament.getPlayerslist().size();

        if (Status.Ready.equals(mTournament.getStatus()) && !(playerCount == 8 || playerCount == 16)) {
            mTournament.setStatus(Status.Setup);
            mProvider.updateTournament(mTournament);
        }
        else if (Status.Setup.equals(mTournament.getStatus()) && (playerCount == 8 || playerCount == 16)) {
            mTournament.setStatus(Status.Ready);
            mProvider.updateTournament(mTournament);
        }

        // display tournament data
        tourneyNameView.setText(mTournament.getTournamentName());
        entranceFeeView.setText("$" + String.valueOf(mTournament.getEntryPrice()));
        houseCutView.setText(String.valueOf(mTournament.getHouseCut()) + "%");
        tournamentStatusView.setText(mTournament.getStatus().name());
        numPlayersView.setText(String.valueOf(playerCount));
        mTournament.setHouseProfit(computeProfit());
        profitView.setText("$" + mTournament.getHouseProfit());

        // calculate prizes
        double total = mTournament.getEntryPrice() * playerCount;
        double houseProfit = total * mTournament.getHouseCut() / 100.0;
        double prizePool = total - houseProfit;
        mTournament.setPrize1st((int) Math.round(prizePool*0.5));
        mTournament.setPrize2nd((int) Math.round(prizePool*0.3));
        mTournament.setPrize3rd((int) Math.round(prizePool*0.2));

        firstPrizeView.setText("$" + mTournament.getPrize1st());
        secondPrizeView.setText("$" + mTournament.getPrize2nd());
        thirdPrizeView.setText("$" + mTournament.getPrize3rd());

        matchListButton.setEnabled(mTournament.getMatchlist().size() > 0);

        // button appearance
        switch (mTournament.getStatus()) {
            case Cancelled:
                cancelButton.setEnabled(false);
                startButton.setEnabled(false);
                break;
            case Completed:
                cancelButton.setEnabled(false);
                startButton.setEnabled(false);
                break;
            case InProgress:
                cancelButton.setEnabled(true);
                startButton.setEnabled(true);
                for (Match match : mTournament.getMatchlist()) {
                    if (!Status.Completed.equals(match.getStatus())) {
                        startButton.setEnabled(false);
                    }
                }
                startButton.setText(getString(R.string.end_tournament_btn));
                break;
            case Ready:
                cancelButton.setEnabled(true);
                startButton.setEnabled(true);
                break;
            case Setup:
                cancelButton.setEnabled(true);
                startButton.setEnabled(false);
                startButton.setText(getString(R.string.start_tournament_btn));
                break;
            default:
                break;
        }
    }

    protected int computeProfit() {
        int entranceFee = mTournament.getEntryPrice();
        int houseCut = mTournament.getHouseCut();
        int numPlayers = mTournament.getPlayerslist().size();

        return (entranceFee * numPlayers * houseCut) / 100;
    }

    protected void addButtonOnClickListeners() {
        final Intent playerListIntent = new Intent(this, PlayersInTournamentActivity.class);
        playerListIntent.putExtra(TOURNAMENT_ID_TO_SHOW, mTournament.getTournamentId());

        playerListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(playerListIntent);
            }
        });

        final Intent matchListIntent = new Intent(this, MatchList.class);
        matchListIntent.putExtra(TOURNAMENT_ID_TO_SHOW, mTournament.getTournamentId());

        matchListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(matchListIntent);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTournament.cancelTournament();
                mProvider.updateTournament(mTournament);
                Toast.makeText(getApplicationContext(), "Tournament has been cancelled", Toast.LENGTH_SHORT).show();
                fetchTournament();
                updateLayout();
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButtonListner(v);
            }
        });
    }

    protected void startButtonListner(View view) {
        String message;
        switch(mTournament.getStatus()) {
            case Setup:
            case Ready:
                mTournament.startTournament();
                message = "Tournament has started";
                break;
            default:
                mTournament.endTournament();
                message = "Tournament has ended";
                break;
        }
        long result = mProvider.updateTournament(mTournament);
        if (result > 0) {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            fetchTournament();
            updateLayout();
        }
    }
}
