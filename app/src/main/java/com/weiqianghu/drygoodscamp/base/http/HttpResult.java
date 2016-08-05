package com.weiqianghu.drygoodscamp.base.http;

import java.util.List;

/**
 * Created by huweiqiang on 2016/6/27.
 */
public class HttpResult<T> {
    public boolean error;
    public List<T> results;
    public int count;
}
