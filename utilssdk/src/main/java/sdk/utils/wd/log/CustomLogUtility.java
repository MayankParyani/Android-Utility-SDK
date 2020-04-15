/*
 * Copyright (c) WEBDUNIA
 * All rights reserved.
 */

package sdk.utils.wd.log;

import android.util.Log;

/**
 * Utility class for custom logs with On/Off feature
 */

public class CustomLogUtility {

    private static boolean mLogEnable = true;

    /**
     * Method to enable or disable logs
     *
     * @param status pass true to enable logs or false to disable logs
     */
    public static void enableLogs(boolean status) {
        mLogEnable = status;
    }

    /**
     * Method to check current log status
     */
    public static boolean getLogStatus() {
        if (mLogEnable) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method for error logs
     *
     * @param tag     tag name for log to be print
     * @param message message to be print in logcat
     */
    public static void logE(String tag, String message) {
        if (mLogEnable) {
            if (message == null) {
                throw new NullPointerException("Log Message cannot be null");
            }
            Log.e(tag, message);
        }
    }

    /**
     * Method for debug logs
     *
     * @param tag     tag name for log to be print
     * @param message message to be print in logcat
     */
    public static void logD(String tag, String message) {
        if (mLogEnable) {
            if (message == null) {
                throw new NullPointerException("Log Message cannot be null");
            }
            Log.d(tag, message);
        }
    }

    /**
     * Method for Info logs
     *
     * @param tag     tag name for log to be print
     * @param message message to be print in logcat
     */
    public static void logI(String tag, String message) {
        if (mLogEnable) {
            if (message == null) {
                throw new NullPointerException("Log Message cannot be null");
            }
            Log.i(tag, message);
        }
    }

    /**
     * Method for Warning logs
     *
     * @param tag     tag name for log to be print
     * @param message message to be print in logcat
     */
    public static void logW(String tag, String message) {
        if (mLogEnable) {
            if (message == null) {
                throw new NullPointerException("Log Message cannot be null");
            }
            Log.w(tag, message);
        }
    }

    /**
     * Method for Verbose logs
     *
     * @param tag     tag name for log to be print
     * @param message message to be print in logcat
     */
    public static void logV(String tag, String message) {
        if (mLogEnable) {
            if (message == null) {
                throw new NullPointerException("Log Message cannot be null");
            }
            Log.v(tag, message);
        }
    }
}