package edu.gatech.seclass.tourneymanager.playerlist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.gatech.seclass.tourneymanager.Player;
import edu.gatech.seclass.tourneymanager.R;
import edu.gatech.seclass.tourneymanager.Tournament;
import edu.gatech.seclass.tourneymanager.tournament.TournamentDetailsActivity;

public class PlayersInTournamentActivity extends PlayerlistActivity{
    private Tournament mTournament;
    private final List<Player> playersNotInTournament = new ArrayList<Player>();
    private PlayersInTournamentActivity thisRef = this;

    @Override
    protected List<Player> fetchPlayers() {
        int tournamentId = getIntent().getIntExtra(TournamentDetailsActivity.TOURNAMENT_ID_TO_SHOW, -1);
        if (tournamentId > 0) {
            mTournament = mProvider.fetchTournament(tournamentId);
            return mTournament.getPlayerslist();
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void addPlayer(View view) {
        new PromptPlayersToAdd().execute(mTournament);
    }

    @Override
    public void deletePlayer(Player player) {
        new RemovePlayerFromTournament().execute(player);
    }

    class PromptPlayersToAdd extends AsyncTask<Tournament, Integer, List<Player>> {

        @Override
        protected List<Player> doInBackground(Tournament... params) {
            List<Player> allPlayers = mProvider.fetchPlayers();
            List<Player> playersInTournament = mProvider.fetchPlayers(mTournament);
            playersNotInTournament.clear();
            playersNotInTournament.addAll(allPlayers);
            for (Player player : allPlayers) {
                for (Player playerInTournament : playersInTournament) {
                    if (player.getUsername().equals(playerInTournament.getUsername())) {
                        playersNotInTournament.remove(player);
                    }
                }
            }
            return playersNotInTournament;
        }

        @Override
        protected void onPostExecute(List<Player> players) {
            AlertDialog.Builder builder = new AlertDialog.Builder(thisRef);
            builder.setTitle(getString(R.string.pick_player_prompt));
            builder.setAdapter(
                    new ArrayAdapter<>(thisRef, android.R.layout.simple_list_item_1, playersNotInTournament),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            new AddPlayerToTournament().execute(which);
                        }
                    });
            builder.show();
        }
    }

    class AddPlayerToTournament extends AsyncTask<Integer, Integer, Player> {

        @Override
        protected Player doInBackground(Integer... params) {
            Player playerToAdd = playersNotInTournament.get(params[0]);
            return playerToAdd;
        }

        @Override
        protected void onPostExecute(Player player) {
            super.onPostExecute(player);
            mProvider.addPlayerToTournament(mTournament, player);
            renderPlayerList();
        }
    }

    class RemovePlayerFromTournament extends AsyncTask<Player, Integer, Integer> {
        @Override
        protected Integer doInBackground(Player... params) {
            mProvider.removePlayerFromTournament(mTournament, params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            renderPlayerList();
        }
    }
}
