package com.weiqianghu.drygoodscamp.biz;

import com.weiqianghu.drygoodscamp.base.http.CallBack;
import com.weiqianghu.drygoodscamp.base.http.HttpResult;
import com.weiqianghu.drygoodscamp.entity.DryGoods;

/**
 * Created by huweiqiang on 2016/7/4.
 */
public interface CategoryBiz {
    void loadData(String category, int page, int pageSize, CallBack<HttpResult<DryGoods>> callBack);
}
