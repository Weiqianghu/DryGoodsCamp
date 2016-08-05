package com.weiqianghu.drygoodscamp.biz.service;


import com.weiqianghu.drygoodscamp.base.http.HttpResult;
import com.weiqianghu.drygoodscamp.entity.HistoryDryGoods;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by huweiqiang on 2016/6/27.
 */
public interface HistoryService{

    @GET("{pageSize}/{page}")
    Observable<HttpResult<HistoryDryGoods>> loadHistoryDyrData(@Path("pageSize") int pageSize, @Path("page") int page);
}
