package com.petersburg_studio.prazdnikraduga.fragment.secondLevel;


import android.arch.lifecycle.ViewModelProviders;
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
import android.widget.Toast;

import com.petersburg_studio.prazdnikraduga.R;
import com.petersburg_studio.prazdnikraduga.adapters.othersActivity.OthersAdapter;
import com.petersburg_studio.prazdnikraduga.adapters.othersActivity.ShowsViewModel;
import com.petersburg_studio.prazdnikraduga.libs.refreshlib.WaveSwipeRefreshLayout;
import com.petersburg_studio.prazdnikraduga.tools.CheckInternetConnection;

import java.util.Objects;

public class ShowsFragment extends Fragment implements WaveSwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private OthersAdapter adapter = new OthersAdapter();
    private View view;
    private ProgressBar progressBar;
    private boolean isRefresh = false;
    private WaveSwipeRefreshLayout waveSwipeRefreshLayout;
    private Snackbar snackbar;
    private FloatingActionButton fab_up;
    private GridLayoutManager layoutManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater
                .inflate(R.layout.fragment_all_animators_recycler, container, false);

        Objects.requireNonNull(getActivity()).setTitle(R.string.shows);

        progressBar = view.findViewById(R.id.progress_bar);

        showLoadingIndicator(true);
        loadItems();

        fab_up = view.findViewById(R.id.fab_up);
        fab_up.hide();

        if (recyclerView != null) {
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (dy >= 0 || layoutManager.findFirstVisibleItemPosition() == 0) {
                        fab_up.hide();
                    } else {
                        fab_up.show();
                    }

                }
            });
        }

        fab_up.setOnClickListener(v -> recyclerView.smoothScrollToPosition(0));

        waveSwipeRefreshLayout = view.findViewById(R.id.main_swipe);
        waveSwipeRefreshLayout.setColorSchemeColors(Color.WHITE, Color.WHITE);
        waveSwipeRefreshLayout.setWaveColor(Color.argb(100, 120, 48, 141));
        waveSwipeRefreshLayout.setMaxDropHeight(500);
        waveSwipeRefreshLayout.setOnRefreshListener(this);

        return view;
    }

    private void loadItems() {
        if (CheckInternetConnection.checkConnection((Objects.requireNonNull(getActivity())).getApplicationContext())) {
            recyclerView = view.findViewById(R.id.recycler);
            layoutManager = new GridLayoutManager(getContext(), 2);
            recyclerView.setLayoutManager(layoutManager);
            ShowsViewModel productViewModel =
                    ViewModelProviders.of(this).get(ShowsViewModel.class);

            productViewModel.itemsPagedList.observe(this, items -> {
                if (progressBar.getVisibility() == View.VISIBLE) {
                    showLoadingIndicator(false);
                }
                recyclerView.setVisibility(View.VISIBLE);
                if (recyclerView.getVisibility() == View.VISIBLE) {
                    showLoadingIndicator(false);
                }
                adapter.submitList(items);

                if (isRefresh) {
                    Handler handler = new Handler();
                    handler.postDelayed(() -> waveSwipeRefreshLayout.setRefreshing(false), 0);
                }
            });
            recyclerView.setAdapter(adapter);
        } else {
            Toast.makeText(getActivity(), R.string.no_internet, Toast.LENGTH_SHORT).show();
            showLoadingIndicator(false);
            if (isRefresh) {
                waveSwipeRefreshLayout.setRefreshing(false);
            }
        }
    }

    public void showLoadingIndicator(boolean active) {
        if (active && !isRefresh) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            Handler handler = new Handler();
            handler.postDelayed(() -> progressBar.setVisibility(View.GONE), 0);
        }
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
