/*
 * Copyright (c) WEBDUNIA
 * All rights reserved.
 */

package sdk.utils.wd.toast;

import android.content.Context;
import android.widget.Toast;

public class ToastUtility {

    private static final String TAG = ToastUtility.class.getSimpleName();

    /**
     * Shows a long time duration toast message.
     *
     * @param context context of Activity from where this method is called
     * @param message Message to be show in the toast.
     * @return Toast
     */
    public static void showToast(Context context, String message) {
        if (message == null) {
            throw new NullPointerException("Toast Message cannot be null");
        } else {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Shows the message passed in the parameter in the Toast.
     *
     * @param msg      Message to be show in the toast.
     * @param duration Duration in milliseconds for which the toast should be shown
     * @return Toast
     **/
    public static void showToastWithCustomDuration(Context ctx, String msg, int duration) {
        if (msg == null) {
            throw new NullPointerException("Toast cannot be null");
        } else {
            Toast toast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);
            toast.setDuration(duration);
            toast.show();
        }
    }

}
