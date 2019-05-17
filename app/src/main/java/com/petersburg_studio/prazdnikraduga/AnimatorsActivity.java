package com.petersburg_studio.prazdnikraduga;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.petersburg_studio.prazdnikraduga.fragment.secondLevel.AdditionalServicesFragment;
import com.petersburg_studio.prazdnikraduga.fragment.secondLevel.ContactsFragment;
import com.petersburg_studio.prazdnikraduga.fragment.secondLevel.MastersFragment;
import com.petersburg_studio.prazdnikraduga.fragment.secondLevel.PricesFragment;
import com.petersburg_studio.prazdnikraduga.fragment.secondLevel.SeasonsHolidaysFragment;
import com.petersburg_studio.prazdnikraduga.fragment.secondLevel.ShowsFragment;
import com.petersburg_studio.prazdnikraduga.fragment.secondLevel.ThematicPartiesFragment;
import com.petersburg_studio.prazdnikraduga.fragment.animators.AllAnimatorsFragment;
import com.petersburg_studio.prazdnikraduga.fragment.animators.CartoonAnimatorsFragment;
import com.petersburg_studio.prazdnikraduga.fragment.animators.FairyAnimatorsFragment;
import com.petersburg_studio.prazdnikraduga.fragment.animators.GameAnimatorsFragment;
import com.petersburg_studio.prazdnikraduga.fragment.animators.RealisticAnimatorsFragment;
import com.petersburg_studio.prazdnikraduga.fragment.animators.SuperheroAnimatorsFragment;
import com.petersburg_studio.prazdnikraduga.stub.StubActivity;

public class AnimatorsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static Fragment iFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animators);

        final Toolbar toolbar = findViewById(R.id.toolbar_animator);
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

        //set tabs
        SectionsPagerAdapter pagerAdapter =
                new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager pager = findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);
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
                intent = new Intent(this, StubActivity.class);
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

    private class SectionsPagerAdapter extends FragmentPagerAdapter {
        SectionsPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return new AllAnimatorsFragment();
                case 1:
                    return new CartoonAnimatorsFragment();
                case 2:
                    return new FairyAnimatorsFragment();
                case 3:
                    return new GameAnimatorsFragment();
                case 4:
                    return new RealisticAnimatorsFragment();
                case 5:
                    return new SuperheroAnimatorsFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public CharSequence getPageTitle(int i) {
            switch (i) {
                case 0:
                    return getResources().getText(R.string.all_animators);
                case 1:
                    return getResources().getText(R.string.mult);
                case 2:
                    return getResources().getText(R.string.fairy);
                case 3:
                    return getResources().getText(R.string.game);
                case 4:
                    return getResources().getText(R.string.real);
                case 5:
                    return getResources().getText(R.string.superhero);
            }
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar_menu_call, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final String phone_number = getString(R.string.phone)
                .replaceAll("\\s \\( \\) -", "");
        Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone_number));
        startActivity(callIntent);
        return true;
    }

    protected void onStop() {
        super.onStop();
        iFragment = null;
    }

    public static Fragment getFragment() {
        return iFragment;
    }
}