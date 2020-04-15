package com.wd.utils.sdk.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wd.utils.sdk.R;
import com.wd.utils.sdk.basecontrols.BaseActivity;

import sdk.utils.wd.toast.ToastUtility;

public class ToastTestActivity extends BaseActivity {

    public static final String TAG = ToastTestActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_test);
        initViews();
    }

    private void initViews() {
        Button generateToast = (Button) findViewById(R.id.generateToast);
        generateToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtility.showToast(ToastTestActivity.this, "Hey there, Here is your Toast");
            }
        });

    }
}
