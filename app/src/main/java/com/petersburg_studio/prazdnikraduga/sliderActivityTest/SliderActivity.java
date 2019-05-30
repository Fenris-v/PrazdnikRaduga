package com.petersburg_studio.prazdnikraduga.sliderActivityTest;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.petersburg_studio.prazdnikraduga.R;
import com.petersburg_studio.prazdnikraduga.sliderActivityTest.adapter.WebBannerAdapter;
import com.petersburg_studio.prazdnikraduga.sliderActivityTest.lib.BannerLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SliderActivity extends AppCompatActivity {

    public static final String EXTRA_TEXT = "extraText";
    public static final String EXTRA_IMG = "extraImg";
    public static final String EXTRA_IMG1 = "extraImg1";
    public static final String EXTRA_IMG2 = "extraImg2";
    public static final String EXTRA_IMG3 = "extraImg3";
//    public static final String EXTRA_POS = "extraPos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        BannerLayout banner = findViewById(R.id.recycler);

        String text = (String) Objects.requireNonNull(getIntent().getExtras()).get(EXTRA_TEXT);

        List<String> list = new ArrayList<>();
        list.add((String) getIntent().getExtras().get(EXTRA_IMG));
        if (getIntent().getExtras().get(EXTRA_IMG1) != null) {
            list.add((String) getIntent().getExtras().get(EXTRA_IMG1));
        }
        if (getIntent().getExtras().get(EXTRA_IMG2) != null) {
            list.add((String) getIntent().getExtras().get(EXTRA_IMG2));
        }
        if (getIntent().getExtras().get(EXTRA_IMG3) != null) {
            list.add((String) getIntent().getExtras().get(EXTRA_IMG3));
        }
//        int pos = (int) getIntent().getExtras().get(EXTRA_POS);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(text);
        actionBar.setDisplayHomeAsUpEnabled(true);

        WebBannerAdapter webBannerAdapter = new WebBannerAdapter(this, list);

        banner.setAdapter(webBannerAdapter);
        TextView textView = findViewById(R.id.name);
        textView.setText(text);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        onBackPressed();
        return true;
    }
}
