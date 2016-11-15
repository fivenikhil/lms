package com.android.lms;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DashBoard_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView leads, conf, sales, finance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
        toolbar.setNavigationIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon_menu));

        TextView txt = (TextView) findViewById(R.id.toolbar_title);
        txt.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH725B.TTF"));

        if (toolbar != null)
        {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null)
            {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setDisplayShowTitleEnabled(false);
                actionBar.setDisplayUseLogoEnabled(false);
                actionBar.setHomeButtonEnabled(true);
                actionBar.setHomeAsUpIndicator(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon_menu));
                toolbar.setNavigationIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon_menu));
            }
        }

        leads = (ImageView)findViewById(R.id.btn_leads);
        conf = (ImageView)findViewById(R.id.btn_config);
        sales = (ImageView)findViewById(R.id.btn_funnel);
        finance = (ImageView)findViewById(R.id.btn_follow);

        leads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashBoard_Activity.this, Accounts_Activity.class);
                startActivity(i);
            }
        });

        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashBoard_Activity.this, Opportunity_Activity.class);
                startActivity(i);
            }
        });

        finance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashBoard_Activity.this, Followup_Activity.class);
                startActivity(i);


            }
        });

        sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashBoard_Activity.this, Sales_Activity.class);
                startActivity(i);


            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FontsOverride.setDefaultFont(this, "SERIF", "GOTH720L.TTF");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_profile) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {

            Intent i = new Intent(DashBoard_Activity.this, DashBoard_Activity.class);
            startActivity(i);

        } else if (id == R.id.nav_leads) {

            Intent i = new Intent(DashBoard_Activity.this, Accounts_Activity.class);
            startActivity(i);

        } else if (id == R.id.nav_configurator) {

            Intent i = new Intent(DashBoard_Activity.this, Opportunity_Activity.class);
            startActivity(i);

        } else if (id == R.id.nav_finance) {

        } else if (id == R.id.nav_creditapp) {

        } else if (id == R.id.nav_followup) {

            Intent i = new Intent(DashBoard_Activity.this, Followup_Activity.class);
            startActivity(i);

        } else if (id == R.id.nav_report) {

        } else if (id == R.id.nav_salesfunnel) {

            Intent i = new Intent(DashBoard_Activity.this, Sales_Activity.class);
            startActivity(i);

        } else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
