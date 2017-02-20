package com.fooock.app.shodand.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.fooock.app.shodand.R;
import com.fooock.app.shodand.ShodandApplication;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

import static com.fooock.app.shodand.fragment.IntroduceKeyFragment.PREF_API_KEY;

/**
 *
 */
public class ShodandMainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    protected DrawerLayout drawerLayout;

    @BindView(R.id.nav_view)
    protected NavigationView navigationView;

    @BindView(R.id.fab)
    protected FloatingActionButton actionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shodand_main);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        Timber.d("In onCreate()");
    }

    @Override
    void initializeComponents(@NonNull ShodandApplication application) {
        Timber.d("Initializing components...");

        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        String apiKey = preferences.getString(PREF_API_KEY, "");

        if (apiKey.isEmpty()) {
            Timber.d("No API key found, opening configuration activity...");
            startAppConfiguration();
            finish();
        }
        Timber.d("Found API key: %s", apiKey);

        application.initializeApiWith(apiKey);
    }

    /**
     * Start the {@link ConfigurationActivity}
     */
    private void startAppConfiguration() {
        Intent confActivity = new Intent(this, ConfigurationActivity.class);
        startActivity(confActivity);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            Timber.d("Closing drawer layout");
            drawerLayout.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                Timber.d("Selected setting menu option");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Timber.d("Navigation item selected [%s]", item.getTitle());
        return false;
    }

    @OnClick(R.id.fab)
    public void onClick(View view) {
        Timber.d("Click on search action button");
    }
}