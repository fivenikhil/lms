package com.android.lms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HOME on 08-Nov-16.
 */

public class Datasource {

    //Singleton pattern
    private static Datasource datasource = null;

    private ArrayList<String> data = null;

    private static final int SIZE = 74;

    public static Datasource getInstance()
    {
        if (datasource == null)
        {
            datasource = new Datasource();
        }
        return datasource;
    }

    private Datasource()
    {
        data = new ArrayList<String>(SIZE);
        for (int i =1 ; i <= SIZE; i++)
        {
            data.add("row " + i);
        }
    }

    public int getSize()
    {
        return SIZE;
    }

    /**
     * Returns the elements in a <b>NEW</b> list.
     */
    public ArrayList<String> getData(int offset, int limit)
    {
        ArrayList<String> newList = new ArrayList<String>(limit);
        int end = offset + limit;
        if (end > data.size())
        {
            end = data.size();
        }
        newList.addAll(data.subList(offset, end));
        return newList;
    }

}
