package com.wd.utils.sdk.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.wd.utils.sdk.R;
import com.wd.utils.sdk.basecontrols.BaseActivity;
import com.wd.utils.sdk.fragment.SampleFragment;

import sdk.utils.wd.network.NetworkListener;
import sdk.utils.wd.network.NetworkUtility;

/**
 * Class to test network receiver functionality of NetworkUtility class from WdUtilsSdk,
 * to receive callbacks of network status a class should implements NetworkListener interface from WdUtilsSdk,
 * & must override onInternetGone and onInternetReceive methods of NetworkListener.
 */
public class NetworkReceiverActivity extends BaseActivity implements NetworkListener {

    private RelativeLayout no_internet_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_receiver);
        //registering a callback to be notified whenever there is a change in network status
        NetworkUtility.getInstance().registerCallback(this);
        initViews();
    }

    private void initViews() {
        no_internet_layout = (RelativeLayout) findViewById(R.id.no_internet_layout);
        if (NetworkUtility.getInstance().isNetworkAvailable(NetworkReceiverActivity.this)) {
            no_internet_layout.setVisibility(View.GONE);
        } else {
            no_internet_layout.setVisibility(View.VISIBLE);
        }
        loadFragment(new SampleFragment());
    }

    //method to load fragment in this activity
    private void loadFragment(android.support.v4.app.Fragment fragment) {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit(); // save the changes
    }

    //Calls when internet is available
    @Override
    public void onInternetReceive() {
        //To DO when network is available
        if (no_internet_layout != null) {
            no_internet_layout.setVisibility(View.GONE);
        }
    }

    //Calls when internet is unavailable
    @Override
    public void onInternetGone() {
        //To DO when network is unavailable
        if (no_internet_layout != null) {
            no_internet_layout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregister a callback to when activity destroys to prevent memory overloading
        NetworkUtility.getInstance().unregisterCallback(this);
    }
}
