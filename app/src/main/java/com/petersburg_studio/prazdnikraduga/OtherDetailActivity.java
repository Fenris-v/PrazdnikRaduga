package com.petersburg_studio.prazdnikraduga;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.petersburg_studio.prazdnikraduga.libs.waverefresh.BannerLayout;

import java.util.Objects;

public class OtherDetailActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "extraName";
    public static final String EXTRA_CONTENT = "extraContent";
    public static final String EXTRA_CONTENT1 = "extraContent1";
    public static final String EXTRA_URL = "extraUrl";
    public static final String EXTRA_IMG = "extraImg";

    private ShareActionProvider shareActionProvider;

    private String name;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_detail);

        BannerLayout banner = findViewById(R.id.recycler);

        name = (String) Objects.requireNonNull(getIntent().getExtras()).get(EXTRA_NAME);
        assert name != null;
        name = name.replaceAll("Аниматор ", "");
        name = name.replaceAll("Аниматоры ", "");
        String content = (String) Objects.requireNonNull(getIntent().getExtras()).get(EXTRA_CONTENT);
        assert content != null;
        String[] text = content.split("<h2>(.*)");
        for (int i = 0; i < text.length; i++) {
            text[i] = android.text.Html.fromHtml(text[i]).toString().replaceAll("\n", "\n\t").trim();
            text[i] = "\t" + text[i];
        }

        String image = (String) getIntent().getExtras().get(EXTRA_IMG);

        url = (String) Objects.requireNonNull(getIntent().getExtras()).get(EXTRA_URL);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(name);
        actionBar.setDisplayHomeAsUpEnabled(true);

        ImageView imageView = findViewById(R.id.image);

        Glide.with(this)
                .load(image)
                .placeholder(R.drawable.cat_wait)
                .into(imageView);

        TextView textView = findViewById(R.id.content);
        TextView textTitle = findViewById(R.id.content_title);
        TextView textView1 = findViewById(R.id.content1);
        if (!text[0].equals("\t")) {
            textView.setText(text[0]);
            if (text.length > 1 && !text[1].equals("\t")) {
                textView1.setText(text[1]);
            } else {
                textView1.setVisibility(View.GONE);
                textTitle.setVisibility(View.GONE);
            }
        } else {
            if (text.length > 1 && !text[1].equals("\t")) {
                textView.setText(text[1]);
                textTitle.setVisibility(View.GONE);
            } else {
                textView.setVisibility(View.GONE);
                textTitle.setText(name);
            }
                textView1.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.animator_detail_app_bar_menu, menu);
        MenuItem shareItem = menu.findItem(R.id.top_menu_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        final String msg_app = getString(R.string.share_url) + name + ":\n" + url;
        setShareActionIntent(msg_app);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final String phone_number = getString(R.string.phone)
                .replaceAll("\\s \\( \\) -", "");
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
