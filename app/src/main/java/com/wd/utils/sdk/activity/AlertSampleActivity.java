package com.wd.utils.sdk.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.wd.utils.sdk.R;
import com.wd.utils.sdk.basecontrols.BaseActivity;

import sdk.utils.wd.dialog.AlertDialogUtility;
import sdk.utils.wd.dialog.DialogButtonListener;
import sdk.utils.wd.toast.ToastUtility;

public class AlertSampleActivity extends BaseActivity {

    private AlertDialogUtility alertDialogUtility;
    private ProgressBar simpleProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_sample);
        initViews();
    }


    private void initViews() {
        alertDialogUtility = AlertDialogUtility.getInstance();
        Button single_dialog = (Button) findViewById(R.id.single_dialog);
        Button double_dialog = (Button) findViewById(R.id.double_dialog);
        Button triple_dialog = (Button) findViewById(R.id.triple_dialog);

        single_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialogUtility.showAlertDialog(AlertSampleActivity.this, "Default Alert", "Hey,Here is your default alert dialog.Press OK to cancel");
            }
        });

        double_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogButtonListener doubleDialogListener = new DialogButtonListener() {
                    @Override
                    public void onPositiveButtonClick() {
                        ToastUtility.showToast(AlertSampleActivity.this, "Exit Pressed");

                    }

                    @Override
                    public void onNegativeButtonClick() {
                        ToastUtility.showToast(AlertSampleActivity.this, "Cancel Pressed");
                    }

                    @Override
                    public void onNeutralButtonClick() {

                    }
                };
                alertDialogUtility.showAlertDialog(AlertSampleActivity.this, "Double Choice Alert", "Hey,Here is your dialog , Please select a option", "Exit", "cancel", doubleDialogListener, false);
            }
        });

        triple_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogButtonListener tripleDialogListener = new DialogButtonListener() {
                    @Override
                    public void onPositiveButtonClick() {
                        ToastUtility.showToast(AlertSampleActivity.this, "Exit Pressed");
                    }

                    @Override
                    public void onNegativeButtonClick() {
                        ToastUtility.showToast(AlertSampleActivity.this, "Cancel Pressed");
                    }

                    @Override
                    public void onNeutralButtonClick() {
                        ToastUtility.showToast(AlertSampleActivity.this, "Later Pressed");
                    }
                };

                alertDialogUtility.showAlertDialog(AlertSampleActivity.this, "Triple Choice Alert", "Hey,Here is your dialog, Please select a option", "Exit", "cancel", "Later", tripleDialogListener, false);
            }
        });

    }
}
