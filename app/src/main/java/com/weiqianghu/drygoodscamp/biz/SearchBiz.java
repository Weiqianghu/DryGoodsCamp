package com.weiqianghu.drygoodscamp.biz;

import com.weiqianghu.drygoodscamp.base.http.CallBack;
import com.weiqianghu.drygoodscamp.base.http.HttpResult;
import com.weiqianghu.drygoodscamp.entity.SearchResult;

/**
 * Description ${Desc}
 * Author huweiqiang
 * Date 2016/8/5.
 */
public interface SearchBiz {
    void search(String query, String category, int pageSize, int page, CallBack<HttpResult<SearchResult>> callBack);
}
