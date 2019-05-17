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

import com.petersburg_studio.prazdnikraduga.arrays.ThematicsParties;

public class ThematicPartyDetailActivity extends AppCompatActivity {
    public final static String EXTRA_THEMATIC_ID = "thematicId";
    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_all);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        int thematicId = (Integer) getIntent().getExtras().get(EXTRA_THEMATIC_ID);
        int thematicNameId = ThematicsParties.thematicsParties[thematicId].getName();
        String thematicName = getString(thematicNameId);
        assert actionBar != null;
        actionBar.setTitle(thematicName);
        actionBar.setDisplayHomeAsUpEnabled(true);

        int thematicImg = ThematicsParties.thematicsParties[thematicId].getImageResourceId();
        ImageView imageView = findViewById(R.id.image);
        imageView.setImageDrawable(ContextCompat.getDrawable(this, thematicImg));
        imageView.setContentDescription(thematicName);

        int thematicContent = ThematicsParties.thematicsParties[thematicId].getContent();
        TextView textThematic = findViewById(R.id.content);
        textThematic.setText(thematicContent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.animator_detail_app_bar_menu, menu);
        MenuItem shareItem = menu.findItem(R.id.top_menu_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        int thematicId = (Integer) getIntent().getExtras().get(EXTRA_THEMATIC_ID);
        int thematicNameId = ThematicsParties.thematicsParties[thematicId].getName();
        String thematicName = getString(thematicNameId);
        int urlResourceId = ThematicsParties.thematicsParties[thematicId].getUrl();
        final String url = getString(urlResourceId);
        final String msg_app = getString(R.string.share_url_thematic) + " " + thematicName + ":\n" + url;
        setShareActionIntent(msg_app);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final String phone_number = getString(R.string.phone)
                .replaceAll("\\s \\( \\) -", "");
        int thematicId = (Integer) getIntent().getExtras().get(EXTRA_THEMATIC_ID);
        final int urlResourceId = ThematicsParties.thematicsParties[thematicId].getUrl();
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
