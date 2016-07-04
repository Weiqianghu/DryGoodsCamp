package com.weiqianghu.drygoodscamp.biz.impl;

import com.weiqianghu.drygoodscamp.base.Biz.BaseBiz;
import com.weiqianghu.drygoodscamp.base.http.ApiProvider;
import com.weiqianghu.drygoodscamp.base.http.CallBack;
import com.weiqianghu.drygoodscamp.base.http.HttpProvider;
import com.weiqianghu.drygoodscamp.base.http.HttpResult;
import com.weiqianghu.drygoodscamp.biz.DayBiz;
import com.weiqianghu.drygoodscamp.biz.service.HistoryDateService;
import com.weiqianghu.drygoodscamp.biz.service.TodayRecommendService;
import com.weiqianghu.drygoodscamp.common.Url;
import com.weiqianghu.drygoodscamp.entity.TodayResult;
import com.weiqianghu.drygoodscamp.utils.DateUtil;

import java.util.Calendar;
import java.util.Date;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by huweiqiang on 2016/6/30.
 */
public class DayBizImpl extends BaseBiz<TodayRecommendService> implements DayBiz {
    private static final String TAG = "DayBizImpl";

    @Override
    public void loadData(CallBack callBack) {

        HttpProvider<HistoryDateService> httpProvider = HttpProvider.getInstance();
        HistoryDateService service = httpProvider.getService(Url.URL_HISTORY_DATE, HistoryDateService.class);

        final TodayRecommendService todayRecommendService = mHttpProvider.getService(Url.URL_DAY, TodayRecommendService.class);
        ApiProvider<TodayResult.TodayRecommend> apiProvider = ApiProvider.getInstance();

        Observable<TodayResult<TodayResult.TodayRecommend>> observable =
                service.loadHistoryDate()
                        .flatMap(new Func1<HttpResult<String>, Observable<TodayResult<TodayResult.TodayRecommend>>>() {

                            @Override
                            public Observable<TodayResult<TodayResult.TodayRecommend>> call(HttpResult<String> stringHttpResult) {
                                int year, month, day;

                                if (stringHttpResult == null || stringHttpResult.results == null || stringHttpResult.results.size() < 0) {
                                    year = DateUtil.getYear();
                                    month = DateUtil.getMonth();
                                    day = DateUtil.getDay();
                                } else {
                                    Date date = DateUtil.forMat(stringHttpResult.results.get(0));
                                    Calendar ca = Calendar.getInstance();
                                    ca.setTime(date);
                                    year = ca.get(Calendar.YEAR);
                                    month = ca.get(Calendar.MONTH) + 1;
                                    day = ca.get(Calendar.DAY_OF_MONTH);
                                }

                                return todayRecommendService.loadTodayDryGoods(year, month, day);
                            }
                        });

        apiProvider.executeTodayRecommend(observable, callBack);
    }
}
