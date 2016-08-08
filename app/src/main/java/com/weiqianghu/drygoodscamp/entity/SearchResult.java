package com.weiqianghu.drygoodscamp.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Description ${Desc}
 * Author huweiqiang
 * Date 2016/8/5.
 */
public class SearchResult implements Parcelable {
    public String desc;
    public String ganhuo_id;
    public String publishedAt;
    public String readability;
    public String type;
    public String url;
    public String who;

    public SearchResult() {
    }

    protected SearchResult(Parcel in) {
        desc = in.readString();
        ganhuo_id = in.readString();
        publishedAt = in.readString();
        readability = in.readString();
        type = in.readString();
        url = in.readString();
        who = in.readString();
    }

    public static final Creator<SearchResult> CREATOR = new Creator<SearchResult>() {
        @Override
        public SearchResult createFromParcel(Parcel in) {
            return new SearchResult(in);
        }

        @Override
        public SearchResult[] newArray(int size) {
            return new SearchResult[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(desc);
        dest.writeString(ganhuo_id);
        dest.writeString(publishedAt);
        dest.writeString(readability);
        dest.writeString(type);
        dest.writeString(url);
        dest.writeString(who);
    }
}
