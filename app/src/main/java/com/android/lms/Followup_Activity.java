package com.android.lms;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Calendar;


public class Followup_Activity extends AppCompatActivity
{

    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.followup_date);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));

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
                actionBar.setHomeButtonEnabled(false);
            }
        }

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        calendarView = (CalendarView) findViewById(R.id.calenderview);
        calendarView.getDate();
        calendarView.getFirstDayOfWeek();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.leads, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {

            Intent i = new Intent(Followup_Activity.this, FAdd_Followup.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
