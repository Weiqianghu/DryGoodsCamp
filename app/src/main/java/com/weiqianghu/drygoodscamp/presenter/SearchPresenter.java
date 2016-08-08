package com.weiqianghu.drygoodscamp.presenter;

import com.weiqianghu.drygoodscamp.base.http.CallBack;
import com.weiqianghu.drygoodscamp.base.http.HttpResult;
import com.weiqianghu.drygoodscamp.biz.SearchBiz;
import com.weiqianghu.drygoodscamp.biz.impl.SearchBizImpl;
import com.weiqianghu.drygoodscamp.common.Constant;
import com.weiqianghu.drygoodscamp.entity.SearchResult;
import com.weiqianghu.drygoodscamp.utils.SPUtil;
import com.weiqianghu.drygoodscamp.view.view.ISearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Description ${Desc}
 * Author huweiqiang
 * Date 2016/8/5.
 */
public class SearchPresenter {
    private static final String TAG = "SearchPresenter";
    private SearchBiz mSearchBiz;
    private ISearchView mSearchView;

    public SearchPresenter(ISearchView searchView) {
        mSearchView = searchView;
        mSearchBiz = new SearchBizImpl();
    }

    public void init(String query, String category, int pageSize) {
        CallBack<HttpResult<SearchResult>> callBack = new CallBack<HttpResult<SearchResult>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(HttpResult<SearchResult> searchResultHttpResult) {
                mSearchView.init(searchResultHttpResult.results);
            }
        };
        mSearchBiz.search(query, category, pageSize, 1, callBack);
    }

    public void search(String query, String category, int pageSize, int page) {
        CallBack<HttpResult<SearchResult>> callBack = new CallBack<HttpResult<SearchResult>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(HttpResult<SearchResult> searchResultHttpResult) {
                mSearchView.search(searchResultHttpResult.results);
            }
        };
        mSearchBiz.search(query, category, pageSize, page, callBack);
    }

    public void saveSearchHistory(String searchContent) {
        Set<String> set = new TreeSet<>();
        set.add(searchContent);
        SPUtil.save(Constant.SP_KEY_SEARCH_HISTORY, set);
        mSearchView.saveSearchHistory(searchContent);
    }

    public void clearSearchHistory() {
        SPUtil.clear(Constant.SP_KEY_SEARCH_HISTORY);
        mSearchView.clearSearchHistories();
    }

    public void getSearchHistories() {
        Set<String> searchHistories = SPUtil.readSet(Constant.SP_KEY_SEARCH_HISTORY);
        List<String> list = new ArrayList<>();

        for (String str : searchHistories) {
            list.add(str);
        }
        mSearchView.showSearchHistories(list);
    }
}
