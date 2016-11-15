package com.android.lms;


import android.app.Application;

import java.util.ArrayList;
import java.util.HashMap;

public class DataHolderClass {

    private String role, bank, showroom, userid, acc_id ,name, site, phnno;
   // private ArrayList<ContactInfo> list;
    public  ArrayList<HashMap<String, String>> list;
    public   ArrayList<HashMap<String, String>> list_segmentid;

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBank() {
        return bank;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setShowroom(String showroom) {
        this.showroom = showroom;
    }

    public String getShowroom() {
        return showroom;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public void setAcc_id(String acc_id) {
        this.acc_id = acc_id;
    }

    public String getAcc_id() {
        return acc_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSite() {
        return site;
    }

    public void setPhnno(String phnno) {
        this.phnno = phnno;
    }

    public String getPhnno() {
        return phnno;
    }


    /* public void setList(ArrayList<ContactInfo> list) {
        this.list = list;
    }

    public ArrayList<ContactInfo> getList() {
        return list;
    }*/

    public void setList_segmentid(ArrayList<HashMap<String, String>> list_segmentid) {
        this.list_segmentid = list_segmentid;
    }

    public ArrayList<HashMap<String, String>> getList_segmentid() {
        return list_segmentid;
    }

    public void setList(ArrayList<HashMap<String, String>> list) {
        this.list = list;
    }

    public ArrayList<HashMap<String, String>> getList() {
        return list;
    }

    private static final DataHolderClass holder = new DataHolderClass();

    public static DataHolderClass getInstance() {
        return holder;
    }


}
