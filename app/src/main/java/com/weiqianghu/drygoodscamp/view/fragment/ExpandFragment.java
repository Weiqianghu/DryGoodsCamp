package com.weiqianghu.drygoodscamp.view.fragment;


import android.support.v4.app.Fragment;

import com.weiqianghu.drygoodscamp.common.Constant;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExpandFragment extends BaseCategoryFragment {
    public static final String TAG = "ExpandFragment";

    @Override
    protected String getCategory() {
        return Constant.CATEGORY_EXPAND;
    }
}
