/*
 * Copyright (c) 2016 riontech-xten
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xtensolution.core.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MIT on 11-Sep-17.
 */

public class PermissionUtils {
    public static final Integer LOCATION = 0x1;
    public static final Integer CALL = 0x2;
    public static final Integer WRITE_EXST = 0x3;
    public static final Integer READ_EXST = 0x4;
    public static final Integer CAMERA = 0x5;
    public static final Integer ACCOUNTS = 0x6;
    public static final Integer INTERNET = 0x7;
    private static final Integer CODE_WENT_WRONG = 0x0;
    public static final Integer READ_PHONE_STATE = 0x7;
    private static final String TAG = PermissionUtils.class.getSimpleName();
    private OnPermissionGrantCallback mListener;
    private Activity mActivity;
    private Fragment mFragment;
    private final int PERMISSION_CODE = 7675;

    public static Map<Integer, String> getPermissionMap() {
        Map<Integer, String> permissionCodes = new HashMap<>();

        permissionCodes.put(LOCATION, Manifest.permission.ACCESS_FINE_LOCATION);
        permissionCodes.put(CALL, Manifest.permission.CALL_PHONE);
        permissionCodes.put(WRITE_EXST, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permissionCodes.put(READ_EXST, Manifest.permission.READ_EXTERNAL_STORAGE);
        permissionCodes.put(CAMERA, Manifest.permission.CAMERA);
        permissionCodes.put(ACCOUNTS, Manifest.permission.GET_ACCOUNTS);
        permissionCodes.put(INTERNET, Manifest.permission.INTERNET);
        permissionCodes.put(READ_PHONE_STATE, Manifest.permission.READ_PHONE_STATE);
        return permissionCodes;
    }

    public PermissionUtils(Activity activity, String[] permission, OnPermissionGrantCallback listener) {
        this.mActivity = activity;
        mListener = listener;
        onPermissionCheck(permission);
    }

    public PermissionUtils(Fragment fragment, String[] permission, OnPermissionGrantCallback listener) {
        this.mFragment = fragment;
        mListener = listener;
        onPermissionCheck(permission);

    }
    public static String getPermission(int statusCode) {
        Map<Integer, String> codeMap = getPermissionMap();
        try {
            if (codeMap.containsKey(statusCode))
                return codeMap.get(statusCode);
            else
                return codeMap.get(CODE_WENT_WRONG);
        } catch (Exception e) {
            return codeMap.get(CODE_WENT_WRONG);
        }
    }

    public static void askForPermission(AppCompatActivity activity, int requestCode) {
        String permission = getPermission(requestCode);
        if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(activity,
                        new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(activity,
                        new String[]{permission}, requestCode);
            }
        } else {
            //Toast.makeText(activity, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
            activity.onRequestPermissionsResult(requestCode, new String[]{permission}, new int[]{PackageManager.PERMISSION_GRANTED});
        }
    }

    public static void askFragmentPermission(AppCompatActivity activity, Fragment fragment, int requestCode) {
        String permission = getPermission(requestCode);
        if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                fragment.requestPermissions(new String[]{permission}, requestCode);

            } else {
                fragment.requestPermissions(new String[]{permission}, requestCode);
            }
        } else {
            //Toast.makeText(activity, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
            fragment.onRequestPermissionsResult(requestCode, new String[]{permission}, new int[]{PackageManager.PERMISSION_GRANTED});
        }
    }


    private void onPermissionCheck(String[] permission) {
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> reqPermissions = requestForPermission(permission);
            if (reqPermissions != null && reqPermissions.size() > 0) {
                if (mActivity != null) {
                    ActivityCompat.requestPermissions(mActivity, reqPermissions
                            .toArray(new String[reqPermissions.size()]), PERMISSION_CODE);
                } else {
                    mFragment.requestPermissions(reqPermissions
                            .toArray(new String[reqPermissions.size()]), PERMISSION_CODE);
                }
            } else {
                if (mListener != null) {
                    mListener.onPermissionGranted();
                }
            }
        } else {
            if (mListener != null) {
                mListener.onPermissionGranted();
            }
        }
    }

    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        try {
            boolean isGranted = true;
            String missedPermission = "";

            if (grantResults != null && permissions != null) {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        Log.d(TAG, "Not Granted Permission is == > " + grantResults[i]);
                        missedPermission = permissions[i];
                        isGranted = false;
                        break;
                    }
                }
            }

            if (isGranted) {
                if (mListener != null)
                    mListener.onPermissionGranted();
            } else {
                if (mListener != null)
                    mListener.onPermissionError(missedPermission);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (mListener != null) {
                mListener.onPermissionError(e.getMessage());
            }
        }
    }

    private List<String> requestForPermission(String[] permission) {
        List<String> reqPermission = new ArrayList<>();
        for (int i = 0; i < permission.length; i++) {
            if (mActivity != null) {
                if (mActivity.checkSelfPermission(permission[i])
                        == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "Permission is granted ==> " + permission[i]);
                } else {
                    reqPermission.add(permission[i]);
                }
            } else {
                if (ContextCompat.checkSelfPermission(mFragment.getActivity(),
                        permission[i])
                        == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "Permission is granted ==> " + permission[i]);
                } else {
                    reqPermission.add(permission[i]);
                }
            }

        }
        return reqPermission;
    }


    public interface OnPermissionGrantCallback {

        void onPermissionGranted();

        void onPermissionError(String permission);

    }
}
