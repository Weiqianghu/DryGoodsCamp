package com.weiqianghu.drygoodscamp.view.fragment;


import android.support.v4.app.Fragment;

import com.weiqianghu.drygoodscamp.common.Constant;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestFragment extends BaseCategoryFragment {
    public static final String TAG = "RestFragment";

    @Override
    protected String getCategory() {
        return Constant.CATEGORY_REST;
    }
}
