/*
 * Copyright (c) WEBDUNIA
 * All rights reserved.
 */

package sdk.utils.wd.network;

public enum NetworkType {

    MOBILE("Mobile", 0),
    MOBILE_2G("Mobile 2g", 1),
    MOBILE_3G("Mobile 3g", 2),
    MOBILE_4G("Mobile 4g", 3),
    WIFI("Wifi", 3),
    NOT_CONNECTED("Not connected to any network", 5);

    private String type;
    private int value;

    NetworkType(String type, int value) {
        this.type = type;
        this.value = value;
    }

    public String getNetworkType() {
        return type;
    }

    public int getNetworkValue() {
        return value;
    }
}
