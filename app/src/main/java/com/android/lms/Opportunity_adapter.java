package com.android.lms;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class Opportunity_adapter extends BaseAdapter
{

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<ContactInfo> acc_list = null;
    private ArrayList<ContactInfo> arraylist;

    public Opportunity_adapter(Context context, ArrayList<ContactInfo> acc_list) {
        this.context = context;
        this.acc_list = acc_list;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<ContactInfo>();
        this.arraylist.addAll(acc_list);
    }

    @Override
    public int getCount() {
        return acc_list.size();
    }

    @Override
    public Object getItem(int position) {
        return acc_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        if (v == null ) {

            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = infalInflater.inflate(R.layout.list_item, null);

            TextView lName = (TextView) v.findViewById(R.id.id_name);
            TextView IPhn = (TextView) v.findViewById(R.id.id_phn);
            TextView ISite = (TextView) v.findViewById(R.id.id_site);

            lName.setText(acc_list.get(position).getName());
            IPhn.setText(acc_list.get(position).getPhn());
            ISite.setText(acc_list.get(position).getSite());

            lName.setTypeface(Typeface.createFromAsset(context.getAssets(), "GOTH720L.TTF"));
            IPhn.setTypeface(Typeface.createFromAsset(context.getAssets(), "GOTH720L.TTF"));
            ISite.setTypeface(Typeface.createFromAsset(context.getAssets(), "GOTH720L.TTF"));
        }

        return v;
    }

    // Filter Class
    public void filter(String charText)
    {
        charText = charText.toLowerCase(Locale.getDefault());
        acc_list.clear();
        if (charText.length() == 0) {
            acc_list.addAll(arraylist);
        } else {
            for (ContactInfo wp : arraylist) {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    acc_list.add(wp);
                }
                else if(charText.length() !=0 && wp.getSite().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    acc_list.add(wp);
                }
                else if(charText.length() !=0 && wp.getPhn().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    acc_list.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }


    /*@Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View v, ViewGroup parent) {

        if (v == null ) {

            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = infalInflater.inflate(R.layout.list_item, null);

            TextView lName = (TextView) v.findViewById(R.id.id_name);
            TextView IPhn = (TextView) v.findViewById(R.id.id_phn);
            TextView ISite = (TextView) v.findViewById(R.id.id_site);

            lName.setText(_listDataHeader.get(groupPosition).getName());
            IPhn.setText(_listDataHeader.get(groupPosition).getPhn());
            ISite.setText(_listDataHeader.get(groupPosition).getSite());
        }

        return v;
    }





    @Override
    public int getChildrenCount(int groupPosition) {
        return _listDataChild.get(_listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return _listDataChild.get(_listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View v, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);
        if (v == null ) {

            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = infalInflater.inflate(R.layout.opportunity_adapter, null);

            TextView model = (TextView) v.findViewById(R.id.model);
            TextView num = (TextView) v.findViewById(R.id.contact);
            TextView stage = (TextView) v.findViewById(R.id.stage);

            num.setText(childText);
          *//*  model.setText(_listDataChild.get(ConstantClass.model).get(groupPosition).toString());
            num.setText(_listDataChild.get(ConstantClass.contactNo).get(groupPosition).toString());
            stage.setText(_listDataChild.get(ConstantClass.salesStage).get(groupPosition).toString());*//*


        }
        return v;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
*/
}
