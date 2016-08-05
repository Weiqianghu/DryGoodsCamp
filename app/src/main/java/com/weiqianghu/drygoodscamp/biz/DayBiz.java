package com.weiqianghu.drygoodscamp.biz;

import com.weiqianghu.drygoodscamp.base.http.CallBack;

/**
 * Created by huweiqiang on 2016/6/30.
 */
public interface DayBiz<T> {
    void loadData(CallBack<T> callBack);

}
