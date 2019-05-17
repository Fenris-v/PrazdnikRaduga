package com.petersburg_studio.prazdnikraduga;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.petersburg_studio.prazdnikraduga.adapters.CaptionedImagesAdapter;
import com.petersburg_studio.prazdnikraduga.arrays.Category;
import com.petersburg_studio.prazdnikraduga.fragment.secondLevel.AdditionalServicesFragment;
import com.petersburg_studio.prazdnikraduga.fragment.secondLevel.ContactsFragment;
import com.petersburg_studio.prazdnikraduga.fragment.secondLevel.MastersFragment;
import com.petersburg_studio.prazdnikraduga.fragment.secondLevel.PricesFragment;
import com.petersburg_studio.prazdnikraduga.fragment.secondLevel.SeasonsHolidaysFragment;
import com.petersburg_studio.prazdnikraduga.fragment.secondLevel.ShowsFragment;
import com.petersburg_studio.prazdnikraduga.fragment.secondLevel.ThematicPartiesFragment;
import com.petersburg_studio.prazdnikraduga.stub.StubActivity;
import com.petersburg_studio.prazdnikraduga.test.TestActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static Fragment iFragment;

    //fab's
    FloatingActionButton fab;
    FloatingActionButton fab_msg;
    FloatingActionButton fab_call;
    FloatingActionButton fab_vk;

    //visible status of fab
    public boolean fab_status = false;

    //animation
    Animation show_fab_msg;
    Animation hide_fab_msg;
    Animation show_fab_call;
    Animation hide_fab_call;
    Animation show_fab_vk;
    Animation hide_fab_vk;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Toolbar activated
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Menu activated
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer,
                toolbar,
                R.string.nav_open_drawer,
                R.string.nav_close_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        //set views for fab's
        fab = findViewById(R.id.main_fab);
        fab_msg = findViewById(R.id.fab_msg);
        fab_call = findViewById(R.id.fab_call);
        fab_vk = findViewById(R.id.fab_vk);

        //set animation for fab's
        show_fab_msg = AnimationUtils.loadAnimation(getApplication(), R.anim.fab_msg_show);
        hide_fab_msg = AnimationUtils.loadAnimation(getApplication(), R.anim.fab_msg_hide);
        show_fab_call = AnimationUtils.loadAnimation(getApplication(), R.anim.fab_call_show);
        hide_fab_call = AnimationUtils.loadAnimation(getApplication(), R.anim.fab_call_hide);
        show_fab_vk = AnimationUtils.loadAnimation(getApplication(), R.anim.fab_vk_show);
        hide_fab_vk = AnimationUtils.loadAnimation(getApplication(), R.anim.fab_vk_hide);

        //animation on create fab
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.simple_grow);
        fab.startAnimation(animation);

        //animate for main fab on scroll
        NestedScrollView nestedScrollView = findViewById(R.id.nestedScrollView);
        if (nestedScrollView != null) {
            nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (nestedScrollView1, x, y, oldX, oldY) -> {

                //hide fab's if scroll down
                if (y > oldY && fab_status) {
                    fab.hide();
                    hideFABs();
                } else if (y > oldY) {
                    fab.hide();
                }

                //show main fab if scroll up
                if (y < oldY && fab_status) {
                    fab.show();
                    hideFABs();
                } else if (y < oldY) {
                    fab.show();
                }

                //show main fab if page home
                if (y == 0) {
                    fab.show();
                }

                //hide fab's if page end
                if (y == nestedScrollView1.getChildAt(0)
                        .getMeasuredHeight() - nestedScrollView1.getMeasuredHeight() && fab_status) {
                    fab.hide();
                    hideFABs();
                } else if (y == nestedScrollView1.getChildAt(0)
                        .getMeasuredHeight() - nestedScrollView1.getMeasuredHeight()) {
                    fab.hide();
                }
            });
        }


        //mini fab's hide on touch
        assert nestedScrollView != null;
        nestedScrollView.setOnTouchListener((v, event) -> {
            if (fab_status) {
                hideFABs();
            }
            return false;
        });

        RecyclerView recyclerView = findViewById(R.id.recycler);

        String[] categoryNames = new String[Category.categories.length];
        for (int i = 0; i < categoryNames.length; i++) {
            int categoryNamesIds = Category.categories[i].getName();
            categoryNames[i] = getString(categoryNamesIds);
        }

        int[] categoryImages = new int[Category.categories.length];
        for (int i = 0; i < categoryImages.length; i++) {
            categoryImages[i] = Category.categories[i].getImageResourceId();
        }
        //adapter for services block
        CaptionedImagesAdapter captionedImagesAdapter =
                new CaptionedImagesAdapter(categoryNames, categoryImages);
        recyclerView.setAdapter(captionedImagesAdapter);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        //make intent for listener of services block
        //TODO: change
        final Intent intentAnimators = new Intent(this, AnimatorsActivity.class);
        final Intent intentOthers = new Intent(this, Other2ndActivity.class);


        //click listener for services block
        captionedImagesAdapter.setListener(position -> {
            Intent intent = null;
            switch (position) {
                case 0:
                    intent = intentAnimators;
                    break;
                case 1:
                    intent = intentOthers;
                    iFragment = new MastersFragment();
                    break;
                case 2:
                    intent = intentOthers;
                    iFragment = new ShowsFragment();
                    break;
                case 3:
                    intent = intentOthers;
                    iFragment = new AdditionalServicesFragment();
                    break;
                case 4:
                    intent = intentOthers;
                    iFragment = new ThematicPartiesFragment();
                    break;
                case 5:
                    intent = intentOthers;
                    iFragment = new SeasonsHolidaysFragment();
                    break;
            }

            if (intent != null) {
                startActivity(intent);
            }

        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        Intent intent = null;
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        Class getClass = this.getClass();
        switch (id) {
            case R.id.nav_main_activity:
                if (getClass == MainActivity.class) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    intent = new Intent(this, MainActivity.class);
                }
                break;
            case R.id.nav_animators:
                if (getClass == AnimatorsActivity.class) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    intent = new Intent(this, AnimatorsActivity.class);
                }
                break;
            case R.id.nav_shows:
                intent = new Intent(this, Other2ndActivity.class);
                iFragment = new ShowsFragment();
                break;
            case R.id.nav_masters:
                intent = new Intent(this, Other2ndActivity.class);
                iFragment = new MastersFragment();
                break;
            case R.id.nav_thematic_party:
                intent = new Intent(this, Other2ndActivity.class);
                iFragment = new ThematicPartiesFragment();
                break;
            case R.id.nav_price:
                intent = new Intent(this, Other2ndActivity.class);
                iFragment = new PricesFragment();
                break;
            case R.id.nav_contacts:
                intent = new Intent(this, Other2ndActivity.class);
                iFragment = new ContactsFragment();
                break;
            case R.id.nav_additional:
                intent = new Intent(this, Other2ndActivity.class);
                iFragment = new AdditionalServicesFragment();
                break;
            case R.id.nav_seasons_holidays:
                intent = new Intent(this, Other2ndActivity.class);
                iFragment = new SeasonsHolidaysFragment();
                break;
            //TODO: delete test
            case R.id.nav_test:
                intent = new Intent(this, TestActivity.class);
        }

        if (intent != null) {
            startActivity(intent);
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //CODING FOR WORK FAB's
    //action for click on main fab
    public void onClickMainFab(View view) {
        if (!fab_status) {
            showFABs();
        } else {
            hideFABs();
        }
    }

    //action for click fab_msg
    @SuppressLint("IntentReset")
    public void onClickMsg(View view) {
        final String email = getString(R.string.email);
        final String name_app = getString(R.string.mobile_app);
        Intent msgIntent = new Intent(Intent.ACTION_SENDTO);
        msgIntent.setType("text/plain");
        msgIntent.putExtra(Intent.EXTRA_SUBJECT, name_app);
        msgIntent.setData(Uri.parse("mailto:" + email));
        msgIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(msgIntent);
    }

    //action for click fab_call
    public void onClickCall(View view) {
        final String phone_number = getString(R.string.phone)
                .replaceAll("\\s \\( \\) -", "");
        Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone_number));
        startActivity(callIntent);
    }

    //action for click fab_vk
    public void onClickVk(View view) {
        final String vk = getString(R.string.vk);
        Intent vkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(vk));
        startActivity(vkIntent);
    }

    //set visible position for mini fabs
    private void showFABs() {
        FrameLayout.LayoutParams layoutParamsMsg = (FrameLayout.LayoutParams) fab_msg.getLayoutParams();
        layoutParamsMsg.rightMargin += (int) (fab_msg.getWidth() * 1.7);
        layoutParamsMsg.bottomMargin += (int) (fab_msg.getHeight() * 0.25);
        fab_msg.setLayoutParams(layoutParamsMsg);
        fab_msg.startAnimation(show_fab_msg);
        fab_msg.setClickable(true);

        FrameLayout.LayoutParams layoutParamsCall = (FrameLayout.LayoutParams) fab_call.getLayoutParams();
        layoutParamsCall.rightMargin += (int) (fab_call.getWidth() * 1.5);
        layoutParamsCall.bottomMargin += (int) (fab_call.getHeight() * 1.5);
        fab_call.setLayoutParams(layoutParamsCall);
        fab_call.startAnimation(show_fab_call);
        fab_call.setClickable(true);

        FrameLayout.LayoutParams layoutParamsVk = (FrameLayout.LayoutParams) fab_vk.getLayoutParams();
        layoutParamsVk.rightMargin += (int) (fab_vk.getWidth() * 0.25);
        layoutParamsVk.bottomMargin += (int) (fab_vk.getHeight() * 1.7);
        fab_vk.setLayoutParams(layoutParamsVk);
        fab_vk.startAnimation(show_fab_vk);
        fab_vk.setClickable(true);

        fab_status = true;
    }

    //set invisible position for mini fab's
    private void hideFABs() {
        FrameLayout.LayoutParams layoutParamsMsg = (FrameLayout.LayoutParams) fab_msg.getLayoutParams();
        layoutParamsMsg.rightMargin -= (int) (fab_msg.getWidth() * 1.7);
        layoutParamsMsg.bottomMargin -= (int) (fab_msg.getHeight() * 0.25);
        fab_msg.setLayoutParams(layoutParamsMsg);
        fab_msg.startAnimation(hide_fab_msg);
        fab_msg.setClickable(false);

        FrameLayout.LayoutParams layoutParamsCall = (FrameLayout.LayoutParams) fab_call.getLayoutParams();
        layoutParamsCall.rightMargin -= (int) (fab_call.getWidth() * 1.5);
        layoutParamsCall.bottomMargin -= (int) (fab_call.getHeight() * 1.5);
        fab_call.setLayoutParams(layoutParamsCall);
        fab_call.startAnimation(hide_fab_call);
        fab_call.setClickable(false);

        FrameLayout.LayoutParams layoutParamsVk = (FrameLayout.LayoutParams) fab_vk.getLayoutParams();
        layoutParamsVk.rightMargin -= (int) (fab_vk.getWidth() * 0.25);
        layoutParamsVk.bottomMargin -= (int) (fab_vk.getHeight() * 1.7);
        fab_vk.setLayoutParams(layoutParamsVk);
        fab_vk.startAnimation(hide_fab_vk);
        fab_vk.setClickable(false);

        fab_status = false;
    }

    protected void onStop() {
        super.onStop();
        System.out.println("main stop");
        iFragment = null;
    }

    protected void onDestroy() {
        super.onDestroy();
        System.out.println("main destroy");
    }

    public static Fragment getFragment() {
        return iFragment;
    }
}