package com.erikbjorvik.googlecn.Adaptere;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.erikbjorvik.myapplication.backend.myApi.model.Entity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by erikbjorvik on 24.02.16.
 */
class ListeAdapter extends ArrayAdapter<String> {

    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

    public ListeAdapter(Context context, int textViewResourceId,
                              List<String> objects) {
        super(context, textViewResourceId, objects);

        for (int i = 0; i < objects.size(); ++i) {
            mIdMap.put(objects.get(i), i);
        }
    }

    @Override
    public long getItemId(int position) {
        String item = getItem(position);
        return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

}

