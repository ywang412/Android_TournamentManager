package edu.gatech.seclass.tourneymanager.tournament;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import edu.gatech.seclass.tourneymanager.R;

public class CreateTournamentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tournament);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void tourneyCreate(View view) {
        // TODO need to be implemented
        Toast.makeText(getApplicationContext(), "Create Tournament button pressed", Toast.LENGTH_SHORT).show();
    }
}
