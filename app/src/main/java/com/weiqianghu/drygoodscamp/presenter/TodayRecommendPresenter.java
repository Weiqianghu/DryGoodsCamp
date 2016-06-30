package com.weiqianghu.drygoodscamp.presenter;

import android.util.Log;

import com.weiqianghu.drygoodscamp.base.http.CallBack;
import com.weiqianghu.drygoodscamp.biz.DayBiz;
import com.weiqianghu.drygoodscamp.biz.impl.DayBizImpl;
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

    public TodayRecommendPresenter(TodayRecommendView todayRecommendView) {
        mTodayRecommendView = todayRecommendView;
        mDayBiz = new DayBizImpl();
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
                mTodayRecommendView.updateWelfares(recommend.福利);
                mTodayRecommendView.updateRecommend(convert(recommend));
            }
        };

        mDayBiz.loadData(callBack);
    }

    List<DryGoods> convert(TodayResult.TodayRecommend recommend) {
        List<DryGoods> list = new ArrayList<>();
        list.addAll(recommend.Android);
        list.addAll(recommend.iOS);
        list.addAll(recommend.拓展资源);
        list.addAll(recommend.瞎推荐);
        list.addAll(recommend.休息视频);
        Collections.shuffle(list);
        return list;
    }

}
