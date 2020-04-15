package com.wd.utils.sdk.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wd.utils.sdk.R;
import com.wd.utils.sdk.basecontrols.BaseActivity;

import sdk.utils.wd.network.NetworkUtility;

/**
 * This class is used to test network availability using function isNetworkAvailable of NetworkUtility class from WdUtilsSdk
 * before calling any method from NetworkUtility class you must instantiate this class using getInstance method
 * isNetworkAvailable returns "true" if network is available else "false"
 */
public class ConnectivityTestActivity extends BaseActivity {

    private Button checkConnectivity;
    private Button retry;
    private TextView displayConnectivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connectivity_test);
        initViews();
    }

    private void initViews() {
        checkConnectivity = (Button) findViewById(R.id.whatsapp);
        retry = (Button) findViewById(R.id.facebook);
        displayConnectivity = (TextView) findViewById(R.id.displayconnectivity);
        checkConnectivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = NetworkUtility.getInstance().isNetworkAvailable(getApplicationContext());
                if (check) {
                    displayConnectivity.setVisibility(View.VISIBLE);
                    displayConnectivity.setText(R.string.connected);
                    checkConnectivity.setEnabled(false);
                } else {
                    displayConnectivity.setVisibility(View.VISIBLE);
                    displayConnectivity.setText(R.string.disconnected);
                    checkConnectivity.setEnabled(false);
                }
            }
        });
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = NetworkUtility.getInstance().isNetworkAvailable(getApplicationContext());
                if (check) {
                    displayConnectivity.setVisibility(View.VISIBLE);
                    displayConnectivity.setText(R.string.connected);
                } else {
                    displayConnectivity.setVisibility(View.VISIBLE);
                    displayConnectivity.setText(R.string.disconnected);
                }
            }
        });
    }
}
