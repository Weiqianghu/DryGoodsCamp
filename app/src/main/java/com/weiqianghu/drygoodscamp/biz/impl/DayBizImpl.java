package com.weiqianghu.drygoodscamp.biz.impl;

import com.weiqianghu.drygoodscamp.base.Biz.BaseBiz;
import com.weiqianghu.drygoodscamp.base.http.CallBack;
import com.weiqianghu.drygoodscamp.biz.DayBiz;
import com.weiqianghu.drygoodscamp.biz.handler.DiskDayHandler;
import com.weiqianghu.drygoodscamp.biz.service.TodayRecommendService;

/**
 * Created by huweiqiang on 2016/6/30.
 */
public class DayBizImpl extends BaseBiz<TodayRecommendService> implements DayBiz {
    private static final String TAG = "DayBizImpl";

    @Override
    public void loadData(CallBack callBack) {
        new DiskDayHandler().handleRequest(callBack);
    }
}
