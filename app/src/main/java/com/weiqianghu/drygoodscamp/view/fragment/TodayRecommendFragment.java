package com.weiqianghu.drygoodscamp.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.weiqianghu.drygoodscamp.R;
import com.weiqianghu.drygoodscamp.base.adapter.IRecycleViewItemClickListener;
import com.weiqianghu.drygoodscamp.base.adapter.SpacesItemDecoration;
import com.weiqianghu.drygoodscamp.base.view.BaseFragment;
import com.weiqianghu.drygoodscamp.common.Constant;
import com.weiqianghu.drygoodscamp.entity.DryGoods;
import com.weiqianghu.drygoodscamp.presenter.TodayRecommendPresenter;
import com.weiqianghu.drygoodscamp.view.TodayRecommendView;
import com.weiqianghu.drygoodscamp.view.activity.WebViewActivity;
import com.weiqianghu.drygoodscamp.view.adapter.RecommendAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodayRecommendFragment extends BaseFragment implements TodayRecommendView, IRecycleViewItemClickListener {
    public static final String TAG = "TodayRecommendFragment";
    private TodayRecommendPresenter mTodayRecommendPresenter;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

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
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });

        mAdapter = new RecommendAdapter(mRecommends);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);

        mTodayRecommendPresenter = new TodayRecommendPresenter(this);
        mTodayRecommendPresenter.loadTodayRecommend();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        mRecyclerView.addItemDecoration(decoration);

        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mTodayRecommendPresenter != null) {
                    mTodayRecommendPresenter.loadTodayRecommend();
                }
            }
        });
    }

    @Override
    public void updateWelfares(List<DryGoods> dryGoodses) {
        mAdapter.setWelfares(dryGoodses);
    }

    @Override
    public void updateRecommend(List<DryGoods> dryGoodses) {
        mRecommends.clear();
        mRecommends.addAll(dryGoodses);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void stopRefreshing() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getActivity(), WebViewActivity.class);
        intent.putExtra(Constant.ARG_URL, mRecommends.get(position - 1).url);
        intent.putExtra(Constant.ARG_TITLE, mRecommends.get(position - 1).desc);
        startActivity(intent);
    }
}
