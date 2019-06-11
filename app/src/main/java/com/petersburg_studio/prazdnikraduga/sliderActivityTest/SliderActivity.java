package com.petersburg_studio.prazdnikraduga.sliderActivityTest;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.petersburg_studio.prazdnikraduga.R;
import com.petersburg_studio.prazdnikraduga.libs.waverefresh.BannerLayout;
import com.petersburg_studio.prazdnikraduga.sliderActivityTest.adapter.WebBannerAdapter;

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
    public static final String EXTRA_IMG15 = "extraImg15";
    public static final String EXTRA_IMG16 = "extraImg16";
    public static final String EXTRA_IMG17 = "extraImg17";
    public static final String EXTRA_IMG18 = "extraImg18";
    public static final String EXTRA_IMG19 = "extraImg19";
    public static final String EXTRA_IMG20 = "extraImg20";
    public static final String EXTRA_IMG21 = "extraImg21";
    public static final String EXTRA_IMG22 = "extraImg22";
    public static final String EXTRA_IMG23 = "extraImg23";
    public static final String EXTRA_IMG24 = "extraImg24";
    public static final String EXTRA_IMG25 = "extraImg25";
    public static final String EXTRA_IMG26 = "extraImg26";
    public static final String EXTRA_IMG27 = "extraImg27";
    public static final String EXTRA_IMG28 = "extraImg28";
    public static final String EXTRA_IMG29 = "extraImg29";
    public static final String EXTRA_IMG30 = "extraImg30";
    public static final String EXTRA_IMG31 = "extraImg31";
    public static final String EXTRA_IMG32 = "extraImg32";
    public static final String EXTRA_IMG33 = "extraImg33";
    public static final String EXTRA_IMG34 = "extraImg34";
    public static final String EXTRA_IMG35 = "extraImg35";
    public static final String EXTRA_IMG36 = "extraImg36";
    public static final String EXTRA_IMG37 = "extraImg37";
    public static final String EXTRA_IMG38 = "extraImg38";
    public static final String EXTRA_IMG39 = "extraImg39";
    public static final String EXTRA_IMG40 = "extraImg40";
    public static final String EXTRA_IMG41 = "extraImg41";
    public static final String EXTRA_IMG42 = "extraImg42";
    public static final String EXTRA_IMG43 = "extraImg43";
    public static final String EXTRA_IMG44 = "extraImg44";
    public static final String EXTRA_IMG45 = "extraImg45";
    public static final String EXTRA_IMG46 = "extraImg46";
    public static final String EXTRA_IMG47 = "extraImg47";
    public static final String EXTRA_IMG48 = "extraImg48";
    public static final String EXTRA_IMG49 = "extraImg49";
    public static final String EXTRA_IMG50 = "extraImg50";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        BannerLayout banner = findViewById(R.id.recycler);

        String text = (String) Objects.requireNonNull(getIntent().getExtras()).get(EXTRA_TEXT);

        List<String> list = new ArrayList<>();
        list.add((String) getIntent().getExtras().get(EXTRA_IMG));

        int i = 1;
        while (getIntent().getExtras().get(EXTRA_IMG + i) != null && i <= 50) {
            list.add((String) getIntent().getExtras().get(EXTRA_IMG + i));
            i++;
        }

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
