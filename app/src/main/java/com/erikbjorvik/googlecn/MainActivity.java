package com.erikbjorvik.googlecn;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*Konstanter.enhetsID = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query.Filter propertyFilter = new Query.FilterPredicate("enhetsID",
                Query.FilterOperator.EQUAL,  Konstanter.enhetsID);

        Query q = new Query("Person").setFilter(propertyFilter);

        // Use PreparedQuery interface to retrieve results
        PreparedQuery pq = datastore.prepare(q);


        for (Entity result : pq.asIterable()) {
            String overskrift = (String) result.getProperty("overskrift");
            String notatet = (String) result.getProperty("notatet");

            Log.i("Overskrift: ", overskrift);
            Log.i("Notatet: ", notatet);
        }*/

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
