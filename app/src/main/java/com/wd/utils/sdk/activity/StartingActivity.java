package com.wd.utils.sdk.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;

import com.wd.utils.sdk.R;
import com.wd.utils.sdk.basecontrols.BaseActivity;

import sdk.utils.wd.image.ImageUtility;
import sdk.utils.wd.image.BitmapDownloadedListener;
import sdk.utils.wd.network.NetworkUtility;
import sdk.utils.wd.system.AppUtility;
import sdk.utils.wd.system.RootCheckUtils;
import sdk.utils.wd.system.UniqueIdUtils;
import sdk.utils.wd.toast.ToastUtility;
import sdk.utils.wd.utils.ValidationUtility;

/**
 * Activity to test different methods of WdUtilsSdk (sdk.utils.wd), select any button to proceed ahead
 */
public class StartingActivity extends BaseActivity implements View.OnClickListener, BitmapDownloadedListener {

    private int INITIAL_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
        initViews();
        askForInitialPermissions();
    }

    private void askForInitialPermissions() {
        if (Build.VERSION.SDK_INT >= 23 && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(StartingActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, INITIAL_PERMISSION);
    }


    private void initViews() {

        Button connectivityTest = (Button) findViewById(R.id.connectivityTest);
        Button receiverTest = (Button) findViewById(R.id.receiverTest);
        Button toastTest = (Button) findViewById(R.id.toastTest);
        Button logsTest = (Button) findViewById(R.id.logTest);
        Button deviceID = (Button) findViewById(R.id.deviceID);
        Button share = (Button) findViewById(R.id.shareApp);
        Button rootCheck = (Button) findViewById(R.id.rootCheck);
        Button packageName = (Button) findViewById(R.id.packageName);
        Button networkType = (Button) findViewById(R.id.networkType);
        Button wifiName = (Button) findViewById(R.id.wifiName);
        Button ipAddress = (Button) findViewById(R.id.ipAddress);
        Button emailCheck = (Button) findViewById(R.id.emailCheck);
        Button cryptographyDemo = (Button) findViewById(R.id.cryptographyDemo);
        Button dialogSample = (Button) findViewById(R.id.dialogSample);
        Button appFinder = (Button) findViewById(R.id.appFinder);

        connectivityTest.setOnClickListener(this);
        receiverTest.setOnClickListener(this);
        logsTest.setOnClickListener(this);
        deviceID.setOnClickListener(this);
        toastTest.setOnClickListener(this);
        share.setOnClickListener(this);
        rootCheck.setOnClickListener(this);
        packageName.setOnClickListener(this);
        networkType.setOnClickListener(this);
        wifiName.setOnClickListener(this);
        ipAddress.setOnClickListener(this);
        cryptographyDemo.setOnClickListener(this);
        emailCheck.setOnClickListener(this);
        dialogSample.setOnClickListener(this);
        appFinder.setOnClickListener(this);
    }

    private void launchActivity(Class activity) {
        Intent intent = new Intent(StartingActivity.this, activity);
        startActivity(intent);
    }

    private void checkRootStatus() {
        if (RootCheckUtils.isDeviceRooted()) {
            ToastUtility.showToast(StartingActivity.this, "Device is Rooted");
        } else {
            ToastUtility.showToast(StartingActivity.this, "Device is not Rooted, You are safe");
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.connectivityTest:
                launchActivity(ConnectivityTestActivity.class);
                break;
            case R.id.receiverTest:
                launchActivity(NetworkReceiverActivity.class);
                break;
            case R.id.toastTest:
                launchActivity(ToastTestActivity.class);
                break;
            case R.id.logTest:
                launchActivity(LogsTestActivity.class);
                break;
            case R.id.deviceID:
                if (ValidationUtility.isEmptyOrNull(UniqueIdUtils.getInstance().getDeviceId(StartingActivity.this)))
                    ToastUtility.showToast(StartingActivity.this, UniqueIdUtils.getInstance().getDeviceId(StartingActivity.this));
                break;
            case R.id.shareApp:
                launchActivity(ShareTestActivity.class);
                break;
            case R.id.rootCheck:
                //ImageUtility.DownloadImageTask  image = new ImageUtility.DownloadImageTask (StartingActivity.this, this);
                //image.execute("https://images.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
/*                CustomLogUtility.logD("Device Brand", new DeviceUtility.deviceBuilder().BRAND());
                CustomLogUtility.logD("Device Model", new DeviceUtility.deviceBuilder().MODEL());
                CustomLogUtility.logD("Device Manufacturer", new DeviceUtility.deviceBuilder().MANUFACTURER());
                CustomLogUtility.logD("Device Board", new DeviceUtility.deviceBuilder().BOARD());
                CustomLogUtility.logD("Device Bootloader", new DeviceUtility.deviceBuilder().BOOTLOADER());
                CustomLogUtility.logD("Device device", new DeviceUtility.deviceBuilder().DEVICE());
                CustomLogUtility.logD("Device Display", new DeviceUtility.deviceBuilder().DISPLAY());
                CustomLogUtility.logD("Device Fingerprint", new DeviceUtility.deviceBuilder().FINGERPRINT());
                CustomLogUtility.logD("Device Host", new DeviceUtility.deviceBuilder().HOST());
                CustomLogUtility.logD("Device Product", new DeviceUtility.deviceBuilder().PRODUCT());
                CustomLogUtility.logD("Device Type", new DeviceUtility.deviceBuilder().TYPE());
                CustomLogUtility.logD("Device User", new DeviceUtility.deviceBuilder().USER());*/
              /*  Bitmap bitmap = ImageUtility.drawableToBitmap(getDrawable(R.drawable.edu));
                if (bitmap != null) {
                 *//*   ToastUtility.showToast(StartingActivity.this, "Bitmap Converted");
                    try {
                        ImageUtility.SaveImage(StartingActivity.this, bitmap, "/testingSDK", "mayank-", 90, "png");
                    } catch (StoragePermissionMissing storagePermissionMissing) {
                        storagePermissionMissing.printStackTrace();
                    }*//*
                    //ToastUtility.showToast(StartingActivity.this, ImageUtility.sizeOf(bitmap));
                } else {
                    ToastUtility.showToast(StartingActivity.this, "Bitmap Failed");
                }*/
                /*String root = Environment.getExternalStorageDirectory().toString();
                try {
                    startActivity(MediaUtility.createTakePictureIntent(StartingActivity.this, ImageUtility.createImageUri(StartingActivity.this)));
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                checkRootStatus();
                break;
            case R.id.packageName:
                ToastUtility.showToast(StartingActivity.this, AppUtility.appPackageName(StartingActivity.this));
                break;
            case R.id.networkType:
                ToastUtility.showToast(StartingActivity.this, NetworkUtility.getInstance().getNetworkType(StartingActivity.this));
                break;
            case R.id.wifiName:
                ToastUtility.showToast(StartingActivity.this, NetworkUtility.getInstance().getWifiName(StartingActivity.this));
                break;
            case R.id.ipAddress:
                ToastUtility.showToast(StartingActivity.this, NetworkUtility.getInstance().getIPAddress(StartingActivity.this));
                break;
            case R.id.emailCheck:
                launchActivity(EmailValidationActivity.class);
                break;
            case R.id.cryptographyDemo:
                launchActivity(CryptographySampleActivity.class);
                break;
            case R.id.dialogSample:
                launchActivity(AlertSampleActivity.class);
                break;
            case R.id.appFinder:
                launchActivity(AppFinderActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public Bitmap OnBitmapDownloaded(Bitmap bitmap) {
        if (bitmap != null) {
            ToastUtility.showToast(StartingActivity.this, "Bitmap downloaded");
        } else {
            ToastUtility.showToast(StartingActivity.this, "Bitmap failed");
        }
        return bitmap;
    }
}
