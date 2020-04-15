/*
 * Copyright (c) WEBDUNIA
 * All rights reserved.
 */

package sdk.utils.wd.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;

import sdk.utils.wd.network.NetworkInfoUtils;
import sdk.utils.wd.network.NetworkListener;

/**
 * Class to check Network related information
 **/
public class NetworkUtility {

    public static final String TAG = NetworkUtility.class.getName();
    private final static String CONNECTIVITY_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    private ArrayList<NetworkListener> callbackList;
    private static NetworkUtility networkUtility = null;

    /***
     * this methods return's object of NetworkUtility class
     ****/
    public static NetworkUtility getInstance() {
        if (networkUtility == null) {
            networkUtility = new NetworkUtility();
        }
        return networkUtility;
    }

    /***
     * This method is used to register network receiver, this method should be called in onCreate() of Application class only once in a project.
     * @param context context should be of application level
     ****/
    public void registerNetworkReceiver(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CONNECTIVITY_ACTION);
        context.registerReceiver(mNetworkReceiver, intentFilter);
    }

    private final BroadcastReceiver mNetworkReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null && intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                if (callbackList != null) {
                    if (isNetworkAvailable(context)) {
                        for (NetworkListener networkListener : callbackList) {
                            networkListener.onInternetReceive();
                        }
                    } else {
                        for (NetworkListener networkListener : callbackList) {
                            networkListener.onInternetGone();
                        }
                    }
                }
            }
        }
    };

    /***
     * This method is used to unregister network receiver, should be called in onDestroy() or onPause() Methods to unregister receivers before application destroyed.
     * @param context context should be of application level
     ****/
    public void unregisterReceiver(Context context) {
        try {
            context.unregisterReceiver(mNetworkReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /***
     * This method is used to check network connectivity returns "true" if network is available else "false"
     * @param context context of the activity from call is being made
     ****/
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        //should check null because in airplane mode it will be null
        return (netInfo != null && netInfo.isConnected());
    }

    /***
     * This method is used to register callbacks for networkReceiver broadcast, you can call this method
     * to receive callbacks anywhere in desired application, should be called in onCreate() method
     * @param networkListener instance of NetworkListener to receiver callback in activity of fragment
     ****/
    public void registerCallback(NetworkListener networkListener) {
        if (callbackList == null) {
            callbackList = new ArrayList<>();
        }
        callbackList.add(networkListener);
    }

    /***
     * This method is used to unregister callbacks for networkReceiver broadcast, you can call this method
     * to stop receiving callbacks from any view, should be called everywhere is onDestroy() method of activities/fragments
     * @param networkListener instance of NetworkListener to receiver callback in activity of fragment
     ****/
    public void unregisterCallback(NetworkListener networkListener) {
        callbackList.remove(networkListener);
    }

    /***
     * This method is used to clear all call backs of networkReceiver in a application
     ****/
    public void clearCallBacks() {
        callbackList.clear();
    }

    public String getNetworkType(Context context) {
        return NetworkInfoUtils.getNetworkType(context);
    }

    public String getWifiName(Context context) {
        return NetworkInfoUtils.getWifiName(context);
    }

    public String getIPAddress(Context context) {
        return NetworkInfoUtils.getIPAddress(context);
    }

}
