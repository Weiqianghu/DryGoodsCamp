package com.weiqianghu.drygoodscamp.biz.impl;

import com.weiqianghu.drygoodscamp.base.Biz.BaseBiz;
import com.weiqianghu.drygoodscamp.base.http.ApiProvider;
import com.weiqianghu.drygoodscamp.base.http.CallBack;
import com.weiqianghu.drygoodscamp.base.http.HttpResult;
import com.weiqianghu.drygoodscamp.biz.AppBiz;
import com.weiqianghu.drygoodscamp.biz.service.CategoryService;
import com.weiqianghu.drygoodscamp.common.Url;
import com.weiqianghu.drygoodscamp.entity.DryGoods;

/**
 * Created by huweiqiang on 2016/6/29.
 */
public class AppBizImpl extends BaseBiz<CategoryService> implements AppBiz {
    public static final String CATEGORY = "App";

    @Override
    public void loadData(int page, int pageSize, CallBack<HttpResult<DryGoods>> callBack) {
        CategoryService categoryService = mHttpProvider.getService(Url.URL_CATEGORY, CategoryService.class);

        ApiProvider<DryGoods> apiProvider = ApiProvider.getInstance();
        apiProvider.execute(categoryService.loadCategory(CATEGORY, pageSize, page), callBack);
    }
}
