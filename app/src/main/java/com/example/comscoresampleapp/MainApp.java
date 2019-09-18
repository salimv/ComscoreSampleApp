package com.example.comscoresampleapp;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import com.comscore.Analytics;
import com.comscore.PublisherConfiguration;

public class MainApp extends Application {

    private static final String TAG = MainApp.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        //Adding ComScore App Usage. Initialization is done if the Publisher ID and Secret is found
        String comScorePublisherID = getResources().getString(R.string.comscore_pub_id);
        String comScorePublisherSecret = getResources().getString(R.string.comscore_pub_secret);
        if (!TextUtils.isEmpty(comScorePublisherID) && !TextUtils.isEmpty(comScorePublisherSecret)) {
            PublisherConfiguration publisherConfiguration = new PublisherConfiguration.Builder()
                    .publisherSecret(comScorePublisherSecret) // ComScore Publisher Secret.
                    .publisherId(comScorePublisherID) // ComScore Publisher ID here.
                    .build();
            Analytics.getConfiguration().addClient(publisherConfiguration);
            Analytics.start(getApplicationContext());
            Log.d(TAG, String.format("Starting Comscore with AppName %1$s, Pub ID: %2$s, Secret: %3$s", getPackageManager().getApplicationLabel(getApplicationInfo()), comScorePublisherID, comScorePublisherSecret));
        }


    }
}
