package com.weiqianghu.drygoodscamp.biz.service;

import com.weiqianghu.drygoodscamp.entity.TodayResult;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by huweiqiang on 2016/6/30.
 */
public interface TodayRecommendService {
    @GET("{year}/{month}/{day}")
    Observable<TodayResult<TodayResult.TodayRecommend>> loadTodayDryGoods(@Path("year") int year, @Path("month") int month, @Path("day") int day);
}
