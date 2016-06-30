package com.weiqianghu.drygoodscamp.biz.service;

import com.weiqianghu.drygoodscamp.base.http.HttpResult;
import com.weiqianghu.drygoodscamp.entity.DryGoods;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by huweiqiang on 2016/6/29.
 */
public interface CategoryService {

    @GET("{category}/{pageSize}/{page}")
    Observable<HttpResult<DryGoods>> loadCategory(@Path("category") String category, @Path("pageSize") int pageSize, @Path("page") int page);
}
