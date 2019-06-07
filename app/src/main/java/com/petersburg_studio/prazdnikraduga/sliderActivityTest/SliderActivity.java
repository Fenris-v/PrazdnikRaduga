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
    public static final String EXTRA_IMG4 = "extraImg4";
    public static final String EXTRA_IMG5 = "extraImg5";
    public static final String EXTRA_IMG6 = "extraImg6";
    public static final String EXTRA_IMG7 = "extraImg7";
    public static final String EXTRA_IMG8 = "extraImg8";
    public static final String EXTRA_IMG9 = "extraImg9";
    public static final String EXTRA_IMG10 = "extraImg10";
    public static final String EXTRA_IMG11 = "extraImg11";
    public static final String EXTRA_IMG12 = "extraImg12";
    public static final String EXTRA_IMG13 = "extraImg13";
    public static final String EXTRA_IMG14 = "extraImg14";
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
        if (getIntent().getExtras().get(EXTRA_IMG4) != null) {
            list.add((String) getIntent().getExtras().get(EXTRA_IMG4));
        }
        if (getIntent().getExtras().get(EXTRA_IMG5) != null) {
            list.add((String) getIntent().getExtras().get(EXTRA_IMG5));
        }
        if (getIntent().getExtras().get(EXTRA_IMG6) != null) {
            list.add((String) getIntent().getExtras().get(EXTRA_IMG6));
        }
        if (getIntent().getExtras().get(EXTRA_IMG7) != null) {
            list.add((String) getIntent().getExtras().get(EXTRA_IMG7));
        }
        if (getIntent().getExtras().get(EXTRA_IMG8) != null) {
            list.add((String) getIntent().getExtras().get(EXTRA_IMG8));
        }
        if (getIntent().getExtras().get(EXTRA_IMG9) != null) {
            list.add((String) getIntent().getExtras().get(EXTRA_IMG9));
        }
        if (getIntent().getExtras().get(EXTRA_IMG10) != null) {
            list.add((String) getIntent().getExtras().get(EXTRA_IMG10));
        }
        if (getIntent().getExtras().get(EXTRA_IMG11) != null) {
            list.add((String) getIntent().getExtras().get(EXTRA_IMG11));
        }
        if (getIntent().getExtras().get(EXTRA_IMG12) != null) {
            list.add((String) getIntent().getExtras().get(EXTRA_IMG12));
        }
        if (getIntent().getExtras().get(EXTRA_IMG13) != null) {
            list.add((String) getIntent().getExtras().get(EXTRA_IMG13));
        }
        if (getIntent().getExtras().get(EXTRA_IMG14) != null) {
            list.add((String) getIntent().getExtras().get(EXTRA_IMG14));
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
