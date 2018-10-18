package com.xtensolution.core.utils;

import android.os.Bundle;
import android.text.TextUtils;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * Created by MIT on 8/19/2016.
 */
public class StringUtils {
    public static void printBundle(Bundle bundle) {
        if (bundle != null) {
            Set<String> set = bundle.keySet();
            System.out.print("Bundle  key => value ");
            for (String s : set) {
                System.out.print(s + " => " + bundle.get(s));
            }
        }
    }

    public static String toJson(Class clazz, Object object) {
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        try {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                sb.append("\"" + field.getName() + "\"").append(":")
                        .append("\"" + getFieldValue(field, object) + "\"").append(",");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        String str = sb.substring(0, sb.length() - 1);
        sb = new StringBuffer(str);
        sb.append("}");
        return sb.toString();
    }

    private static String getFieldValue(Field field, Object object) throws IllegalAccessException {
        if (field.get(object) == null)
            return "";
        return field.get(object).toString();
    }

    public static boolean isValidString(String str) {
        return str != null && !TextUtils.isEmpty(str.trim()) && str.trim().length() > 0;
    }
}
