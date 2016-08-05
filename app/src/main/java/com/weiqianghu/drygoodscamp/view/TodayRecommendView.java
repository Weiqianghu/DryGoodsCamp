package com.weiqianghu.drygoodscamp.view;

import com.weiqianghu.drygoodscamp.entity.DryGoods;

import java.util.List;

/**
 * Created by huweiqiang on 2016/6/30.
 */
public interface TodayRecommendView {
    void updateWelfares(List<DryGoods> dryGoodses);

    void updateRecommend(List<DryGoods> dryGoodses);

    void stopRefreshing();
}
