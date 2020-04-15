package com.wd.utils.sdk.basecontrols;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import sdk.utils.wd.network.NetworkUtility;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
