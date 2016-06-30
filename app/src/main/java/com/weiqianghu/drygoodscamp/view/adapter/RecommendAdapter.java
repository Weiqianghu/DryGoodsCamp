package com.weiqianghu.drygoodscamp.view.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weiqianghu.drygoodscamp.R;
import com.weiqianghu.drygoodscamp.base.adapter.CommonAdapterForRecycleView;
import com.weiqianghu.drygoodscamp.base.viewholder.ViewHolderForRecyclerView;
import com.weiqianghu.drygoodscamp.entity.DryGoods;

import java.util.List;

/**
 * Created by huweiqiang on 2016/6/30.
 */
public class RecommendAdapter extends CommonAdapterForRecycleView<DryGoods> {
    private static final int HEADER = 1001;


    public RecommendAdapter(List<DryGoods> datas) {
        super(datas, R.layout.recommend_item);

    }

    @Override
    public void convert(ViewHolderForRecyclerView helper, DryGoods item) {

        helper.setText(R.id.tv_title, item.desc);
        helper.setText(R.id.tv_who, item.who);
        if ("Android".equals(item.type)) {
            helper.setImageResource(R.id.iv_type, R.drawable.android);
        } else if ("iOS".equals(item.type)) {
            helper.setImageResource(R.id.iv_type, R.drawable.ios);
        } else if ("休息视频".equals(item.type)) {
            helper.setImageResource(R.id.iv_type, R.drawable.rest);
        } else if ("前端".equals(item.type)) {
            helper.setImageResource(R.id.iv_type, R.drawable.front);
        } else if ("瞎推荐".equals(item.type)) {
            helper.setImageResource(R.id.iv_type, R.drawable.recommend);
        } else if ("拓展资源".equals(item.type)) {
            helper.setImageResource(R.id.iv_type, R.drawable.expand);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolderForRecyclerView holder, int position) {
        super.onBindViewHolder(holder, position);
        if (position != 0) {
            convert(holder, mDatas.get(position));
        }
    }

    @Override
    public ViewHolderForRecyclerView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == HEADER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_header, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(mItemLayoutId, parent, false);
            view.setOnClickListener(this);
        }

        return ViewHolderForRecyclerView.get(view);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER;
        }
        return super.getItemViewType(position);
    }


}
