package com.petersburg_studio.prazdnikraduga.test;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.petersburg_studio.prazdnikraduga.R;

public class ItemDetailActivity extends AppCompatActivity {

    public static final String EXTRA_TEXT = "extraText";
    public static final String EXTRA_IMG = "extraImg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        String text = (String) getIntent().getExtras().get(EXTRA_TEXT);
        String image = (String) getIntent().getExtras().get(EXTRA_IMG);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(text);
        actionBar.setDisplayHomeAsUpEnabled(true);

        TextView textView = findViewById(R.id.text_item);
        ImageView imageView = findViewById(R.id.image_item);

        textView.setText(text);
        Glide.with(this)
                .load(image)
                .placeholder(R.drawable.cat_wait)
                .into(imageView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        onBackPressed();
        return true;
    }
}
