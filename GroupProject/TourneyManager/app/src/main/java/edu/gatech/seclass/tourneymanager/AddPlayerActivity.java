package edu.gatech.seclass.tourneymanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.seclass.tourneymanager.data.TourneyManagerProvider;


public class AddPlayerActivity extends AppCompatActivity {

    private ApplicationController mController;
    private TourneyManagerProvider mProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addplayer);

    }

    @Override
    protected void onStart() {
        super.onStart();

        mController = new ApplicationController(getApplicationContext());
        mProvider = new TourneyManagerProvider(getApplicationContext());

    }

    @Override
    protected void onStop() {
        super.onStop();
        mController.shutdown();
        mProvider.shutdown();
    }

    public void addPlayer(View view) {
        String username, name, phonenum, deck;

        EditText userNameInput = (EditText) findViewById(R.id.textUserName);
        username = userNameInput.getText().toString();

        EditText phoneNumInput = (EditText) findViewById(R.id.textPhoneNumber);
        phonenum = phoneNumInput.getText().toString();

        EditText nameInput = (EditText) findViewById(R.id.textPlayerName);
        name = nameInput.getText().toString();

        EditText deckInput = (EditText) findViewById(R.id.textDeck);
        deck = deckInput.getText().toString();

        Player Test = new Player(username, name, phonenum, new Deck(deck));
        mProvider.insertPlayer(Test);

        Toast.makeText(getApplicationContext(), "Player added: " , Toast.LENGTH_SHORT).show();
        userNameInput.setText("");
        phoneNumInput.setText("");
        nameInput.setText("");
        deckInput.setText("");

    }

}
