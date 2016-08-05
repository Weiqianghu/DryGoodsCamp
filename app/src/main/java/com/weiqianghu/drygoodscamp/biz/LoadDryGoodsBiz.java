package com.weiqianghu.drygoodscamp.biz;

import com.weiqianghu.drygoodscamp.base.http.CallBack;
import com.weiqianghu.drygoodscamp.base.http.HttpResult;

/**
 * Created by huweiqiang on 2016/6/30.
 */
public interface LoadDryGoodsBiz<T> {
    void loadData(CallBack<HttpResult<T>> callBack);
}
