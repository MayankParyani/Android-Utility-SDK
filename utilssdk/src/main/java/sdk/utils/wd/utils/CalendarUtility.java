/*
 * Copyright (c) WEBDUNIA
 * All rights reserved.
 */

package sdk.utils.wd.utils;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.text.format.DateUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class CalendarUtility {

    /****
     * Parses date string and return a {@link java.util.Date} object
     *
     * @return The ISO formatted date object
     */
    public static Date parseDate(String date) {

        if (date == null) {
            return null;
        }

        StringBuffer sbDate = new StringBuffer();
        sbDate.append(date);
        String newDate = null;
        Date dateDT = null;

        try {
            newDate = sbDate.substring(0, 19).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String rDate = newDate.replace("T", " ");
        String nDate = rDate.replaceAll("-", "/");

        try {
            dateDT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault()).parse(nDate);
            // Log.v( TAG, "#parseDate dateDT: " + dateDT );
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dateDT;
    }

    /**
     * Gets the name of the day of the week.
     *
     * @param date String ISO format date example 2018-10-03T01:00:00Z
     * @return The name of the day of the week
     **/
    public static String getDayOfWeek(String date) {
        Date dateDT = parseDate(date);

        if (dateDT == null) {
            return null;
        }

        // Get current date
        Calendar c = Calendar.getInstance();
        // it is very important to
        // set the date of
        // the calendar.
        c.setTime(dateDT);
        int day = c.get(Calendar.DAY_OF_WEEK);

        String dayStr = null;

        switch (day) {

            case Calendar.SUNDAY:
                dayStr = "Sunday";
                break;

            case Calendar.MONDAY:
                dayStr = "Monday";
                break;

            case Calendar.TUESDAY:
                dayStr = "Tuesday";
                break;

            case Calendar.WEDNESDAY:
                dayStr = "Wednesday";
                break;

            case Calendar.THURSDAY:
                dayStr = "Thursday";
                break;

            case Calendar.FRIDAY:
                dayStr = "Friday";
                break;

            case Calendar.SATURDAY:
                dayStr = "Saturday";
                break;
        }

        return dayStr;
    }

    /**
     * Returns abbreviated (3 letters) day of the week.
     *
     * @param date String ISO format date example 2018-10-03T01:00:00Z
     * @return The name of the day of the week in 3 letters
     */
    public static String getDayOfWeekAbbreviated(String date) {
        Date dateDT = parseDate(date);

        if (dateDT == null) {
            return null;
        }

        // Get current date
        Calendar c = Calendar.getInstance();
        // it is very important to
        // set the date of
        // the calendar.
        c.setTime(dateDT);
        int day = c.get(Calendar.DAY_OF_WEEK);

        String dayStr = null;

        switch (day) {

            case Calendar.SUNDAY:
                dayStr = "Sun";
                break;

            case Calendar.MONDAY:
                dayStr = "Mon";
                break;

            case Calendar.TUESDAY:
                dayStr = "Tue";
                break;

            case Calendar.WEDNESDAY:
                dayStr = "Wed";
                break;

            case Calendar.THURSDAY:
                dayStr = "Thu";
                break;

            case Calendar.FRIDAY:
                dayStr = "Fri";
                break;

            case Calendar.SATURDAY:
                dayStr = "Sat";
                break;
        }

        return dayStr;
    }

    /***
     * Gets the name of the month from the given date.
     *
     * @param date String ISO format date example 2018-10-03T01:00:00Z
     * @return Returns the name of the month
     */
    public static String getMonth(String date) {
        Date dateDT = parseDate(date);

        if (dateDT == null) {
            return null;
        }

        Calendar c = Calendar.getInstance();
        c.setTime(dateDT);
        int day = c.get(Calendar.MONTH);

        String dayStr = null;

        switch (day) {

            case Calendar.JANUARY:
                dayStr = "January";
                break;

            case Calendar.FEBRUARY:
                dayStr = "February";
                break;

            case Calendar.MARCH:
                dayStr = "March";
                break;

            case Calendar.APRIL:
                dayStr = "April";
                break;

            case Calendar.MAY:
                dayStr = "May";
                break;

            case Calendar.JUNE:
                dayStr = "June";
                break;

            case Calendar.JULY:
                dayStr = "July";
                break;

            case Calendar.AUGUST:
                dayStr = "August";
                break;

            case Calendar.SEPTEMBER:
                dayStr = "September";
                break;

            case Calendar.OCTOBER:
                dayStr = "October";
                break;

            case Calendar.NOVEMBER:
                dayStr = "November";
                break;

            case Calendar.DECEMBER:
                dayStr = "December";
                break;
        }

        return dayStr;
    }

    /**
     * Converts string object into date object
     *
     * @param dtStart String to be converted into Date object example "2018-10-02T01:00:00Z"
     * @return date object
     */
    public static Date convertStringToDate(String dtStart) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date = null;
        try {
            date = format.parse(dtStart);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * Converts string object into date object
     *
     * @param date date to be converted into String object
     * @return String object like "2018-10-02T01:00:00Z"
     */
    public static String convertDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String dateTime = null;
        try {
            dateTime = dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateTime;
    }

    /**
     * Gets abbreviated name of the month from the given date.
     *
     * @param date ISO format date example 2014-09-02T08:05:23.653Z
     * @return Returns the name of the month
     */
    public static String getMonthAbbreviated(String date) {
        Date dateDT = parseDate(date);

        if (dateDT == null) {
            return null;
        }

        // Get current date
        Calendar c = Calendar.getInstance();
        // it is very important to
        // set the date of
        // the calendar.
        c.setTime(dateDT);
        int day = c.get(Calendar.MONTH);

        String dayStr = null;

        switch (day) {

            case Calendar.JANUARY:
                dayStr = "Jan";
                break;

            case Calendar.FEBRUARY:
                dayStr = "Feb";
                break;

            case Calendar.MARCH:
                dayStr = "Mar";
                break;

            case Calendar.APRIL:
                dayStr = "Apr";
                break;

            case Calendar.MAY:
                dayStr = "May";
                break;

            case Calendar.JUNE:
                dayStr = "Jun";
                break;

            case Calendar.JULY:
                dayStr = "Jul";
                break;

            case Calendar.AUGUST:
                dayStr = "Aug";
                break;

            case Calendar.SEPTEMBER:
                dayStr = "Sep";
                break;

            case Calendar.OCTOBER:
                dayStr = "Oct";
                break;

            case Calendar.NOVEMBER:
                dayStr = "Nov";
                break;

            case Calendar.DECEMBER:
                dayStr = "Dec";
                break;
        }

        return dayStr;
    }

    /**
     * Gets a string TimeStamp phrase like 5 mins ago
     * yesterday, 3 days ago maximum to 5 days ago after than it starts returning passed date as string
     *
     * @param originalDate object of Date
     * @return convertedDate String
     */
    public static String getTimeStamp(Date originalDate) {

        String convertedDate = DateUtils.getRelativeTimeSpanString(originalDate.getTime(),
                new Date().getTime(),
                DateUtils.SECOND_IN_MILLIS).toString();

        return convertedDate;
    }

    /**
     * Gets a string TimeStamp phrase like 5 mins ago
     * yesterday, 3 days ago.
     *
     * @param originalDateTime time in milliseconds since epoch
     * @return convertedDate String
     */
    public static String getTimeStamp(Long originalDateTime) {

        String convertedDate = DateUtils.getRelativeTimeSpanString(originalDateTime,
                new Date().getTime(),
                DateUtils.SECOND_IN_MILLIS).toString();

        return convertedDate;

    }

    /***
     * Converts ISO date string to UTC timezone equivalent.
     *
     * @param dateAndTime ISO formatted time string.
     */
    public static String getUtcTime(String dateAndTime) {
        Date d = parseDate(dateAndTime);

        String format = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());

        // Convert Local Time to UTC
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        return sdf.format(d);
    }

    /**
     * Transforms Calendar to ISO 8601 string.
     **/
    public static String fromCalendar(final Calendar calendar) {
        // TODO: move this method to DateUtils
        Date date = calendar.getTime();
        String formatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(date);
        return formatted.substring(0, 22) + ":" + formatted.substring(22);
    }

    /**
     * Gets current date and time formatted as ISO 8601 string.
     **/
    public static String now() {
        // TODO: move this method to DateUtils
        return fromCalendar(GregorianCalendar.getInstance());
    }

    /**
     * Transforms ISO 8601 string to Calendar.
     **/
    public static Calendar toCalendar(final String iso8601string) throws ParseException {
        // TODO: move this method to DateUtils
        Calendar calendar = GregorianCalendar.getInstance();
        String s = iso8601string.replace("Z", "+00:00");
        try {
            s = s.substring(0, 22) + s.substring(23);
        } catch (IndexOutOfBoundsException e) {
            // throw new org.apache.http.ParseException();
            e.printStackTrace();
        }

        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(s);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        return calendar;
    }

    /**
     * @param time ISO formatted time when the event occurred in local time zone.
     * @deprecated Totally bloated code.
     * Calculates the elapsed time after the given parameter date.
     **/
    public static String getElapsedTime(String time) {
        TimeZone defaultTimeZone = TimeZone.getDefault();

        // TODO: its advisable not to use this method as it changes the
        // timezone.
        // Change it at some time in future.
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        Date eventTime = parseDate(time);

        Date currentDate = new Date();

        long diffInSeconds = (currentDate.getTime() - eventTime.getTime()) / 1000;
        String elapsed = "";
        long seconds = diffInSeconds;
        long mins = diffInSeconds / 60;
        long hours = diffInSeconds / (60 * 60);
        long days = diffInSeconds / 86400;
        long weeks = diffInSeconds / 604800;
        long months = diffInSeconds / 2592000;

        // Log.v( TAG, "#getElapsedTime seconds: " + seconds + " mins: " + mins
        // + " hours: " + hours + " days: " + days );

        if (seconds < 120) {
            elapsed = "a min ago";
        } else if (mins < 60) {
            elapsed = mins + " mins ago";
        } else if (hours < 24) {
            elapsed = hours + " " + (hours > 1 ? "hrs" : "hr") + " ago";
        } else if (hours < 48) {
            elapsed = "a day ago";
        } else if (days < 7) {
            elapsed = days + " days ago";
        } else if (weeks < 5) {
            elapsed = weeks + " " + (weeks > 1 ? "weeks" : "week") + " ago";
        } else if (months < 12) {
            elapsed = months + " " + (months > 1 ? "months" : "months") + " ago";
        } else {
            elapsed = "more than a year ago";
        }

        TimeZone.setDefault(defaultTimeZone);

        return elapsed;
    }

    /**
     * Set Mock Location for test device. DDMS cannot be used to mock location on an actual device.
     * So this method should be used which forces the GPS Provider to mock the location on an actual
     * device.
     **/
    public static void setMockLocation(Context ctx, double longitude, double latitude) {
        // use application level context to avoid unnecessary leaks.
        LocationManager locationManager = (LocationManager) ctx.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        locationManager.addTestProvider(LocationManager.GPS_PROVIDER, "requiresNetwork" == "", "requiresSatellite" == "", "requiresCell" == "", "hasMonetaryCost" == "", "supportsAltitude" == "", "supportsSpeed" == "", "supportsBearing" == "",

                android.location.Criteria.POWER_LOW, android.location.Criteria.ACCURACY_FINE);

        Location newLocation = new Location(LocationManager.GPS_PROVIDER);

        newLocation.setLongitude(longitude);
        newLocation.setLatitude(latitude);
        newLocation.setTime(new Date().getTime());

        newLocation.setAccuracy(500);

        locationManager.setTestProviderEnabled(LocationManager.GPS_PROVIDER, true);

        locationManager.setTestProviderStatus(LocationManager.GPS_PROVIDER, LocationProvider.AVAILABLE, null, System.currentTimeMillis());

        // http://jgrasstechtips.blogspot.it/2012/12/android-incomplete-location-object.html
        makeLocationObjectComplete(newLocation);

        locationManager.setTestProviderLocation(LocationManager.GPS_PROVIDER, newLocation);
    }

    private static void makeLocationObjectComplete(Location newLocation) {
        Method locationJellyBeanFixMethod = null;
        try {
            locationJellyBeanFixMethod = Location.class.getMethod("makeComplete");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        if (locationJellyBeanFixMethod != null) {
            try {
                locationJellyBeanFixMethod.invoke(newLocation);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

}
