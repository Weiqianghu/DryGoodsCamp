package com.weiqianghu.drygoodscamp.biz.handler;

import com.weiqianghu.drygoodscamp.base.http.ApiProvider;
import com.weiqianghu.drygoodscamp.base.http.CallBack;
import com.weiqianghu.drygoodscamp.base.http.HttpProvider;
import com.weiqianghu.drygoodscamp.biz.service.TodayRecommendService;
import com.weiqianghu.drygoodscamp.common.Constant;
import com.weiqianghu.drygoodscamp.common.Url;
import com.weiqianghu.drygoodscamp.entity.TodayResult;
import com.weiqianghu.drygoodscamp.utils.DateUtil;
import com.weiqianghu.drygoodscamp.utils.SPUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by huweiqiang on 2016/7/12.
 */
public class NetTodayRecommendHandler extends BaseHandler {
    @Override
    public void handleRequest(CallBack callBack) {
        Date date = DateUtil.parse(SPUtil.readLong(Constant.SP_KEY_UPDATE_DATE));
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int year = ca.get(Calendar.YEAR);
        int month = ca.get(Calendar.MONTH) + 1;
        int day = ca.get(Calendar.DAY_OF_MONTH);

        HttpProvider<TodayRecommendService> httpProvider = HttpProvider.getInstance();
        TodayRecommendService todayRecommendService = httpProvider.getService(Url.URL_DAY, TodayRecommendService.class);
        ApiProvider<TodayResult.TodayRecommend> apiProvider = ApiProvider.getInstance();
        apiProvider.executeTodayRecommend(todayRecommendService.loadTodayDryGoods(year, month, day), callBack);
    }
}
