package com.weiqianghu.drygoodscamp.base.http;

/**
 * Created by huweiqiang on 2016/6/29.
 */
public class HttpErrorException extends Throwable {
    public HttpErrorException() {
        super();
    }

    public HttpErrorException(Throwable cause) {
        super(cause);
    }

    public HttpErrorException(String detailMessage) {
        super(detailMessage);
    }

    public HttpErrorException(String detailMessage, Throwable cause) {
        super(detailMessage, cause);
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
