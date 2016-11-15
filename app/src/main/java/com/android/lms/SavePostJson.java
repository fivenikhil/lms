package com.android.lms;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SavePostJson extends AsyncTask<String, Integer, String>
{

    Parcel parcel;
    private Context context;
    private String url;
    private String type;
    public AppCompatActivity ad;

    private JSONObject jo;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public  ArrayList<ContactInfo> listt;
    public ArrayList<HashMap<String, String>> acc_list, funnel_list, opp_list;

    public SavePostJson(Context context, JSONObject jo,String type, AppCompatActivity ad)
    {
        this.context=context;
        this.jo=jo;
        this.ad =ad;
        this.type=type;

        acc_list = new ArrayList<HashMap<String, String>>();
        funnel_list =  new ArrayList<HashMap<String, String>>();
        opp_list = new ArrayList<HashMap<String, String>>();
        listt =  new ArrayList<ContactInfo>();
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params)
    {
        try
        {

            OkHttpClient client = new OkHttpClient();

           /* RequestBody formBody = new FormBody.Builder()

                    .build();*/
            RequestBody formBody = RequestBody.create(JSON, jo.toString());
            Request request = new Request.Builder()
                    .url(ConstantClass.URL+"/"+type)
                    .post(formBody)
                    .build();

            Response response = client.newCall(request).execute();
            String output = response.body().string();
            //System.out.println("POSTING : " + output);
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

        try
        {

            if (type.equals("DICV/SaveAccount"))
            {
                System.out.println("ACCOUNT_POST=" + output);
                JSONObject jb = new JSONObject(output);
                String acc_id = jb.getString("accountId");

                DataHolderClass.getInstance().setAcc_id(acc_id);

            }
            else if(type.equals("DICV/SaveOpportunity"))
            {
                System.out.println("OPP_POST=" + output);
            }

            else if(type.equals("DICV/SearchAccounts"))
            {
               // System.out.println("Search_ACC=" + output);
                JSONArray jb =  new JSONArray(output);
                for (int i = 0; i < jb.length(); i++)
                {
                    JSONObject jobj = jb.getJSONObject(i);
                    String name = jobj.getString(ConstantClass.companyName);
                    String site = jobj.getString(ConstantClass.siteLocation);
                    String phn = jobj.getString(ConstantClass.mainPhoneNumber);

                   /* String opp1 = jobj.getString(ConstantClass.opportunities);
                    //System.out.println("OPPPPPP"+opp1);

                  JSONArray jsonArray =  new JSONArray(opp1);
                    for (int j=0; j< opp1.length(); j++)
                    {
                        String contactno = jsonArray.getString(i);
                       //JSONObject jsonobject= (JSONObject) jsonArray.get(i);
                       *//* JSONObject jsonObject = jb.getJSONObject(1);
                        String model = jobj.getString(ConstantClass.model);
                        String contactno = jsonObject.getString(ConstantClass.contactNo);
                        String stages = jsonObject.getString(ConstantClass.salesStage);


                        HashMap<String, String> opp = new HashMap<>();
                        //opp.put(ConstantClass.model, model);
                        opp.put(ConstantClass.contactNo, contactno);
                        opp.put(ConstantClass.salesStage, stages);
                        opp_list.add(opp);*//*
                        System.out.println("JJJJJJJ"+ jsonArray +contactno);
                    }*/
                    HashMap<String, String> list21 = new HashMap<>();
                    list21.put(ConstantClass.companyName, name);
                    list21.put(ConstantClass.siteLocation , site);
                    list21.put(ConstantClass.mainPhoneNumber, phn);
                    acc_list.add(list21);


                }

                if(ad instanceof Accounts_Activity)
                {
                    Accounts_Activity Odd = (Accounts_Activity) ad;
                    Odd.datalist(acc_list);
                }
                else if(ad instanceof Opportunity_Activity)
                {
                    Opportunity_Activity addd = (Opportunity_Activity) ad;
                    addd.listdata(acc_list);
                }
                else
                {

                }
            }
            else if(type.equals("DICV/SalesFunnel"))
            {
                JSONArray jb =  new JSONArray(output);
                System.out.println("Total" + output);

                for (int i = 0; i < jb.length(); i++)
                {
                    JSONObject jobj = jb.getJSONObject(i);
                    String stage = jobj.getString(ConstantClass.stage);
                    String qty = jobj.getString(ConstantClass.qty);
                    String count = jobj.getString(ConstantClass.opportunityCount);

                    HashMap<String, String> listfunnel = new HashMap<>();
                    listfunnel.put(ConstantClass.stage, stage);
                    listfunnel.put(ConstantClass.opportunityCount, count);
                    funnel_list.add(listfunnel);
                }
                System.out.println("SALES" + funnel_list);

                Sales_Activity sales_activity = (Sales_Activity) ad;
                sales_activity.funnellist(funnel_list);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
