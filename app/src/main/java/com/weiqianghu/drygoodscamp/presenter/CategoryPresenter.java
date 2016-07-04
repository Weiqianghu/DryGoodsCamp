package com.weiqianghu.drygoodscamp.presenter;

import com.weiqianghu.drygoodscamp.base.http.CallBack;
import com.weiqianghu.drygoodscamp.base.http.HttpResult;
import com.weiqianghu.drygoodscamp.biz.CategoryBiz;
import com.weiqianghu.drygoodscamp.biz.impl.CategoryBizImpl;
import com.weiqianghu.drygoodscamp.entity.DryGoods;
import com.weiqianghu.drygoodscamp.view.view.ICategoryView;

/**
 * Created by huweiqiang on 2016/7/4.
 */
public class CategoryPresenter {
    private CategoryBiz mCategoryBiz;
    private ICategoryView mCategoryView;

    public CategoryPresenter(ICategoryView categoryView) {
        mCategoryView = categoryView;
        mCategoryBiz = new CategoryBizImpl();
    }

    public void initCategory(String category, int pageSize) {
        CallBack<HttpResult<DryGoods>> callBack = new CallBack<HttpResult<DryGoods>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(HttpResult<DryGoods> welfareHttpResult) {
                if (welfareHttpResult.results.size() > 0) {
                    mCategoryView.refreshListView(welfareHttpResult.results);
                }
            }
        };
        mCategoryBiz.loadData(category, 1, pageSize, callBack);
    }

    public void loadCategory(String category, int page, int pageSize) {
        CallBack<HttpResult<DryGoods>> callBack = new CallBack<HttpResult<DryGoods>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(HttpResult<DryGoods> welfareHttpResult) {
                if (welfareHttpResult.results.size() > 0) {
                    mCategoryView.updateListView(welfareHttpResult.results);
                }
            }
        };
        mCategoryBiz.loadData(category, page, pageSize, callBack);
    }
}
