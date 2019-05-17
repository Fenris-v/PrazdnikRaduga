package com.petersburg_studio.prazdnikraduga.test;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
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

public class TestActivity extends AppCompatActivity {

    //    !!!!!!!
//    NEW CODE FOR TEST BEGIN
//    !!!!!!!
    Snackbar snackbar;
    private SwipeRefreshLayout refresh;
    private boolean isRefresh = false;
//    !!!!!!!
//    NEW CODE FOR TEST END
//    !!!!!!!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Products");
        actionBar.setDisplayHomeAsUpEnabled(true);

//    !!!!!!!
//    NEW CODE FOR TEST BEGIN
//    !!!!!!!
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
//    !!!!!!!
//    NEW CODE FOR TEST END
//    !!!!!!!

//        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
//        recyclerView.setLayoutManager(layoutManager);
//
//        ProductViewModel productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
//        final ProductAdapter adapter = new ProductAdapter(this);
//
//        productViewModel.productPagedList.observe(this, adapter::submitList);
//        recyclerView.setAdapter(adapter);
    }
    //    !!!!!!!
//    NEW CODE FOR TEST BEGIN
//    !!!!!!!
    private void loadItems() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        ProductViewModel productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        final ProductAdapter adapter = new ProductAdapter(this);

        productViewModel.productPagedList.observe(this, adapter::submitList);
        recyclerView.setAdapter(adapter);
    }

    public void setVisibleProgressBar() {
        ProgressBar progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void setInvisibleProgressBar() {
        final ProgressBar progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.INVISIBLE);
    }
    //    !!!!!!!
//    NEW CODE FOR TEST END
//    !!!!!!!
}

//    !!!!!!!
//    NEW CODE FOR TEST BEGIN
//    !!!!!!!

//    !!!!!!!
//    NEW CODE FOR TEST END
//    !!!!!!!