package com.erikbjorvik.googlecn.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.erikbjorvik.googlecn.AsyncTasks.HenteAsyncTask;
import com.erikbjorvik.googlecn.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String enhetsID = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        HenteAsyncTask hasync = new HenteAsyncTask(getFragmentManager().findFragmentById(R.id.listeFrag), this);
        hasync.execute(new Pair<Context, String>(this, enhetsID));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tilNyttNotatActivity(View view) {
        Intent intent = new Intent(this, LagreNyttNotatActivity.class);
        startActivity(intent);
    }
}
