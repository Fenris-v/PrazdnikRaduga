package com.petersburg_studio.prazdnikraduga.test.homescreen;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.petersburg_studio.prazdnikraduga.R;
import com.petersburg_studio.prazdnikraduga.test.pojo.Product;

public class TestActivity extends AppCompatActivity {

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setUpUi();
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        ProductViewModel productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        final ProductAdapter adapter = new ProductAdapter(this);

//        productViewModel.productPagedList.observe(this, adapter::submitList);

//        showLoadingIndicator(true);
        productViewModel.productPagedList.observe(this, new Observer<PagedList<Product>>() {
            @Override
            public void onChanged(@Nullable final PagedList<Product> products) {

                if (linearLayout.getVisibility() == View.VISIBLE) {
                    showLoadingIndicator(false);
                }
                recyclerView.setVisibility(View.VISIBLE);
                adapter.submitList(products);
            }
        });

        recyclerView.setAdapter(adapter);
    }

    public void showLoadingIndicator(boolean active) {
        if (active) {
            linearLayout.setVisibility(View.VISIBLE);
        } else {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    linearLayout.setVisibility(View.GONE);
                }
            }, 2000);
        }
    }

    public void setUpUi() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Products");
        setSupportActionBar(toolbar);
        linearLayout = findViewById(R.id.linear);
    }
}
