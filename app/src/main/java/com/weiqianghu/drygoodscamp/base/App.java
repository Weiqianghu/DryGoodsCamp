package com.weiqianghu.drygoodscamp.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by huweiqiang on 2016/6/27.
 */
public class App extends Application {
    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}
