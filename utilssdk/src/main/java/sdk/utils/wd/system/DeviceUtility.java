package sdk.utils.wd.system;

import android.annotation.TargetApi;
import android.os.Build;

public class DeviceUtility {

    private static String TAG = DeviceUtility.class.getName();

    /**
     * Instantiates a new Easy device mod.
     * <p>
     * This is the basic and easy "builder" method that lets you use do stuff.
     */
    public DeviceUtility() {
    }

    public static class abiBuilder {
        private String TAG = ".abiBuilder";

        /**
         * The X86.
         * <p>
         * This String method returns "x86";
         */
        public String X86 = "x86";

        /**
         * The Arm.
         * <p>
         * This String method returns "armeabi-v7a";
         */
        public String ARM = "armeabi-v7a";

        /**
         * The Arm64.
         * <p>
         * This String method returns "arm64-v8a";
         */
        public String ARM64 = "arm64-v8a";

        /**
         * Device supported abis string[].
         * <p>
         * This String[] method returns Build.SUPPORTED_ABIS;
         *
         * @return the string[]
         */
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        public String[] SUPPORTED_ABIS() {
            return (String[]) utilObject(Build.SUPPORTED_ABIS);
        }

        /**
         * Device supported 64 bit abis string.
         * <p>
         * This String[] method returns Build.SUPPORTED_64_BIT_ABIS;
         *
         * @return the string[]
         */
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        public String[] SUPPORTED_64_BIT_ABIS() {
            return (String[]) utilObject(Build.SUPPORTED_64_BIT_ABIS);
        }

        /**
         * Device supported 32 bit abis string[].
         * <p>
         * This String[] method returns Build.SUPPORTED_32_BIT_ABIS;
         *
         * @return the string[]
         */
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        public String[] SUPPORTED_32_BIT_ABIS() {
            return (String[]) utilObject(Build.SUPPORTED_32_BIT_ABIS);
        }

        /**
         * Device cpu abi 2 string.
         * <p>
         * This String method returns Build.CPU_ABI2;
         *
         * @return the string
         */
        @SuppressWarnings("deprecation")
        public String CPU_ABI2() {
            return (String) utilObject(Build.CPU_ABI2);
        }

        /**
         * Device cpu abi string.
         * <p>
         * This String method returns Build.CPU_ABI;
         *
         * @return the string
         */
        @SuppressWarnings("deprecation")
        public String CPU_ABI() {
            return (String) utilObject(Build.CPU_ABI);
        }
    }

    public static class versionBuilder {
        private String TAG = ".versionBuilder";

        /**
         * Device sdk int.
         * <p>
         * This int method returns Build.VERSION.SDK_INT;
         *
         * @return the int
         */
        public int SDK_INT() {
            return (Integer) utilObject(Build.VERSION.SDK_INT);
        }

        /**
         * Device base OS string.
         * <p>
         * This String method returns Build.VERSION.BASE_OS;
         *
         * @return the string
         */
        @TargetApi(Build.VERSION_CODES.M)
        public String BASE_OS() {
            return (String) utilObject(Build.VERSION.BASE_OS);
        }

        /**
         * Device sdk dep string.
         * <p>
         * This String method returns Build.VERSION.SDK;
         *
         * @return the string
         * @deprecated
         */
        public String SDK_DEP() {
            return (String) utilObject(Build.VERSION.SDK);
        }
    }

    public static class deviceBuilder {
        private String TAG = ".deviceBuilder";

        /**
         * Device manufacturer string.
         * <p>
         * This String method returns Build.MANUFACTURER;
         *
         * @return the string
         */
        public String MANUFACTURER() {
            return (String) utilObject(Build.MANUFACTURER);
        }

        /**
         * Device model string.
         * <p>
         * This String method returns Build.MODEL;
         *
         * @return the string
         */
        public String MODEL() {
            return (String) utilObject(Build.MODEL);
        }

