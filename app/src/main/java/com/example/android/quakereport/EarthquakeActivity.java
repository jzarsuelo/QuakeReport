/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.quakereport.adapter.EarthquakeAdapter;
import com.example.android.quakereport.loader.EarthquakeLoader;
import com.example.android.quakereport.pojo.Earthquake;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Earthquake>>{

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private ListView mEarthquakeListView;
    private EarthquakeAdapter mEarthquakeAdapter;

    private static final int EARTHQUAKE_LOADER_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Execute loader
        getLoaderManager().initLoader(EARTHQUAKE_LOADER_ID, null, this);

        // Find a reference to the {@link ListView} in the layout
        mEarthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        mEarthquakeAdapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());

        // Set the mEarthquakeAdapter on the {@link ListView}
        // so the list can be populated in the user interface
        mEarthquakeListView.setAdapter(mEarthquakeAdapter);
        mEarthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Earthquake earthquake = mEarthquakeAdapter.getItem(position);
                String url = earthquake.getUrl();

                // Create an implicit Intent to view the URL about the details of the earthquake
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                // Check if any app can handle the implicit intent
                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivity(i);
                }

            }
        });
    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int id, Bundle args) {
        return new EarthquakeLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> data) {
        mEarthquakeAdapter.clear();
        mEarthquakeAdapter.addAll(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        mEarthquakeAdapter.clear();
    }

}
