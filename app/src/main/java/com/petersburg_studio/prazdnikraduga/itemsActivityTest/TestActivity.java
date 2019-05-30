package com.petersburg_studio.prazdnikraduga.itemsActivityTest;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.petersburg_studio.prazdnikraduga.R;
import com.petersburg_studio.prazdnikraduga.itemsActivityTest.adapter.ProductAdapter;
import com.petersburg_studio.prazdnikraduga.itemsActivityTest.adapter.ProductViewModel;
import com.petersburg_studio.prazdnikraduga.itemsActivityTest.refreshlib.WaveSwipeRefreshLayout;
import com.petersburg_studio.prazdnikraduga.tools.CheckInternetConnection;

public class TestActivity extends AppCompatActivity implements WaveSwipeRefreshLayout.OnRefreshListener {

    private ProgressBar progressBar;
    private boolean isRefresh = false;
    private Snackbar snackbar;
    private FloatingActionButton fab_up;
    final ProductAdapter adapter = new ProductAdapter(this);
    RecyclerView recyclerView;
    GridLayoutManager layoutManager;
    private WaveSwipeRefreshLayout waveSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setUpUi();
        showLoadingIndicator(true);
        loadItems();

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

    public void setUpUi() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Products");
        setSupportActionBar(toolbar);
        progressBar = findViewById(R.id.progress_bar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        fab_up = findViewById(R.id.fab_up);

        waveSwipeRefreshLayout = findViewById(R.id.main_swipe);
        waveSwipeRefreshLayout.setColorSchemeColors(Color.WHITE, Color.WHITE);
        waveSwipeRefreshLayout.setWaveColor(Color.argb(100, 120, 48, 141));
        waveSwipeRefreshLayout.setMaxDropHeight(500);
        waveSwipeRefreshLayout.setOnRefreshListener(this);

        fab_up.hide();
    }

    private void loadItems() {

        final View parentView = findViewById(R.id.parentLayout);

        if (CheckInternetConnection.checkConnection(getApplicationContext())) {
            recyclerView = findViewById(R.id.recyclerView);
            layoutManager = new GridLayoutManager(this, 2);
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
        final View parentView = findViewById(R.id.parentLayout);
        if (snackbar != null) snackbar.dismiss();
        if (CheckInternetConnection.checkConnection(getApplicationContext())) {
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
