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
import com.petersburg_studio.prazdnikraduga.arrays.SeasonsHolidays;
import com.petersburg_studio.prazdnikraduga.R;
import com.petersburg_studio.prazdnikraduga.SeasonHolidayDetailActivity;

import java.util.Objects;

public class SeasonsHolidaysFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Objects.requireNonNull(getActivity()).setTitle(R.string.seasons);

        //set captions menu
        RecyclerView recyclerView = (RecyclerView) inflater
                .inflate(R.layout.fragment_all_recycler, container, false);

        //get extras for blocks
        String[] name = new String[SeasonsHolidays.seasonsHolidays.length];
        for (int i = 0; i < SeasonsHolidays.seasonsHolidays.length; i++) {
            int nameId = SeasonsHolidays.seasonsHolidays[i].getName();
            name[i] = getString(nameId);
        }

        int[] imageResourceId = new int[SeasonsHolidays.seasonsHolidays.length];
        for (int i = 0; i < imageResourceId.length; i++) {
            imageResourceId[i] = SeasonsHolidays.seasonsHolidays[i].getImageResourceId();
        }

        //set adapter for blocks
        AllCaptionedImagesAdapter adapter = new AllCaptionedImagesAdapter(name, imageResourceId);
        recyclerView.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);

        adapter.setListener(position -> {
            Intent intent = new Intent(getActivity(), SeasonHolidayDetailActivity.class);
            intent.putExtra(SeasonHolidayDetailActivity.EXTRA_SEASON_ID, position);
            Objects.requireNonNull(getActivity()).startActivity(intent);
        });
        return recyclerView;
    }
}
