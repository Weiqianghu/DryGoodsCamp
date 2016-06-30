package com.weiqianghu.drygoodscamp.base.http;

/**
 * Created by huweiqiang on 2016/6/27.
 */
public class HttpProvider<T> {
    private HttpProvider() {
    }

    private static HttpProvider instance;

    public static HttpProvider getInstance() {
        if (instance == null) {
            synchronized (HttpProvider.class) {
                if (instance == null) {
                    instance = new HttpProvider();
                }
            }
        }
        return instance;
    }

    public T getService(String baseUrl, Class<T> service) {
        return CommonRx.initRetrofit(baseUrl).create(service);
    }

}
