package com.weiqianghu.drygoodscamp.utils;


import android.text.format.Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        return sTime.month + 1;
    }

    public static int getDay() {
        sTime.setToNow();
        return sTime.monthDay;
    }

    public static final Date forMat(String str) {
        SimpleDateFormat sp = new SimpleDateFormat("yy-MM-dd");
        try {
            return sp.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

}
