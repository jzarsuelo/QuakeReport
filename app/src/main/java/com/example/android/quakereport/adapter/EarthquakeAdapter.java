package com.example.android.quakereport.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.quakereport.R;
import com.example.android.quakereport.pojo.Earthquake;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

        String locationOffset = getLocationOffset(earthquake.getLocation());
        TextView locationOffsetTextView = (TextView) listItemView.findViewById(R.id.locationOffsetTextView);
        locationOffsetTextView.setText( locationOffset );

        String primaryLocation = getPrimaryLocation(earthquake.getLocation());
        TextView primaryLocationTextView = (TextView) listItemView.findViewById(R.id.primarylocationTextView);
        primaryLocationTextView.setText( primaryLocation );

        Date dateObject = new Date(earthquake.getTimeInMilliseconds());

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.dateTextView);
        dateTextView.setText( formatDate(dateObject) );

        TextView timeTextView = (TextView) listItemView.findViewById(R.id.timeTextView);
        timeTextView.setText( formatTime(dateObject) );

        return listItemView;
    }

    /**
     * Return the formatted date string (eg. Jan 1, 2000)
     * @param dateObject Date object to format
     * @return           Formatted string value
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy");
        return formatter.format(dateObject);
    }

    /**
     * Return the formatted time string (eg. 7:00 AM)
     * @param dateObject Date object to format
     * @return           Formatted string value
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
        return formatter.format(dateObject);
    }

    /**
     * Return the proximity location based on the location string
     */
    private String getLocationOffset(String location) {
        int indexOf = location.indexOf("of");
        if (indexOf > -1) {
            // (+3) to include chars (of )
            return location.substring(0, indexOf + 3);
        }
        return "Near the";
    }

    /**
     * Return the primary location based on the location string
     */
    private String getPrimaryLocation(String location) {
        int indexOf = location.indexOf("of");
        if (indexOf > -1) {
            // (+2) to exlude chars (of )
            return location.substring(indexOf + 2);
        }
        return location;
    }

    private String formatMagnitude(Double magnitude) {
        DecimalFormat formatter = new DecimalFormat("0.0");
        return formatter.format(magnitude);
    }
}
