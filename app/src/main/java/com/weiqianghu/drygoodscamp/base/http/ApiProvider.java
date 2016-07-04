package com.weiqianghu.drygoodscamp.base.http;

import com.weiqianghu.drygoodscamp.common.Constant;
import com.weiqianghu.drygoodscamp.entity.TodayResult;
import com.weiqianghu.drygoodscamp.utils.NetUtil;
import com.weiqianghu.drygoodscamp.utils.ToastUtil;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by huweiqiang on 2016/6/27.
 */
public class ApiProvider<T> {

    private ApiProvider() {
    }

    private static ApiProvider instance;

    public static ApiProvider getInstance() {
        if (instance == null) {
            synchronized (HttpProvider.class) {
                if (instance == null) {
                    instance = new ApiProvider();
                }
            }
        }
        return instance;
    }

    public void execute(Observable<HttpResult<T>> observable, final CallBack<HttpResult<T>> callBack) {

        if (checkNetWork()) {
            return;
        }

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<T>>() {
                    @Override
                    public void onCompleted() {
                        callBack.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(e);
                    }

                    @Override
                    public void onNext(HttpResult<T> tHttpResult) {
                        if (tHttpResult.error) {
                            callBack.onError(new HttpErrorException("请求数据出错"));
                        } else {
                            callBack.onNext(tHttpResult);
                        }
                    }
                });
    }

    public void executeTodayRecommend(Observable<TodayResult<T>> observable, final CallBack<TodayResult<T>> callBack) {

        if (checkNetWork()) {
            return;
        }

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TodayResult>() {
                    @Override
                    public void onCompleted() {
                        callBack.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        callBack.onError(e);
                    }

                    @Override
                    public void onNext(TodayResult todayResult) {
                        if (todayResult.error) {
                            callBack.onError(new HttpErrorException("请求数据出错"));
                        } else {
                            callBack.onNext(todayResult);
                        }
                    }
                });
    }


    private boolean checkNetWork() {
        int netWorkType = NetUtil.getNetWorkType();
        if (netWorkType == Constant.NETWORKTYPE_INVALID) {
            ToastUtil.show("当前网络不可用，请稍后再试！");
            return true;
        } else if (netWorkType != Constant.NETWORKTYPE_WIFI) {
            ToastUtil.show("当前网络是数据网络，请注意数据流量！");
        }
        return false;
    }
}
