package com.example.android.quakereport.loader;


import android.content.Context;
import android.content.AsyncTaskLoader;
import android.util.Log;

import com.example.android.quakereport.QueryUtils;
import com.example.android.quakereport.R;
import com.example.android.quakereport.pojo.Earthquake;

import java.util.List;

/**
 * Loads a list of Earthquake from USGS API
 */
public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    final String API_URL;
    private final static String LOG_TAG = EarthquakeLoader.class.getSimpleName();

    public EarthquakeLoader(Context context) {
        super(context);
        API_URL = context.getString(R.string.api_url);
    }

    @Override
    public List<Earthquake> loadInBackground() {
        return QueryUtils.fetchEarthquakeData(API_URL);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
