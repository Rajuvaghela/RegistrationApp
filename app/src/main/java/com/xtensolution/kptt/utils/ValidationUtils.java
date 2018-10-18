package com.xtensolution.kptt.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import android.widget.Spinner;

import com.xtensolution.core.utils.AppLog;

import java.util.List;

/**
 * VALIDATION UTILS USED FOR SOME BASIC VALIDATION
 */

public class ValidationUtils {
    private static final String TAG = ValidationUtils.class.getSimpleName();
    private static final String ERROR_TEXT = " is required.";
    private static final String MOBILE_NUMBER_LENGTH_ERROR = "Mobile number is minimum 10 digit above.";
    private static final String PASSWORD_ERROR_TEXT = "Password must be between 6 to 30 characters.";
    private static final String EMAIL_ERROR_TEXT = "Please enter valid email address.";
    private static final String EMAIL = "Email";
    private static final String PASSWORD = "Password";
    private static final String MOBILE = "Mobile";
    private static final int PASSWORD_MIN_LENGTH = 6;
    private static final int PASSWORD_MAX_LENGTH = 30;
    private static final String MINMUM_LENGTH_ERROR = "  is must be above 3 character.";
    private static final String HOURS = "Hour";
    private static final String HOUR_ERROR_TEXT = "Please enter valid hour.";

    private static final String GST_NUMBER_LENGTH_ERROR = "Gst number is minimum 15 digit length";
    private static final String GSTNO = "GstNo";
//

    /**
     * Check String is Valid or Not
     *
     * @param string
     * @return
     */
    public static boolean isValidString(String string) {
        try {
            if (string != null && string.length() > 0 && !string.isEmpty())
                return true;
            else
                return false;
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
            return false;
        }
    }

    /**
     * get String from edit text
     *
     * @param editText
     * @return
     */
    public static String getString(EditText editText) {
        try {
            if (editText != null) {
                if (isValidString(editText.getText().toString()))
                    return editText.getText().toString();
                else
                    return "";
            } else {
                AppLog.d(TAG, "Edit text is null");
                return "";
            }
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
            return "";
        }
    }

    /**
     * get String from edit text and check valid or not
     *
     * @param editText
     * @return
     */
    public static boolean isValidString(EditText editText, String hint) {
        try {
            hint.replace('*', ' ');
            if (editText != null)
                if (isValidString(getString(editText))) {
//                    if (getString(editText).length() >= 3) {
                    editText.setError(null);
                    return true;
//                    } else {
//                        editText.setError(hint + MINMUM_LENGTH_ERROR);
//                        editText.requestFocus();
//                        return false;
//                    }
                } else {
                    editText.setError(hint + ERROR_TEXT);
                    editText.requestFocus();
                    return false;
                }
            else {
                AppLog.d(TAG, "Edit text is null");
                return false;
            }
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
            return false;
        }
    }

    /**
     * check list is valid or not
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> boolean isValidList(List<T> list) {
        try {
            if (list != null && !list.isEmpty() && list.size() > 0)
                return true;
            else
                return false;
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
            return false;
        }
    }

    /**
     * check object valid or not
     *
     * @param object
     * @param <T>
     * @return
     */
    public static <T> boolean isValidObject(T object) {
        try {
            if (object != null)
                return true;
            else
                return false;
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
            return false;
        }
    }


    /**
     * password validation for check is valid or not
     * also check its length above minimum character
     *
     * @param editText
     * @return
     */
    public static boolean isValidPassword(EditText editText) {
        try {
            if (isValidObject(editText)) {

                if (isValidString(editText, PASSWORD)) {

                    editText.setError(null);
                    if (getString(editText).length() >= PASSWORD_MIN_LENGTH
                            && getString(editText).length() <= PASSWORD_MAX_LENGTH) {
                        return true;
                    } else {
                        editText.requestFocus();
                        editText.setError(PASSWORD_ERROR_TEXT);
                        return false;
                    }

                } else {
                    AppLog.d(TAG, "isValidPassword hint" + editText.getHint());
                    editText.setError(PASSWORD + ERROR_TEXT);
                    return false;

                }
            } else {
                AppLog.d(TAG, "Edit text is null");
                return false;
            }
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
            return false;
        }
    }

    /**
     * Check Internet Available or Not
     */
    public static boolean checkInternetConnection(final Context context) {
        try {
            final ConnectivityManager conMgr = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
            return false;
        }
    }

