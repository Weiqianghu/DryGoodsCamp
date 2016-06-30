package com.weiqianghu.drygoodscamp.base.http;

/**
 * Created by huweiqiang on 2016/6/27.
 */
public interface CallBack<T> {
    void onCompleted();

    void onError(Throwable e);

    void onNext(T t);
}
