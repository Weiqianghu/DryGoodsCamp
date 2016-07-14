package com.weiqianghu.drygoodscamp.base.viewholder;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.weiqianghu.drygoodscamp.base.imageloader.ImageLoader;

/**
 * Created by 胡伟强 on 2016/1/18.
 */
public class ViewHolderForRecyclerView extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews = null;
    private static View mConvertView;

    private ViewHolderForRecyclerView(View view) {
        super(view);
        mConvertView = view;
        this.mViews = new SparseArray<View>();
    }


    public static ViewHolderForRecyclerView get(View view) {
        return new ViewHolderForRecyclerView(view);
    }


    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {

        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolderForRecyclerView setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    public ViewHolderForRecyclerView setChecked(int viewId, boolean isChecked) {
        CheckBox view = getView(viewId);
        view.setChecked(isChecked);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public ViewHolderForRecyclerView setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        ImageLoader.displayImg(view, drawableId);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param bm
     * @return
     */
    public ViewHolderForRecyclerView setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    public ViewHolderForRecyclerView setImageUrl(int viewId, String url) {
        ImageView view = getView(viewId);
        ImageLoader.displayImg(view, url);
        return this;
    }


    public void setViewVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        if (visible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.INVISIBLE);
        }
    }

    public void setRating(int viewId, int value) {
        RatingBar ratingBar = getView(viewId);
        if (value > 0 && value <= 5) {
            ratingBar.setRating(value);
        }
    }

}
