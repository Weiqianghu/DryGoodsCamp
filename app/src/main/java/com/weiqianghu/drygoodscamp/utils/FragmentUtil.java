package com.weiqianghu.drygoodscamp.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.weiqianghu.drygoodscamp.R;

/**
 * Created by 胡伟强 on 2016/2/3.
 */
public class FragmentUtil {
    public static void switchContentAddToBackStack(Fragment from, Fragment to, int container, FragmentManager manager, String tag) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(
                R.anim.push_left_in,
                R.anim.push_left_out,
                R.anim.push_right_in,
                R.anim.push_right_out);

        if (!to.isAdded()) {
            transaction.hide(from);
            transaction.add(container, to, tag);
            transaction.addToBackStack(null);
            transaction.commit();
        } else {
            transaction.hide(from);
            transaction.show(to);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    public static void switchContent(Fragment from, Fragment to, int container, FragmentManager manager) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(
                R.anim.push_left_in,
                R.anim.push_left_out,
                R.anim.push_right_in,
                R.anim.push_right_out);

        if (!to.isAdded()) {
            transaction.hide(from);
            transaction.add(container, to);
            transaction.addToBackStack(null);
            transaction.commit();
        } else {
            transaction.hide(from);
            transaction.show(to);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    public static void addContent(int container, Fragment content, FragmentManager manager, String tag) {
        FragmentTransaction ft = manager.beginTransaction();
        ft.setCustomAnimations(
                R.anim.push_left_in,
                R.anim.push_left_out,
                R.anim.push_right_in,
                R.anim.push_right_out);
        ft.replace(container, content, tag);
        ft.commit();
    }

    public static void addContentNoAnim(int container, Fragment content, FragmentManager manager, String tag) {
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(container, content, tag);
        ft.commit();
    }


}
