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
 * {@link EarthquakeAdapter} is an {@link ArrayAdapter} that provides the layout for each item
 * on the data source, which is a list of {@link Earthquake} objects.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    /**
     * Custom constructor
     *
     * @param context     Used to inflate the layout
     * @param earthquakes List of Earthquake object to display in a list
     */
    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        // Initialize the parent class by passing the context and earthquakes (data)
        // The second argument is layout to be used by the ArrayAdapter, since
        // EarthquakeAdapter is a custom adapter and it will not be used by the
        // adapter, we can pass any value on it.
        super(context, 0, earthquakes);
    }

    /**
     * Provides a view for a certain data
     *
     * @param position      The position of the data in the list of data that we want to display
     * @param convertView   A Recycled view. Purpose is to reuse the view if possible.
     * @param parent        Used for inflation
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            // Inflate the view
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item, parent, false);
        }

        Earthquake earthquake = getItem(position);

        // Initialize the View
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitudeTextView);
        magnitudeTextView.setText( earthquake.getMagnitude().toString() );

        TextView locationTextView = (TextView) listItemView.findViewById(R.id.locationTextView);
        locationTextView.setText( earthquake.getLocation() );

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.dateTextView);
        dateTextView.setText( earthquake.getDate() );

        return listItemView;
    }
}
