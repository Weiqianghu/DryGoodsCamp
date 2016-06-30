package com.weiqianghu.drygoodscamp.biz;


import com.weiqianghu.drygoodscamp.base.http.CallBack;
import com.weiqianghu.drygoodscamp.base.http.HttpResult;
import com.weiqianghu.drygoodscamp.entity.HistoryDryGoods;

/**
 * Created by huweiqiang on 2016/6/27.
 */
public interface HistoryBiz {
    void loadDryData(int page, int pageSize, CallBack<HttpResult<HistoryDryGoods>> callBack);
}
