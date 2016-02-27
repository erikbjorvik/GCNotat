package com.erikbjorvik.googlecn.AsyncTasks;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.Settings;
import android.util.Log;
import android.util.Pair;

import com.erikbjorvik.googlecn.Fragmenter.ListeFragment;
import com.erikbjorvik.googlecn.LokaleData.DataSingleton;
import com.example.erikbjorvik.myapplication.backend.myApi.MyApi;
import com.example.erikbjorvik.myapplication.backend.myApi.model.Entity;

import com.example.erikbjorvik.myapplication.backend.myApi.model.EntityCollection;
import com.example.erikbjorvik.myapplication.backend.myApi.model.Notat;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;


/**
 * Created by erikbjorvik on 23.02.16.
 */
public class HenteAsyncTask extends AsyncTask<Pair<Context, String>, Void, EntityCollection> {

    private static MyApi myApiService = null;
    private Context context;
    private Fragment view;
    private Activity activity;

    public HenteAsyncTask(Fragment view, Context context/*, Activity activity*/) {

        this.view = view;
        this.context = context;
        this.activity = activity;
    }

    @Override
    protected EntityCollection doInBackground(Pair<Context, String>... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0].first;
        String name = params[0].second;
        String enhetsID = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Log.i("SKJER","ja");
        try {
            EntityCollection sam = myApiService.hentNotater(enhetsID).execute();
            Log.i("SKJER", sam.toString());
            return sam;

        } catch (IOException e) {
            return null;
        }
    }

    protected void onPostExecute(EntityCollection respons) {
        Log.i("FANT","ja");
        Log.i("entiet", respons.toString());
        DataSingleton.getInstance().setEntitetsListe(respons);
        ListeFragment lf = (ListeFragment) view;
        lf.oppdaterListe();

    }
}
