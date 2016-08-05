package com.weiqianghu.drygoodscamp.widget.listener;

import android.util.Log;
import android.view.animation.Animation;

/**
 * Created by huchangfu on 2015/7/23.
 * Email:huchangfu@lvmama.com.
 * Desc:普通动画的监听
 * Version:7.2.0
 */
public class SimpleAnimationListener implements Animation.AnimationListener {
    private static final String TAG = "SimpleAnimationListener";

    @Override
    public void onAnimationStart(Animation animation) {
        Log.d(TAG,"onAnimationStart");
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Log.d(TAG,"onAnimationEnd");
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        Log.d(TAG,"onAnimationRepeat");
    }
}
