package com.petersburg_studio.prazdnikraduga.fragment.animators;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.petersburg_studio.prazdnikraduga.R;
import com.petersburg_studio.prazdnikraduga.adapters.AllAnimatorsAdapter;
import com.petersburg_studio.prazdnikraduga.adapters.AnimatorViewModel;

public class AllAnimatorsFragment extends Fragment {

    private final AllAnimatorsAdapter adapter = new AllAnimatorsAdapter();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView recyclerView = (RecyclerView) inflater
                .inflate(R.layout.fragment_all_recycler, container, false);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        AnimatorViewModel productViewModel =
                ViewModelProviders.of(this).get(AnimatorViewModel.class);

        productViewModel.animatorPagedList.observe(this, items -> {
            recyclerView.setVisibility(View.VISIBLE);
            adapter.submitList(items);
        });
        recyclerView.setAdapter(adapter);

        return recyclerView;
    }
}
