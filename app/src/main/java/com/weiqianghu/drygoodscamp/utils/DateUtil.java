package com.weiqianghu.drygoodscamp.utils;


import android.text.format.Time;

/**
 * Created by huweiqiang on 2016/6/30.
 */
public class DateUtil {
    private static Time sTime = new Time("GMT+8");

    public static int getYear() {
        sTime.setToNow();
        return sTime.year;
    }

    public static int getMonth() {
        sTime.setToNow();
        return sTime.month;
    }

    public static int getDay() {
        sTime.setToNow();
        return sTime.monthDay;
    }

}
