package edu.gatech.seclass.tourneymanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.tourneymanager.data.TourneyManagerProvider;


public class AddPlayerActivity extends AppCompatActivity implements OnItemSelectedListener {

    private ApplicationController mController;
    private TourneyManagerProvider mProvider;
    Spinner deckInput;

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

        deckInput = (Spinner) findViewById(R.id.deckspinner);
        deckInput.setOnItemSelectedListener(this);

        List<String> decks = new ArrayList<>();
        decks.add("Engineer");
        decks.add("Buzz");
        decks.add("Sideways");
        decks.add("Wreck");
        decks.add("T");
        decks.add("RAT");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, decks);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deckInput.setAdapter(dataAdapter);


    }

    @Override
    protected void onStop() {
        super.onStop();
        mController.shutdown();
        mProvider.shutdown();
    }

    public void addPlayer(View view) {
        String username, name, phonenum, deck;
        int errorFound = 0;

        EditText userNameInput = (EditText) findViewById(R.id.textUserName);
        username = userNameInput.getText().toString();

        if (username.isEmpty()) {
            errorFound = 1;
            userNameInput.setError("Invalid username");
        }

        EditText phoneNumInput = (EditText) findViewById(R.id.textPhoneNumber);
        phonenum = phoneNumInput.getText().toString();

        if (phonenum.isEmpty()) {
            errorFound = 1;
            phoneNumInput.setError("Invalid phone number");
        }

        EditText nameInput = (EditText) findViewById(R.id.textPlayerName);
        name = nameInput.getText().toString();

        if (name.isEmpty()) {
            errorFound = 1;
            nameInput.setError("Invalid name");
        }

        deck = String.valueOf(deckInput.getSelectedItem());

        Player Test = new Player(username, name, phonenum, new Deck(deck));
        mProvider.insertPlayer(Test);

        if (errorFound == 0) {
            Toast.makeText(getApplicationContext(), "Player added: " + username, Toast.LENGTH_SHORT).show();
            userNameInput.setText("");
            phoneNumInput.setText("");
            nameInput.setText("");
            deckInput.setSelection(1);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }
    public void onNothingSelected(AdapterView<?> arg0) {
    }

}
