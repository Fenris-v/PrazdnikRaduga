package com.petersburg_studio.prazdnikraduga;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.petersburg_studio.prazdnikraduga.fragment.secondLevel.AdditionalServicesFragment;
import com.petersburg_studio.prazdnikraduga.fragment.secondLevel.ContactsFragment;
import com.petersburg_studio.prazdnikraduga.fragment.secondLevel.MastersFragment;
import com.petersburg_studio.prazdnikraduga.fragment.secondLevel.PricesFragment;
import com.petersburg_studio.prazdnikraduga.fragment.secondLevel.SeasonsHolidaysFragment;
import com.petersburg_studio.prazdnikraduga.fragment.secondLevel.ShowsFragment;
import com.petersburg_studio.prazdnikraduga.fragment.secondLevel.ThematicPartiesFragment;

public class Other2ndActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other2nd);

        if (MainActivity.getFragment() != null) {
            fragment = MainActivity.getFragment();
        } else if (AnimatorsActivity.getFragment() != null) {
            fragment = AnimatorsActivity.getFragment();
        }


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_frame, fragment);
        ft.commit();

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
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        Intent intent = null;
        Fragment fragment = null;
        drawer = findViewById(R.id.drawer_layout);
        switch (id) {
            case R.id.nav_main_activity:
                intent = new Intent(this, MainActivity.class);
                break;
            case R.id.nav_animators:
                intent = new Intent(this, AnimatorsActivity.class);
                break;
            case R.id.nav_shows:
                fragment = new ShowsFragment();
                break;
            case R.id.nav_masters:
                fragment = new MastersFragment();
                break;
            case R.id.nav_thematic_party:
                fragment = new ThematicPartiesFragment();
                break;
            case R.id.nav_price:
                fragment = new PricesFragment();
                break;
            case R.id.nav_contacts:
                fragment = new ContactsFragment();
                break;
            case R.id.nav_additional:
                fragment = new AdditionalServicesFragment();
                break;
            case R.id.nav_seasons_holidays:
                fragment = new SeasonsHolidaysFragment();
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.replace(R.id.content_frame, fragment);
            ft.addToBackStack(null);
            ft.commit();
        } else {
            startActivity(intent);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //action for click fab_call
    public void onClickStringCall(View view) {
        final String phone_number = getString(R.string.phone)
                .replaceAll("\\s \\( \\) -", "");
        Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone_number));
        startActivity(callIntent);
    }

    //action for click fab_msg
    @SuppressLint("IntentReset")
    public void onClickStringMsg(View view) {
        final String email = getString(R.string.email);
        final String name_app = getString(R.string.mobile_app);
        Intent msgIntent = new Intent(Intent.ACTION_SENDTO);
        msgIntent.setType("text/plain");
        msgIntent.putExtra(Intent.EXTRA_SUBJECT, name_app);
        msgIntent.setData(Uri.parse("mailto:" + email));
        msgIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(msgIntent);
    }
}
