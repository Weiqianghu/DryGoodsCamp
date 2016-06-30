package com.weiqianghu.drygoodscamp.base.imageloader;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.weiqianghu.drygoodscamp.R;
import com.weiqianghu.drygoodscamp.base.view.App;

/**
 * Created by huweiqiang on 2016/6/29.
 */
public class ImageLoader {

    public static void displayImg(ImageView imageView, String url) {
        displayImg(imageView, url, R.drawable.default_img);
    }

    public static void displayImg(ImageView imageView, String url, int defaultImgRes) {
        displayImg(imageView, url, R.drawable.default_img, R.drawable.error_img);
    }

    public static void displayImg(ImageView imageView, String url, int defaultImgRes, int errorImgRes) {
        Picasso.with(App.getContext())
                .load(url)
                .placeholder(defaultImgRes)
                .error(errorImgRes)
                .into(imageView);
    }
}
