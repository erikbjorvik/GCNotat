package com.erikbjorvik.googlecn;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;

public class LagreNyttNotatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lagre_nytt_notat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void lagre(View view) {
        EditText overskrift = (EditText) findViewById(R.id.overskrift);
        EditText notatet = (EditText) findViewById(R.id.notatet);
        String[] liste = {overskrift.getText().toString(), notatet.getText().toString()};

        new EndpointsAsyncTask().execute(new Pair<Context, String[]>(this, liste));
        
    }

}
