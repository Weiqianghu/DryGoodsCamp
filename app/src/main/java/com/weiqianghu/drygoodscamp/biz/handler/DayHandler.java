package com.weiqianghu.drygoodscamp.biz.handler;

import com.weiqianghu.drygoodscamp.base.http.CallBack;

/**
 * Created by huweiqiang on 2016/7/12.
 */
public abstract class DayHandler {
    /**
     * 持有后继的责任对象
     */
    protected DayHandler successor;

    /**
     * 示意处理请求的方法，虽然这个示意方法是没有传入参数的
     * 但实际是可以传入参数的，根据具体需要来选择是否传递参数
     */
    public abstract void handleRequest(CallBack callBack);

    /**
     * 取值方法
     */
    public DayHandler getSuccessor() {
        return successor;
    }

    /**
     * 赋值方法，设置后继的责任对象
     */
    public void setSuccessor(DayHandler successor) {
        this.successor = successor;
    }
}
