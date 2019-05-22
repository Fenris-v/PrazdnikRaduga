package com.petersburg_studio.prazdnikraduga.test.homescreen;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.petersburg_studio.prazdnikraduga.R;
import com.petersburg_studio.prazdnikraduga.tools.CheckInternetConnection;

public class TestActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private SwipeRefreshLayout refresh;
    private boolean isRefresh = false;
    private Snackbar snackbar;
    FloatingActionButton fab_up;
    NestedScrollView nestedScrollView;
    final ProductAdapter adapter = new ProductAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setUpUi();
        showLoadingIndicator(true);
        loadItems();

        refresh = findViewById(R.id.refresh);
        refresh.setColorSchemeResources(R.color.refresh2, R.color.refresh, R.color.refresh1);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                loadItems();
            }
        });

        fab_up.hide();
        if (nestedScrollView != null) {
            nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                @Override
                public void onScrollChange(NestedScrollView nestedScrollView, int x, int y, int oldX, int oldY) {
                    if (y < oldY && y != 0) {
                        fab_up.show();
                    } else {
                        fab_up.hide();
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
            }, 1000);
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
        nestedScrollView = findViewById(R.id.scroll);
    }

    private void loadItems() {

        final View parentView = findViewById(R.id.parentLayout);

        if (CheckInternetConnection.checkConnection(getApplicationContext())) {
            final RecyclerView recyclerView = findViewById(R.id.recyclerView);
            GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
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
                            refresh.setRefreshing(false);
                        }
                    }, 1000);
                }
            });

            recyclerView.setAdapter(adapter);
            System.out.println("fuck");
            System.out.println(recyclerView);
        } else {
            showLoadingIndicator(false);
            snackbar = Snackbar
                    .make(parentView, R.string.no_internet, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.close, v -> snackbar.dismiss());
            snackbar.show();
            if (isRefresh) {
                refresh.setRefreshing(false);
            }
        }
    }

    public void onClickUpFab(View view) {
        nestedScrollView.smoothScrollTo(0, 0);
    }
}