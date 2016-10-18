package com.weiqianghu.drygoodscamp.view.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.weiqianghu.drygoodscamp.R;
import com.weiqianghu.drygoodscamp.base.imageloader.ImageLoader;
import com.weiqianghu.drygoodscamp.base.view.BaseActivity;
import com.weiqianghu.drygoodscamp.entity.DryGoods;
import com.weiqianghu.drygoodscamp.presenter.WelfarePresenter;
import com.weiqianghu.drygoodscamp.utils.DimensUtil;
import com.weiqianghu.drygoodscamp.view.view.IWelfareView;

public class SplashActivity extends BaseActivity implements IWelfareView {

    private ImageView mIvWelcome;
    private WelfarePresenter mWelfarePresenter;
    private TextView mTvAppName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
        initData();
        mIvWelcome.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        }, 2000);
    }

    private void initData() {
        mWelfarePresenter = new WelfarePresenter(this);
        mWelfarePresenter.loadWelfare();
    }

    private void initView() {
        mIvWelcome = (ImageView) findViewById(R.id.iv_welcome);
        mTvAppName = (TextView) findViewById(R.id.tv_app_name);
        ObjectAnimator animator = ObjectAnimator.
                ofFloat(mTvAppName, "TextSize",
                        DimensUtil.dip2px(24),
                        DimensUtil.dip2px(40));
        animator.setDuration(2000);
        animator.start();
    }

    @Override
    public void showWelfareImg(DryGoods dryGoods) {
        ImageLoader.displayImg(mIvWelcome, dryGoods.url, R.mipmap.head, R.mipmap.head);
    }
}
