package com.weiqianghu.drygoodscamp.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weiqianghu.drygoodscamp.base.viewholder.ViewHolderForRecyclerView;

import java.util.List;

/**
 * Created by weiqianghu on 2016/3/4.
 */
public abstract class CommonAdapterForRecycleView<T> extends RecyclerView.Adapter<ViewHolderForRecyclerView> implements View.OnClickListener {

    protected List<T> mDatas;
    protected int mItemLayoutId;

    private int footCount = 0;

    protected static final int FOOTER_VIEW = 1;

    public CommonAdapterForRecycleView(List<T> datas, int itemLayoutId) {
        this.mDatas = datas;
        this.mItemLayoutId = itemLayoutId;
    }


    @Override
    public ViewHolderForRecyclerView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(mItemLayoutId, parent, false);
        view.setOnClickListener(this);

        return ViewHolderForRecyclerView.get(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderForRecyclerView holder, int position) {
        if (position < mDatas.size()) {
            holder.itemView.setTag(position);
            convert(holder, mDatas.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (mDatas == null || mDatas.size() == 0) {
            return 0;
        }
        return mDatas.size() + footCount;
    }

    public void addFooter() {
        this.footCount = 1;
    }

    public void removeFooter() {
        this.footCount = 0;
    }

    public abstract void convert(ViewHolderForRecyclerView helper, T item);

    @Override
    public int getItemViewType(int position) {
        if (position == mDatas.size()) {
            return FOOTER_VIEW;
        }
        return super.getItemViewType(position);
    }

    private IRecycleViewItemClickListener mClickListener;

    public void setOnItemClickListener(IRecycleViewItemClickListener listener) {
        this.mClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mClickListener != null) {
            mClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

}
