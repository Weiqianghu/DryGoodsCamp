package com.weiqianghu.drygoodscamp.presenter;

import com.weiqianghu.drygoodscamp.base.http.CallBack;
import com.weiqianghu.drygoodscamp.base.http.HttpResult;
import com.weiqianghu.drygoodscamp.biz.WelfareBiz;
import com.weiqianghu.drygoodscamp.biz.impl.WelfareBizImpl;
import com.weiqianghu.drygoodscamp.entity.DryGoods;
import com.weiqianghu.drygoodscamp.view.view.IWelfareView;

/**
 * Created by huweiqiang on 2016/6/29.
 */
public class WelfarePresenter {

    private WelfareBiz mWelfareBiz;
    private IWelfareView mWelfareView;

    public WelfarePresenter(IWelfareView welfareView) {
        mWelfareView = welfareView;
        mWelfareBiz = new WelfareBizImpl();
    }

    public void loadWelfare() {
        CallBack<HttpResult<DryGoods>> callBack = new CallBack<HttpResult<DryGoods>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(HttpResult<DryGoods> welfareHttpResult) {
                if (welfareHttpResult.results.size() > 0) {
                    mWelfareView.showWelfareImg(welfareHttpResult.results.get(0));
                }
            }
        };
        mWelfareBiz.loadData(1, 1, callBack);
    }
}
