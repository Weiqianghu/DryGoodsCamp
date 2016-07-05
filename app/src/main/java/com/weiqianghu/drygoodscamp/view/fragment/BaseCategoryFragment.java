package com.weiqianghu.drygoodscamp.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.cundong.recyclerview.EndlessRecyclerOnScrollListener;
import com.weiqianghu.drygoodscamp.R;
import com.weiqianghu.drygoodscamp.base.adapter.CommonAdapterForRecycleView;
import com.weiqianghu.drygoodscamp.base.adapter.SpacesItemDecoration;
import com.weiqianghu.drygoodscamp.base.view.BaseFragment;
import com.weiqianghu.drygoodscamp.base.viewholder.ViewHolderForRecyclerView;
import com.weiqianghu.drygoodscamp.entity.DryGoods;
import com.weiqianghu.drygoodscamp.presenter.CategoryPresenter;
import com.weiqianghu.drygoodscamp.view.view.ICategoryView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseCategoryFragment extends BaseFragment implements ICategoryView {
    private static final String TAG = "BaseCategoryFragment";
    private static final int PAGE_SIZE = 20;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private CategoryPresenter mCategoryPresenter;
    private CommonAdapterForRecycleView<DryGoods> mAdapter;
    private List<DryGoods> mDryGoodses = new ArrayList<>();
    private int page = 1;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_category;
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

        mAdapter = new CommonAdapterForRecycleView<DryGoods>(mDryGoodses, R.layout.category_item) {
            @Override
            public void convert(ViewHolderForRecyclerView helper, DryGoods item) {
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
        mRecyclerView.setAdapter(mAdapter);
        mCategoryPresenter = new CategoryPresenter(this);
        mCategoryPresenter.initCategory(getCategory(), PAGE_SIZE);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        mRecyclerView.addItemDecoration(decoration);
        mRecyclerView.addOnScrollListener(mOnScrollListener);

        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mCategoryPresenter != null) {
                    mSwipeRefreshLayout.setRefreshing(true);
                    mCategoryPresenter.initCategory(getCategory(), PAGE_SIZE);
                }
            }
        });
    }

    protected abstract String getCategory();

    @Override
    public void refreshListView(List<DryGoods> data) {
        mDryGoodses.clear();
        updateListView(data);
    }

    @Override
    public void updateListView(List<DryGoods> data) {
        mDryGoodses.addAll(data);
        mAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private EndlessRecyclerOnScrollListener mOnScrollListener = new EndlessRecyclerOnScrollListener() {

        @Override
        public void onLoadNextPage(View view) {
            super.onLoadNextPage(view);
            mSwipeRefreshLayout.setRefreshing(true);
            mCategoryPresenter.loadCategory(getCategory(), ++page, PAGE_SIZE);
        }
    };
}
