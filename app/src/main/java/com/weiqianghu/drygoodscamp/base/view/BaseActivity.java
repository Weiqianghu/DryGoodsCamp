package com.weiqianghu.drygoodscamp.base.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.weiqianghu.drygoodscamp.utils.ToastUtil;


public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void showToast(String msg) {
        ToastUtil.show(msg);
    }
}
