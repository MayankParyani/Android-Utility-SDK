/*
 * Copyright (c) WEBDUNIA
 * All rights reserved.
 */

package sdk.utils.wd.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;

import java.util.List;

import sdk.utils.wd.com.utilssdk.R;
import sdk.utils.wd.toast.ToastUtility;

public class ShareUtility {

    private static ShareUtility instance = null;

    //Lazy Initialization, here we are going to initialize new instance of the class in getInstance() method itself.
    public static ShareUtility getInstance() {
        if (instance == null) {
            instance = new ShareUtility(); //it creates a new instance of the singleton class in JVM and returns that instance.
        }
        return instance;
    }

    /**
     * Share on Facebook. Using Facebook app if installed or web link otherwise, using intent without fb SDK
     *
     * @param activity activity which launches the intent
     * @param url      url to share
     */
    public void shareFacebook(Activity activity, String url) {
        boolean facebookAppFound = false;
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, url);
        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(url));

        PackageManager pm = activity.getPackageManager();
        List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
        for (final ResolveInfo app : activityList) {
            if ((app.activityInfo.packageName).contains(activity.getString(R.string.wdsdk_facebook_package_name))) {
                final ActivityInfo activityInfo = app.activityInfo;
                final ComponentName name = new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name);
                shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                shareIntent.setComponent(name);
                facebookAppFound = true;
                break;
            }
        }
        if (!facebookAppFound) {
            String sharerUrl = activity.getString(R.string.wdsdk_facebook_share_url) + url;
            shareIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
        }
        activity.startActivity(shareIntent);
    }

    /**
     * Share on Whatsapp (if installed) else redirect user to play store
     *
     * @param context activity which launches the intent
     * @param data    text to share
     */
    public void shareOnWhatsapp(Context context, String data) {
        if (!TextUtils.isEmpty(data)) {
            Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
            whatsappIntent.setType("text/plain");
            whatsappIntent.setPackage(context.getString(R.string.wdsdk_whatsapp_package_name));
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, data);
            try {
                context.startActivity(whatsappIntent);
            } catch (android.content.ActivityNotFoundException ex) {
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(context.getString(R.string.wdsdk_whatsapp_playstore_url)));
                context.startActivity(i);
            }
        } else {
            ToastUtility.showToast(context, context.getString(R.string.wdsdk_please_try_again_later));
        }
    }

    /**
     * Share on Twitter. Using Twitter app if installed or web link otherwise.
     *
     * @param context activity which launches the intent
     * @param data    url to share
     */
    public void shareOnTwitter(Context context, String data) {
        if (!TextUtils.isEmpty(data)) {
            Intent i = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(context.getString(R.string.wdsdk_twitter_sharing_url) + data));
            try {
                context.startActivity(i);
            } catch (android.content.ActivityNotFoundException ex) {
                ToastUtility.showToast(context, "There are no application to handle this");
            }
        } else {
            ToastUtility.showToast(context, context.getString(R.string.wdsdk_please_try_again_later));
        }
    }

    /**
     * Share app using default share intent, by sharing play store url of the app
     *
     * @param app_share_message Message to be pre-populated when the 3rd party app dialog opens up.
     * @param app_share_subject Message that shows up as a subject while sharing through email.
     * @param activity          activity which launches the intent
     */
    public void shareApp(Activity activity, String app_share_message, String app_share_subject) {
        try {
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            if (!TextUtils.isEmpty(app_share_subject)) {
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, app_share_subject);
            }
            String message;
            if (!TextUtils.isEmpty(app_share_message)) {
                message = app_share_message + "http://play.google.com/store/apps/details?id=" + activity.getPackageName();
            } else {
                message = "http://play.google.com/store/apps/details?id=" + activity.getPackageName();
            }
            sendIntent.putExtra(Intent.EXTRA_TEXT, message);
            activity.startActivity(sendIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Share a data using default share intent.
     *
     * @param context activity which launches the intent
     * @param data    url to share
     */
    public void shareData(Context context, String data) {
        try {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, data);
            sendIntent.setType("text/plain");
            context.startActivity(sendIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Copies specified text to the clipboard
     *
     * @param text text to be copied to the clipboard.
     */
    public String copyToClipboard(Context context, String text) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
            ToastUtility.showToast(context, "Copied to Clipboard");
            return text;
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
            clipboard.setPrimaryClip(clip);
            ToastUtility.showToast(context, "Copied to Clipboard");
            return text;
        }
    }


}
