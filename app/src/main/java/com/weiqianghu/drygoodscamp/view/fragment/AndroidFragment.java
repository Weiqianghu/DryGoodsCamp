package com.weiqianghu.drygoodscamp.view.fragment;


import android.support.v4.app.Fragment;

import com.weiqianghu.drygoodscamp.common.Constant;

/**
 * A simple {@link Fragment} subclass.
 */
public class AndroidFragment extends BaseCategoryFragment {
    public static final String TAG = "AndroidFragment";

    @Override
    protected String getCategory() {
        return Constant.CATEGORY_ANDROID;
    }
}
