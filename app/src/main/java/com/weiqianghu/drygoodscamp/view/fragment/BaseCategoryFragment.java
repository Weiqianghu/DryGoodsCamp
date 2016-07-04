package com.weiqianghu.drygoodscamp.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.weiqianghu.drygoodscamp.R;
import com.weiqianghu.drygoodscamp.base.view.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseCategoryFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_category;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        initView(savedInstanceState);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    protected abstract String getCategory();

}
