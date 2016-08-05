package com.weiqianghu.drygoodscamp.presenter;

import android.content.Context;
import android.util.Log;

import com.weiqianghu.drygoodscamp.base.http.CallBack;
import com.weiqianghu.drygoodscamp.base.http.HttpResult;
import com.weiqianghu.drygoodscamp.biz.HistoryBiz;
import com.weiqianghu.drygoodscamp.biz.impl.HistoryBizImpl;
import com.weiqianghu.drygoodscamp.entity.HistoryDryGoods;
import com.weiqianghu.drygoodscamp.view.HistoryView;


/**
 * Created by huweiqiang on 2016/6/27.
 */
public class HistoryPresenter {
    private static final String TAG = "HistoryPresenter";
    private static final int PAGE_SIZE = 10;

    private Context mContext;
    private HistoryBiz mHistoryBiz;
    private HistoryView mHistoryView;

    public HistoryPresenter(Context context, HistoryView historyView) {
        mContext = context;
        mHistoryView = historyView;
        mHistoryBiz = new HistoryBizImpl();
    }

    public void loadHistopryDry(int page) {
        CallBack<HttpResult<HistoryDryGoods>> callBack = new CallBack<HttpResult<HistoryDryGoods>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onNext(HttpResult<HistoryDryGoods> dryDataHttpResult) {
                Log.d(TAG, "onNext: " + dryDataHttpResult);
            }
        };

        mHistoryBiz.loadDryData(page, PAGE_SIZE, callBack);
    }
}
