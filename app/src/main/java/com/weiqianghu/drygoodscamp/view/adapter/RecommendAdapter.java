package com.weiqianghu.drygoodscamp.view.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weiqianghu.drygoodscamp.R;
import com.weiqianghu.drygoodscamp.base.adapter.CommonAdapterForRecycleView;
import com.weiqianghu.drygoodscamp.base.viewholder.ViewHolderForRecyclerView;
import com.weiqianghu.drygoodscamp.entity.DryGoods;
import com.weiqianghu.drygoodscamp.widget.Kanner;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by huweiqiang on 2016/6/30.
 */
public class RecommendAdapter extends CommonAdapterForRecycleView<DryGoods> {
    private static final int HEADER = 1001;

    private Set<String> mWelfares = new TreeSet<>();
    private Kanner mKanner;


    public RecommendAdapter(List<DryGoods> datas) {
        super(datas, R.layout.recommend_item);

    }

    @Override
    public void convert(ViewHolderForRecyclerView helper, DryGoods item) {

        helper.setText(R.id.tv_title, item.desc);
        helper.setText(R.id.tv_who, "来自于" + item.who);
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
        } else if ("App".equals(item.type)) {
            helper.setImageResource(R.id.iv_type, R.drawable.app);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolderForRecyclerView holder, int position) {
        if (position != 0) {
            holder.itemView.setTag(position);
            convert(holder, mDatas.get(position - 1));
        } else {
            convertHeaderView(holder);
        }
    }

    private void convertHeaderView(ViewHolderForRecyclerView holder) {
        if (mKanner == null) {
            mKanner = holder.getView(R.id.kanner);
        }
        String[] urlArray = new String[0];

        if (mWelfares != null && mWelfares.size() > 0) {
            mKanner.setImagesUrl(mWelfares.toArray(urlArray));
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

    public void setWelfares(Set<String> welfares) {
        if (welfares != null && welfares.size() > 0) {
            mWelfares.clear();
            mWelfares = welfares;
        }
    }
}
