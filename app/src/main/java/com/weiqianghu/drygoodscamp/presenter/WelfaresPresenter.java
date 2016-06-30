package com.weiqianghu.drygoodscamp.presenter;

import com.weiqianghu.drygoodscamp.base.http.CallBack;
import com.weiqianghu.drygoodscamp.base.http.HttpResult;
import com.weiqianghu.drygoodscamp.biz.WelfareBiz;
import com.weiqianghu.drygoodscamp.biz.impl.WelfareBizImpl;
import com.weiqianghu.drygoodscamp.entity.DryGoods;
import com.weiqianghu.drygoodscamp.view.view.IWelfaresView;

/**
 * Created by huweiqiang on 2016/6/29.
 */
public class WelfaresPresenter {

    private WelfareBiz mWelfareBiz;
    private IWelfaresView mWelfaresView;

    public WelfaresPresenter(IWelfaresView welfaresView) {
        mWelfaresView = welfaresView;
        mWelfareBiz = new WelfareBizImpl();
    }

    public void initWelfares(int pageSize) {
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
                    mWelfaresView.refreshListView(welfareHttpResult.results);
                }
            }
        };
        mWelfareBiz.loadData(1, pageSize, callBack);
    }

    public void loadWelfareS(int page, int pageSize) {
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
                    mWelfaresView.updateListView(welfareHttpResult.results);
                }
            }
        };
        mWelfareBiz.loadData(page, pageSize, callBack);
    }

}
