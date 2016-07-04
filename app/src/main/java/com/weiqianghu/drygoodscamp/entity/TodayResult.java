package com.weiqianghu.drygoodscamp.entity;

import java.util.List;

/**
 * Created by huweiqiang on 2016/6/30.
 */
public class TodayResult<T> {
    public List<String> category;
    public boolean error;
    public T results;


    public static class TodayRecommend {
        public List<DryGoods> Android;
        public List<DryGoods> iOS;
        public List<DryGoods> 休息视频;
        public List<DryGoods> 拓展资源;
        public List<DryGoods> 瞎推荐;
        public List<DryGoods> 福利;
        public List<DryGoods> 前端;
        public List<DryGoods> APP;
    }
}
