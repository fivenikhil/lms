package com.android.lms;

import android.os.Parcel;
import android.os.Parcelable;

public class Search_Accounts implements Parcelable {

    private String name, site, phnno;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(name);
        dest.writeString(site);
        dest.writeString(phnno);
    }

    public Search_Accounts(Parcel parcel) {
        this.name = parcel.readString();
        this.site = parcel.readString();
        this.phnno = parcel.readString();
    }

    public Search_Accounts (String name, String site, String phnno) {
        this.name = name;
        this.site = site;
        this.phnno = phnno;

    }

    public static Creator<Search_Accounts> CREATOR = new Creator<Search_Accounts>() {

        @Override
        public Search_Accounts createFromParcel(Parcel source) {
            return new Search_Accounts(source);
        }

        @Override
        public Search_Accounts[] newArray(int size) {
            return new Search_Accounts[size];
        }

    };
}
