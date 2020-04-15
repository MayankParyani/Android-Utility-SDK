package com.wd.utils.sdk.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wd.utils.sdk.R;
import com.wd.utils.sdk.basecontrols.BaseActivity;

import sdk.utils.wd.cryptography.CryptographyUtility;
import sdk.utils.wd.toast.ToastUtility;
import sdk.utils.wd.utils.ValidationUtility;

public class CryptographySampleActivity extends BaseActivity {

    EditText query_encrypt;
    EditText result_encrypt;
    EditText query_decrypt;
    EditText result_decrypt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cryptography_sample);
        initViews();
    }

    private void initViews() {
        query_encrypt = (EditText) findViewById(R.id.query_encrypt);
        query_decrypt = (EditText) findViewById(R.id.query_decrypt);
        result_encrypt = (EditText) findViewById(R.id.result_encrypt);
        result_decrypt = (EditText) findViewById(R.id.result_decrypt);

        Button encrypt_submit = (Button) findViewById(R.id.encrypt_submit);
        Button decrypt_submit = (Button) findViewById(R.id.decrypt_submit);

        encrypt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                encryptValue();
            }
        });

        decrypt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decryptValue();
            }
        });

    }

    private void encryptValue() {
        if (ValidationUtility.isEmptyOrNull(query_encrypt.getText().toString())) {
            String result = CryptographyUtility.Encrypt(query_encrypt.getText().toString(), getString(R.string.sample_secret_key));
            if (ValidationUtility.isEmptyOrNull(result)) {
                result_encrypt.setText(result);
            } else {
                ToastUtility.showToast(CryptographySampleActivity.this, "Some error occured while Encrypting");
            }
        } else {
            ToastUtility.showToast(CryptographySampleActivity.this, "Please enter value to Encrypt");
        }
    }

    private void decryptValue() {
        if (ValidationUtility.isEmptyOrNull(query_decrypt.getText().toString())) {
            String result = CryptographyUtility.Decrypt(query_decrypt.getText().toString(), getString(R.string.sample_secret_key));
            if (ValidationUtility.isEmptyOrNull(result)) {
                result_decrypt.setText(result);
            } else {
                ToastUtility.showToast(CryptographySampleActivity.this, "Some error occured while Decrypting");
            }
        } else {
            ToastUtility.showToast(CryptographySampleActivity.this, "Please enter value to Decrypt");
        }
    }
}
