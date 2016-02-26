package com.erikbjorvik.googlecn.Fragmenter;

import android.app.ListFragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;


import android.widget.ListView;

import com.erikbjorvik.googlecn.Activities.LagreNyttNotatActivity;
import com.erikbjorvik.googlecn.LokaleData.DataSingleton;
import com.erikbjorvik.googlecn.R;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by erikbjorvik on 24.02.16.
 */
public class ListeFragment extends ListFragment {

    public final static String EXTRA_OVERSKRIFT = "com.erikbjorvik.googlecn.overskrift";
    public final static String EXTRA_NOTATET = "com.erikbjorvik.googlecn.notatet";
    public final static String EXTRA_DATO = "com.erikbjorvik.googlecn.dato";
    public final static String EXTRA_TOKEN = "com.erikbjorvik.googlecn.token";


    boolean mDualPane;
    int mCurCheckPosition = 0;
    public ArrayAdapter<String> adap;
    List<String> items = new ArrayList<String>(Arrays.asList(DataSingleton.getInstance().getListe()));

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Populate list with our static array of title
        String[] liste = DataSingleton.getInstance().getListe();


        adap = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1, liste);
        setListAdapter(adap);

    }

    public void oppdaterListe() {

        List<String> items = new ArrayList<String>(Arrays.asList(DataSingleton.getInstance().getListe()));
        adap = new ArrayAdapter<String>(getActivity(),
                R.layout.fragment_liste, items);
        setListAdapter(adap);
/*
        if (items.size()>0) {
            adap.clear();
            for (int i = 0; i < items.size(); i++) {
                adap.insert(items.get(i), i);
                Log.i("FITTE INN", "JADAD");
            }*/
            adap.notifyDataSetChanged();
        //}

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosition);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = getListView();
        listView.setDivider(new ColorDrawable(Color.WHITE));
        listView.setDividerHeight(3); // 3 pixels height

    }

    public void onListItemClick (ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Log.i("Du trøkka på ", position + " / " + id);
        Log.i("JSON Fisk: ", DataSingleton.getInstance().getJsonListe()[position].toString());


        JSONObject jObj = DataSingleton.getInstance().getJsonListe()[position];

        Intent intent = new Intent(getActivity(), LagreNyttNotatActivity.class);
        //intent.putExtra(EXTRA_OVERSKRIFT, (String) jObj.get("overskrift"));
        intent.putExtra(EXTRA_NOTATET, (String) jObj.get("notatet"));
        //intent.putExtra(EXTRA_DATO, (String) jObj.get("dato"));
        intent.putExtra(EXTRA_TOKEN, (String) jObj.get("token"));

        startActivity(intent);

    }


}