package com.weiqianghu.drygoodscamp.biz.impl;

import com.weiqianghu.drygoodscamp.base.Biz.BaseBiz;
import com.weiqianghu.drygoodscamp.base.http.ApiProvider;
import com.weiqianghu.drygoodscamp.base.http.CallBack;
import com.weiqianghu.drygoodscamp.base.http.HttpResult;
import com.weiqianghu.drygoodscamp.biz.HistoryDateBiz;
import com.weiqianghu.drygoodscamp.biz.service.HistoryDateService;
import com.weiqianghu.drygoodscamp.common.Url;

/**
 * Created by huweiqiang on 2016/7/4.
 */
public class HistoryDateBizImpl extends BaseBiz<HistoryDateService> implements HistoryDateBiz {

    @Override
    public void loadHistoryDate(CallBack<HttpResult<String>> callBack) {
        HistoryDateService service = mHttpProvider.getService(Url.URL_HISTORY_DATE, HistoryDateService.class);

        ApiProvider<String> apiProvider = ApiProvider.getInstance();
        apiProvider.execute(service.loadHistoryDate(), callBack);
    }
}
