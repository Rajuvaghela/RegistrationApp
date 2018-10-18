package com.xtensolution.kptt.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.xtensolution.kptt.model.GST;
import com.xtensolution.kptt.ui.activity.SplashActivity;
import com.xtensolution.kptt.ui.activity.ThankYouActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Test on 11/8/2017.
 */

public class AppUtils {
    public static List<GST> getGstCodes() {
        List<GST> list = new ArrayList<>();
        GST gst15 = new GST(1, "Jammu and Kashmir", "JK", 1);
        GST gst14 = new GST(2, "Himachal Pradesh", "HP", 2);
        GST gst28 = new GST(3, "Punjab", "PB", 3);
        GST gst6 = new GST(4, "Chandigarh", "CH", 4);
        GST gst35 = new GST(5, "Uttarakhand", "UT", 5);
        GST gst13 = new GST(6, "Haryana", "HR", 6);
        GST gst10 = new GST(7, "Delhi", "DL", 7);
        GST gst29 = new GST(8, "Rajasthan", "RJ", 8);
        GST gst34 = new GST(9, "Uttar Pradesh", "UP", 9);
        GST gst5 = new GST(10, "Bihar", "BR", 10);
        GST gst30 = new GST(11, "Sikkim", "SK", 11);
        GST gst3 = new GST(12, "Arunachal Pradesh", "AR", 12);
        GST gst25 = new GST(13, "Nagaland", "NL", 13);
        GST gst22 = new GST(14, "Manipur", "MN", 14);
        GST gst24 = new GST(15, "Mizoram", "MI", 15);
        GST gst33 = new GST(16, "Tripura", "TR", 16);
        GST gst23 = new GST(17, "Meghalaya", "ME", 17);
        GST gst4 = new GST(18, "Assam", "AS", 18);
        GST gst36 = new GST(19, "West Bengal", "WB", 19);
        GST gst16 = new GST(20, "Jharkhand", "JH", 20);
        GST gst26 = new GST(21, "Odisha", "OR", 21);
        GST gst7 = new GST(22, "Chattisgarh", "CT", 22);
        GST gst20 = new GST(23, "Madhya Pradesh", "MP", 23);
        GST gst12 = new GST(24, "Gujarat", "GJ", 24);
        GST gst9 = new GST(25, "Daman and Diu", "DD", 25);
        GST gst8 = new GST(26, "Dadra and Nagar Haveli", "DN", 26);
        GST gst21 = new GST(27, "Maharashtra", "MH", 27);
        GST gst2 = new GST(28, "Andhra Pradesh", "AD", 28);
        GST gst17 = new GST(29, "Karnataka", "KA", 29);
        GST gst11 = new GST(30, "Goa", "GA", 30);
        GST gst19 = new GST(31, "Lakshadweep Islands", "LD", 31);
        GST gst18 = new GST(32, "Kerala", "KL", 32);
        GST gst31 = new GST(33, "Tamil Nadu", "TN", 33);
        GST gst27 = new GST(34, "Pondicherry", "PY", 34);
        GST gst1 = new GST(35, "Andaman and Nicobar Islands", "AN", 35);
        GST gst32 = new GST(36, "Telangana", "TS", 36);
        GST gst37 = new GST(37, "Andhra Pradesh New", "AD", 37);

        list.add(gst1);
        list.add(gst2);
        list.add(gst3);
        list.add(gst4);
        list.add(gst5);
        list.add(gst6);
        list.add(gst7);
        list.add(gst8);
        list.add(gst9);
        list.add(gst10);
        list.add(gst11);
        list.add(gst12);
        list.add(gst13);
        list.add(gst14);
        list.add(gst15);
        list.add(gst16);
        list.add(gst17);
        list.add(gst18);
        list.add(gst19);
        list.add(gst20);
        list.add(gst21);
        list.add(gst22);
        list.add(gst23);
        list.add(gst23);
        list.add(gst24);
        list.add(gst25);
        list.add(gst26);
        list.add(gst27);
        list.add(gst28);
        list.add(gst29);
        list.add(gst30);
        list.add(gst31);
        list.add(gst32);
        list.add(gst33);
        list.add(gst34);
        list.add(gst35);
        list.add(gst36);
        list.add(gst37);
        return list;
    }

    public static String getGstCodeOfState(String state) {
        for (GST gst : getGstCodes()) {
            if (state.equalsIgnoreCase(gst.getState()))
                return gst.getCode();
        }
        return null;
    }

    public static String getGstCodeOfStateByNo(int no) {
        for (GST gst : getGstCodes()) {
            if (no == gst.getNo())
                return gst.getCode();
        }
        return null;
    }

    public static String getFiscalYear() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = (Calendar.getInstance().get(Calendar.MONTH) + 1);

        String financiyalYearFrom;
        String financiyalYearTo;
        if (currentMonth < 4) {
            /*financiyalYearFrom = "01-04-" + (CurrentYear - 1);
            financiyalYearTo = "31-03-" + (CurrentYear);*/

            financiyalYearFrom = "" + (currentYear - 1);
            financiyalYearTo = "" + currentYear;
        } else {
            /*financiyalYearFrom = "01-04-" + (CurrentYear);
            financiyalYearTo = "31-03-" + (CurrentYear + 1);*/

            financiyalYearFrom = "" + (currentYear);
            financiyalYearTo = "" + (currentYear + 1);
        }

        return financiyalYearFrom + "-" + financiyalYearTo;
    }

    public static void onLogoutEvent(Context ctx) {
        PreferenceHelper.getInstance().delete(Constants.PREF_USER);
        Intent intent = new Intent(ctx,
                SplashActivity.class);

        intent.addCategory(Intent.CATEGORY_HOME);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NO_HISTORY);
        ctx.startActivity(intent);
    }

    public static void onSubmitDataClear(Context ctx) {
        PreferenceHelper.getInstance().delete(Constants.PREF_PERSONALDETAILS);
        PreferenceHelper.getInstance().delete(Constants.PREF_CONTACTDETAILS);
        PreferenceHelper.getInstance().delete(Constants.PREF_EMPLOYMENTDETAILS);
        PreferenceHelper.getInstance().delete(Constants.PREF_UPLOADDETAILS);
        Intent intent = new Intent(ctx,
                ThankYouActivity.class);

        intent.addCategory(Intent.CATEGORY_HOME);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NO_HISTORY);
        ctx.startActivity(intent);
    }



    /**
     * @param context
     * @param activity
     */
    public static void hideKeyboard(Context context, Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) context.
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }
}