        /**
         * Device board string.
         * <p>
         * This String method returns Build.BOARD;
         *
         * @return the string
         */
        public String BOARD() {
            return (String) utilObject(Build.BOARD);
        }

        /**
         * Device bootloader string.
         * <p>
         * This String method returns Build.BOOTLOADER;
         *
         * @return the string
         */
        public String BOOTLOADER() {
            return (String) utilObject(Build.BOOTLOADER);
        }

        /**
         * Device brand string.
         * <p>
         * This String method returns Build.BRAND;
         *
         * @return the string
         */
        public String BRAND() {
            return (String) utilObject(Build.BRAND);
        }

        /**
         * Device device string.
         * <p>
         * This String method returns Build.DEVICE;
         *
         * @return the string
         */
        public String DEVICE() {
            return (String) utilObject(Build.DEVICE);
        }

        /**
         * Device display string.
         * <p>
         * This String method returns Build.DISPLAY;
         *
         * @return the string
         */
        public String DISPLAY() {
            return (String) utilObject(Build.DISPLAY);
        }

        /**
         * Device fingerprint string.
         * <p>
         * This String method returns Build.FINGERPRINT;
         *
         * @return the string
         */
        public String FINGERPRINT() {
            return (String) utilObject(Build.FINGERPRINT);
        }

        /**
         * Device host string.
         * <p>
         * This String method returns Build.HOST;
         *
         * @return the string
         */
        public String HOST() {
            return (String) utilObject(Build.HOST);
        }

        /**
         * Device product string.
         * <p>
         * This String method returns Build.PRODUCT;
         *
         * @return the string
         */
        public String PRODUCT() {
            return (String) utilObject(Build.PRODUCT);
        }

        /**
         * Device tags string.
         * <p>
         * This String method returns Build.TAGS;
         *
         * @return the string
         */
        public String TAGS() {
            return (String) utilObject(Build.TAGS);
        }

        /**
         * Device type string.
         * <p>
         * This String method returns Build.TYPE;
         *
         * @return the string
         */
        public String TYPE() {
            return (String) utilObject(Build.TYPE);
        }

        /**
         * Device user string.
         * <p>
         * This String method returns Build.USER;
         *
         * @return the string
         */
        public String USER() {
            return (String) utilObject(Build.USER);
        }
    }

    public static class utilsBuilder {
        private String TAG = ".utilsBuilder";

        /**
         * Device radioversion string.
         * <p>
         * This String method returns Build.getRadioVersion;
         *
         * @return the string
         */
        @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
        public String RADIO_VERSION() {
            return (String) utilObject(Build.getRadioVersion());
        }

        /**
         * Device radio dep string.
         * <p>
         * This String method returns Build.RADIO;
         *
         * @return the string
         */
        public String RADIO_DEP() {
            return (String) utilObject(Build.RADIO);
        }

        /**
         * Device hardware string.
         * <p>
         * This String method returns Build.HARDWARE;
         *
         * @return the string
         */
        public String HARDWARE() {
            return (String) utilObject(Build.HARDWARE);
        }

        /**
         * Device id string.
         * <p>
         * This String method returns Build.ID;
         *
         * @return the string
         */
        public final String ID() {
            return (String) utilObject(Build.ID);
        }

        /**
         * Device time long.
         * <p>
         * This Long method returns Build.TIME;
         *
         * @return the long
         */
        public long TIME() {
            return (Long) utilObject(Build.TIME);
        }
    }

    private static Object utilObject(Object object) {
        try {
            if (object instanceof String) {
                String data = (String) object;
                if (data != null && !data.isEmpty())
                    return object;
                else
                    return null;
            } else if (object instanceof Integer) {
                Integer data = (Integer) object;
                if (data != null)
                    return object;
                else
                    return null;
            } else if (object instanceof String[]) {
                String[] data = (String[]) object;
                if (data != null && data.length != 0)
                    return object;
                else
                    return null;
            } else if (object instanceof Long) {
                Long data = (Long) object;
                if (data != null)
                    return object;
                else
                    return null;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
