package com.weiqianghu.drygoodscamp.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.weiqianghu.drygoodscamp.R;
import com.weiqianghu.drygoodscamp.base.adapter.SpacesItemDecoration;
import com.weiqianghu.drygoodscamp.base.view.BaseFragment;
import com.weiqianghu.drygoodscamp.entity.DryGoods;
import com.weiqianghu.drygoodscamp.presenter.TodayRecommendPresenter;
import com.weiqianghu.drygoodscamp.view.TodayRecommendView;
import com.weiqianghu.drygoodscamp.view.adapter.RecommendAdapter;
import com.weiqianghu.drygoodscamp.widget.Kanner;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodayRecommendFragment extends BaseFragment implements TodayRecommendView {
    public static final String TAG = "TodayRecommendFragment";
    private TodayRecommendPresenter mTodayRecommendPresenter;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Kanner mKanner;
    private RecyclerView mRecyclerView;

    private List<DryGoods> mWelfares = new ArrayList<>();
    private List<DryGoods> mRecommends = new ArrayList<>();
    private RecommendAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_today_recommend;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        initView(savedInstanceState);

        initData();
    }

    private void initData() {
        mSwipeRefreshLayout.setRefreshing(true);
        mTodayRecommendPresenter = new TodayRecommendPresenter(this);
        mTodayRecommendPresenter.loadTodayRecommend();

        mAdapter = new RecommendAdapter(mRecommends);
        mRecyclerView.setAdapter(mAdapter);
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        mRecyclerView.addItemDecoration(decoration);

        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipe_refresh_layout);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);
    }

    @Override
    public void updateWelfares(List<DryGoods> dryGoodses) {
        /*if (mKanner == null) {
            mKanner = (Kanner) mRecyclerView.getChildAt(0);
        }

        List<String> urls = new ArrayList<>();
        String[] urlArray = new String[0];
        for (DryGoods goods : dryGoodses) {
            urls.add(goods.url);
        }
        mKanner.setImagesUrl(urls.toArray(urlArray));*/
    }

    @Override
    public void updateRecommend(List<DryGoods> dryGoodses) {
        mRecommends.addAll(dryGoodses);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void stopRefreshing() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
}
