package com.weiqianghu.drygoodscamp.utils;

import android.widget.Toast;

import com.weiqianghu.drygoodscamp.base.view.App;


/**
 * Created by huweiqiang on 2016/6/27.
 */
public class ToastUtil {
    public static void show(String msg) {
        Toast.makeText(App.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void show(int msg) {
        Toast.makeText(App.getContext(), msg + "", Toast.LENGTH_SHORT).show();
    }

    public static void show(Object msg) {
        Toast.makeText(App.getContext(), msg.toString(), Toast.LENGTH_SHORT).show();
    }
}
