package com.example.android.sunshine;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

// DONE (22) Extend RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder>
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder> {

    // DONE (23) Create a private string array called mWeatherData
    private String[] mWeatherData;

    // DONE (47) Create the default constructor (we will pass in parameters in a later lesson)
    public ForecastAdapter() {

    }

    // DONE (24) Override onCreateViewHolder
    // DONE (25) Within onCreateViewHolder, inflate the list item xml into a view
    // DONE (26) Within onCreateViewHolder, return a new ForecastAdapterViewHolder with the above view passed in as a parameter
    @Override
    public ForecastAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.forecast_list_item, viewGroup, /* attachToRoot */false);
        return new ForecastAdapterViewHolder(view);
    }

    // DONE (27) Override onBindViewHolder
    // DONE (28) Set the text of the TextView to the weather for this list item's position
    @Override
    public void onBindViewHolder(@NonNull ForecastAdapterViewHolder viewHolder, int position) {
        String weatherInfo = mWeatherData[position];
        viewHolder.mWeatherTextView.setText(weatherInfo);
    }

    // DONE (29) Override getItemCount
    // DONE (30) Return 0 if mWeatherData is null, or the size of mWeatherData if it is not null
    @Override
    public int getItemCount() {
        if (mWeatherData == null) {
            return 0;
        }
        return mWeatherData.length;
    }

    // DONE (31) Create a setWeatherData method that saves the weatherData to mWeatherData
    // DONE (32) After you save mWeatherData, call notifyDataSetChanged
    public void setWeatherData(String[] weatherData) {
        mWeatherData = weatherData;
        notifyDataSetChanged();
    }


    // DONE (16) Create a class within ForecastAdapter called ForecastAdapterViewHolder
    // DONE (17) Extend RecyclerView.ViewHolder
    public class ForecastAdapterViewHolder extends RecyclerView.ViewHolder {
        // DONE (18) Create a public final TextView variable called mWeatherTextView
        public final TextView mWeatherTextView;

        // DONE (19) Create a constructor for this class that accepts a View as a parameter
        // DONE (20) Call super(view) within the constructor for ForecastAdapterViewHolder
        // DONE (21) Using view.findViewById, get a reference to this layout's TextView and save it to mWeatherTextView
        public ForecastAdapterViewHolder(View view) {
            super(view);
            mWeatherTextView = view.findViewById(R.id.tv_weather_data);
        }
    }
}
