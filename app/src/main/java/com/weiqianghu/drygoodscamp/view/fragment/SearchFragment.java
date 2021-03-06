package com.weiqianghu.drygoodscamp.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.cundong.recyclerview.EndlessRecyclerOnScrollListener;
import com.weiqianghu.drygoodscamp.R;
import com.weiqianghu.drygoodscamp.base.adapter.CommonAdapterForRecycleView;
import com.weiqianghu.drygoodscamp.base.adapter.IRecycleViewItemClickListener;
import com.weiqianghu.drygoodscamp.base.view.BaseFragment;
import com.weiqianghu.drygoodscamp.base.viewholder.ViewHolderForRecyclerView;
import com.weiqianghu.drygoodscamp.common.Constant;
import com.weiqianghu.drygoodscamp.entity.SearchResult;
import com.weiqianghu.drygoodscamp.presenter.SearchPresenter;
import com.weiqianghu.drygoodscamp.view.activity.WebViewActivity;
import com.weiqianghu.drygoodscamp.view.view.ISearchView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseFragment implements ISearchView, IRecycleViewItemClickListener {
    private AppCompatEditText mEditText;
    private SearchPresenter mPresenter;
    private Spinner mSpinner;
    private static final int PAGE_SIZE = 20;

    private String[] mSearchItem = {"all", "Android", "iOS", "休息视频", "福利", "拓展资源", "前端", "瞎推荐", "App"};

    private String mCategory = "all";

    public static final String TAG = SearchFragment.class.getSimpleName();

    private CommonAdapterForRecycleView<SearchResult> mAdapter;
    private List<SearchResult> mSearchResults = new ArrayList<>();

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private String mQuery;
    private int page = 1;

    private CommonAdapterForRecycleView<String> mSearchHistoryAdapter;
    private List<String> mSearchHistories = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private Button mClearSearchHistoryBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        initData();
        initView(savedInstanceState);
    }

    private void initData() {
        mPresenter = new SearchPresenter(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mEditText = (AppCompatEditText) mRootView.findViewById(R.id.et_search);
        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    search();
                }
                return false;
            }
        });

        mSpinner = (Spinner) mRootView.findViewById(R.id.sp_search_item);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mCategory = mSearchItem[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mAdapter = new CommonAdapterForRecycleView<SearchResult>(mSearchResults, R.layout.category_item) {
            @Override
            public void convert(ViewHolderForRecyclerView helper, SearchResult item) {
                helper.setText(R.id.tv_title, item.desc);
                helper.setText(R.id.tv_who, "来自于" + item.who);
                try {
                    URL url = new URL(item.url);
                    String host = url.getHost();
                    if (host == null) {
                        helper.setImageResource(R.id.iv_host, R.drawable.blog);
                    }
                    Log.d(TAG, "convert: " + host);
                    if ("github.com".equals(host)) {
                        helper.setImageResource(R.id.iv_host, R.drawable.github);
                    } else if ("www.jianshu.com".equals(host)) {
                        helper.setImageResource(R.id.iv_host, R.drawable.jianshu);
                    } else if (host.contains("weixin")) {
                        helper.setImageResource(R.id.iv_host, R.drawable.weixin);
                    } else if (host.contains("csdn")) {
                        helper.setImageResource(R.id.iv_host, R.drawable.csdn);
                    } else if (host.contains("oschina")) {
                        helper.setImageResource(R.id.iv_host, R.drawable.oschina);
                    } else if (host.contains("youku")) {
                        helper.setImageResource(R.id.iv_host, R.drawable.youku);
                    } else if (host.contains("weibo")) {
                        helper.setImageResource(R.id.iv_host, R.drawable.weibo);
                    } else if (host.contains("bilibili")) {
                        helper.setImageResource(R.id.iv_host, R.drawable.bilibili);
                    } else if (host.contains("miaopai")) {
                        helper.setImageResource(R.id.iv_host, R.drawable.miaopai);
                    } else {
                        helper.setImageResource(R.id.iv_host, R.drawable.blog);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    helper.setImageResource(R.id.iv_host, R.drawable.blog);
                }
            }
        };

        mSearchHistoryAdapter = new CommonAdapterForRecycleView<String>(mSearchHistories, R.layout.item_search_history) {
            @Override
            public void convert(ViewHolderForRecyclerView helper, String item) {
                helper.setText(R.id.tv_search_history, item);
            }
        };

        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.rv_search_result);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(mOnScrollListener);

        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });

        mClearSearchHistoryBtn = (Button) mRootView.findViewById(R.id.btn_clear_history);
        mClearSearchHistoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.clearSearchHistory();
            }
        });

        mAdapter.setOnItemClickListener(this);
    }

    private EndlessRecyclerOnScrollListener mOnScrollListener = new EndlessRecyclerOnScrollListener() {

        @Override
        public void onLoadNextPage(View view) {
            super.onLoadNextPage(view);
            mSwipeRefreshLayout.setRefreshing(true);
            mPresenter.search(mQuery, mCategory, PAGE_SIZE, ++page);
        }
    };

    private void search() {
        mQuery = mEditText.getText().toString().trim();
        if (!TextUtils.isEmpty(mQuery)) {
            hideSoftWindow();
            mSwipeRefreshLayout.setRefreshing(true);
            mClearSearchHistoryBtn.setVisibility(View.GONE);
            mPresenter.saveSearchHistory(mQuery);
            mPresenter.init(mQuery, mCategory, PAGE_SIZE);
        }
    }

    @Override
    public void init(List<SearchResult> results) {
        mSearchResults.clear();
        page = 0;
        mSearchResults.addAll(results);
        mAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void search(List<SearchResult> results) {
        mSearchResults.addAll(results);
        mAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showSearchHistories(List<String> searchHistories) {
        mSearchHistories.clear();
        mSearchHistories.addAll(searchHistories);
        mSearchHistoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void clearSearchHistories() {
        mSearchHistories.clear();
        mSearchHistoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void saveSearchHistory(String searchContent) {
        mSearchHistories.add(searchContent);
        mSearchHistoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getActivity(), WebViewActivity.class);
        intent.putExtra(Constant.ARG_URL, mSearchResults.get(position).url);
        intent.putExtra(Constant.ARG_TITLE, mSearchResults.get(position).desc);
        startActivity(intent);
    }
}
