/*
 * Copyright (c) WEBDUNIA
 * All rights reserved.
 */

package sdk.utils.wd.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static android.content.Context.WIFI_SERVICE;

public class NetworkInfoUtils {

    /**
     * Checks the type of data connection that is currently available on the device.
     *
     * @param context use application level context to avoid unnecessary leaks.
     * @return String of connected type i.e. Mobile or Wifi
     **/
    public static String getDataConnectionType(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null && connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null) {
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()) {
                return NetworkType.MOBILE.getNetworkType();
            } else if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()) {
                return NetworkType.WIFI.getNetworkType();
            } else
                return NetworkType.NOT_CONNECTED.getNetworkType();
        } else
            return NetworkType.NOT_CONNECTED.getNetworkType();
    }

    /**
     * To get device consuming network type is 2g,3g,4g or wifi
     *
     * @param context must be application level context
     * @return "2g","3g","4g" or Wifi as a String based on the network type
     */
    public static String getNetworkType(Context context) {
        if (!TextUtils.isEmpty(NetworkInfoUtils.getDataConnectionType(context)) &&
                NetworkInfoUtils.getDataConnectionType(context).equalsIgnoreCase(NetworkType.WIFI.getNetworkType())) {
            return NetworkType.WIFI.getNetworkType();
        } else if (!TextUtils.isEmpty(NetworkInfoUtils.getDataConnectionType(context)) &&
                NetworkInfoUtils.getDataConnectionType(context).equalsIgnoreCase(NetworkType.MOBILE.getNetworkType())) {
            TelephonyManager mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            int networkType = mTelephonyManager.getNetworkType();

            switch (networkType) {
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_CDMA:
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_IDEN:
                    return NetworkType.MOBILE_2G.getNetworkType();

                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_HSPA:
                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                case TelephonyManager.NETWORK_TYPE_EHRPD:
                case TelephonyManager.NETWORK_TYPE_HSPAP:
                    return NetworkType.MOBILE_3G.getNetworkType();

                case TelephonyManager.NETWORK_TYPE_LTE:
                    return NetworkType.MOBILE_4G.getNetworkType();

                default:
                    return NetworkType.NOT_CONNECTED.getNetworkType();
            }
        }
        return NetworkType.NOT_CONNECTED.getNetworkType();
    }

    /**
     * Get wifi name of your device is connected to
     *
     * @param context must be application level context
     * @return connected wifi name
     */
    public static String getWifiName(Context context) {
        if (!TextUtils.isEmpty(NetworkInfoUtils.getDataConnectionType(context)) && NetworkInfoUtils.getDataConnectionType(context).equalsIgnoreCase(NetworkType.WIFI.getNetworkType())) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                String ssid = info.getExtraInfo();
                return ssid;
            }
        }
        return "Not connected to any Wifi";
    }

    /**
     * Get IP address from first non-localhost interface
     *
     * @param context must be application level context
     * @return address or empty string
     */
    public static String getIPAddress(Context context) {
        String ip;
        WifiManager wm = (WifiManager) context.getSystemService(WIFI_SERVICE);
        ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        return ip;
    }


    /**
     * Get the network info
     *
     * @param context must be application level context
     * @return Network info object
     */
    public static NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }
}
