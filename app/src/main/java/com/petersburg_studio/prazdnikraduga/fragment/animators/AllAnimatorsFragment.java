package com.petersburg_studio.prazdnikraduga.fragment.animators;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.petersburg_studio.prazdnikraduga.adapters.AnimatorsImagesAdapter;
import com.petersburg_studio.prazdnikraduga.AnimatorDetailActivity;
import com.petersburg_studio.prazdnikraduga.arrays.Animators;
import com.petersburg_studio.prazdnikraduga.R;

import java.util.Objects;

public class AllAnimatorsFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView animatorsRecycler = (RecyclerView) inflater
                .inflate(R.layout.fragment_all_recycler, container, false);
        String[] name = new String[Animators.animators.length];
        for (int i = 0; i < name.length; i++) {
            int nameId = Animators.animators[i].getName();
            name[i] = getString(nameId);
        }

        int[] imageResourceId = new int[Animators.animators.length];
        for (int i= 0; i < imageResourceId.length; i++) {
            imageResourceId[i] = Animators.animators[i].getImageResourceId();
        }

        AnimatorsImagesAdapter adapter = new AnimatorsImagesAdapter(name, imageResourceId);
        animatorsRecycler.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        animatorsRecycler.setLayoutManager(layoutManager);

        adapter.setListener(position -> {
            Intent intent = new Intent(getActivity(), AnimatorDetailActivity.class);
            intent.putExtra(AnimatorDetailActivity.EXTRA_ANIMATOR_ID, position);
            Objects.requireNonNull(getActivity()).startActivity(intent);
        });
        return animatorsRecycler;
    }

}
