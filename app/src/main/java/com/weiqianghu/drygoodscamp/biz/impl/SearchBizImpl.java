package com.weiqianghu.drygoodscamp.biz.impl;

import com.weiqianghu.drygoodscamp.base.Biz.BaseBiz;
import com.weiqianghu.drygoodscamp.base.http.ApiProvider;
import com.weiqianghu.drygoodscamp.base.http.CallBack;
import com.weiqianghu.drygoodscamp.base.http.HttpResult;
import com.weiqianghu.drygoodscamp.biz.SearchBiz;
import com.weiqianghu.drygoodscamp.biz.service.SearchService;
import com.weiqianghu.drygoodscamp.common.Url;
import com.weiqianghu.drygoodscamp.entity.SearchResult;

/**
 * Description ${Desc}
 * Author huweiqiang
 * Date 2016/8/5.
 */
public class SearchBizImpl extends BaseBiz<SearchService> implements SearchBiz {
    @Override
    public void search(String query, String category, int pageSize, int page, CallBack<HttpResult<SearchResult>> callBack) {
        SearchService searchService = mHttpProvider.getService(Url.URL_SEARCH, SearchService.class);

        ApiProvider<SearchResult> apiProvider = ApiProvider.getInstance();
        apiProvider.execute(searchService.search(query, category, pageSize, page), callBack);
    }
}
