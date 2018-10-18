package com.xtensolution.kptt.utils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by RIONTECH
 * Riontech on 30/11/17.
 */

public class DateTimePickerDialog {

    private static final String TAG = DateTimePickerDialog.class.getSimpleName();
    private Context mContext;
    private DateTimePickerListener mListener;
//    private DateFormat originalFormat = DateTimeUtils.getSimpleDateFormat(Enums.DATE_TIME_FORMAT.DD_MM_YYYY);
    //private DateFormat targetFormat = DateTimeUtils.getSimpleDateFormat(Enums.DATE_TIME_FORMAT.MM_DD_YYYY);

    /**
     * DateTimePickerDialog Constructor
     *
     * @param ctx
     */
    public DateTimePickerDialog(Context ctx) {
        mContext = ctx;

    }

    /**
     * Display Date Picker Dialog
     *
     * @param listener(DateListener)
     */
    public void showDatePickerDialog(DateTimePickerListener listener) {
        mListener = listener;
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        String selectedDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        try {
//                            Date date = originalFormat.parse(selectedDate);
//                            String formattedDate = targetFormat.format(date);
                            if (mListener != null)
                                mListener.getDataTime(selectedDate);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    /**
     * Display Time Picker Dialog
     *
     * @param listener(TimePickerListener)
     */
    public void showTimePickerDialog(DateTimePickerListener listener) {
        mListener = listener;
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(mContext,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        if (mListener != null) {
                            try {
                                String date = hourOfDay + ":" + minute;
//                                SimpleDateFormat utcSimpleDateFormat = new SimpleDateFormat("HH:mm");
//                                Date utcDate = utcSimpleDateFormat.parse(date);
//
//                                SimpleDateFormat localSimpleDateFormat =
//                                        DateTimeUtils.getSimpleDateFormat(Enums.DATE_TIME_FORMAT.HH_MM_A);
////                                localSimpleDateFormat.setTimeZone(TimeZone.getDefault());
//                                String localDateFormatted = localSimpleDateFormat.format(utcDate);
                                mListener.getDataTime(date);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    public interface DateTimePickerListener {
        void getDataTime(String datetime);

    }
}
