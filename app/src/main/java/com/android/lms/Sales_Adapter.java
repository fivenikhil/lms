package com.android.lms;


import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Sales_Adapter extends BaseAdapter
{
    private Context context;
    private ArrayList<HashMap<String, String>> mArrayList;

    //constructor
    public Sales_Adapter(Context context, ArrayList<HashMap<String, String>> mArrayList)
    {
        // mArrayList.addAll(myList);
        this.context=context;
        this.mArrayList=mArrayList;
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        if (v == null ) {
           v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.sales_content_recyclerview, parent, false);

            TextView txt_stage = (TextView) v.findViewById(R.id.stages);
            TextView txt_id = (TextView) v.findViewById(R.id.oppId);

            txt_stage.setText(mArrayList.get(position).get(ConstantClass.stage));
            txt_id.setText(mArrayList.get(position).get(ConstantClass.opportunityCount));

            txt_stage.setTypeface(Typeface.createFromAsset(context.getAssets(), "GOTH720L.TTF"));
            txt_id.setTypeface(Typeface.createFromAsset(context.getAssets(), "GOTH720L.TTF"));
        }
        return v;
    }

   /* private Context context;
    private ArrayList<HashMap<String, String>> mArrayList;

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView txt_stage, txt_id;

        public ViewHolder(View v)
        {
            super(v);
            txt_stage = (TextView) v.findViewById(R.id.stages);
            txt_id = (TextView) v.findViewById(R.id.oppId);

        }
    }

    //constructor
    public Sales_Adapter(Context context, ArrayList<HashMap<String, String>> mArrayList)
    {
       // mArrayList.addAll(myList);
        this.context=context;
        this.mArrayList=mArrayList;
    }

    @Override
    public Sales_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sales_content_recyclerview, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(Sales_Adapter.ViewHolder holder, int position) {

        holder.txt_stage.setText(mArrayList.get(position).get(ConstantClass.stage));
        holder.txt_id.setText(mArrayList.get(position).get(ConstantClass.opportunityCount));

        holder.txt_stage.setTypeface(Typeface.createFromAsset(context.getAssets(), "GOTH720L.TTF"));
        holder.txt_id.setTypeface(Typeface.createFromAsset(context.getAssets(), "GOTH720L.TTF"));
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }*/
}
