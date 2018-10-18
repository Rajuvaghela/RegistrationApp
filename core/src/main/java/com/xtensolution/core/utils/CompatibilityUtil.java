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

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;


public class CompatibilityUtil {

//	/**
//	 * Enables strict mode. This should only be called when debugging the
//	 * application and is useful for finding some potential bugs or best
//	 * practice violations.
//	 */
//	public static void enableStrictMode() {
//		// Strict mode is only available on gingerbread or later
//		if (isGingerbread()) {
//
//			// Enable all thread strict mode policies
//			StrictMode.ThreadPolicy.Builder threadPolicyBuilder = new StrictMode.ThreadPolicy.Builder()
//					.detectAll().penaltyLog();
//
//			// Enable all VM strict mode policies
//			StrictMode.VmPolicy.Builder vmPolicyBuilder = new StrictMode.VmPolicy.Builder()
//					.detectAll().penaltyLog();
//
//			// Honeycomb introduced some additional strict mode features
//			if (isHoneycomb()) {
//				// Flash screen when thread policy is violated
//				threadPolicyBuilder.penaltyFlashScreen();
//				// For each activity class, set an instance limit of member1. Any more
//				// instances and
//				// there could be a memory leak.
//				vmPolicyBuilder.setClassInstanceLimit(
//						AlbumActivity.class, member1);
//			}
//
//			// Use builders to enable strict mode policies
//			StrictMode.setThreadPolicy(threadPolicyBuilder.build());
//			StrictMode.setVmPolicy(vmPolicyBuilder.build());
//		}
//	}

	/** Get the current Android API level. */
	public static int getSdkVersion() {
		return Build.VERSION.SDK_INT;
	}

	/** Determine if the device is running API level 8 or higher. */
	public static boolean isFroyo() {
		return getSdkVersion() >= Build.VERSION_CODES.FROYO;
	}

	/** Determine if the device is running API level 11 or higher. */
	public static boolean isHoneycomb() {
		return getSdkVersion() >= Build.VERSION_CODES.HONEYCOMB;
	}

	/** Determine if the device is running API level 21 or higher. */
	public static boolean isLollipop() {
		return getSdkVersion() >= Build.VERSION_CODES.LOLLIPOP;
	}

	/**
	 * Determine if the device is a tablet (i.e. it has a large screen).
	 * 
	 * @param context
	 *            The calling context.
	 */
	public static boolean isTablet(Context context) {
		return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}

	/**
	 * Determine if the device is a HoneyComb tablet.
	 * 
	 * @param context
	 *            The calling context.
	 */
	public static boolean isHoneycombTablet(Context context) {
		return isHoneycomb() && isTablet(context);
	}

	/** This class can't be instantiated. */
	private CompatibilityUtil() {
	}

	/**
	 * Uses static final constants to detect if the device's platform version is
	 * Gingerbread or later.
	 */
	public static boolean isGingerbread() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
	}
	
	/**
     * Uses static final constants to detect if the device's platform version is ICS or
     * later.
     */
    public static boolean isICS() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }
    
    /**
     * Uses static final constants to detect if the device's platform version is ICS or
     * later.
     */
    public static boolean isJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }
}
