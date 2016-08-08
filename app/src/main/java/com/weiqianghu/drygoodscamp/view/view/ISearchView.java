package com.weiqianghu.drygoodscamp.view.view;

import com.weiqianghu.drygoodscamp.entity.SearchResult;

import java.util.List;

/**
 * Description ${Desc}
 * Author huweiqiang
 * Date 2016/8/5.
 */
public interface ISearchView {

    void init(List<SearchResult> results);

    void search(List<SearchResult> results);

    void showSearchHistories(List<String> searchHistories);

    void clearSearchHistories();

    void saveSearchHistory(String searchContent);
}
