package com.erikbjorvik.googlecn.Fragmenter;

import android.app.ListFragment;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.erikbjorvik.googlecn.LokaleData.DataSingleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by erikbjorvik on 24.02.16.
 */
public class ListeFragment extends ListFragment {
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
                android.R.layout.simple_list_item_activated_1, items);
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


}