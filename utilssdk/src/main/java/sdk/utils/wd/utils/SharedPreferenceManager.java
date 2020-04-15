/*
 * Copyright (c) WEBDUNIA
 * All rights reserved.
 */

package sdk.utils.wd.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Provides convenience methods and abstraction for storing data in the
 * {@link android.content.SharedPreferences}
 **/
public class SharedPreferenceManager {

    private static final String PREFERENCE_NAME = "app_data";

    private static SharedPreferenceManager sharePref = null;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static Gson gson;

    private SharedPreferenceManager(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            gson = new Gson();
        }
    }

    //The context passed into the getInstance should be application level context.
    public static SharedPreferenceManager getInstance(Context context) {
        if (sharePref == null) {
            sharePref = new SharedPreferenceManager(context);
        }
        return sharePref;
    }

    /***
     * Set a string data for the key
     ****/
    public void saveString(String key, String data) {
        editor.putString(key, data);
        editor.apply();
    }

    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    /***
     * Set a int data for the key
     ****/
    public void saveInt(String key, int value) {
        editor.putInt(key, value);
        editor.apply();
    }

    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    /***
     * Set a boolean data for the key
     ****/
    public void saveBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    /***
     * Set a float data for the key
     ****/
    public void saveFloat(String key, float value) {
        editor.putFloat(key, value);
        editor.apply();
    }

    public float getFloat(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    /***
     * Set a long data for the key
     ****/
    public void saveLong(String key, long value) {
        editor.putLong(key, value);
        editor.apply();
    }

    public long getLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    /**
     * Clear all the preferences store in this {@link android.content.SharedPreferences.Editor}
     */
    public void clearData() {
        try {
            editor.clear().apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes preference entry for the given key.
     *
     * @param key
     */
    public void clearDataWithKey(String key) {
        editor.remove(key).apply();
    }

    /***
     * Checks whether given key exists or not in shared preference
     ****/
    public boolean exists(String key) {
        return sharedPreferences.contains(key);
    }

    /***
     * put any type of object in shared preference with a associated key
     ****/
    public void putObject(String key, Object object) {
        if (object == null) {
            throw new IllegalArgumentException("object is null");
        }

        if (key.equals("") || key == null) {
            throw new IllegalArgumentException("key is empty or null");
        }

        editor.putString(key, gson.toJson(object));
        editor.apply();
    }

    public <T> T getObject(String key, Class<T> className) {
        String data = sharedPreferences.getString(key, null);
        if (gson == null) {
            return null;
        } else {
            try {
                return gson.fromJson(data, className);
            } catch (Exception e) {
                throw new IllegalArgumentException("Object stored with key " + key + " is instanceof other class");
            }
        }
    }

    /***
     * Set a string array data with the provided key
     ****/
    public void saveStringArray(ArrayList<String> list, String key) {
        editor.putString(key, gson.toJson(list));
        editor.apply();
    }

    /***
     * get a string array data with the provided key
     ****/
    public ArrayList<String> getStringArray(String key) {
        String json = sharedPreferences.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

}
