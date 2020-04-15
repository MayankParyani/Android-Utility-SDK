/*
 * Copyright (c) WEBDUNIA
 * All rights reserved.
 */

package sdk.utils.wd.utils;

import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtility {

    /**
     * Checks if the input parameter is a valid email.
     *
     * @param email email to ver verified
     * @return boolean "true" if email is valid else "false"
     */
    public static boolean isValidMail(String email) {
        boolean check;
        Pattern p;
        Matcher m;

        String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        p = Pattern.compile(EMAIL_STRING);

        m = p.matcher(email);
        check = m.matches();

        return check;
    }

    /**
     * Checks if the url is valid
     */
    public static boolean isValidURL(String url) {
        URL urlObj;

        try {
            urlObj = new URL(url);
        } catch (MalformedURLException e) {
            return false;
        }

        try {
            urlObj.toURI();
        } catch (URISyntaxException e) {
            return false;
        }

        return true;
    }

    public static boolean isValidMobile(String phone) {
        boolean check = false;
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            if (phone.length() < 6 || phone.length() > 13) {
                check = false;
            } else {
                check = true;
            }
        } else {
            check = false;
        }
        return check;
    }

    /**
     * Capitalizes first word in the sentence.
     *
     * @param string to be converted
     * @return converted String
     */
    public static String capitalizeString(String string) {

        if (string == null) {
            return null;
        }

        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') { // You
                // can add other
                // chars here
                found = false;
            }
        }

        return String.valueOf(chars);
    }

    /**
     * Capitalizes each word in the string.
     *
     * @param string to be converted
     * @return converted String
     */
    public static String convertToUpperCase(String string) {
        String convertedString = "";
        if (!TextUtils.isEmpty(string)) {
            convertedString = string.toUpperCase();
        }
        return convertedString;
    }

    /**
     * converts a uppercase string into lowercase
     *
     * @param string to be converted
     * @return converted String
     */
    public static String convertToLowerCase(String string) {
        String convertedString = "";
        if (!TextUtils.isEmpty(string)) {
            convertedString = string.toLowerCase();
        }
        return convertedString;
    }

    /**
     * check whether passed string is empty or null
     *
     * @param string to be checked
     * @return "true" if string is not Empty or not null else false
     */
    public static boolean isEmptyOrNull(String string) {

        if (!TextUtils.isEmpty(string)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * replaces a string with respect to target and replacement
     *
     * @param actualString string which needs to be modified
     * @param target       target char to be replaced in actual string
     * @param replacement  char which needs to be replaced in place of target char in actual string
     * @return replaced string
     * Example replaceAllOccurrences("Loren Ipsum", "Loren", "Candid"));
     * returns Candid Ipsum
     */
    public static String replaceAllOccurrences(String actualString, String target, String replacement) {
        String replacedString = "";
        if (!TextUtils.isEmpty(actualString) && !TextUtils.isEmpty(target) && !TextUtils.isEmpty(replacement)) {
            replacedString = actualString.replaceAll(target, replacement);
            return replacedString;
        } else {
            return replacedString;
        }
    }

    /**
     * replaces a string with respect to target and replacement
     *
     * @param actualString string which needs to be modified
     * @param target       target char to be replaced in actual string
     * @param replacement  char which needs to be replaced in place of target char in actual string
     * @return replaced string
     * Example replaceAllOccurrences("Loren", 'L', 'C'));
     * returns Candid
     */
    public static String replaceSingleOccurrence(String actualString, char target, char replacement) {
        String replacedString = "";
        if (!TextUtils.isEmpty(actualString)) {
            replacedString = actualString.replace(target, replacement);
            return replacedString;
        } else {
            return replacedString;
        }
    }

    /**
     * Limits a string to a particular length
     *
     * @param str actual string
     * @param len length upto string to be truncated
     * @return truncated string
     */
    public static String truncate(String str, int len) {
        if (str.length() > len) {
            return str.substring(0, len) + "...";
        } else {
            return str;
        }
    }

    /**
     * calculates length of a string
     *
     * @param string string whose length to be calculated
     * @return length of string in int
     */
    public static int getLength(String string) {
        int length = 0;
        if (!TextUtils.isEmpty(string)) {
            length = string.length();
        }
        return length;
    }

    /**
     * Check whether a string contains another string
     * NOTE : This method is Case Sensitive
     *
     * @param actualString Original string which can contains a subset of a string
     * @param subset
     * @return boolean true if String contains else false
     */
    public static boolean contains(String actualString, String subset) {
        if (!TextUtils.isEmpty(actualString) && !TextUtils.isEmpty(subset)) {
            if (actualString.contains(subset)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Check whether a string contains another string
     * NOTE : This method is Case Insensitive
     *
     * @param actualString Original string which can contains a subset of a string
     * @param subset
     * @return boolean true if String contains else false
     */
    public static boolean containsIgnoreCase(String actualString, String subset) {
        if (!TextUtils.isEmpty(actualString) && !TextUtils.isEmpty(subset)) {
            if (Pattern.compile(Pattern.quote(subset), Pattern.CASE_INSENSITIVE).matcher(actualString).find()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * generates random string
     *
     * @return random string
     */
    private String getRandomString() {
        return UUID.randomUUID().toString();
    }

    /**
     * generates random number
     *
     * @return random integer
     */
    public static int getRandomNumber() {
        Random generator = new Random();
        int mRandonNumber = 10000;
        mRandonNumber = generator.nextInt(mRandonNumber);
        return mRandonNumber;
    }
}
