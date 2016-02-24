package com.erikbjorvik.googlecn.Fragmenter;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.erikbjorvik.googlecn.LokaleData.DataSingleton;

public class ListeFragment extends ListFragment {

    boolean mDualPane;
    int mCurCheckPosition = 0;
    public Activity activity = getActivity();
    public ArrayAdapter<String> ar = new ArrayAdapter<String>(activity,
            android.R.layout.simple_list_item_activated_1, DataSingleton.getInstance().getListe());

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Populate list with our static array of titles.
        setListAdapter(this.ar);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosition);
    }


}