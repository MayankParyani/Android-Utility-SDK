package sdk.utils.wd.utils;

import android.os.Handler;
import android.text.InputFilter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class ViewUtility {
    /**
     * Set max text length for editText
     */
    public static void setMaxLength(EditText editText, int maxLength) {

        if (editText == null) {
            throw new NullPointerException("EditText cannot be null");
        }

        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);
        editText.setFilters(fArray);
    }

    /**
     * Set max text length for textView
     */
    public static void setMaxLength(TextView textView, int maxLength) {

        if (textView == null) {
            throw new NullPointerException("TextView cannot be null");
        }

        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);
        textView.setFilters(fArray);
    }
}
