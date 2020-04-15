package sdk.utils.wd.exception;

import android.os.Build;
import android.support.annotation.RequiresApi;

public class StoragePermissionMissing extends RuntimeException {

    public StoragePermissionMissing() {
    }

    public StoragePermissionMissing(String message) {
        super(message);
    }

    public StoragePermissionMissing(String message, Throwable cause) {
        super(message, cause);
    }

    public StoragePermissionMissing(Throwable cause) {
        super(cause);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public StoragePermissionMissing(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
