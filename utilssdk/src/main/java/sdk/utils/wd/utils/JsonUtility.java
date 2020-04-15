/*
 * Copyright (c) WEBDUNIA
 * All rights reserved.
 */

package sdk.utils.wd.utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonUtility {

    /**
     * converts a plain old java object into a json string
     *
     * @param object object of any valid pojo class
     * @return Json String
     */
    public static String convertPOJOToString(Object object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        return json;
    }

    /**
     * converts a json string into plain old java object (POJO) class
     *
     * @param jsonString refers to the actual response string to be converted
     * @param Object     Object class refers to the POJO class object you want to generate using string
     * @return returns the generated pojo object if string is a valid json
     */
    public static Object convertStringToPojo(String jsonString, Object Object) {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new StringReader(jsonString));
        reader.setLenient(true);
        try {
            Object = gson.fromJson(reader, (Type) Object);
            return Object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<String> toStringArray(JSONArray jsonArr) {

        if (jsonArr == null || jsonArr.length() == 0) {
            return null;
        }

        ArrayList<String> stringArray = new ArrayList<String>();

        for (int i = 0, count = jsonArr.length(); i < count; i++) {
            try {
                String str = jsonArr.getString(i);
                stringArray.add(str);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return stringArray;
    }

    /**
     * Convert a given list of {@link String} into a {@link org.json.JSONArray}
     **/
    public static JSONArray toJSONArray(ArrayList<String> stringArr) {
        JSONArray jsonArr = new JSONArray();

        for (int i = 0; i < stringArr.size(); i++) {
            String value = stringArr.get(i);
            jsonArr.put(value);
        }

        return jsonArr;
    }

}
