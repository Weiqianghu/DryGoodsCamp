package com.weiqianghu.drygoodscamp.base.view;

import java.util.List;

/**
 * Created by huweiqiang on 2016/6/29.
 */
public interface IListView<T> {
    void refreshListView(List<T> data);

    void updateListView(List<T> data);
}
