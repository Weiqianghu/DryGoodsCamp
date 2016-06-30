package com.weiqianghu.drygoodscamp.biz;

import com.weiqianghu.drygoodscamp.base.http.CallBack;
import com.weiqianghu.drygoodscamp.base.http.HttpResult;

/**
 * Created by huweiqiang on 2016/6/29.
 */
public interface LoadDataByPageBiz<T> {
    void loadData(int page, int pageSize, CallBack<HttpResult<T>> callBack);
}
