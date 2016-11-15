package com.android.lms;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Accounts_Adapter extends BaseAdapter {


    Context context;
    private LayoutInflater inflater;
    private ArrayList<ContactInfo> acc_list = null;
    private ArrayList<ContactInfo> arraylist;


    public Accounts_Adapter(Context context,
                            ArrayList<ContactInfo> acc_list) {
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


    public class ViewHolder {
        TextView lName;
        TextView IPhn;
        TextView ISite;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        final ViewHolder lViewHolder;
        if (v == null )
        {
            v = inflater.inflate(R.layout.list_item, null );
            lViewHolder = new ViewHolder();
            lViewHolder.lName = (TextView) v.findViewById(R.id.id_name);
            lViewHolder.IPhn = (TextView) v.findViewById(R.id.id_phn);
            lViewHolder.ISite = (TextView) v.findViewById(R.id.id_site);

            v.setTag(lViewHolder);
        }
        else
        {
            lViewHolder = (ViewHolder) v.getTag();
        }

        lViewHolder.lName.setText(acc_list.get(position).getName());
        lViewHolder.lName.setTypeface(Typeface.createFromAsset(context.getAssets(), "GOTH720L.TTF"));

        lViewHolder.IPhn.setText(acc_list.get(position).getPhn());
        lViewHolder.IPhn.setTypeface(Typeface.createFromAsset(context.getAssets(), "GOTH720L.TTF"));

        lViewHolder.ISite.setText(acc_list.get(position).getSite());
        lViewHolder.ISite.setTypeface(Typeface.createFromAsset(context.getAssets(), "GOTH720L.TTF"));


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
}
