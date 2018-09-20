package com.nicoletanetedu.demoapp;

import android.app.Application;
import android.content.Context;

public class MainApplication extends Application {
    private static MainApplication mInstance;
    private static APIInterface apiInterface;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        apiInterface = APIClient.getClient().create(APIInterface.class);


    }

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

    public static synchronized MainApplication getInstance() {
        return mInstance;
    }

    public static synchronized APIInterface getApiInterface() {
        return apiInterface;
    }

}
