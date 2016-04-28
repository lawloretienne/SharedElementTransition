package com.etiennelawlor.sharedelementtransition.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.etiennelawlor.sharedelementtransition.R;
import com.etiennelawlor.sharedelementtransition.fragments.CheesesFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by etiennelawlor on 4/28/16.
 */
public class MainActivity extends AppCompatActivity {

    // region Constants
    private static final String CHEESES_FRAGMENT = "CheesesFragment";
    private static final String CHEESES_ACTIVITY = "CheesesActivity";
    // endregion

    // region Views
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    // endregion

    // region Member Variables
    private Typeface font;
    // endregion

    // region Listeners
    private NavigationView.OnNavigationItemSelectedListener navigationViewOnNavigationItemSelectedListener
            = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            drawerLayout.closeDrawers();

            String title = menuItem.getTitle().toString();
            switch (title) {
                case CHEESES_FRAGMENT:
                    menuItem.setChecked(true);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_fl, CheesesFragment.newInstance(), "")
                            .commit();
                    break;
                case CHEESES_ACTIVITY:
                    Intent intent = new Intent(MainActivity.this, CheesesActivity.class);
                    intent.putExtra("up_nav", true);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
            return true;
        }
    };

    // endregion

    // region Lifecycle Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        View header = LayoutInflater.from(this).inflate(R.layout.nav_header, null);
        navigationView.addHeaderView(header);

        // Setup NavigationView
        navigationView.setNavigationItemSelectedListener(navigationViewOnNavigationItemSelectedListener);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_fl, CheesesFragment.newInstance(), "")
                .commit();

    }

    @Override
    protected void onResume() {
        super.onResume();

        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.five_percent_transparency_black));
    }

    // endregion

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
}