package com.erikbjorvik.googlecn.AsyncTasks;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.Settings;
import android.util.Log;
import android.util.Pair;
import android.widget.ArrayAdapter;

import com.erikbjorvik.googlecn.Activities.MainActivity;
import com.erikbjorvik.googlecn.Fragmenter.ListeFragment;
import com.erikbjorvik.googlecn.LokaleData.DataSingleton;
import com.example.erikbjorvik.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by erikbjorvik on 23.02.16.
 */
public class HenteAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {

    private static MyApi myApiService = null;
    private Context context;
    private Fragment view;

    public HenteAsyncTask(Fragment view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
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
        try {
            return myApiService.hentNotater(enhetsID).execute().toString();

        } catch (IOException e) {
            return null;
        }
    }

    protected void onPostExecute(String res) {
        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(res);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray arr = (JSONArray) jsonObject.get("items");
            Log.i("ARRE", arr.toString());

            String[] retur = null;

            for (int i = 0; i < arr.size(); i++) {
                JSONObject cur = (JSONObject) arr.get(i);
                JSONObject prop = (JSONObject) cur.get("properties");
                //retur[i] = (String) prop.get("overskrift");
                Log.i("propper", prop.toString());

            }

            //ListeFragment lf = (ListeFragment) view;

            String[] liste = {"Svinekuk", "Svinefitte"};
            /*DataSingleton.getInstance().setListe(liste);
            ListeFragment lf = (ListeFragment) view;
            lf.ar.clear();
            lf.ar = new ArrayAdapter<String>(lf.activity,
                    android.R.layout.simple_list_item_activated_1, DataSingleton.getInstance().getListe());
            lf.ar.notifyDataSetChanged();*/
            //lf.ad.notifyDataSetChanged();

        } catch (ParseException e) {

        }

    }
}
