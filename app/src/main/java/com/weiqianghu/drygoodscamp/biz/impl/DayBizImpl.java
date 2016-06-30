package com.weiqianghu.drygoodscamp.biz.impl;

import android.util.Log;

import com.weiqianghu.drygoodscamp.base.Biz.BaseBiz;
import com.weiqianghu.drygoodscamp.base.http.ApiProvider;
import com.weiqianghu.drygoodscamp.base.http.CallBack;
import com.weiqianghu.drygoodscamp.biz.DayBiz;
import com.weiqianghu.drygoodscamp.biz.service.TodayRecommendService;
import com.weiqianghu.drygoodscamp.common.Url;
import com.weiqianghu.drygoodscamp.entity.TodayResult;
import com.weiqianghu.drygoodscamp.utils.DateUtil;

/**
 * Created by huweiqiang on 2016/6/30.
 */
public class DayBizImpl extends BaseBiz<TodayRecommendService> implements DayBiz {
    private static final String TAG = "DayBizImpl";

    @Override
    public void loadData(CallBack callBack) {
        TodayRecommendService todayRecommendService = mHttpProvider.getService(Url.URL_DAY, TodayRecommendService.class);
        ApiProvider<TodayResult.TodayRecommend> apiProvider = ApiProvider.getInstance();
        apiProvider.executeTodayRecommend(todayRecommendService.loadTodayDryGoods(
                DateUtil.getYear(),
                DateUtil.getMonth(),
                DateUtil.getDay()),
                callBack);

        Log.d(TAG, "loadData: " + DateUtil.getYear() + " " + DateUtil.getMonth() + " " + DateUtil.getDay());
    }
}
