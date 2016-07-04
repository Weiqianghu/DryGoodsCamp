package com.weiqianghu.drygoodscamp.biz.service;

import com.weiqianghu.drygoodscamp.base.http.HttpResult;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by huweiqiang on 2016/7/4.
 */
public interface HistoryDateService {
    @GET("history")
    Observable<HttpResult<String>> loadHistoryDate();
}
