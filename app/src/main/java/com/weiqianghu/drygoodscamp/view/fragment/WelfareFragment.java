package com.weiqianghu.drygoodscamp.view.fragment;


import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.cundong.recyclerview.EndlessRecyclerOnScrollListener;
import com.weiqianghu.drygoodscamp.R;
import com.weiqianghu.drygoodscamp.base.adapter.CommonAdapterForRecycleView;
import com.weiqianghu.drygoodscamp.base.adapter.IRecycleViewItemClickListener;
import com.weiqianghu.drygoodscamp.base.adapter.SpacesItemDecoration;
import com.weiqianghu.drygoodscamp.base.imageloader.ImageLoader;
import com.weiqianghu.drygoodscamp.base.view.BaseFragment;
import com.weiqianghu.drygoodscamp.base.viewholder.ViewHolderForRecyclerView;
import com.weiqianghu.drygoodscamp.entity.DryGoods;
import com.weiqianghu.drygoodscamp.presenter.WelfaresPresenter;
import com.weiqianghu.drygoodscamp.utils.ImageUtil;
import com.weiqianghu.drygoodscamp.utils.ToastUtil;
import com.weiqianghu.drygoodscamp.view.view.IWelfaresView;
import com.weiqianghu.drygoodscamp.widget.BaseAnimationPopupWindow;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WelfareFragment extends BaseFragment implements IWelfaresView, IRecycleViewItemClickListener {
    private static final int PAGE_SIZE = 20;
    public static final String TAG = "WelfareFragment";

    private WelfaresPresenter mWelfaresPresenter;
    private List<DryGoods> mDryGoodses = new ArrayList<>();

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private CommonAdapterForRecycleView<DryGoods> mAdapter;

    private int page = 1;
    private BaseAnimationPopupWindow mPopupWindow;
    private View mPopupWindowView;
    private int mHeight;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_welfare;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        initView(savedInstanceState);
        initData();
    }

    private void initData() {
        mWelfaresPresenter = new WelfaresPresenter(this);
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                mWelfaresPresenter.initWelfares(PAGE_SIZE);
            }
        });
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new CommonAdapterForRecycleView<DryGoods>(mDryGoodses, R.layout.welfare_item) {
            @Override
            public void convert(ViewHolderForRecyclerView helper, DryGoods item) {
                helper.setImageUrl(R.id.iv_welfare, item.url);
            }
        };
        mRecyclerView.setAdapter(mAdapter);
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        mRecyclerView.addItemDecoration(decoration);
        mRecyclerView.addOnScrollListener(mOnScrollListener);
        mAdapter.setOnItemClickListener(this);

        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mWelfaresPresenter.initWelfares(PAGE_SIZE);
            }
        });

    }

    @Override
    public void refreshListView(List<DryGoods> data) {
        page = 1;
        mDryGoodses.clear();
        mDryGoodses.addAll(data);
        mAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
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
            mWelfaresPresenter.loadWelfareS(++page, PAGE_SIZE);
        }
    };


    private void showWelfareDetailWindow(int position) {
        initPopupWindowView(position);
        if (mPopupWindow == null) {
            mPopupWindow = new BaseAnimationPopupWindow(getActivity(), mPopupWindowView);
        }

        if (mHeight < 1) {
            DisplayMetrics dm = getResources().getDisplayMetrics();
            mHeight = dm.heightPixels;
        }
        mPopupWindow.showAtLocation(mRootView, Gravity.BOTTOM, 0, 0);
    }


    private PhotoView mIvWelfareDetail;
    private Button mBtnSave;

    private void initPopupWindowView(final int position) {
        if (mPopupWindowView == null) {
            mPopupWindowView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_welfare_detail, null);
            mIvWelfareDetail = (PhotoView) mPopupWindowView.findViewById(R.id.iv_welfare_detail);
            mIvWelfareDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPopupWindow.dismiss();
                }
            });
            mBtnSave = (Button) mPopupWindowView.findViewById(R.id.btn_save);
        }
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtnSave.setClickable(false);
                new AsyncTask<String, Void, Boolean>() {

                    @Override
                    protected Boolean doInBackground(String... params) {
                        Bitmap bitmap = ImageUtil.getBitMapByUrl(params[0]);
                        if (bitmap != null) {
                            String url = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(),
                                    bitmap, "title", "description");
                            return true;
                        }
                        return false;
                    }

                    @Override
                    protected void onPostExecute(Boolean aBoolean) {
                        super.onPostExecute(aBoolean);
                        if (aBoolean) {
                            ToastUtil.show("保存成功");
                        } else {
                            ToastUtil.show("保存失败！");
                        }
                        mBtnSave.setClickable(true);
                    }
                }.execute(mDryGoodses.get(position).url);
            }
        });
        ImageLoader.displayImg(mIvWelfareDetail, mDryGoodses.get(position).url);
    }

    @Override
    public void onItemClick(View view, int position) {
        showWelfareDetailWindow(position);
    }
}
