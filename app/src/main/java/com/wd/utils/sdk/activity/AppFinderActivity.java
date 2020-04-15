package com.wd.utils.sdk.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wd.utils.sdk.R;
import com.wd.utils.sdk.basecontrols.BaseActivity;

import sdk.utils.wd.system.AppUtility;
import sdk.utils.wd.toast.ToastUtility;
import sdk.utils.wd.utils.ValidationUtility;

public class AppFinderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appfinder);
        initViews();
    }

    private void initViews() {
        final EditText editText = (EditText) findViewById(R.id.enterAppname);
        Button search_app = (Button) findViewById(R.id.search_app);

        search_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ValidationUtility.isEmptyOrNull(editText.getText().toString())) {
                    if (AppUtility.isAppInstalled(AppFinderActivity.this, editText.getText().toString())) {
                        ToastUtility.showToast(AppFinderActivity.this, "App Installed");
                    } else {
                        ToastUtility.showToast(AppFinderActivity.this, "App Not Installed");
                    }
                }else {
                    ToastUtility.showToast(AppFinderActivity.this,"Please Enter a Package name to search");
                }
            }
        });
    }
}
