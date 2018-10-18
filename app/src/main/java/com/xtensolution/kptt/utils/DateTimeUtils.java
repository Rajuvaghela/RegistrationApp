package com.xtensolution.kptt.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Scorpion on 17-11-2017.
 */

public class DateTimeUtils {
    public static final String SERVER_FORMAT = "yyyy-MM-dd hh:mm:ss";
//    public static final String DISPLAY_FORMAT = "dd-MMM-yyyy";
     public static final String DISPLAY_FORMAT = "dd MMM,yyyy";

    public static final String MM_YYYY = "MM, yyyy";
    public static final String MMM_DD_YYYY = "MMM dd, yyyy";
    public static final String DD_MM_YYYY = "dd-MM-yyyy";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String MM_DD_YYYY_HH_MM = "MM-dd-yyyy HH:mm";
    public static final String YYYY_MM_DD_HH_MM_S = "yyyy-MM-dd HH:mm:ss";
    public static final String DD_MMM_YYYY = "dd MMM, yyyy";
    private static final String CALENDAR_DATE_FORMAT = "MMM dd yyyy";
    private static final String HH_MM_SS = "hh:mm a";
    private static final String TAG = DateTimeUtils.class.getSimpleName();

    public static SimpleDateFormat getDisplayDateFormat() {
        return new SimpleDateFormat(MMM_DD_YYYY, Locale.getDefault());
    }
    public static SimpleDateFormat getDisplayDateFormatOther() {
        return new SimpleDateFormat(DD_MMM_YYYY, Locale.getDefault());
    }

    public static SimpleDateFormat getPickerDateFormat() {
        return new SimpleDateFormat(DD_MM_YYYY, Locale.getDefault());
    }

    public static SimpleDateFormat getCalendarDBFormat() {
        return new SimpleDateFormat(YYYY_MM_DD, Locale.getDefault());
    }

    public static SimpleDateFormat getCalendarMonthYearFormat() {
        return new SimpleDateFormat(MM_YYYY, Locale.getDefault());
    }


    public static SimpleDateFormat getDBFormat() {
        return new SimpleDateFormat(YYYY_MM_DD_HH_MM_S, Locale.getDefault());
    }

    public static SimpleDateFormat getTimeFormat() {
        return new SimpleDateFormat(HH_MM_SS, Locale.getDefault());
    }

    public static boolean isDateAfterSpecificDate(String date, String dateSpecific) {
        try {
            if (ValidationUtils.isValidString(date)) {
                SimpleDateFormat formate = new SimpleDateFormat(MMM_DD_YYYY);
                Date selectedDate = formate.parse(date);
                Date specificDate = formate.parse(dateSpecific);

                if (selectedDate.after(specificDate)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
            return false;
        }
    }

}


