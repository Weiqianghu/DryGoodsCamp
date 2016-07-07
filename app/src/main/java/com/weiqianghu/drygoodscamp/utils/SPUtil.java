package com.weiqianghu.drygoodscamp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.weiqianghu.drygoodscamp.R;
import com.weiqianghu.drygoodscamp.base.view.App;

/**
 * Created by huweiqiang on 2016/7/6.
 */
public class SPUtil {
    private static SharedPreferences sSharedPreferences;

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
    }

    public static String readStr(String key) {
        SharedPreferences sharedPref = getSp();
        return sharedPref.getString(key, "");
    }

    public static long readLong(String key) {
        SharedPreferences sharedPref = getSp();
        return sharedPref.getLong(key, 0);
    }

    private static SharedPreferences getSp() {
        if (sSharedPreferences == null) {
            sSharedPreferences = App.getContext().getSharedPreferences(
                    App.getContext().getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        }
        return sSharedPreferences;
    }
}
