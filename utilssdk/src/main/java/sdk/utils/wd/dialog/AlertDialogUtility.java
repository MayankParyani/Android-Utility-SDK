/*
 * Copyright (c) WEBDUNIA
 * All rights reserved.
 */

package sdk.utils.wd.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

/**
 * Utility class to show Alert Dialogs whenever needed
 */
public class AlertDialogUtility {

    private static AlertDialogUtility utility = null;

    public static AlertDialogUtility getInstance() {
        if (utility == null) {
            utility = new AlertDialogUtility();
        }
        return utility;
    }

    /**
     * Shows an alert dialog with the Default OK button. When the user presses OK button, the dialog
     * dismisses.
     *
     * @param context context of calling Activity
     * @param title   Title of Alert dialog
     * @param message Message to be displayed in alert dialog
     */
    public void showAlertDialog(Context context, String title, String message) {
        showAlertDialog(context, title, message, "OK", null, null, null, true);
    }

    /**
     * Shows an alert dialog with the Default OK button. When the user presses OK button, the dialog
     * dismisses.
     *
     * @param context              context of calling Activity
     * @param title                Title of Alert dialog
     * @param message              Message to be displayed in alert dialog
     * @param dialogButtonListener Override required methods of this Listener for custom functionality of Button
     */
    public void showAlertDialog(Context context, String title, String message, DialogButtonListener dialogButtonListener) {
        showAlertDialog(context, title, message, "OK", null, null, dialogButtonListener, true);
    }

    /**
     * Shows an alert dialog with a single button, with default alert dismiss functionality
     *
     * @param context     context of calling Activity
     * @param title       Title of Alert dialog
     * @param message     Message to be displayed in alert dialog
     * @param ButtonLabel Label of Positive Button
     */
    public void showAlertDialog(Context context, String title, String message, String ButtonLabel) {
        showAlertDialog(context, title, message, ButtonLabel, null, null, null, true);
    }

    /**
     * Shows an alert dialog with a single button, with custom alert functionality
     *
     * @param context              context of calling Activity
     * @param title                Title of Alert dialog
     * @param message              Message to be displayed in alert dialog
     * @param ButtonLabel          Label of Positive Button
     * @param dialogButtonListener Override required methods of this Listener for custom functionality of Button
     */
    public void showAlertDialog(Context context, String title, String message, String ButtonLabel, DialogButtonListener dialogButtonListener, boolean cancelable) {
        showAlertDialog(context, title, message, ButtonLabel, null, null, dialogButtonListener, cancelable);
    }

    /**
     * Shows an alert dialog with two buttons Positive & Negative. When the user presses any button, the dialog
     * dismisses.
     *
     * @param context            context of calling Activity
     * @param title              Title of Alert dialog
     * @param message            Message to be displayed in alert dialog
     * @param positiveButtonText label for positive Button
     * @param negativeButtonText label for negative Button
     * @param dialogListener     reference of interface where action of positive button to be implemented
     * @param cancelable         boolean to make dialog dismiss on back pressed or not
     */
    public void showAlertDialog(Context context, String title, String message, String positiveButtonText
            , String negativeButtonText, final DialogButtonListener dialogListener, boolean cancelable) {

        showAlertDialog(context, title, message, positiveButtonText, negativeButtonText, null, dialogListener, cancelable);
    }

    /**
     * Shows an alert dialog with three buttons Positive,Negative and Neutral. When the user presses any button, the dialog
     * dismisses.
     *
     * @param context            context of calling Activity
     * @param title              Title of Alert dialog
     * @param message            Message to be displayed in alert dialog
     * @param positiveButtonText label for positive Button
     * @param negativeButtonText label for negative Button
     * @param neutralButtonText  label for neutral Button
     * @param dialogListener     reference of interface where action of buttons to be implemented
     * @param cancelable         boolean to make dialog dismiss on back pressed or not
     */
    public void showAlertDialog(Context context, String title, String message, String positiveButtonText
            , String negativeButtonText, String neutralButtonText, final DialogButtonListener dialogListener, boolean cancelable) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        builder.setMessage(message);
        builder.setCancelable(cancelable);
        builder.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (dialogListener != null) {
                    dialogListener.onPositiveButtonClick();
                }
            }
        });
        if (!TextUtils.isEmpty(negativeButtonText)) {
            builder.setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    if (dialogListener != null) {
                        dialogListener.onNegativeButtonClick();
                    }
                }
            });
        }

        if (!TextUtils.isEmpty(neutralButtonText)) {
            builder.setNeutralButton(neutralButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    if (dialogListener != null) {
                        dialogListener.onNeutralButtonClick();
                    }
                }
            });
        }

        builder.show();
    }

}

