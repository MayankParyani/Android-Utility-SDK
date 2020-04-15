/*
 * Copyright (c) WEBDUNIA
 * All rights reserved.
 */

package sdk.utils.wd.system;

import android.os.Build;

import java.io.File;
import java.io.IOException;

/**
 * Created by Mayank Paryani
 * Utility class to Check Rooted Devices
 */

public class RootCheckUtils {

    /**
     * This Method gathered the results from the three tests and if any of the three test results were successful,
     * would return true, indicating the device is rooted.
     */
    public static Boolean isDeviceRooted() {
        boolean isRooted = hasSuperuserApk() || isTestKeyBuild() || canExecuteSuCommand();
        return isRooted;
    }

    /**
     * The First method created an “abstract” representation of a file by using the absolute path and filename to the most popular Superuser application.
     * A call would be made to this representation of the file to check whether the file exists. If the file exists, the command was determined successful.
     **/
    private static boolean hasSuperuserApk() {
        File file = new File("/system/app/Superuser.apk");
        if (file.exists()) {
            return true;
        }
        return false;
    }

    /**
     * The Second method created an interface to the environment, where the app was running, through getting a singleton instance by invoking getRuntime()
     * and passing the “su” command. If an IOException error was not encountered, the command was determined successful.
     */
    private static boolean canExecuteSuCommand() {
        boolean executedSuccessfully;
        try {
            Runtime.getRuntime().exec("su");
            executedSuccessfully = true;
        } catch (IOException localIOException) {
            executedSuccessfully = false;
        }
        return executedSuccessfully;
    }

    /**
     * The third method would extract the operating system build information from a system properties file (/system/build.prop).
     * The extracted build tags information was searched for the phrase “test-keys” and if “test-keys” was found in the build tags information,
     * the command was determined successful.
     */
    private static boolean isTestKeyBuild() {
        String str = Build.TAGS;
        if ((str != null) && (str.contains("test-keys"))) {
            return true;
        }
        return false;
    }
}
