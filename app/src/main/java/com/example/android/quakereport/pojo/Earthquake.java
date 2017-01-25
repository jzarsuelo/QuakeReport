package com.example.android.quakereport.pojo;

/**
 * Created by Pao on 24/01/2017.
 */

public class Earthquake {
    private Double mMagnitude;
    private String mLocation;
    private Long mTimeInMilliseconds;
    private String mUrl;

    public Earthquake() {
    }

    public Earthquake(Double magnitude, String location, Long timeInMilliseconds, String url) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl = url;
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

    public Long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public void setTimeInMilliseconds(Long timeInMilliseconds) {
        mTimeInMilliseconds = timeInMilliseconds;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}
