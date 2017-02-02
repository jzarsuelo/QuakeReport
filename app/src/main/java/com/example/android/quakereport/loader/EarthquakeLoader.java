package com.example.android.quakereport.loader;


import android.content.Context;
import android.content.AsyncTaskLoader;
import android.util.Log;

import com.example.android.quakereport.EarthquakeActivity;
import com.example.android.quakereport.QueryUtils;
import com.example.android.quakereport.R;
import com.example.android.quakereport.pojo.Earthquake;

import java.util.List;

/**
 * Loads a list of Earthquake from USGS API
 */
public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    private final static String LOG_TAG = EarthquakeLoader.class.getSimpleName();
    private final String mApiUrl;

    public EarthquakeLoader(Context context, String apiUrl) {
        super(context);
        mApiUrl = apiUrl;
    }

    @Override
    public List<Earthquake> loadInBackground() {
        return QueryUtils.fetchEarthquakeData(mApiUrl);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
