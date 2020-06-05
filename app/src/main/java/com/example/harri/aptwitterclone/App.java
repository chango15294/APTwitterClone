package com.example.harri.aptwitterclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("hVoxWeIoWeiMMGnL75nTTOIOl5uGWYdphagzMV2s")
                // if defined
                .clientKey("P37dyqPg7skOK7gnHaHzBra4OfM412VDkBGKE1l3")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
