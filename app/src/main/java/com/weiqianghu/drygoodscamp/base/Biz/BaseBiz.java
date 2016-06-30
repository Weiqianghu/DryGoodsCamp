package com.weiqianghu.drygoodscamp.base.Biz;


import com.weiqianghu.drygoodscamp.base.http.HttpProvider;

/**
 * Created by huweiqiang on 2016/6/27.
 */
public class BaseBiz<T> {
    protected HttpProvider<T> mHttpProvider = HttpProvider.getInstance();

}
