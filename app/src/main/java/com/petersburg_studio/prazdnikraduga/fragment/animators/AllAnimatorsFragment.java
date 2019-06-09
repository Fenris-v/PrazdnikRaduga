package com.petersburg_studio.prazdnikraduga.fragment.animators;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.petersburg_studio.prazdnikraduga.adapters.AnimatorsImagesAdapter;
import com.petersburg_studio.prazdnikraduga.AnimatorDetailActivity;
import com.petersburg_studio.prazdnikraduga.arrays.Animators;
import com.petersburg_studio.prazdnikraduga.R;
import com.petersburg_studio.prazdnikraduga.itemsActivityTest.adapter.ProductAdapter;
import com.petersburg_studio.prazdnikraduga.itemsActivityTest.adapter.ProductViewModel;
import com.petersburg_studio.prazdnikraduga.itemsActivityTest.refreshlib.WaveSwipeRefreshLayout;
import com.petersburg_studio.prazdnikraduga.tools.CheckInternetConnection;

import java.util.Objects;

public class AllAnimatorsFragment extends Fragment implements WaveSwipeRefreshLayout.OnRefreshListener {

    private ProgressBar progressBar;
    private boolean isRefresh = false;
    private Snackbar snackbar;
//    private FloatingActionButton fab_up;
    private final ProductAdapter adapter = new ProductAdapter(getActivity());
    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private WaveSwipeRefreshLayout waveSwipeRefreshLayout;
    View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater
                .inflate(R.layout.activity_test, container, false);

        progressBar = view.findViewById(R.id.progress_bar);

        waveSwipeRefreshLayout = view.findViewById(R.id.main_swipe);
        waveSwipeRefreshLayout.setColorSchemeColors(Color.WHITE, Color.WHITE);
        waveSwipeRefreshLayout.setWaveColor(Color.argb(100, 120, 48, 141));
        waveSwipeRefreshLayout.setMaxDropHeight(500);
        waveSwipeRefreshLayout.setOnRefreshListener(this);

//        fab_up.hide();



        return recyclerView;
    }

    public void showLoadingIndicator(boolean active) {
        if (active && !isRefresh) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.GONE);
                }
            }, 500);
        }
    }

    private void loadItems() {

        final View parentView = view.findViewById(R.id.parentLayout);

        if (CheckInternetConnection.checkConnection(Objects.requireNonNull(getActivity()).getApplicationContext())) {
            recyclerView = view.findViewById(R.id.recyclerView);
            layoutManager = new GridLayoutManager(getActivity(), 3);
            recyclerView.setLayoutManager(layoutManager);
            ProductViewModel productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

            productViewModel.productPagedList.observe(this, products -> {
                if (progressBar.getVisibility() == View.VISIBLE) {
                    showLoadingIndicator(false);
                }
                recyclerView.setVisibility(View.VISIBLE);
                if (recyclerView.getVisibility() == View.VISIBLE) {
                    showLoadingIndicator(false);
                }
                adapter.submitList(products);

                if (isRefresh) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            waveSwipeRefreshLayout.setRefreshing(false);
                        }
                    }, 0);
                }
            });
            recyclerView.setAdapter(adapter);

        } else {
            showLoadingIndicator(false);
            snackbar = Snackbar
                    .make(parentView, R.string.no_internet, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.close, v -> snackbar.dismiss());
            snackbar.show();
            if (isRefresh) {
                waveSwipeRefreshLayout.setRefreshing(false);
            }
        }
    }

    public void onClickUpFab(View view) {
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void onRefresh() {
        final View parentView = view.findViewById(R.id.parentLayout);
        if (snackbar != null) snackbar.dismiss();
        if (CheckInternetConnection.checkConnection(Objects.requireNonNull(getActivity()).getApplicationContext())) {
            isRefresh = true;
            loadItems();
        } else {
            snackbar = Snackbar
                    .make(parentView, R.string.no_internet, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.close, v -> snackbar.dismiss());
            snackbar.show();
            waveSwipeRefreshLayout.setRefreshing(false);
        }
    }
}
