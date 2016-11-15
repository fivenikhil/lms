package com.android.lms;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddAccount_Activity extends AppCompatActivity
{

    EditText name, site, pincode, address, city, taluk, district, state, phone, email, pancard,
                                 ed_firstname, ed_lastname, ed_mobile, ed_mail_new ;

    TextView txt_name, txt_site, txt_address, txt_pincode, txt_taluk, txt_city, txt_district, txt_state, txt_phone,
                 txt_email, txt_pancard, txt_accounttype, txt_account, txt_fleet, txt_contact, txt_firstname,
                 txt_lastname, txt_mobile, txt_des, txt_email_h;

    Button btn_save, btn_contd, btn_save_h, btn_add_h;
    Spinner acc_type, acc_category, fleetsize, ed_desgination;
    ProgressDialog pd;
    ImageView img;
    String u_name,u_site,u_pincode,u_address, u_city,u_taluk,u_district,u_state,u_mainphn,u_email,u_card,u_acctype,
                         u_accategory, u_fleet, c_firstname, c_lastname, c_mobile, c_mail, c_desgination;

    public HashMap<String, String> listdata;

    String get_userid, get_bankid, getshowid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_content);

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

       // asyncTask();
        listdata = new HashMap<String,String>();
       // PostJson p=new PostJson(context);
        new PostJson(AddAccount_Activity.this,ConstantClass.URL,"DICV/AccountTypes",this, pd).execute();
        new PostJson(AddAccount_Activity.this,ConstantClass.URL,"DICV/AccountCategories",this,pd).execute();
        new PostJson(AddAccount_Activity.this,ConstantClass.URL,"DICV/FleetSizes",this,pd).execute();

        //list1.add(p.list);
        //p.execute();


        img = (ImageView)findViewById(R.id.loading);

        //TextViews
        txt_name =(TextView)findViewById(R.id.txt_name);
        txt_name.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_site =(TextView)findViewById(R.id.txt_site);
        txt_site.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_address =(TextView)findViewById(R.id.txt_address);
        txt_address.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_pincode =(TextView)findViewById(R.id.txt_pincode);
        txt_pincode.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_taluk =(TextView)findViewById(R.id.txt_taluk);
        txt_taluk.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_city =(TextView)findViewById(R.id.txt_city);
        txt_city.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_district =(TextView)findViewById(R.id.txt_district);
        txt_district.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_state =(TextView)findViewById(R.id.txt_state);
        txt_state.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_phone =(TextView)findViewById(R.id.txt_phone);
        txt_phone.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_email =(TextView)findViewById(R.id.txt_email);
        txt_email.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_pancard =(TextView)findViewById(R.id.txt_pancard);
        txt_pancard.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_accounttype =(TextView)findViewById(R.id.txt_accounttype);
        txt_accounttype.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_account =(TextView)findViewById(R.id.txt_account);
        txt_account.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_fleet =(TextView)findViewById(R.id.txt_fleet);
        txt_fleet.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_contact =(TextView)findViewById(R.id.txt_contact);
        txt_contact.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_firstname =(TextView)findViewById(R.id.txt_firstname);
        txt_firstname.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_lastname =(TextView)findViewById(R.id.txt_lastname);
        txt_lastname.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_mobile =(TextView)findViewById(R.id.txt_mobile);
        txt_mobile.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_des =(TextView)findViewById(R.id.txt_des);
        txt_des.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_email_h =(TextView)findViewById(R.id.txt_email_h);
        txt_email_h.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        //EditText
        name = (EditText)findViewById(R.id.edit_name);
        name.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720N.TTF"));

        site = (EditText)findViewById(R.id.edit_site);
        site.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720N.TTF"));

        pincode = (EditText)findViewById(R.id.edit_pincode);
        pincode.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720N.TTF"));

        address = (EditText)findViewById(R.id.edit_address);
        address.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720N.TTF"));

        city = (EditText)findViewById(R.id.edit_city);
        city.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720N.TTF"));

        taluk = (EditText)findViewById(R.id.edit_taluk);
        taluk.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720N.TTF"));

        district = (EditText)findViewById(R.id.edit_district);
        district.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720N.TTF"));

        state = (EditText)findViewById(R.id.edit_state);
        state.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720N.TTF"));

        phone = (EditText)findViewById(R.id.edit_phone);
        phone.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720N.TTF"));

        email = (EditText)findViewById(R.id.edit_email);
        email.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720N.TTF"));

        pancard = (EditText)findViewById(R.id.edit_pancard);
        pancard.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720N.TTF"));

        //Contacts editText
        ed_firstname = (EditText)findViewById(R.id.edit_firstname);
        ed_lastname = (EditText)findViewById(R.id.edit_lastname);
        ed_mobile = (EditText)findViewById(R.id.edit_mobile);
        ed_mail_new = (EditText)findViewById(R.id.edit_email_new);

        ///Buttons
        btn_save = (Button)findViewById(R.id.btn_save);
        btn_save.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));
        btn_contd = (Button)findViewById(R.id.btn_savecontine);
        btn_contd.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        btn_save_h  = (Button)findViewById(R.id.btn_save_contact);
        btn_save_h.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));
        btn_add_h = (Button)findViewById(R.id.save_contd_contact);
        btn_add_h.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        ///Spinners
        acc_type = (Spinner)findViewById(R.id.spin_accounttype);
        acc_type.setVisibility(View.INVISIBLE);

        acc_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                u_acctype = PostJson.acc_typelist.get(position).get(ConstantClass.acctypeId);
              //  u_acctype =  parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        acc_category = (Spinner)findViewById(R.id.spin_account);

        acc_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                u_accategory = PostJson.acc_catlist.get(position).get(ConstantClass.accountCategoryId);
                //u_accategory = parent.getSelectedItem().toString();
                ((TextView) acc_category.getSelectedView()).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                ((TextView) acc_category.getSelectedView()).setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720N.TTF"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        fleetsize = (Spinner)findViewById(R.id.spin_fleet);

        fleetsize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                u_fleet = PostJson.fleet_list.get(position).get("Id");
                ((TextView) fleetsize.getSelectedView()).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                ((TextView) fleetsize.getSelectedView()).setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720N.TTF"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ed_desgination = (Spinner) findViewById(R.id.edit_desgination);


        Glide.with(this)
                .load("")
                .asGif()
                .placeholder(R.drawable.loading)
                .into(img);


        get_userid = DataHolderClass.getInstance().getUserid();
        get_bankid = DataHolderClass.getInstance().getBank();
        getshowid = DataHolderClass.getInstance().getShowroom();


        btn_save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                if (acc_category.getSelectedItem().toString().trim().equals("Pick one"))
                {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }

                u_name = name.getText().toString().trim();
                u_site = site.getText().toString().trim();
                u_pincode = pincode.getText().toString().trim();
                u_address = address.getText().toString().trim();
                u_city = city.getText().toString().trim();
                u_taluk = taluk.getText().toString().trim();
                u_district = district.getText().toString().trim();
                u_state = state.getText().toString().trim();
                u_mainphn = phone.getText().toString().trim();
                u_email = email.getText().toString().trim();
                u_card = pancard.getText().toString().trim();

                //Contacts
                c_firstname = ed_firstname.getText().toString().trim();
                c_lastname = ed_lastname.getText().toString().trim();
                c_mobile = ed_mobile.getText().toString().trim();
                c_mail = ed_mail_new.getText().toString().trim();

                boolean failFlag = false;

                if (u_name.isEmpty()) {
                    name.setHint("Company Name");
                    name.requestFocus();
                    name.setHintTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
                    failFlag = true;
                }
                if(u_site.isEmpty()){
                    site.setHint("Site/Location");
                    site.requestFocus();
                    site.setHintTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
                    failFlag = true;
                }
                if(u_address.isEmpty()){
                    address.setHint("Address");
                    address.requestFocus();
                    address.setHintTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
                    failFlag = true;
                }
                if(u_pincode.isEmpty()){
                    pincode.setHint("Pincode");
                    pincode.requestFocus();
                    pincode.setHintTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
                    failFlag = true;
                }
                if(u_city.isEmpty()){
                    city.setHint("City");
                    city.requestFocus();
                    city.setHintTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
                    failFlag = true;
                }
                if(u_mainphn.isEmpty() || !Patterns.PHONE.matcher(u_mainphn).matches()){
                    phone.setHint("Main Phone");
                    phone.requestFocus();
                    phone.setHintTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
                    failFlag = true;
                }
                if(u_email.isEmpty() ||!android.util.Patterns.EMAIL_ADDRESS.matcher(u_email).matches()){
                    email.setHint("Email");
                    email.requestFocus();
                    email.setHintTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
                    failFlag = true;
                }
                if(u_card.isEmpty()){
                    pancard.setHint("Pancard");
                    pancard.requestFocus();
                    pancard.setHintTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
                    failFlag = true;
                }

                if(!failFlag)
                {

                    try {
                        JSONObject json = new JSONObject();
                        json.put(ConstantClass.showroomId, getshowid);
                        json.put(ConstantClass.bankId, get_bankid);

                        JSONObject jb = new JSONObject();
                        /*jb.put(ConstantClass.id, "");
                        jb.put(ConstantClass.acc_id_contact, "");
                        jb.put(ConstantClass.firstName, c_firstname);
                        jb.put(ConstantClass.lastName, c_lastname);
                        jb.put(ConstantClass.mobile, c_mobile);
                        jb.put(ConstantClass.email_new, c_mail);
                        jb.put(ConstantClass.designation, "");
                        jb.put(ConstantClass.isPrimary, "");*/

                        JSONArray array = new JSONArray();
                        //array.put(jb);
                        json.put(ConstantClass.Contacts, array);

                        json.put(ConstantClass.acc_id, "");
                        json.put(ConstantClass.company_name, u_name);
                        json.put(ConstantClass.site_location, u_site);
                        json.put(ConstantClass.pin_code, u_pincode);
                        json.put(ConstantClass.address1, u_address);
                        json.put(ConstantClass.address2, u_address);
                        json.put(ConstantClass.city, u_city);
                        json.put(ConstantClass.tauluk, u_taluk);
                        json.put(ConstantClass.distrct, u_district);
                        json.put(ConstantClass.state, u_state);
                        json.put(ConstantClass.main_phone, u_mainphn);
                        json.put(ConstantClass.email, u_email);
                        json.put(ConstantClass.panCard, u_card);
                        json.put(ConstantClass.acc_type, u_acctype);
                        json.put(ConstantClass.acc_category, u_accategory);
                        json.put(ConstantClass.fleetSizeId, u_fleet);
                        json.put(ConstantClass.userId1, get_userid);


                        new SavePostJson(AddAccount_Activity.this, json, "DICV/SaveAccount",AddAccount_Activity.this).execute();

                        System.out.println("ACCCC" + json);
                    } catch (JSONException io) {
                        io.printStackTrace();
                    }
                }
                }

        });


        /*///New INTENT
        btn_contd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                checkforvalidation();

                try
                {
                   JSONObject json = new JSONObject();
                    json.put(ConstantClass.acc_id, "");
                    json.put(ConstantClass.company_name, u_name);
                    json.put(ConstantClass.site_location,u_site);
                    json.put(ConstantClass.pin_code,u_pincode);
                    json.put(ConstantClass.address1,u_address);
                    json.put(ConstantClass.address2,u_address);
                    json.put(ConstantClass.city,u_city);
                    json.put(ConstantClass.tauluk,u_taluk);
                    json.put(ConstantClass.distrct,u_district);
                    json.put(ConstantClass.state,u_state);
                    json.put(ConstantClass.main_phone,u_mainphn);
                    json.put(ConstantClass.email,u_email);
                    json.put(ConstantClass.panCard, u_card);
                    json.put(ConstantClass.acc_type, u_acctype);
                    json.put(ConstantClass.acc_category, u_accategory);
                    json.put(ConstantClass.fleetsize,u_fleet);


                    // JSONArray array =  new JSONArray(ConstantClass.Contacts);
                    if(json.has(ConstantClass.Contacts)){
                        JSONArray array =  new JSONArray(json);

                        JSONObject jb = new JSONObject();
                        //JSONObject json_c = new JSONObject();
                        for (int i = 0; i < jb.length(); i++) {

                            jb.put(ConstantClass.firstName, "null");
                            jb.put(ConstantClass.lastName, "null");
                            jb.put(ConstantClass.mobile, "null");
                            jb.put(ConstantClass.email_new, "null");
                            jb.put(ConstantClass.designation, "null");
                            array.put(jb);
                        }
                    }

                    new SavePostJson(AddAccount_Activity.this, json,"DICV/SaveAccount").execute();

                    // json.put(ConstantClass.Contacts, array);
                    *//*json.put(ConstantClass.Contacts,jb);
                        for (int i = 0; i < jb.length(); i++) {
                            JSONObject jobj = jb.getJSONObject(i);
                            //JSONObject json_c = new JSONObject();
                            jobj.put(ConstantClass.firstName, "null");
                            jobj.put(ConstantClass.lastName, "null");
                            jobj.put(ConstantClass.mobile, "null");
                            jobj.put(ConstantClass.email_new, "null");
                            jobj.put(ConstantClass.designation, "null");
                        }*//*

                    Intent i = new Intent(AddAccount_Activity.this, Opportunity_Activity.class);
                    startActivity(i);


                }
                catch(JSONException io)
                {
                    io.printStackTrace();
                }

            }
        });*/


    }


 public void checkforvalidation() {
/*
        name.setError(null);
        site.setError(null);
        pincode.setError(null);
        address.setError(null);
        city.setError(null);
        taluk.setError(null);
        district.setError(null);
        state.setError(null);
        phone.setError(null);
        email.setError(null);
        pancard.setError(null);*/


    }


    // Do nothing.

 /*public void checkforvalidation()
    {


        // Do nothing.
        if (TextUtils.isEmpty(u_name)) {
            name.setHint("Company Name");
            name.setHintTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
            return;
        }
         if(TextUtils.isEmpty(u_site)){
            site.setHint("Site/Location");
            site.setHintTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
             return;
        }
         if(TextUtils.isEmpty(u_address)){
            address.setHint("Address");
            address.setHintTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
             return;

        }
         if(TextUtils.isEmpty(u_pincode)){
            pincode.setHint("Pincode");
            pincode.setHintTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
             return;
        }
         if(TextUtils.isEmpty(u_city)){
            city.setHint("City");
            city.setHintTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
             return;
        }
         if(TextUtils.isEmpty(u_mainphn)){
            phone.setHint("Main Phone");
            phone.setHintTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
             return;
        }
         if(TextUtils.isEmpty(u_email)){
            email.setHint("Email");
            email.setHintTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
             return;
        }
         if(TextUtils.isEmpty(u_card)){
            pancard.setHint("PAN card");
            pancard.setHintTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
             return;
        }

    }
*/
}