    public static boolean isValidEmail(EditText editText) {
        try {
            if (isValidObject(editText)) {
                if (isValidString(editText, EMAIL)) {
                    if (android.util.Patterns.EMAIL_ADDRESS.matcher(getString(editText))
                            .matches()) {
                        return true;
                    } else {
                        editText.requestFocus();
                        editText.setError(EMAIL_ERROR_TEXT);
                        return false;
                    }
                } else {
                    editText.requestFocus();
                    editText.setError(EMAIL + ERROR_TEXT);
                    return false;
                }
            } else {
                AppLog.d(TAG, "Edit text is null.");
                return false;
            }
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
            return false;
        }
    }

    public static boolean isValidateGender(Spinner spinner) {
        try {
            if (spinner != null && spinner.getSelectedItemPosition() != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
            return false;
        }
    }

    public static boolean isValidMobileNumber(EditText editText) {
        try {
            if (editText != null) {
                if (isValidString(getString(editText))) {
                    if (getString(editText).length() >= 10) {
                        return true;
                    } else {
                        editText.requestFocus();
                        editText.setError(MOBILE_NUMBER_LENGTH_ERROR);
                        return false;
                    }
                } else {
                    editText.requestFocus();

                    editText.setError(MOBILE + ERROR_TEXT);
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

    //Gst validation

    public static boolean isValidGstNo(EditText editText) {
        try {
            if (editText != null) {
                if (isValidString(getString(editText))) {
                    if (getString(editText).length() >= 15) {
                        return true;
                    } else {
                        editText.requestFocus();
                        editText.setError(GST_NUMBER_LENGTH_ERROR);
                        return false;
                    }
                } else {
                    editText.requestFocus();

                    editText.setError(GSTNO + ERROR_TEXT);
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


    //Mobilevalidation

    public static boolean isValidMobileNo(TextInputLayout textInputLayout,EditText editText) {
        try {
            if (textInputLayout != null) {
                if (isValidString(getString(editText))) {
                    if (getString(editText).length() >= 10) {
                        return true;
                    } else {
                        textInputLayout.requestFocus();
                        textInputLayout.setError(MOBILE_NUMBER_LENGTH_ERROR);
                        return false;
                    }
                } else {
                    textInputLayout.requestFocus();

                    textInputLayout.setError(MOBILE + ERROR_TEXT);
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
    /**
     * get String from edit text
     *
     * @param string
     * @return
     */
    public static String getValidString(String string) {
        try {
            if (isValidString(string)) {
                return string;
            } else {
                AppLog.d(TAG, "string text is null");
                return "";
            }
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
            return "";
        }
    }


    /**
     * get String from edit text and check valid or not
     *
     * @param editText
     * @return
     */
    public static boolean isValidString(TextInputLayout layout, EditText editText, String hint) {
        try {
//            editText.getBackground().mutate().setColorFilter(
//                    ContextCompat.getColor(getContext(), R.color.white),
//                    PorterDuff.Mode.SRC_ATOP);
            layout.setErrorEnabled(true);
//            layout.setBackgroundColor(ResourceUtils.getColor(editText.getContext(), R.color.sep));
//            editText.setBackgroundResource(R.drawable.edt_selector);
            if (layout != null)
                if (isValidString(getString(editText))) {
                    layout.setError(null);
                    return true;
                } else {
                    layout.setError(hint + ERROR_TEXT);
                    return false;
                }
            else {
                AppLog.d(TAG, "Edit text is null");
                return false;
            }
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
            return false;
        }
    }


    public static boolean isValidHours(EditText editText) {
        try {
            if (isValidObject(editText)) {

                if (isValidString(editText, HOURS)) {

                    editText.setError(null);
                    String hourText = editText.getText().toString();
                    Float hour = Float.valueOf(hourText);
                    if (hour > 00.00f && hour <= 24.00f) {
                        return true;
                    } else {
                        editText.requestFocus();
                        editText.setError(HOUR_ERROR_TEXT);
                        return false;
                    }

                } else {
                    AppLog.d(TAG, "isValidPassword hint" + editText.getHint());
                    editText.setError(HOURS + ERROR_TEXT);
                    return false;

                }
            } else {
                AppLog.d(TAG, "Edit text is null");
                return false;
            }
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
            return false;
        }
    }
}
