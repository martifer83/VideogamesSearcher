package marti.com.example.exampleapp.utils;

import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by mferrando on 02/06/16.
 */
public class UtilsDate {

    public static String getStringFromDate(Date date) {
        return getStringFromDate(date, "EEEE d MMMM");
    }

    public static String getStringFromFullDate(Date date) {
        return getStringFromDate(date, "EEEE d MMMM à HH:mm");
    }

    public static String getStringFromDate(Date date, String format) {
        if (date == null)
            return "";

        Format formatter = new SimpleDateFormat(format, Locale.getDefault());
        return formatter.format(date);
    }

    public static Date getDateFromString(String stDate, String format) {
        if (TextUtils.isEmpty(stDate))
            return null;

        try {
            return new SimpleDateFormat(format, Locale.getDefault()).parse(stDate);
        } catch (ParseException e) {
            Log.d("Unparseable date: ", stDate + " to " + format);
            return null;
        }
    }

    /**
     * La fecha de un evento puede venir sin hora ("yyyy-MM-dd") o con hora ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
     *
     * @param eventStringDate
     * @return
     */
    public static Date getDateFromEventStringDate(String eventStringDate) {
        Date hourDate = getDateFromString(eventStringDate, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        if (hourDate != null) {
            return UtilsDate.convertTimeZone(hourDate, TimeZone.getTimeZone("UTC"), TimeZone.getDefault());
        } else {
            return getDateFromString(eventStringDate, "yyyy-MM-dd");
        }
    }

    /**
     * Segun el formato recibido, printamos la fecha del evento con hora o sin hora
     *
     * @param eventStringDate
     * @return
     */
    public static String printEventDate(String eventStringDate) {
        Date hourDate = getDateFromString(eventStringDate, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        if (hourDate != null) {
            Date utcDate = UtilsDate.convertTimeZone(hourDate, TimeZone.getTimeZone("UTC"), TimeZone.getDefault());
            return getStringFromFullDate(utcDate);
        } else {
            Date noHourDate = getDateFromString(eventStringDate, "yyyy-MM-dd");
            return getStringFromDate(noHourDate);
        }
    }

    public static Date convertTimeZone(Date date, TimeZone fromTZ, TimeZone toTZ) {
        if (date == null) return null;

        long fromTZDst = 0;
        if (fromTZ.inDaylightTime(date)) {
            fromTZDst = fromTZ.getDSTSavings();
        }

        long fromTZOffset = fromTZ.getRawOffset() + fromTZDst;

        long toTZDst = 0;
        if (toTZ.inDaylightTime(date)) {
            toTZDst = toTZ.getDSTSavings();
        }
        long toTZOffset = toTZ.getRawOffset() + toTZDst;

        return new Date(date.getTime() + (toTZOffset - fromTZOffset));
    }

    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static String getCurrentDateForTag() {
        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }

    public static String getDateForTag(String date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String formattedDate = df.format(getDateFromEventStringDate(date));
        return formattedDate;
    }

    public static String secondsToHour(int seconds){

        int i = seconds/3600;
        return String.valueOf(i);
    }

    /*public static String secondsToDateString(int seconds){
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(seconds*1000);
        String date = DateFormat.format(" hh:mm:ss ", cal).toString();
        return date;
    }*/

    public static String getDateFromTimeStamp(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time);
        //String date = DateFormat.format("EEE MMM dd hh:mm:ss yyyy ", cal).toString();
        String date = DateFormat.format(" MMM dd yyyy ", cal).toString();
        return date;
    }

}