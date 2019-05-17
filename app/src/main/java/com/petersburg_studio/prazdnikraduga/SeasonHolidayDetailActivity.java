package com.petersburg_studio.prazdnikraduga;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.petersburg_studio.prazdnikraduga.arrays.SeasonsHolidays;

public class SeasonHolidayDetailActivity extends AppCompatActivity {
    public final static String EXTRA_SEASON_ID = "seasonId";
    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_all);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        int seasonId = (Integer) getIntent().getExtras().get(EXTRA_SEASON_ID);
        int seasonNameId = SeasonsHolidays.seasonsHolidays[seasonId].getName();
        String seasonName = getString(seasonNameId);
        assert actionBar != null;
        actionBar.setTitle(seasonName);
        actionBar.setDisplayHomeAsUpEnabled(true);

        int seasonImg = SeasonsHolidays.seasonsHolidays[seasonId].getImageResourceId();
        ImageView imageView = findViewById(R.id.image);
        imageView.setImageDrawable(ContextCompat.getDrawable(this, seasonImg));
        imageView.setContentDescription(seasonName);

        int seasonContent = SeasonsHolidays.seasonsHolidays[seasonId].getContent();
        TextView textSeason = findViewById(R.id.content);
        textSeason.setText(seasonContent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.animator_detail_app_bar_menu, menu);
        MenuItem shareItem = menu.findItem(R.id.top_menu_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        int seasonId = (Integer) getIntent().getExtras().get(EXTRA_SEASON_ID);
        int seasonNameId = SeasonsHolidays.seasonsHolidays[seasonId].getName();
        String seasonName = getString(seasonNameId);
        int urlResourceId = SeasonsHolidays.seasonsHolidays[seasonId].getUrl();
        final String url = getString(urlResourceId);
        final String msg_app = getString(R.string.share_url_season) + " " + seasonName + ":\n" + url;
        setShareActionIntent(msg_app);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final String phone_number = getString(R.string.phone)
                .replaceAll("\\s \\( \\) -", "");
        int seasonId = (Integer) getIntent().getExtras().get(EXTRA_SEASON_ID);
        final int urlResourceId = SeasonsHolidays.seasonsHolidays[seasonId].getUrl();
        final String url = getString(urlResourceId);
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.top_menu_call:
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone_number));
                startActivity(callIntent);
                return true;
            case R.id.top_menu_show_in_browser:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setShareActionIntent(String text) {
        Intent msgIntent = new Intent(Intent.ACTION_SEND);
        msgIntent.setType("text/plain");
        msgIntent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(msgIntent);
    }
}
