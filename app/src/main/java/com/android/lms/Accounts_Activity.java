package com.android.lms;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class Accounts_Activity extends AppCompatActivity
{

    //SearchView searchView;
    TableLayout table;
    ListView list;
    ProgressDialog pd;
    String get_userid;

    private ArrayList<ContactInfo> mArrayList = null;
    private Accounts_Adapter adapter;

    private int offset = 0;
    private ProgressBar progressBar;
    private Button first;
    private Button last;
    private Button prev;
    private Button next;
    int no=0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leads_content);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));

        TextView txt = (TextView) findViewById(R.id.toolbar_title);
        txt.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH725B.TTF"));

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setDisplayShowTitleEnabled(false);
                actionBar.setDisplayUseLogoEnabled(false);
                actionBar.setHomeButtonEnabled(false);
            }
        }


        get_userid = DataHolderClass.getInstance().getUserid();

       /* searchView = (SearchView) findViewById(R.id.search_view);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchQuery) {
                //list.invalidate();
                String text = searchQuery;
                adapter.filter(text);
                return true;
            }
        });*/

        table = (TableLayout) findViewById(R.id.table_new);
        list = (ListView) findViewById(R.id.list_view);


        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        TextView textViewDisplaying = (TextView) findViewById(R.id.displaying);
        first = (Button) findViewById(R.id.buttonfirst);
        prev = (Button) findViewById(R.id.buttonprev);
        next = (Button) findViewById(R.id.buttonnext);
        last = (Button) findViewById(R.id.buttonlast);
        Datasource datasource = Datasource.getInstance();


       // next.setOnClickListener(this);
       // prev.setOnClickListener(this);


        try {
            JSONObject json = new JSONObject();
            json.put(ConstantClass.searchKeyword, "");
            json.put(ConstantClass.index, 1);
            json.put(ConstantClass.offset, 10);
            json.put(ConstantClass.UserId, get_userid);

         new SavePostJson(Accounts_Activity.this, json, "DICV/SearchAccounts",this).execute();



        } catch (JSONException io) {
            io.printStackTrace();
        }



   /* String ss = DataHolderClass.getInstance().getList().toString();

        for (int i = 0; i < ss.length(); i++)
        {
            String s = DataHolderClass.getInstance().getList().get(i).toString();

        }*/

       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(Accounts_Activity.this, android.R.layout.simple_list_item_1);
       // list.setAdapter(adapter);
       // adapter.notifyDataSetChanged();


    }

 public void datalist(ArrayList<HashMap<String, String>> acc_list)
    {
        mArrayList = new ArrayList<>();
        for (int i = 0; i < acc_list.size(); i++)
        {
            String sss = acc_list.get(i).toString();

            String s1 = acc_list.get(i).get("CompanyName");
            String s2 = acc_list.get(i).get("MainPhoneNumber");
            String s3 = acc_list.get(i).get("SiteLocation");
            System.out.println("ssssssssssss"+s1);

            //mArrayList.add(s1);
            ContactInfo info = new ContactInfo();
            info.setName(s1);
            info.setPhn(s2);
            info.setSite(s3);
            mArrayList.add(info);

        }

       // changeTheList(mArrayList.get(no));
      //  no++;

       /*next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("The value of no==",String.valueOf(no));
                if(mArrayList.size() > no) {
                    changeTheList(mArrayList.get(no));
                    no++;
                }
            }
        });*/

        adapter = new Accounts_Adapter(Accounts_Activity.this, mArrayList);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                // TODO Auto-generated method stub

                System.out.println("jjjj"+mArrayList.get(position));
            }
        });

    }

    public void changeTheList(ContactInfo ci)
    {
        ArrayList<ContactInfo> temp=new ArrayList<>();
        temp.add(ci);
        adapter = new Accounts_Adapter(Accounts_Activity.this, temp);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub

                //System.out.println("jjjj"+mArrayList.get(arg2));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.leads, menu);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.colorWhite));
        searchEditText.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorWhite)));
        searchEditText.setHintTextColor(getResources().getColor(R.color.colorWhite));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchQuery) {
                //list.invalidate();
                String text = searchQuery.toString().trim();
                adapter.filter(text);
                return true;
            }
        });
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

            Intent i = new Intent(Accounts_Activity.this, AddAccount_Activity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


   /*@Override
    public void onClick(View v)
    {
        switch (v.getId())
        {

            case R.id.buttonnext:
            Log.d("The value of no==",String.valueOf(no));
            if(mArrayList.size() > no) {
                changeTheList(mArrayList.get(no));
                no++;
            }
                break;

            case R.id.buttonprev:
                Log.d("The value of no==",String.valueOf(no));
                if(no <mArrayList.size()) {
                    changeTheList(mArrayList.get(no));
                    no--;
                }
        }

    }*/
}
