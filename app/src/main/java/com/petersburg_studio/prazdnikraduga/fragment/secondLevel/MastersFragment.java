package com.petersburg_studio.prazdnikraduga.fragment.secondLevel;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.petersburg_studio.prazdnikraduga.adapters.AllCaptionedImagesAdapter;
import com.petersburg_studio.prazdnikraduga.arrays.Masters;
import com.petersburg_studio.prazdnikraduga.MasterDetailActivity;
import com.petersburg_studio.prazdnikraduga.R;

import java.util.Objects;

public class MastersFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Objects.requireNonNull(getActivity()).setTitle(R.string.masters);

        //set captions menu
        RecyclerView recyclerView = (RecyclerView) inflater
                .inflate(R.layout.fragment_all_recycler, container, false);

        //get extras for blocks
        String[] name = new String[Masters.masters.length];
        for (int i = 0; i < Masters.masters.length; i++) {
            int nameId = Masters.masters[i].getName();
            name[i] = getString(nameId);
        }

        int[] imageResourceId = new int[Masters.masters.length];
        for (int i = 0; i < imageResourceId.length; i++) {
            imageResourceId[i] = Masters.masters[i].getImageResourceId();
        }

        //set adapter for blocks
        AllCaptionedImagesAdapter adapter = new AllCaptionedImagesAdapter(name, imageResourceId);
        recyclerView.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);

        adapter.setListener(position -> {
            Intent intent = new Intent(getActivity(), MasterDetailActivity.class);
            intent.putExtra(MasterDetailActivity.EXTRA_MASTER_ID, position);
            Objects.requireNonNull(getActivity()).startActivity(intent);
        });
        return recyclerView;
    }
}
