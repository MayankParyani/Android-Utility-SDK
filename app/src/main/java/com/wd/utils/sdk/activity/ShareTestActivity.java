package com.wd.utils.sdk.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wd.utils.sdk.R;
import com.wd.utils.sdk.basecontrols.BaseActivity;

import sdk.utils.wd.utils.ShareUtility;

public class ShareTestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_test);
        initViews();
    }

    private void initViews() {
        Button whatsapp = (Button) findViewById(R.id.whatsapp);
        Button facebook = (Button) findViewById(R.id.facebook);
        Button twitter = (Button) findViewById(R.id.twitter);
        Button normal = (Button) findViewById(R.id.other);

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareUtility.getInstance().shareOnWhatsapp(ShareTestActivity.this, "Hey, Whatsapp Sharing Works");
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareUtility.getInstance().shareOnTwitter(ShareTestActivity.this, "Hey, Twitter Sharing Works");
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareUtility.getInstance().shareFacebook(ShareTestActivity.this, "http://www.ibc24.in/newsreader/newsdetail/index/42244/Amit-Shah-Statement--");
            }
        });

        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareUtility.getInstance().shareApp(ShareTestActivity.this, null, null);
            }
        });
    }
}
