package com.android.lms;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Sales_Activity extends AppCompatActivity {

    //private RecyclerView rv;
    String getuser_id;
    ListView rv;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sales_content);

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

        rv = (ListView) findViewById(R.id.recyclerview);

        getuser_id = DataHolderClass.getInstance().getUserid();

        try
        {
            JSONObject jj = new JSONObject();
            jj.put(ConstantClass.UserId, getuser_id);
            new SavePostJson(Sales_Activity.this,jj, "DICV/SalesFunnel", this).execute();

        } catch (JSONException io) {
            io.printStackTrace();
        }


    }

    public void funnellist (ArrayList<HashMap<String, String>> funnel_list)
    {
        if(funnel_list.size()!=0)
        {
           /* //stringArrayList = new ArrayList<>();
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            rv.setLayoutManager(layoutManager);
            rv.setNestedScrollingEnabled(false);
            rv.setHasFixedSize(true);*/
            Sales_Adapter adapter = new Sales_Adapter(this, funnel_list);
            rv.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }
        else
        {
            Toast.makeText(this,"No data Available",Toast.LENGTH_SHORT).show();

        }

    }


}
