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
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.quakereport.adapter.EarthquakeAdapter;
import com.example.android.quakereport.pojo.Earthquake;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private ListView mEarthquakeListView;
    private EarthquakeAdapter mEarthquakeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        final String apiUrl = getString(R.string.api_url);

        EarthquakeTask earthquakeTask = new EarthquakeTask();
        earthquakeTask.execute(apiUrl);

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

    /**
     * AsyncTask to fetch the data from the USGS API
     */
    private class EarthquakeTask extends AsyncTask<String, Void, List<Earthquake>>{

        private final String TAG = EarthquakeTask.class.getSimpleName();

        @Override
        protected List<Earthquake> doInBackground(String... strings) {
            if ( strings==null || strings[0]==null ) {
                return new ArrayList<>();
            }

            return QueryUtils.fetchEarthquakeData(strings[0]);
        }

        @Override
        protected void onPostExecute(List<Earthquake> earthquakes) {
            mEarthquakeAdapter.clear();
            mEarthquakeAdapter.addAll(earthquakes);
        }



    }
}
