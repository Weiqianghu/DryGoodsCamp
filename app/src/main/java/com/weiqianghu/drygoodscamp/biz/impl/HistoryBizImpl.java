package com.weiqianghu.drygoodscamp.biz.impl;


import com.weiqianghu.drygoodscamp.base.Biz.BaseBiz;
import com.weiqianghu.drygoodscamp.base.http.ApiProvider;
import com.weiqianghu.drygoodscamp.base.http.CallBack;
import com.weiqianghu.drygoodscamp.base.http.HttpResult;
import com.weiqianghu.drygoodscamp.biz.HistoryBiz;
import com.weiqianghu.drygoodscamp.biz.service.HistoryService;
import com.weiqianghu.drygoodscamp.common.Url;
import com.weiqianghu.drygoodscamp.entity.HistoryDryGoods;

/**
 * Created by huweiqiang on 2016/6/27.
 */
public class HistoryBizImpl extends BaseBiz<HistoryService> implements HistoryBiz {

    @Override
    public void loadDryData(int page, int pageSize, CallBack<HttpResult<HistoryDryGoods>> callBack) {
        HistoryService historyService = mHttpProvider.getService(Url.URL_HISTORY, HistoryService.class);

        ApiProvider<HistoryDryGoods> apiProvider = ApiProvider.getInstance();
        apiProvider.execute(historyService.loadHistoryDyrData(pageSize, page), callBack);
    }
}
