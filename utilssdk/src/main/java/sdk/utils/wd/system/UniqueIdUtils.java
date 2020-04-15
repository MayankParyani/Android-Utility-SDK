/*
 * Copyright (c) WEBDUNIA
 * All rights reserved.
 */

package sdk.utils.wd.system;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.text.TextUtils;

/**
 * Class to Generate Unique Device ID of Mobile
 */

public class UniqueIdUtils {

    private static UniqueIdUtils uniqueIdUtils = null;

    public static UniqueIdUtils getInstance() {
        if (uniqueIdUtils == null) {
            uniqueIdUtils = new UniqueIdUtils();
        }
        return uniqueIdUtils;
    }

    /**
     * returns device id of the device. User should grant "android.permission.READ_PHONE_STATE" permission
     *
     * @return
     */
    public String getDeviceId(Context context) {
        String uniqueId = "";
        try {
            uniqueId = getSecureId(context);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (TextUtils.isEmpty(uniqueId)) {
            uniqueId = "";
        }
        uniqueId = uniqueId + "-" + getMacAddress(context);
        return uniqueId;
    }

    /**
     * returns secure id of the device. User should grant "android.permission.READ_PHONE_STATE" permission
     *
     * @return
     */
    private String getSecureId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    /**
     * returns mac address of the device. User should grant "android.permission.READ_PHONE_STATE" permission
     *
     * @return
     */
    private String getMacAddress(Context context) {
        WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        return wm.getConnectionInfo().getMacAddress();
    }

    /**
     * returns the IMEI Code of the device. proper permission to be granted inorder to make this method work.
     * @return IMEI code of the device.
     */
/*    public String getIMEICode(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            return telephonyManager.getDeviceId();
        }
        return "Phone State Permission not found";

    }*/

}
