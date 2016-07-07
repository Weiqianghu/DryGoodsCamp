package com.weiqianghu.drygoodscamp.presenter;

import android.util.Log;

import com.weiqianghu.drygoodscamp.base.db.DaoWrapper;
import com.weiqianghu.drygoodscamp.base.http.CallBack;
import com.weiqianghu.drygoodscamp.base.http.HttpResult;
import com.weiqianghu.drygoodscamp.biz.DayBiz;
import com.weiqianghu.drygoodscamp.biz.WelfareBiz;
import com.weiqianghu.drygoodscamp.biz.impl.DayBizImpl;
import com.weiqianghu.drygoodscamp.biz.impl.WelfareBizImpl;
import com.weiqianghu.drygoodscamp.common.Global;
import com.weiqianghu.drygoodscamp.entity.DryGoods;
import com.weiqianghu.drygoodscamp.entity.TodayResult;
import com.weiqianghu.drygoodscamp.utils.ToastUtil;
import com.weiqianghu.drygoodscamp.view.TodayRecommendView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by huweiqiang on 2016/6/30.
 */
public class TodayRecommendPresenter {
    private static final String TAG = "TodayRecommendPresenter";
    private TodayRecommendView mTodayRecommendView;
    private DayBiz mDayBiz;
    private WelfareBiz mWelfareBiz;

    public TodayRecommendPresenter(TodayRecommendView todayRecommendView) {
        mTodayRecommendView = todayRecommendView;
        mDayBiz = new DayBizImpl();
        mWelfareBiz = new WelfareBizImpl();
    }

    public void loadTodayRecommend() {
        CallBack<TodayResult<TodayResult.TodayRecommend>> callBack = new CallBack<TodayResult<TodayResult.TodayRecommend>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToastUtil.show("服务器维护中，请稍后再试");
            }


            @Override
            public void onNext(TodayResult todayResult) {
                Log.d(TAG, "onNext: ");
                if (todayResult == null || todayResult.results == null) {
                    ToastUtil.show("今天没有数据哦！");
                }
                TodayResult.TodayRecommend recommend = (TodayResult.TodayRecommend) todayResult.results;
                //mTodayRecommendView.updateWelfares(recommend.福利);
                mTodayRecommendView.updateRecommend(convert(recommend));
                mTodayRecommendView.stopRefreshing();
                if (Global.isUpdate) {
                    DaoWrapper.insert(convert(recommend));
                }
            }
        };

        mDayBiz.loadData(callBack);
    }

    public void loadWelfares() {
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
                    mTodayRecommendView.updateWelfares(welfareHttpResult.results);
                }
            }
        };
        mWelfareBiz.loadData(1, 4, callBack);
    }

    List<DryGoods> convert(TodayResult.TodayRecommend recommend) {
        List<DryGoods> list = new ArrayList<>();
        if (recommend.Android != null) {
            list.addAll(recommend.Android);
        }
        if (recommend.iOS != null) {
            list.addAll(recommend.iOS);
        }
        if (recommend.拓展资源 != null) {
            list.addAll(recommend.拓展资源);
        }
        if (recommend.瞎推荐 != null) {
            list.addAll(recommend.瞎推荐);
        }
        if (recommend.休息视频 != null) {
            list.addAll(recommend.休息视频);
        }
        if (recommend.前端 != null) {
            list.addAll(recommend.前端);
        }
        if (recommend.APP != null) {
            list.addAll(recommend.APP);
        }
        Collections.shuffle(list);
        return list;
    }

}
