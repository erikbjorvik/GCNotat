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
import com.example.erikbjorvik.myapplication.backend.myApi.model.Entity;
import com.example.erikbjorvik.myapplication.backend.myApi.model.EntityCollection;
import com.example.erikbjorvik.myapplication.backend.myApi.model.Notat;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by erikbjorvik on 24.02.16.
 */
public class ListeFragment extends ListFragment {

    public final static String VALGT_ENTITETS_NR = "com.erikbjorvik.googlecn.entitetsNr";



    int mCurCheckPosition = 0;
    public ArrayAdapter<String> adap;
    List<String> items = new ArrayList<String>();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Populate list with our static array of title

        EntityCollection ec = DataSingleton.getInstance().getEntitetsListe();

        try {
            for (int i = 0; i < ec.getItems().size(); i++) {
                items.add(ec.getItems().get(i).getProperties().get("overskrift").toString());
            }
        }catch (Exception e) {
            Log.w("Ikke denne gangen", "du");
        }

        adap = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1,items);

        setListAdapter(adap);

    }

    public void oppdaterListe() {
        EntityCollection ec = DataSingleton.getInstance().getEntitetsListe();

        try {
            adap.clear();
            for (int i = 0; i < ec.getItems().size(); i++) {
                String inn = ec.getItems().get(i).getProperties().get("overskrift").toString();
                adap.insert(inn, i);
                Log.i("opp s iterasjon....", items.get(i));
            }
            adap.notifyDataSetChanged();
        }catch (Exception e) {
            Log.w("Ikke denne gangen", "du");
        }

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
        Intent intent = new Intent(getActivity(), LagreNyttNotatActivity.class);
        intent.putExtra(VALGT_ENTITETS_NR, position);
        Log.i("Du trøkka på ", position + " / " + id);
        startActivity(intent);

    }


}