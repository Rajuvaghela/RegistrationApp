package com.xtensolution.kptt.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.xtensolution.kptt.KPTApp;
import com.xtensolution.kptt.model.ContactDetails;
import com.xtensolution.kptt.model.EmploymentDetails;
import com.xtensolution.kptt.model.Login;
import com.xtensolution.kptt.model.PersonalDetails;
import com.xtensolution.kptt.model.UploadDetails;
import com.xtensolution.kptt.model.WorkSpace;

import static com.xtensolution.kptt.utils.Constants.PREF_CONTACTDETAILS;
import static com.xtensolution.kptt.utils.Constants.PREF_EMPLOYMENTDETAILS;
import static com.xtensolution.kptt.utils.Constants.PREF_PERSONALDETAILS;
import static com.xtensolution.kptt.utils.Constants.PREF_UPLOADDETAILS;
import static com.xtensolution.kptt.utils.Constants.PREF_USER;
import static com.xtensolution.kptt.utils.Constants.PREF_WORKSPACE;


/**
 * USED FOR STORE AND GET DATA FROM PREFERENCE
 * Created by HalfBloodPrince(RIONTECH)
 * Riontech on 8/3/17.
 */
public class PreferenceHelper {
    private static final String SHARED_PREFS_NAME = KPTApp.getInstance().getPackageName();

    private static PreferenceHelper instance;

    private SharedPreferences sharedPreferences;

    public static synchronized PreferenceHelper getInstance() {
        if (instance == null) {
            instance = new PreferenceHelper();
        }

        return instance;
    }

    private PreferenceHelper() {
        instance = this;
        sharedPreferences = KPTApp.getInstance().getSharedPreferences(SHARED_PREFS_NAME,
                Context.MODE_PRIVATE);
    }

    public void delete(String key) {
        if (sharedPreferences.contains(key)) {
            getEditor().remove(key).commit();
        }
    }

    public void save(String key, Object value) {
        SharedPreferences.Editor editor = getEditor();
        if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value != null) {
            throw new RuntimeException("Attempting to save non-supported preference");
        }

        editor.commit();
    }

    public <T> T get(String key) {
        return (T) sharedPreferences.getAll().get(key);
    }

    public <T> T get(String key, T defValue) {
        T returnValue = (T) sharedPreferences.getAll().get(key);
        return returnValue == null ? defValue : returnValue;
    }

    public boolean has(String key) {
        return sharedPreferences.contains(key);
    }

    private SharedPreferences.Editor getEditor() {
        return sharedPreferences.edit();
    }

    public void saveUser(Login user) {
        save(PREF_USER, new Gson().toJson(user));
    }

    public Login getUser() {
        try {
            String userStr = get(PREF_USER);
            if (ValidationUtils.isValidString(userStr)) {
                Login user = new Gson().fromJson(userStr, Login.class);
                return user;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//
    public void savePersonalDetails(PersonalDetails personalDetails) {
        save(PREF_PERSONALDETAILS, new Gson().toJson(personalDetails));
    }

    public PersonalDetails getPersonalDetails() {
        try {
            String userStr = get(PREF_PERSONALDETAILS);
            if (ValidationUtils.isValidString(userStr)) {
                PersonalDetails personalDetails = new Gson().fromJson(userStr, PersonalDetails.class);
                return personalDetails;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveContactDetails(ContactDetails contactDetails) {
        save(PREF_CONTACTDETAILS, new Gson().toJson(contactDetails));
    }

    public ContactDetails getContactDetails() {
        try {
            String userStr = get(PREF_CONTACTDETAILS);
            if (ValidationUtils.isValidString(userStr)) {
                ContactDetails contactDetails = new Gson().fromJson(userStr, ContactDetails.class);
                return contactDetails;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveEmploymentDetails(EmploymentDetails employmentDetails) {
        save(PREF_EMPLOYMENTDETAILS, new Gson().toJson(employmentDetails));
    }

    public EmploymentDetails getEmploymentDetails() {
        try {
            String userStr = get(PREF_EMPLOYMENTDETAILS);
            if (ValidationUtils.isValidString(userStr)) {
                EmploymentDetails employmentDetails = new Gson().fromJson(userStr, EmploymentDetails.class);
                return employmentDetails;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveUploadDetails(UploadDetails uploadDetails) {
        save(PREF_UPLOADDETAILS, new Gson().toJson(uploadDetails));
    }

    public UploadDetails getUploadDetails() {
        try {
            String userStr = get(PREF_UPLOADDETAILS);
            if (ValidationUtils.isValidString(userStr)) {
                UploadDetails uploadDetails = new Gson().fromJson(userStr, UploadDetails.class);
                return uploadDetails;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


//

    public void saveWorkSpace(WorkSpace workSpace) {
        save(PREF_WORKSPACE, new Gson().toJson(workSpace));
    }

    public WorkSpace getWorkSpace() {
        try {
            String workSpaceStr = get(PREF_WORKSPACE);
            if (ValidationUtils.isValidString(workSpaceStr)) {
                WorkSpace workSpace = new Gson().fromJson(workSpaceStr, WorkSpace.class);
                return workSpace;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
