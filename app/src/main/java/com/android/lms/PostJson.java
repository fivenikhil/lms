package com.android.lms;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostJson extends AsyncTask<String, Integer, String>
{

    private Context context;
    private String url;
    private String type;
    public static ArrayList<String> acc_type, acc_cat_type, fleet_size, enq_service;
    public static ArrayList<String> opp_lob, opp_indapp, opp_subapp, opp_load, opp_sales;
    public static ArrayList<String> follow_type, follow_status;
    public AppCompatActivity ad;
    ProgressDialog pd;
    public static ArrayList<HashMap<String, String>> lob_list, indus_list, sub_list, lead_list, sales_list, enq_list,
                             acc_typelist, acc_catlist, fleet_list;

    public PostJson(Context context, String url, String type, AppCompatActivity ad, ProgressDialog pd)
    {
        this.context=context;
        this.type=type;
        this.url=url;
        this.ad=ad;
        this.pd = pd;
        acc_type = new ArrayList<>();
        acc_cat_type = new ArrayList<>();
        fleet_size = new ArrayList<>();
        enq_service = new ArrayList<>();

        opp_lob = new ArrayList<>();
        opp_indapp = new ArrayList<>();
        opp_subapp = new ArrayList<>();
        opp_load = new ArrayList<>();
        opp_sales = new ArrayList<>();

        follow_type = new ArrayList<>();
        follow_status = new ArrayList<>();

        lob_list=new ArrayList<HashMap<String, String>>();
        indus_list=new ArrayList<HashMap<String, String>>();
        sub_list=new ArrayList<HashMap<String, String>>();
        lead_list=new ArrayList<HashMap<String, String>>();
        sales_list=new ArrayList<HashMap<String, String>>();
        enq_list=new ArrayList<HashMap<String, String>>();

        acc_typelist=new ArrayList<HashMap<String, String>>();
        acc_catlist=new ArrayList<HashMap<String, String>>();
        fleet_list=new ArrayList<HashMap<String, String>>();


    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        pd = new ProgressDialog(context);
       /* pd.setProgressDrawable(ContextCompat.getDrawable(context,R.drawable.loading));
        Glide.with(context).load(R.drawable.loading)
                .priority(Priority.IMMEDIATE)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(context.R.id.anim);*/
        pd.show();

    }

    @Override
    protected String doInBackground(String... arg0)
    {
        try
        {

            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormBody.Builder().build();

            Request request = new Request.Builder()
                    .url(ConstantClass.URL+"/"+type)
                    .post(formBody)
                    .build();

            Response response = client.newCall(request).execute();
            String output = response.body().string();
            System.out.println("RESULT : " + output);
            return output;

        }
        catch(IOException io)
        {
            io.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String output)
    {
        super.onPostExecute(output);
        System.out.println("REQUEST_GET=" + output);

        if (pd.isShowing()) {
            pd.dismiss();
        }

        try
        {

            if(type.equals("DICV/AccountTypes"))
            {

                JSONArray jobject = new JSONArray(output);
                for (int i = 0; i < jobject.length(); i++)
                {
                    JSONObject jobj = jobject.getJSONObject(i);
                    String acctype = jobj.getString(ConstantClass.acctype);
                    String type = jobj.getString(ConstantClass.acctypeId);
                    //System.out.println("RESULTTTT : " + acctype);

                    HashMap<String, String> list21 = new HashMap<>();
                    list21.put(ConstantClass.acctypeId, type);
                    list21.put(ConstantClass.acctype , acctype);
                    acc_typelist.add(list21);

                    acc_type.add(acctype);
                }

                AddAccount_Activity add = (AddAccount_Activity) ad;
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, acc_type);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter.notifyDataSetChanged();
                add.acc_type.setAdapter(dataAdapter);

            }

            else if(type.equals("DICV/AccountCategories"))
            {

                JSONArray jobject = new JSONArray(output);
                for (int i = 0; i < jobject.length(); i++)
                {
                    JSONObject jobj = jobject.getJSONObject(i);
                    String accatgoery = jobj.getString(ConstantClass.acccatogery);
                    String acccatid = jobj.getString(ConstantClass.accountCategoryId);
                    //System.out.println("RESULTTTT : " + accatgoery);
                    acc_cat_type.add(accatgoery);

                    HashMap<String, String> list21 = new HashMap<>();
                    list21.put(ConstantClass.accountCategoryId , acccatid);
                    list21.put(ConstantClass.acccatogery, accatgoery);
                    acc_catlist.add(list21);

                }

                AddAccount_Activity add = (AddAccount_Activity) ad;
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, acc_cat_type);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter.notifyDataSetChanged();
                add.acc_category.setAdapter(dataAdapter);
              }

            else if(type.equals("DICV/FleetSizes"))
            {

                JSONArray jobject = new JSONArray(output);
                for (int i = 0; i < jobject.length(); i++)
                {
                    JSONObject jobj = jobject.getJSONObject(i);
                    String ftsize = jobj.getString(ConstantClass.fleetsize);
                    String idg = jobj.getString("Id");
                    //System.out.println("RESULTTTT : " + ftsize);
                    fleet_size.add(ftsize);

                    HashMap<String, String> list21 = new HashMap<>();
                    list21.put("Id" , idg);
                    list21.put(ConstantClass.fleetsize, ftsize);
                    fleet_list.add(list21);
                }

                AddAccount_Activity add = (AddAccount_Activity) ad;
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, fleet_size);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter.notifyDataSetChanged();
                add.fleetsize.setAdapter(dataAdapter);
            }

            else if(type.equals("DICV/EnquirySources"))
            {
                JSONArray jobject = new JSONArray(output);
                for (int i = 0; i < jobject.length(); i++)
                {
                    JSONObject jobj = jobject.getJSONObject(i);
                    String service = jobj.getString(ConstantClass.enquiry);
                    String id1 = jobj.getString("Id");
                    //System.out.println("RESULTTTT : " + service);
                    enq_service.add(service);

                    HashMap<String, String> list21 = new HashMap<>();
                    list21.put(ConstantClass.lob , service);
                    list21.put("Id", id1);
                    enq_list.add(list21);
                }

                OAdd_Opportunity add = (OAdd_Opportunity) ad;
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, enq_service);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter.notifyDataSetChanged();
                add.enquiry.setAdapter(dataAdapter);
            }

            else if(type.equals("DICV/LOB"))
            {
                JSONArray jobject = new JSONArray(output);
                for (int i = 0; i < jobject.length(); i++)
                {
                    JSONObject jobj = jobject.getJSONObject(i);
                    String lob = jobj.getString(ConstantClass.lob);
                    String lobid = jobj.getString("LOBId");
                    //System.out.println("RESULTTTT : " + service);
                    opp_lob.add(lob);


                    HashMap<String, String> list21 = new HashMap<>();
                    list21.put(ConstantClass.lob , lob);
                    list21.put("LOBId", lobid);
                    lob_list.add(list21);
                }

                OAdd_Opportunity add = (OAdd_Opportunity) ad;
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, opp_lob);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter.notifyDataSetChanged();
                add.lob.setAdapter(dataAdapter);

               // System.out.println("lobbbb" +  lob_list);
            }

            else if(type.equals("DICV/Segments"))
            {

                JSONArray jobject = new JSONArray(output);
                for (int i = 0; i < jobject.length(); i++)
                {
                    JSONObject jobj = jobject.getJSONObject(i);
                    String segment = jobj.getString(ConstantClass.indus_application);
                    String id = jobj.getString("Id");
                    //System.out.println("RESULTTTT : " + service);
                    opp_indapp.add(segment);

                    HashMap<String, String> l = new HashMap<>();
                    l.put(ConstantClass.indus_application , segment);
                    l.put("Id", id);
                    indus_list.add(l);
                }
                OAdd_Opportunity add = (OAdd_Opportunity) ad;
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, opp_indapp);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter.notifyDataSetChanged();
                add.indapp.setAdapter(dataAdapter);

            }

            else if(type.equals("DICV/Application"))
            {

                JSONArray jobject = new JSONArray(output);
                for (int i = 0; i < jobject.length(); i++)
                {
                    JSONObject jobj = jobject.getJSONObject(i);
                    String app = jobj.getString(ConstantClass.sub_application);
                    String segmentID = jobj.getString(ConstantClass.SegmentId);
                    String s_id = jobj.getString(ConstantClass.id);
                    //System.out.println("RESULTTTT : " + service);
                    opp_subapp.add(app);

                    HashMap<String, String> ll = new HashMap<>();
                    ll.put(ConstantClass.sub_application , app);
                    ll.put(ConstantClass.SegmentId, segmentID);
                    ll.put(ConstantClass.id,s_id);
                    sub_list.add(ll);

                }
                DataHolderClass.getInstance().setList_segmentid(sub_list);
                //System.out.println("RESULTTTT : " + sub_list);
               // OAdd_Opportunity add = (OAdd_Opportunity) ad;
               // ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, opp_subapp);
               // dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
               // dataAdapter.notifyDataSetChanged();
               // add.subapp.setAdapter(dataAdapter);
            }

            else if(type.equals("DICV/ProductCategory"))
            {

                JSONArray jobject = new JSONArray(output);
                for (int i = 0; i < jobject.length(); i++)
                {
                    JSONObject jobj = jobject.getJSONObject(i);
                    String lead = jobj.getString(ConstantClass.lead_quality);
                    String idd = jobj.getString("Id");
                    //System.out.println("RESULTTTT : " + service);
                    opp_load.add(lead);

                    HashMap<String, String> ll = new HashMap<>();
                    ll.put(ConstantClass.lead_quality , lead);
                    ll.put("Id", idd);
                    lead_list.add(ll);
                }

                OAdd_Opportunity add = (OAdd_Opportunity) ad;
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, opp_load);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter.notifyDataSetChanged();
                add.leadqty.setAdapter(dataAdapter);
            }

            else if(type.equals("DICV/Stages"))
            {

                JSONArray jobject = new JSONArray(output);
                for (int i = 0; i < jobject.length(); i++)
                {
                    JSONObject jobj = jobject.getJSONObject(i);
                    String sales = jobj.getString(ConstantClass.sales_stage);
                    String iddd = jobj.getString("Id");
                    //System.out.println("RESULTTTT : " + service);
                    opp_sales.add(sales);

                    HashMap<String, String> ll = new HashMap<>();
                    ll.put(ConstantClass.sales_stage , sales);
                    ll.put("Id", iddd);
                    sales_list.add(ll);
                }

                OAdd_Opportunity add = (OAdd_Opportunity) ad;
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, opp_sales);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter.notifyDataSetChanged();
                add.sales.setAdapter(dataAdapter);
            }

            else if(type.equals("DICV/FollowupTypes"))
            {

                JSONArray jobject = new JSONArray(output);
                for (int i = 0; i < jobject.length(); i++)
                {
                    JSONObject jobj = jobject.getJSONObject(i);
                    String sales = jobj.getString(ConstantClass.follow_types);
                    //System.out.println("RESULTTTT : " + service);
                    follow_type.add(sales);
                }

                FAdd_Followup_Next add = (FAdd_Followup_Next) ad;
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, follow_type);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter.notifyDataSetChanged();
                add.type.setAdapter(dataAdapter);
            }


            else if(type.equals("DICV/FollowupStatus"))
            {

                JSONArray jobject = new JSONArray(output);
                for (int i = 0; i < jobject.length(); i++)
                {
                    JSONObject jobj = jobject.getJSONObject(i);
                    String status = jobj.getString(ConstantClass.follow_status);
                    //System.out.println("RESULTTTT : " + service);
                    follow_status.add(status);
                }

                FAdd_Followup_Next add = (FAdd_Followup_Next) ad;
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, follow_status);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter.notifyDataSetChanged();
                add.status.setAdapter(dataAdapter);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
