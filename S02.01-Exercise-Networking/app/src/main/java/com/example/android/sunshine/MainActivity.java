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
package com.example.android.sunshine;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.example.android.sunshine.data.SunshinePreferences;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView mWeatherTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        /*
         * Using findViewById, we get a reference to our TextView from xml. This allows us to
         * do things like set the text of the TextView.
         */
        mWeatherTextView = (TextView) findViewById(R.id.tv_weather_data);

        // DONE (4) Delete the dummy weather data. You will be getting REAL data from the Internet in this lesson.

        // DONE (3) Delete the for loop that populates the TextView with dummy data

        // DONE (9) Call loadWeatherData to perform the network request to get the weather
        loadWeatherData();
    }

    // DONE (8) Create a method that will get the user's preferred location and execute your new AsyncTask and call it loadWeatherData
    private void loadWeatherData() {
        String userPreferedLocation = SunshinePreferences.getPreferredWeatherLocation(this);
        new GetWeatherTask().execute(userPreferedLocation);
    }

    // DONE (5) Create a class that extends AsyncTask to perform network requests
    // DONE (6) Override the doInBackground method to perform your network requests
    // DONE (7) Override the onPostExecute method to display the results of the network request
    public class GetWeatherTask extends AsyncTask<String, Void, String[]> {
        @Override
        protected String[] doInBackground(String... locations) {
            String location = locations[0];
            URL weatherSearchURL = NetworkUtils.buildUrl(location);
            String[] weatherResults = null;
            try {
                String rawWeatherResults = NetworkUtils.getResponseFromHttpUrl(weatherSearchURL);
                weatherResults = OpenWeatherJsonUtils
                    .getSimpleWeatherStringsFromJson(MainActivity.this, rawWeatherResults);
            } catch(Exception e) {
                e.printStackTrace();
            }
            return weatherResults;
        }

        @Override
        protected void onPostExecute(String[] weatherResults) {
            for (String weatherResult : weatherResults) {
                mWeatherTextView.append(weatherResult + "\n\n\n");
            }
        }
    }
}