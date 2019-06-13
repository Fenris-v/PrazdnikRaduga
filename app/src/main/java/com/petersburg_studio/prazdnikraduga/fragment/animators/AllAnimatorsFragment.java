package com.petersburg_studio.prazdnikraduga.fragment.animators;

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

import com.petersburg_studio.prazdnikraduga.R;
import com.petersburg_studio.prazdnikraduga.adapters.AllAnimatorsAdapter;
import com.petersburg_studio.prazdnikraduga.adapters.AnimatorViewModel;
import com.petersburg_studio.prazdnikraduga.libs.refreshlib.WaveSwipeRefreshLayout;
import com.petersburg_studio.prazdnikraduga.tools.CheckInternetConnection;

import java.util.Objects;

public class AllAnimatorsFragment extends Fragment implements WaveSwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private AllAnimatorsAdapter adapter = new AllAnimatorsAdapter();
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
                        fab_up.animate().translationY(-138);
                    } else {
                        fab_up.show();
                    }

                }
            });
        }

        fab_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.smoothScrollToPosition(0);
            }
        });

        waveSwipeRefreshLayout = view.findViewById(R.id.main_swipe);
        waveSwipeRefreshLayout.setColorSchemeColors(Color.WHITE, Color.WHITE);
        waveSwipeRefreshLayout.setWaveColor(Color.argb(100, 120, 48, 141));
        waveSwipeRefreshLayout.setMaxDropHeight(500);
        waveSwipeRefreshLayout.setOnRefreshListener(this);

        return view;
    }

    private void loadItems() {
        recyclerView = view.findViewById(R.id.recycler);
        layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        AnimatorViewModel productViewModel =
                ViewModelProviders.of(this).get(AnimatorViewModel.class);

        productViewModel.animatorPagedList.observe(this, items -> {
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
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        waveSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 0);
            }
        });
        recyclerView.setAdapter(adapter);
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
