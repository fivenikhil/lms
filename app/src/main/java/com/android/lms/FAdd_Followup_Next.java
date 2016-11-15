package com.android.lms;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TableLayout;
import android.widget.TextView;

import com.andexert.expandablelayout.library.ExpandableLayout;

import java.util.Calendar;
import java.util.Locale;


public class FAdd_Followup_Next extends AppCompatActivity
{

    AppCompatSpinner type, status, notes;
    ExpandableLayout el, el1;
    int day_x, month_x, year_x;
    static final int Dialog_id =0;
    private TextView txt_type, txt_date, txt_status, txt_notes, txt_lob, txt_name, txt_site, datepicker;
    private Button btn_save;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.followup_content_next);

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

        //PostJson
        new PostJson(FAdd_Followup_Next.this,ConstantClass.URL,"DICV/FollowupTypes",this,pd).execute();
        new PostJson(FAdd_Followup_Next.this,ConstantClass.URL,"DICV/FollowupStatus",this, pd).execute();


        txt_type = (TextView) findViewById(R.id.txt_type);
        txt_type.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_status = (TextView) findViewById(R.id.txt_status);
        txt_status.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_notes = (TextView) findViewById(R.id.txt_notes);
        txt_notes.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_lob = (TextView) findViewById(R.id.txt_lob);
        txt_lob.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_name = (TextView) findViewById(R.id.txt_name);
        txt_name.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_site = (TextView) findViewById(R.id.txt_site);
        txt_site.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_date = (TextView)findViewById(R.id.txt_date);
        txt_date.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        datepicker =(TextView)findViewById(R.id.txt_datepicker);
        datepicker.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720N.TTF"));

        type = (AppCompatSpinner) findViewById(R.id.spin_followtype);
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) type.getSelectedView()).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                ((TextView) type.getSelectedView()).setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720N.TTF"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        status = (AppCompatSpinner) findViewById(R.id.spin_status);
        status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) status.getSelectedView()).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                ((TextView) status.getSelectedView()).setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720N.TTF"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        notes = (AppCompatSpinner) findViewById(R.id.spin_notes);
        notes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) notes.getSelectedView()).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                ((TextView) notes.getSelectedView()).setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720N.TTF"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        el = (ExpandableLayout)findViewById(R.id.expandableLayout);
        el1 = (ExpandableLayout)findViewById(R.id.expandableLayout_one);

        btn_save = (Button)findViewById(R.id.btn_save);
        btn_save.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        final Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);

        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                       showDialog(Dialog_id);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id){

        if(id == Dialog_id)
            return  new DatePickerDialog(this, datapickerdp, day_x, month_x, year_x);
        return  null;
    }

    private DatePickerDialog.OnDateSetListener datapickerdp = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            day_x = day;
            month_x = month;
            year_x = year;
            datepicker.setText(day_x +"/"+ month_x+"/" +year_x);
            //TextView tv1= (TextView) getActivity().findViewById(R.id.textview1);
            //tv1.setText("Year: "+view.getYear()+" Month: "+view.getMonth()+" Day: "+view.getDayOfMonth());


        }
    };
}
