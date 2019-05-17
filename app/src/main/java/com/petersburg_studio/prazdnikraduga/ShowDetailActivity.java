package com.petersburg_studio.prazdnikraduga;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.petersburg_studio.prazdnikraduga.arrays.Shows;

public class ShowDetailActivity extends AppCompatActivity {
    public final static String EXTRA_SHOW_ID = "showId";
    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_all);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        int showId = (Integer) getIntent().getExtras().get(EXTRA_SHOW_ID);
        int showNameId = Shows.shows[showId].getName();
        String showName = getString(showNameId);
        assert actionBar != null;
        actionBar.setTitle(showName);
        actionBar.setDisplayHomeAsUpEnabled(true);

        int showImg = Shows.shows[showId].getImageResourceId();
        ImageView imageView = findViewById(R.id.image);
        imageView.setImageDrawable(ContextCompat.getDrawable(this, showImg));
        imageView.setContentDescription(showName);

        int showContent = Shows.shows[showId].getContent();
        TextView textShow = findViewById(R.id.content);
        textShow.setText(showContent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.animator_detail_app_bar_menu, menu);
        MenuItem shareItem = menu.findItem(R.id.top_menu_share);
        shareActionProvider = (android.support.v7.widget.ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        int showId = (Integer) getIntent().getExtras().get(EXTRA_SHOW_ID);
        int showNameId = Shows.shows[showId].getName();
        String showName = getString(showNameId);
        int urlResourceId = Shows.shows[showId].getUrl();
        final String url = getString(urlResourceId);
        final String msg_app = getString(R.string.share_url_show) + " " + showName + ":\n" + url;
        setShareActionIntent(msg_app);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final String phone_number = getString(R.string.phone)
                .replaceAll("\\s \\( \\) -", "");
        int showId = (Integer) getIntent().getExtras().get(EXTRA_SHOW_ID);
        final int urlResourceId = Shows.shows[showId].getUrl();
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
