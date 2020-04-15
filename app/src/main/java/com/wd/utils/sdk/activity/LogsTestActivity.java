package com.wd.utils.sdk.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wd.utils.sdk.R;
import com.wd.utils.sdk.basecontrols.BaseActivity;

import sdk.utils.wd.log.CustomLogUtility;
import sdk.utils.wd.toast.ToastUtility;

public class LogsTestActivity extends BaseActivity {

    public static final String TAG = LogsTestActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_test);
        initViews();
    }

    private void initViews() {
        Button enableLog = (Button) findViewById(R.id.enableLog);
        Button disableLog = (Button) findViewById(R.id.disableLog);
        Button logD = (Button) findViewById(R.id.whatsapp);
        Button logE = (Button) findViewById(R.id.facebook);
        Button logV = (Button) findViewById(R.id.twitter);

        enableLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomLogUtility.enableLogs(true);
                ToastUtility.showToast(LogsTestActivity.this, "Log's Enabled");
            }
        });

        disableLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomLogUtility.enableLogs(false);
                ToastUtility.showToast(LogsTestActivity.this, "Log's Disabled");
            }
        });

        logD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CustomLogUtility.getLogStatus()) {
                    CustomLogUtility.logD(TAG, "SampleLogD");
                    ToastUtility.showToast(LogsTestActivity.this, "LogD Generated in Logcat");
                } else {
                    ToastUtility.showToast(LogsTestActivity.this, "Log's Disabled, please enable them");
                }
            }
        });

        logE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CustomLogUtility.getLogStatus()) {
                    CustomLogUtility.logE(TAG, "SampleLogE");
                    ToastUtility.showToast(LogsTestActivity.this, "LogE Generated in Logcat");
                } else {
                    ToastUtility.showToast(LogsTestActivity.this, "Log's Disabled, please enable them");
                }
            }
        });

        logV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CustomLogUtility.getLogStatus()) {
                    CustomLogUtility.logV(TAG, "SampleLogV");
                    ToastUtility.showToast(LogsTestActivity.this, "LogV Generated in Logcat");
                } else {
                    ToastUtility.showToast(LogsTestActivity.this, "Log's Disabled, please enable them");
                }
            }
        });
    }
}
