package com.erikbjorvik.googlecn.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;

import com.erikbjorvik.googlecn.AsyncTasks.LagreAsyncTask;
import com.erikbjorvik.googlecn.Fragmenter.ListeFragment;
import com.erikbjorvik.googlecn.R;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;

public class LagreNyttNotatActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lagre_nytt_notat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        String notatet = intent.getStringExtra(ListeFragment.EXTRA_NOTATET);

        if (intent.hasExtra(ListeFragment.EXTRA_NOTATET)) {
            EditText notatET = (EditText) findViewById(R.id.notatet);
            notatET.setText(notatet);
        }

    }

    public void lagre(View view) {
        EditText overskrift = (EditText) findViewById(R.id.overskrift);
        EditText notatet = (EditText) findViewById(R.id.notatet);
        String[] liste = new String[3];
        Intent intent = getIntent();

        liste[0] = overskrift.getText().toString();
        liste[1] = notatet.getText().toString();

        if (intent.hasExtra(ListeFragment.EXTRA_NOTATET)) {


            Log.i("Dette er en endring", "ja");
            liste[2] = intent.getStringExtra(ListeFragment.EXTRA_TOKEN);
        }

        else {
            Log.i("Dette er en NYYYY", "ja");
            SecureRandom random = new SecureRandom();
            String token = new BigInteger(130, random).toString(32);
            liste[2] = token;
        }

        Log.i("Dette er liste2:",liste[2]);

        new LagreAsyncTask().execute(new Pair<Context, String[]>(this, liste));

        Intent intentForside = new Intent(this, MainActivity.class);
        startActivity(intentForside);

    }

}
