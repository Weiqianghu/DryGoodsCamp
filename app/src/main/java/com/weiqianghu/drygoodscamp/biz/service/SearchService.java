package com.weiqianghu.drygoodscamp.biz.service;

import com.weiqianghu.drygoodscamp.base.http.HttpResult;
import com.weiqianghu.drygoodscamp.entity.SearchResult;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Description ${Desc}
 * Author huweiqiang
 * Date 2016/8/5.
 */
public interface SearchService {
    @GET("query/{query}/category/{category}/count/{pageSize}/page/{page}")
    Observable<HttpResult<SearchResult>> search(@Path("query") String query, @Path("category") String category, @Path("pageSize") int pageSize, @Path("page") int page);
}
