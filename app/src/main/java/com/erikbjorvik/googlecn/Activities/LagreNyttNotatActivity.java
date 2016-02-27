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
import android.widget.Toast;

import com.erikbjorvik.googlecn.AsyncTasks.LagreAsyncTask;
import com.erikbjorvik.googlecn.Fragmenter.ListeFragment;
import com.erikbjorvik.googlecn.LokaleData.DataSingleton;
import com.erikbjorvik.googlecn.R;
import com.example.erikbjorvik.myapplication.backend.myApi.model.Entity;
import com.example.erikbjorvik.myapplication.backend.myApi.model.JsonMap;


public class LagreNyttNotatActivity extends AppCompatActivity {

    public String key = "-";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lagre_nytt_notat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setView();

    }

    public void setView() {
        Intent intent = getIntent();

        if (intent.hasExtra(ListeFragment.VALGT_ENTITETS_NR)) {
            int entitetsNr = intent.getExtras().getInt(ListeFragment.VALGT_ENTITETS_NR);

            Entity entity = DataSingleton.getInstance().getEntitetsListe().getItems().get(entitetsNr);
            JsonMap notat = entity.getProperties();
            EditText overskriftET = (EditText) findViewById(R.id.overskrift);
            EditText notatET = (EditText) findViewById(R.id.notatet);

            overskriftET.setText((String) notat.get("overskrift"));
            notatET.setText((String) notat.get("notatet").toString());

            this.key = (String) entity.getProperties().get("token");
        }
        else {
            this.key ="-";
        }
    }

    public void onResume() {
        super.onResume();
        setView();
    }

    public void lagre(View view) {

        EditText overskrift = (EditText) findViewById(R.id.overskrift);
        EditText notatet = (EditText) findViewById(R.id.notatet);

        if (overskrift.getText().toString().equals(""))
            Toast.makeText(getBaseContext(),"Legg til en overskrift!", Toast.LENGTH_SHORT).show();
        else if (notatet.getText().toString().equals(""))
            Toast.makeText(getBaseContext(),"Legg til notattekst!", Toast.LENGTH_SHORT).show();
        else {
            String[] liste = new String[3];
            Intent intent = getIntent();

            liste[0] = overskrift.getText().toString();
            liste[1] = notatet.getText().toString();
            liste[2] = this.key;
            Log.i("Lagrer med key", this.key);
            new LagreAsyncTask().execute(new Pair<Context, String[]>(this, liste));

            Intent intentForside = new Intent(this, MainActivity.class);
            startActivity(intentForside);
        }

    }

}
