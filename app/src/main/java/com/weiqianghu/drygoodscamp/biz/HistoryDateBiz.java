package com.weiqianghu.drygoodscamp.biz;

import com.weiqianghu.drygoodscamp.base.http.CallBack;
import com.weiqianghu.drygoodscamp.base.http.HttpResult;

/**
 * Created by huweiqiang on 2016/7/4.
 */
public interface HistoryDateBiz {
    void loadHistoryDate(CallBack<HttpResult<String>> callBack);
}
