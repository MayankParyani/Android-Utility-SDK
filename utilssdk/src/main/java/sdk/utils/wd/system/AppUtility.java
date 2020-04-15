/*
 * Copyright (c) WEBDUNIA
 * All rights reserved.
 */

package sdk.utils.wd.system;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sdk.utils.wd.network.NetworkUtility;

public class AppUtility {

    public static final String TAG = AppUtility.class.getName();

    private static AppUtility appUtility = null;

    public static AppUtility getInstance() {
        if (appUtility == null) {
            appUtility = new AppUtility();
        }
        return appUtility;
    }

    /**
     * Gets the version name of the application. For e.g. 1.9.3
     **/
    public static String getApplicationVersionNumber(Context context) {

        String versionName = null;
        if (context == null) {
            return versionName;
        }
        try {
            versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * Gets the version code of the application. For e.g. Maverick Meerkat or 2013050301
     **/
    public static int getApplicationVersionCode(Context ctx) {

        int versionCode = 0;
        try {
            versionCode = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * Gets the version number of the Android OS For e.g. 2.3.4 or 4.1.2
     **/
    public static String getOsVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * Gets the name of the application that has been defined in AndroidManifest.xml
     *
     * @throws android.content.pm.PackageManager.NameNotFoundException
     **/
    public static String getApplicationName(Context ctx) throws PackageManager.NameNotFoundException {

        if (ctx == null) {
            throw new NullPointerException("Context cannot be null");
        }

        final PackageManager packageMgr = ctx.getPackageManager();
        ApplicationInfo appInfo = null;

        try {
            appInfo = packageMgr.getApplicationInfo(ctx.getPackageName(), PackageManager.SIGNATURE_MATCH);
        } catch (final PackageManager.NameNotFoundException e) {
            throw new PackageManager.NameNotFoundException(e.getMessage());
        }

        final String applicationName = (String) (appInfo != null ? packageMgr.getApplicationLabel(appInfo) : "UNKNOWN");

        return applicationName;
    }

    /**
     * Checks if the build version passed as the parameter is
     * lower than the current build version.
     *
     * @param buildVersion One of the values from {@link android.os.Build.VERSION_CODES}
     * @return
     */
    public static boolean isBuildBelow(int buildVersion) {
        if (Build.VERSION.SDK_INT < buildVersion) return true;
        else return false;
    }

    /**
     * Checks if the service with the given name is currently running on the device.
     *
     * @param serviceName Fully qualified name of the server. <br/>
     *                    For e.g. nl.changer.myservice.name
     **/
    public static boolean isServiceRunning(Context context, String serviceName) {

        if (serviceName == null) {
            throw new NullPointerException("Service name cannot be null");
        }

        // use application level context to avoid unnecessary leaks.
        ActivityManager manager = (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (service.service.getClassName().equals(serviceName)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Method returns package name of the current application
     *
     * @param context context of calling Activity
     * @return package name of application as string
     */
    public static String appPackageName(Context context) {
        String pkg = context.getPackageName();
        return pkg;
    }

    /**
     * checks if a particular app is installed in device or not
     *
     * @param context context of calling Activity
     * @param uri name of the application package to be searched
     * @return boolean "true" if app found else "false"
     */
    public static boolean isAppInstalled(Context context, String uri) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return false;
    }
    /**
     * This method retrieves all the permissions declared in the application's manifest.
     * It returns a non null array of permissions that can be declared.
     *
     * @param activity the Activity necessary to check what permissions we have.
     * @return a non null array of permissions that are declared in the application manifest.
     */
    public static synchronized String[] getManifestPermissions(final Activity activity) {
        PackageInfo packageInfo = null;
        List<String> list = new ArrayList<String>(1);
        try {
            packageInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(),
                    PackageManager.GET_PERMISSIONS);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo != null) {
            String[] permissions = packageInfo.requestedPermissions;
            if (permissions != null) {
                Collections.addAll(list, permissions);
            }
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * Method to check whether App has permissions, it is executed if android version is 6 & above
     * Need to send array of permissions.
     *
     * @param context     -  context of the activity
     * @param permissions - permissions array
     * @return - Has permissions or not
     */
    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null &&
                permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission)
                        != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void goToAppSettings(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", activity.getPackageName(), null));
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

}
