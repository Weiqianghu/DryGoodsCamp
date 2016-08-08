package com.weiqianghu.drygoodscamp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.weiqianghu.drygoodscamp.R;
import com.weiqianghu.drygoodscamp.base.view.App;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by huweiqiang on 2016/7/6.
 */
public class SPUtil {
    private static final String TAG = "SPUtil";

    public static void save(String key, String value) {
        SharedPreferences sharedPref = getSp();

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void save(String key, long value) {
        SharedPreferences sharedPref = getSp();

        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putLong(key, value);
        editor.commit();

        Log.d(TAG, "save: " + value);
    }

    public static void save(String key, Set<String> set) {
        SharedPreferences sp = getSp();
        SharedPreferences.Editor editor = sp.edit();
        editor.putStringSet(key, set);
        editor.apply();

        Log.d(TAG, "save: " + set);
    }

    public static void clear(String key) {
        SharedPreferences sp = getSp();
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
        editor.apply();
    }

    public static String readStr(String key) {
        SharedPreferences sharedPref = getSp();
        return sharedPref.getString(key, "");
    }

    public static long readLong(String key) {
        SharedPreferences sharedPref = getSp();
        Log.d(TAG, "readLong: " + sharedPref.getLong(key, -1));
        return sharedPref.getLong(key, -1);
    }

    public static Set<String> readSet(String key) {
        SharedPreferences preferences = getSp();
        Log.d(TAG, "readSet: " + preferences.getStringSet(key, new HashSet<String>()));
        return preferences.getStringSet(key, new HashSet<String>());
    }

    private static SharedPreferences getSp() {
        return App.getContext().getSharedPreferences(
                App.getContext().getString(R.string.preference_file_key), Context.MODE_PRIVATE);

    }
}
