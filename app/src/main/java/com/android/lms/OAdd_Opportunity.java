package com.android.lms;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class OAdd_Opportunity extends AppCompatActivity
{

    AppCompatSpinner lob, indapp, subapp, model, bodylength, leadqty, sales, enquiry, reason, support, action, documents;
    Button btn_save, save_contd;
    TextView txt_lob, txt_application, txt_subapp, txt_model, txt_qty,txt_leadqty, txt_sales, txt_sale, txt_support, txt_remark,
            txt_action, txt_actionRemark, txt_finame, txt_branch, txt_exename, txt_document;
    ProgressDialog pd;
    JSONObject json;

    String opp_lob, opp_indus, opp_subapp, opp_lead, opp_sales, opp_enquiry;
    String get_userid, get_bankid, getshowid, getroleid, getacc_id;
    ImageView load;
    LinearLayout lr;
    EditText qty, sup_remark, action_remark, finame, branch, exename;

    String ind, getsegid;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_opportunity);

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

        findViewIds();
        //PostJson
        new PostJson(OAdd_Opportunity.this,ConstantClass.URL,"DICV/LOB",this,pd).execute();
        new PostJson(OAdd_Opportunity.this,ConstantClass.URL,"DICV/Segments",this,pd).execute();
        new PostJson(OAdd_Opportunity.this,ConstantClass.URL,"DICV/Application",this,pd).execute();
        new PostJson(OAdd_Opportunity.this,ConstantClass.URL,"DICV/ProductCategory",this,pd).execute();
        new PostJson(OAdd_Opportunity.this,ConstantClass.URL,"DICV/Stages",this,pd).execute();
        new PostJson(OAdd_Opportunity.this,ConstantClass.URL,"DICV/EnquirySources",this,pd).execute();

       // new SavePostJson(OAdd_Opportunity.this, json,"DICV/SaveAccount").execute();



        lob = (AppCompatSpinner)findViewById(R.id.spin_lob);
        lob.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                opp_lob = PostJson.lob_list.get(position).get("LOBId");
                ((TextView) lob.getSelectedView()).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                ((TextView) lob.getSelectedView()).setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720N.TTF"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        indapp = (AppCompatSpinner)findViewById(R.id.spin_IndApplication);
        subapp = (AppCompatSpinner)findViewById(R.id.spin_SubApplication);

        final ArrayList<String> list = new ArrayList<String>();
        ArrayAdapter<String> subFriendAdapter = new ArrayAdapter<String>(
                getApplicationContext(), android.R.layout.simple_spinner_item,
                list);
        subapp.setAdapter(subFriendAdapter);


        indapp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                opp_indus = PostJson.indus_list.get(position).get("Id");
                //opp_indus = parent.getSelectedItem().toString();
                System.out.println("IGGGG"+opp_indus);

                ((TextView) indapp.getSelectedView()).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                ((TextView) indapp.getSelectedView()).setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720N.TTF"));

                list.removeAll(list);

                for (int i = 0; i < PostJson.sub_list.size(); i++)
                {
                    getsegid = PostJson.sub_list.get(i).get(ConstantClass.SegmentId);
                    System.out.println("IDD" + getsegid);

                    if(getsegid.equals(opp_indus))
                    {
                        String sss = PostJson.sub_list.get(i).get(ConstantClass.sub_application);
                        list.add(sss);
                    }

                }
                restlist();
            }

            private void restlist()
            {

                ArrayAdapter<String> subapplication = new ArrayAdapter<String>(getApplicationContext(),
                                                                  android.R.layout.simple_spinner_item, list);
                subapplication.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                subapplication.notifyDataSetChanged();
                subapp.setAdapter(subapplication);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        subapp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                opp_subapp =PostJson.sub_list.get(position).get("Id");
                //opp_subapp = parent.getSelectedItem().toString();
                ((TextView) subapp.getSelectedView()).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                ((TextView) subapp.getSelectedView()).setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720N.TTF"));

                    System.out.println("GOTT"+ opp_subapp);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        leadqty = (AppCompatSpinner)findViewById(R.id.spin_quality);
        leadqty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                opp_lead = PostJson.lead_list.get(position).get("Id");
                //opp_lead = parent.getSelectedItem().toString();
                ((TextView) leadqty.getSelectedView()).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                ((TextView) leadqty.getSelectedView()).setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720N.TTF"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        sales = (AppCompatSpinner)findViewById(R.id.spin_sales);
        sales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                opp_sales = PostJson.sales_list.get(position).get("Id");
                //opp_sales = parent.getSelectedItem().toString();
                String opp = PostJson.sales_list.get(position).get("Title");
                ((TextView) sales.getSelectedView()).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                ((TextView) sales.getSelectedView()).setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720N.TTF"));

               if( opp.equals("0. Account Created"))

                {
                  lr.setVisibility(View.GONE);
                }

                else if (opp.equals("1. Customer Contacted"))
               {
                   lr.setVisibility(View.VISIBLE);

                   //Support
                   txt_support.setVisibility(View.GONE);
                   support.setVisibility(View.GONE);
                   //sup_remark
                   txt_remark.setVisibility(View.GONE);
                   sup_remark.setVisibility(View.GONE);

                   //action
                   txt_action.setVisibility(View.GONE);
                   action.setVisibility(View.GONE);
                   //action_remark
                   txt_actionRemark.setVisibility(View.GONE);
                   action_remark.setVisibility(View.GONE);

                   txt_finame.setVisibility(View.GONE);
                   finame.setVisibility(View.GONE);
                   txt_branch.setVisibility(View.GONE);
                   branch.setVisibility(View.GONE);
                   txt_exename.setVisibility(View.GONE);
                   exename.setVisibility(View.GONE);
                   txt_document.setVisibility(View.GONE);
                   documents.setVisibility(View.GONE);

                   //Reason for sale
                   txt_sale.setVisibility(View.GONE);
                   reason.setVisibility(View.GONE);

               }

                else if (opp.equals("2. Actively in Buying Stage"))
               {
                   lr.setVisibility(View.VISIBLE);

                   //Support
                   txt_support.setVisibility(View.VISIBLE);
                   support.setVisibility(View.VISIBLE);
                   //sup_remark
                   txt_remark.setVisibility(View.VISIBLE);
                   sup_remark.setVisibility(View.VISIBLE);

                   //action
                   txt_action.setVisibility(View.VISIBLE);
                   action.setVisibility(View.VISIBLE);
                   //action_remark
                   txt_actionRemark.setVisibility(View.VISIBLE);
                   action_remark.setVisibility(View.VISIBLE);

                   txt_finame.setVisibility(View.GONE);
                   finame.setVisibility(View.GONE);
                   txt_branch.setVisibility(View.GONE);
                   branch.setVisibility(View.GONE);
                   txt_exename.setVisibility(View.GONE);
                   exename.setVisibility(View.GONE);
                   txt_document.setVisibility(View.GONE);
                   documents.setVisibility(View.GONE);

                   //reason fr sale
                   txt_sale.setVisibility(View.GONE);
                   reason.setVisibility(View.GONE);
               }
                else if (opp.equals("3. Interested in BharatBenz"))
               {
                   lr.setVisibility(View.VISIBLE);

                   //Support
                   txt_support.setVisibility(View.GONE);
                   support.setVisibility(View.GONE);
                   //sup_remark
                   txt_remark.setVisibility(View.GONE);
                   sup_remark.setVisibility(View.GONE);

                   //action
                   txt_action.setVisibility(View.GONE);
                   action.setVisibility(View.GONE);
                   //action_remark
                   txt_actionRemark.setVisibility(View.GONE);
                   action_remark.setVisibility(View.GONE);

                   txt_finame.setVisibility(View.GONE);
                   finame.setVisibility(View.GONE);
                   txt_branch.setVisibility(View.GONE);
                   branch.setVisibility(View.GONE);
                   txt_exename.setVisibility(View.GONE);
                   exename.setVisibility(View.GONE);
                   txt_document.setVisibility(View.GONE);
                   documents.setVisibility(View.GONE);

                   //reason fr sale
                   txt_sale.setVisibility(View.GONE);
                   reason.setVisibility(View.GONE);
               }

                else if (opp.equals("4. Case under Finance"))
               {
                   lr.setVisibility(View.VISIBLE);

                   //Support
                   txt_support.setVisibility(View.GONE);
                   support.setVisibility(View.GONE);
                   //sup_remark
                   txt_remark.setVisibility(View.GONE);
                   sup_remark.setVisibility(View.GONE);

                   //action
                   txt_action.setVisibility(View.GONE);
                   action.setVisibility(View.GONE);
                   //action_remark
                   txt_actionRemark.setVisibility(View.GONE);
                   action_remark.setVisibility(View.GONE);

                   txt_finame.setVisibility(View.VISIBLE);
                   finame.setVisibility(View.VISIBLE);
                   txt_branch.setVisibility(View.VISIBLE);
                   branch.setVisibility(View.VISIBLE);
                   txt_exename.setVisibility(View.VISIBLE);
                   exename.setVisibility(View.VISIBLE);
                   txt_document.setVisibility(View.VISIBLE);
                   documents.setVisibility(View.VISIBLE);


                   //reason fr sale
                   txt_sale.setVisibility(View.GONE);
                   reason.setVisibility(View.GONE);
               }

                else if (opp.equals("5a. Retailed"))
               {
                   lr.setVisibility(View.VISIBLE);

                   //Support
                   txt_support.setVisibility(View.GONE);
                   support.setVisibility(View.GONE);
                   //sup_remark
                   txt_remark.setVisibility(View.GONE);
                   sup_remark.setVisibility(View.GONE);

                   //action
                   txt_action.setVisibility(View.GONE);
                   action.setVisibility(View.GONE);
                   //action_remark
                   txt_actionRemark.setVisibility(View.GONE);
                   action_remark.setVisibility(View.GONE);

                   txt_finame.setVisibility(View.GONE);
                   finame.setVisibility(View.GONE);
                   txt_branch.setVisibility(View.GONE);
                   branch.setVisibility(View.GONE);
                   txt_exename.setVisibility(View.GONE);
                   exename.setVisibility(View.GONE);
                   txt_document.setVisibility(View.GONE);
                   documents.setVisibility(View.GONE);

                   //reason fr sale
                   txt_sale.setVisibility(View.GONE);
                   reason.setVisibility(View.GONE);
               }

                else if (opp.equals("5b. Lost Analysis"))
               {
                   lr.setVisibility(View.VISIBLE);

                   //Support
                   txt_support.setVisibility(View.GONE);
                   support.setVisibility(View.GONE);
                   //sup_remark
                   txt_remark.setVisibility(View.GONE);
                   sup_remark.setVisibility(View.GONE);

                   //action
                   txt_action.setVisibility(View.GONE);
                   action.setVisibility(View.GONE);
                   //action_remark
                   txt_actionRemark.setVisibility(View.GONE);
                   action_remark.setVisibility(View.GONE);

                   txt_finame.setVisibility(View.GONE);
                   finame.setVisibility(View.GONE);
                   txt_branch.setVisibility(View.GONE);
                   branch.setVisibility(View.GONE);
                   txt_exename.setVisibility(View.GONE);
                   exename.setVisibility(View.GONE);
                   txt_document.setVisibility(View.GONE);
                   documents.setVisibility(View.GONE);

                   //reason fr sale
                   txt_sale.setVisibility(View.VISIBLE);
                   reason.setVisibility(View.VISIBLE);
               }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        enquiry = (AppCompatSpinner)findViewById(R.id.spin_enquiry);
        enquiry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                opp_enquiry = PostJson.enq_list.get(position).get("Id");
               // opp_enquiry = parent.getSelectedItem().toString();
                ((TextView) enquiry.getSelectedView()).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                ((TextView) enquiry.getSelectedView()).setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720N.TTF"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        System.out.println("SPINN" + opp_indus +opp_subapp + opp_lead+ opp_sales + opp_enquiry);

        get_userid = DataHolderClass.getInstance().getUserid();
        get_bankid = DataHolderClass.getInstance().getBank();
        getshowid = DataHolderClass.getInstance().getShowroom();
        getroleid = DataHolderClass.getInstance().getRole();
        getacc_id = DataHolderClass.getInstance().getAcc_id();

        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));
        save_contd = (Button) findViewById(R.id.btn_savecontine);
        save_contd.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));



        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    JSONObject json = new JSONObject();
                    json.put(ConstantClass.showroomId, getshowid);
                    json.put(ConstantClass.bankId,get_bankid);
                    json.put(ConstantClass.roleId, getroleid);
                    json.put(ConstantClass.OpportunityId, "");
                    json.put(ConstantClass.UserId, get_userid);
                    json.put(ConstantClass.acc_id, getacc_id);
                    json.put(ConstantClass.EnquirySourceId, opp_enquiry);
                    json.put(ConstantClass.SegmentId, opp_indus);
                    json.put(ConstantClass.ApplicationId, opp_subapp);
                    json.put(ConstantClass.ProductCategoryId, opp_lead);
                    json.put(ConstantClass.ModelId, "");
                    json.put(ConstantClass.WheelBaseId, "");
                    json.put(ConstantClass.LoadBodyId,"");
                    json.put(ConstantClass.Quantity, "");
                    json.put(ConstantClass.StageId, opp_sales);
                    json.put(ConstantClass.LeadQualityId, "");
                    json.put(ConstantClass.PurchasePlanId, "");
                    json.put(ConstantClass.SupportId, "");
                    json.put(ConstantClass.ActionId, "");
                    json.put(ConstantClass.SupportRemarks, "");
                    json.put(ConstantClass.ActionRemarks, "");
                    json.put(ConstantClass.DocsCollectedId, "");
                    json.put(ConstantClass.CreditApprovalId, "");
                    json.put(ConstantClass.ApprovalReceivedId, "");
                    json.put(ConstantClass.FinanceSignedId, "");
                    json.put(ConstantClass.PDCId, "");
                    json.put(ConstantClass.FinancerSanctionLetterId, "");
                    json.put(ConstantClass.ListRemainingDocs, "");
                    json.put(ConstantClass.LostAnalysisId, "");
                    json.put(ConstantClass.OEM, "");
                    json.put(ConstantClass.Model, "");
                    json.put(ConstantClass.Qty, "");
                    json.put(ConstantClass.Diff, "");
                    json.put(ConstantClass.Price, "");
                    json.put(ConstantClass.FiancerName, "");
                    json.put(ConstantClass.Branch, "");
                    json.put(ConstantClass.Executive, "");
                    json.put(ConstantClass.LOBId, opp_lob);
                    json.put(ConstantClass.Notes, "");

                    new SavePostJson(OAdd_Opportunity.this, json, "DICV/SaveOpportunity", OAdd_Opportunity.this).execute();

                    System.out.println("OPPP" + json);
                } catch (JSONException io) {
                    io.printStackTrace();
                }
            }
        });
    }



    public void findViewIds()
    {
        load = (ImageView)findViewById(R.id.anim);
        lr = (LinearLayout)findViewById(R.id.linear_opp);

        txt_lob = (TextView)findViewById(R.id.txt_lob);
        txt_lob.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_application = (TextView)findViewById(R.id.txt_application);
        txt_application.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_subapp = (TextView)findViewById(R.id.txt_subapp);
        txt_subapp.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_model = (TextView)findViewById(R.id.txt_model);
        txt_model.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_qty = (TextView)findViewById(R.id.txt_qty);
        txt_qty.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_leadqty = (TextView)findViewById(R.id.txt_leadqty);
        txt_leadqty.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_sales = (TextView)findViewById(R.id.txt_sales);
        txt_sales.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_sale = (TextView)findViewById(R.id.txt_sale);
        txt_sale.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_support = (TextView)findViewById(R.id.txt_support);
        txt_support.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_remark = (TextView)findViewById(R.id.txt_remark);
        txt_remark.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_action = (TextView)findViewById(R.id.txt_action);
        txt_action.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_actionRemark = (TextView)findViewById(R.id.txt_actionRemark);
        txt_actionRemark.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_finame = (TextView)findViewById(R.id.txt_finame);
        txt_finame.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_branch = (TextView)findViewById(R.id.txt_branch);
        txt_branch.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_exename = (TextView)findViewById(R.id.txt_exename);
        txt_exename.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));

        txt_document = (TextView)findViewById(R.id.txt_document);
        txt_document.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720L.TTF"));


        model = (AppCompatSpinner)findViewById(R.id.spin_model);
        bodylength = (AppCompatSpinner) findViewById(R.id.spin_body);
        qty = (EditText)findViewById(R.id.edit_qty);
        sup_remark = (EditText)findViewById(R.id.edit_remark);
        action_remark = (EditText)findViewById(R.id.edit_actionRemark);
        action = (AppCompatSpinner)findViewById(R.id.spin_action);
        support = (AppCompatSpinner)findViewById(R.id.spin_support);
        reason = (AppCompatSpinner)findViewById(R.id.spin_rsale);
        finame = (EditText)findViewById(R.id.edit_finame);
        branch = (EditText)findViewById(R.id.edit_branch);
        exename = (EditText)findViewById(R.id.edit_exename);
        documents = (AppCompatSpinner)findViewById(R.id.spin_docs);
    }
}
