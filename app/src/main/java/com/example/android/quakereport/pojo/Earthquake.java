package com.example.android.quakereport.pojo;

/**
 * Created by Pao on 24/01/2017.
 */

public class Earthquake {
    private Double mMagnitude;
    private String mLocation;
    private String mDate;

    public Earthquake() {
    }

    public Earthquake(Double magnitude, String location, String date) {
        mMagnitude = magnitude;
        mLocation = location;
        mDate = date;
    }

    public Double getMagnitude() {
        return mMagnitude;
    }

    public void setMagnitude(Double magnitude) {
        mMagnitude = magnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }
}
