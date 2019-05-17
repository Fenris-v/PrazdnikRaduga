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

import com.petersburg_studio.prazdnikraduga.arrays.Masters;

public class MasterDetailActivity extends AppCompatActivity {
    public final static String EXTRA_MASTER_ID = "masterId";
    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_all);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        int masterId = (Integer) getIntent().getExtras().get(EXTRA_MASTER_ID);
        int masterNameId = Masters.masters[masterId].getName();
        String masterName = getString(masterNameId);
        assert actionBar != null;
        actionBar.setTitle(masterName);
        actionBar.setDisplayHomeAsUpEnabled(true);

        int masterImg = Masters.masters[masterId].getImageResourceId();
        ImageView imageView = findViewById(R.id.image);
        imageView.setImageDrawable(ContextCompat.getDrawable(this, masterImg));
        imageView.setContentDescription(masterName);

        int masterContent = Masters.masters[masterId].getContent();
        TextView textMaster = findViewById(R.id.content);
        textMaster.setText(masterContent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.animator_detail_app_bar_menu, menu);
        MenuItem shareItem = menu.findItem(R.id.top_menu_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        int masterId = (Integer) getIntent().getExtras().get(EXTRA_MASTER_ID);
        int masterNameId = Masters.masters[masterId].getName();
        String masterName = getString(masterNameId);
        int urlResourceId = Masters.masters[masterId].getUrl();
        final String url = getString(urlResourceId);
        final String msg_app = getString(R.string.share_url_master) + " " + masterName + ":\n" + url;
        setShareActionIntent(msg_app);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final String phone_number = getString(R.string.phone)
                .replaceAll("\\s \\( \\) -", "");
        int masterId = (Integer) getIntent().getExtras().get(EXTRA_MASTER_ID);
        final int urlResourceId = Masters.masters[masterId].getUrl();
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
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(url)));
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
