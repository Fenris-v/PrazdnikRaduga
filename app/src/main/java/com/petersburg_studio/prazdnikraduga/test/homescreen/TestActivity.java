package com.petersburg_studio.prazdnikraduga.test.homescreen;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
    final ProductAdapter adapter = new ProductAdapter(this);
    RecyclerView recyclerView;
    GridLayoutManager layoutManager;

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
            }, 0);
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


//                ProductDataSource productDataSource = new ProductDataSource();
//                productDataSource.getRequestFailureLiveData().observe(this, new Observer<RequestFailure>() {
//                    @Override
//                    public void onChanged(@Nullable RequestFailure requestFailure) {
//                        System.out.println(123);
//                        if (requestFailure == null) {
//                            return;
//                        }
//
//                        Snackbar.make(parentView, requestFailure.getErrorMessage(), Snackbar.LENGTH_INDEFINITE)
//                                .setAction("Retry", new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        requestFailure.getRetryable().retry();
//                                    }
//                                }).show();
//                    }
//                });

                if (isRefresh) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            refresh.setRefreshing(false);
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
                refresh.setRefreshing(false);
            }
        }
    }

    public void onClickUpFab(View view) {
        recyclerView.smoothScrollToPosition(0);
    }
}