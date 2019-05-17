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

import com.petersburg_studio.prazdnikraduga.adapters.FilterAnimatorsImagesAdapter;
import com.petersburg_studio.prazdnikraduga.AnimatorDetailActivity;
import com.petersburg_studio.prazdnikraduga.arrays.Animators;
import com.petersburg_studio.prazdnikraduga.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameAnimatorsFragment extends Fragment {
    final int ANIMATORS_TYPE = 3;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView animatorsRecycler = (RecyclerView) inflater
                .inflate(R.layout.fragment_all_recycler, container, false);
        int[] type = new int[Animators.animators.length];
        int[] name = new int[Animators.animators.length];
        int[] imageResourceId = new int[Animators.animators.length];
        int[] number = new int[Animators.animators.length];
        int y = 0;
        for (int i = 0; i < type.length; i++) {
            int typeNum = Animators.animators[i].getType();
            if (typeNum == ANIMATORS_TYPE && Animators.animators[i].getName() != 0) {
                type[y] = Animators.animators[i].getType();
                name[y] = Animators.animators[i].getName();
                number[y] = Animators.animators[i].getNumber();
                number[y] = Animators.animators[i].getNumber();
                imageResourceId[y] = Animators.animators[i].getImageResourceId();
                y++;
            }
        }

        //convert array with name to arrayList and delete null elements
        List<String> listOfNames = new ArrayList<>();
        for (int aName : name) {
            if (aName != 0) listOfNames.add(getString(aName));
        }

        //convert array with image to arrayList and delete elements with 0
        List<Integer> listOfImg = new ArrayList<>();
        for (int anImageResourceId : imageResourceId) {
            if (anImageResourceId != 0) {
                listOfImg.add(anImageResourceId);
            }
        }

        final List<Integer> listOfNumber = new ArrayList<>();
        for (int aNumber : number) {
            if (aNumber != 0) listOfNumber.add(aNumber);
        }

        FilterAnimatorsImagesAdapter adapter = new FilterAnimatorsImagesAdapter(listOfNames, listOfImg);
        animatorsRecycler.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        animatorsRecycler.setLayoutManager(layoutManager);

        adapter.setListener(position -> {
            position += listOfNumber.get(position) - 1;
            Intent intent = new Intent(getActivity(), AnimatorDetailActivity.class);
            intent.putExtra(AnimatorDetailActivity.EXTRA_ANIMATOR_ID, position);
            Objects.requireNonNull(getActivity()).startActivity(intent);
        });
        return animatorsRecycler;
    }

}
