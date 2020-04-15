package com.wd.utils.sdk.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wd.utils.sdk.R;
import com.wd.utils.sdk.basecontrols.BaseActivity;

import sdk.utils.wd.toast.ToastUtility;
import sdk.utils.wd.utils.ValidationUtility;
import sdk.utils.wd.utils.ViewUtility;

public class EmailValidationActivity extends BaseActivity {

    EditText editText;
    EditText PhoneEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_validation);
        initViews();
    }

    private void initViews() {
        editText = (EditText) findViewById(R.id.editText);
        PhoneEditText = (EditText) findViewById(R.id.PhoneEditText);
        ViewUtility.setMaxLength(PhoneEditText, 10);
        Button validate = (Button) findViewById(R.id.submit_email);
        Button submit_phone = (Button) findViewById(R.id.submit_phone);

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateEmail();
            }
        });

        submit_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validatePhone();
            }
        });
    }

    private void validateEmail() {
        if (ValidationUtility.isEmptyOrNull(editText.getText().toString())) {
            if (ValidationUtility.isValidMail(editText.getText().toString())) {
                ToastUtility.showToast(EmailValidationActivity.this, "Congratulations, Email is Valid");
            } else {
                ToastUtility.showToast(EmailValidationActivity.this, "Hey,Entered Email is Incorrect");
            }
        } else {
            ToastUtility.showToast(EmailValidationActivity.this, "Please enter a email to validate");
        }
    }

    private void validatePhone() {
        if (ValidationUtility.isEmptyOrNull(PhoneEditText.getText().toString())) {
            if (ValidationUtility.isValidMobile(PhoneEditText.getText().toString())) {
                ToastUtility.showToast(EmailValidationActivity.this, "Congratulations, Phone Number is Valid");
            } else {
                ToastUtility.showToast(EmailValidationActivity.this, "Hey,Entered Phone Number is Incorrect");
            }
        } else {
            ToastUtility.showToast(EmailValidationActivity.this, "Please enter a phone number to validate");
        }
    }
}
