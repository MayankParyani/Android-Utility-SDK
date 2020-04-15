package com.wd.utils.sdk.application;

import android.app.Application;

import sdk.utils.wd.network.NetworkUtility;

public class MyApplication extends Application {

    private static MyApplication mInstance;

    public static synchronized MyApplication getInstance() {
        if (mInstance == null) {
            mInstance = new MyApplication();
        }
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //registering network receiver for this application whenever app runs
        NetworkUtility.getInstance().registerNetworkReceiver(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        //unregister network receiver for this application when app destroy
        NetworkUtility.getInstance().unregisterReceiver(this);

    }
}
