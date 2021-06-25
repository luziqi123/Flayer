package com.longface.flyermodel.utils;

import android.content.SharedPreferences;

import com.longface.flyermodel.App;
import com.tencent.mmkv.MMKV;

public class Edit {

    private static MMKV mmkv;

    public static void init() {
        if (mmkv == null) {
            MMKV.initialize(App.context);
            mmkv = MMKV.defaultMMKV();
        }
    }

    public static void save(String key, Object value) {
        if (value instanceof String) {
            mmkv.putString(key, (String) value);
        } else if (value instanceof Integer) {
            mmkv.putInt(key, (Integer) value);
        } else if (value instanceof Long) {
            mmkv.putLong(key, (Long) value);
        } else if (value instanceof Float) {
            mmkv.putFloat(key, (Float) value);
        } else if (value instanceof Boolean) {
            mmkv.putBoolean(key, (Boolean) value);
        }
    }

    public SharedPreferences.Editor clear() {
        return mmkv.clear();
    }

    public static String getString(String key, String defValue) {
        return mmkv.decodeString(key, defValue);
    }

    public static int getInt(String key, int defValue) {
        return mmkv.decodeInt(key, defValue);
    }

    public static long getLong(String key, long defValue) {
        return mmkv.decodeLong(key, defValue);
    }

    public static float getFloat(String key, float defValue) {
        return mmkv.decodeFloat(key, defValue);
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return mmkv.decodeBool(key, defValue);
    }

}
