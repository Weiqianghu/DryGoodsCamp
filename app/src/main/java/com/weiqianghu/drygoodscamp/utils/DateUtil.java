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

    public static final Date parse(String str) {
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sp.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static String format(Date date) {
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
        return sp.format(date);
    }

    public static long today() {
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.HOUR,0);
//        cal.set(Calendar.SECOND,0);
//        cal.set(Calendar.MINUTE,0);
//        cal.set(Calendar.MILLISECOND,0);
//
//

        return parse(format(new Date())).getTime();
    }

    public static Date parse(long time) {
        return new Date(time);
    }

}
