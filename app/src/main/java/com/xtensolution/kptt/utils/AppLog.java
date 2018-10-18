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

package com.xtensolution.kptt.utils;

import android.util.Log;

public class AppLog {
	private static boolean debug = true;

	public static void e(String tag, String message) {
		if (debug) {
			Log.e(tag, message);
		}
	}

	public static void e(String tag, String message, Exception e) {
		if (debug) {
			Log.e(tag, message, e);
		}
	}

	public static void w(String tag, String message) {
		if (debug) {
			Log.w(tag, message);
		}
	}

	public static void d(String tag, String message) {
		if (debug) {
			Log.d(tag, message);
		}
	}

	public static void i(String tag, String message) {
		if (debug) {
			Log.i(tag, message);
		}
	}

	public static void wtf(String tag, String message) {
		if (debug) {
			Log.wtf(tag, message);
		}
	}

	public static void wtf(String tag, String message, Exception e) {
		if (debug) {
			Log.wtf(tag, message, e);
		}
	}

	public static void e(String tag, int value) {
		AppLog.e(tag, String.valueOf(value));
	}

	public static void e(String tag, float value) {
		AppLog.e(tag, String.valueOf(value));
	}

	public static void e(String tag, double value) {
		AppLog.e(tag, String.valueOf(value));
	}

	public static void e(String tag, boolean value) {
		AppLog.e(tag, String.valueOf(value));
	}

	public static void w(String tag, int value) {
		AppLog.w(tag, String.valueOf(value));
	}

	public static void w(String tag, float value) {
		AppLog.w(tag, String.valueOf(value));
	}

	public static void w(String tag, double value) {
		AppLog.w(tag, String.valueOf(value));
	}

	public static void w(String tag, boolean value) {
		AppLog.w(tag, String.valueOf(value));
	}

	public static void d(String tag, int value) {
		AppLog.d(tag, String.valueOf(value));
	}

	public static void d(String tag, float value) {
		AppLog.d(tag, String.valueOf(value));
	}

	public static void d(String tag, double value) {
		AppLog.d(tag, String.valueOf(value));
	}

	public static void d(String tag, boolean value) {
		AppLog.d(tag, String.valueOf(value));
	}

	public static void i(String tag, int value) {
		AppLog.i(tag, String.valueOf(value));
	}

	public static void i(String tag, float value) {
		AppLog.i(tag, String.valueOf(value));
	}

	public static void i(String tag, double value) {
		AppLog.i(tag, String.valueOf(value));
	}

	public static void i(String tag, boolean value) {
		AppLog.i(tag, String.valueOf(value));
	}

	public static void wtf(String tag, int value) {
		AppLog.wtf(tag, String.valueOf(value));
	}

	public static void wtf(String tag, float value) {
		AppLog.wtf(tag, String.valueOf(value));
	}

	public static void wtf(String tag, double value) {
		AppLog.wtf(tag, String.valueOf(value));
	}

	public static void wtf(String tag, boolean value) {
		AppLog.wtf(tag, String.valueOf(value));
	}

	public static void enableLogging() {
		debug = true;
	}
}