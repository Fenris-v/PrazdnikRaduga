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
import com.petersburg_studio.prazdnikraduga.arrays.Shows;
import com.petersburg_studio.prazdnikraduga.R;
import com.petersburg_studio.prazdnikraduga.ShowDetailActivity;

import java.util.Objects;

public class ShowsFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Objects.requireNonNull(getActivity()).setTitle(R.string.shows);

        //set captions menu
        RecyclerView recyclerView = (RecyclerView) inflater
                .inflate(R.layout.fragment_all_recycler, container, false);

        //get extras for blocks
        String[] name = new String[Shows.shows.length];
        for (int i = 0; i < Shows.shows.length; i++) {
            int nameId = Shows.shows[i].getName();
            name[i] = getString(nameId);
        }

        int[] imageResourceId = new int[Shows.shows.length];
        for (int i = 0; i < imageResourceId.length; i++) {
            imageResourceId[i] = Shows.shows[i].getImageResourceId();
        }

        //set adapter for blocks
        AllCaptionedImagesAdapter adapter = new AllCaptionedImagesAdapter(name, imageResourceId);
        recyclerView.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);

        adapter.setListener(position -> {
            Intent intent = new Intent(getActivity(), ShowDetailActivity.class);
            intent.putExtra(ShowDetailActivity.EXTRA_SHOW_ID, position);
            Objects.requireNonNull(getActivity()).startActivity(intent);
        });
        return recyclerView;
    }
}
