package com.example.android.quakereport.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.quakereport.R;
import com.example.android.quakereport.pojo.Earthquake;

import java.util.ArrayList;

/**
 * Created by cloudemployee on 24/01/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item, parent, false);
        }

        Earthquake earthquake = getItem(position);

        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitudeTextView);
        magnitudeTextView.setText( earthquake.getMagnitude().toString() );

        TextView locationTextView = (TextView) listItemView.findViewById(R.id.locationTextView);
        locationTextView.setText( earthquake.getLocation() );

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.dateTextView);
        dateTextView.setText( earthquake.getDate() );

        return listItemView;
    }
}
