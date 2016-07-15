package com.weiqianghu.drygoodscamp.biz.handler;

import android.util.Log;

import com.weiqianghu.drygoodscamp.base.db.DaoWrapper;
import com.weiqianghu.drygoodscamp.base.http.CallBack;
import com.weiqianghu.drygoodscamp.base.http.HttpResult;
import com.weiqianghu.drygoodscamp.biz.HistoryDateBiz;
import com.weiqianghu.drygoodscamp.biz.impl.HistoryDateBizImpl;
import com.weiqianghu.drygoodscamp.common.Constant;
import com.weiqianghu.drygoodscamp.common.Global;
import com.weiqianghu.drygoodscamp.entity.DryGoods;
import com.weiqianghu.drygoodscamp.entity.TodayResult;
import com.weiqianghu.drygoodscamp.utils.DateUtil;
import com.weiqianghu.drygoodscamp.utils.SPUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by huweiqiang on 2016/7/12.
 */
public class DiskTodayRecommendHandler extends BaseHandler {
    private HistoryDateBiz mHistoryDateBiz;

    public DiskTodayRecommendHandler() {
        setSuccessor(new NetTodayRecommendHandler());
        mHistoryDateBiz = new HistoryDateBizImpl();
    }

    @Override
    public void handleRequest(final CallBack callBack) {
        final long savedUpdateDate = SPUtil.readLong(Constant.SP_KEY_UPDATE_DATE);
        Log.d("savedUpdateDate", "savedUpdateDate:" + savedUpdateDate);

        CallBack<HttpResult<String>> historyCallBack = new CallBack<HttpResult<String>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                List<DryGoods> dryGoodses = DaoWrapper.query(DateUtil.parse(savedUpdateDate));
                if (dryGoodses != null && dryGoodses.size() > 0) {
                    callBack.onNext(TodayResult.build(dryGoodses));
                } else {
                    getSuccessor().handleRequest(callBack);
                }
            }

            @Override
            public void onNext(HttpResult<String> stringHttpResult) {
                if (stringHttpResult == null || stringHttpResult.results == null || stringHttpResult.results.size() < 0) {
                    getSuccessor().handleRequest(callBack);
                } else {
                    Date date = DateUtil.parse(stringHttpResult.results.get(0));
                    long updateDate = SPUtil.readLong(Constant.SP_KEY_UPDATE_DATE);
                    if (date.getTime() > updateDate) {
                        SPUtil.save(Constant.SP_KEY_UPDATE_DATE, date.getTime());
                        Global.isUpdate = true;
                        getSuccessor().handleRequest(callBack);
                    } else {
                        List<DryGoods> dryGoodses = DaoWrapper.query(DateUtil.parse(updateDate));
                        if (dryGoodses != null && dryGoodses.size() > 0) {
                            callBack.onNext(TodayResult.build(dryGoodses));
                        } else {
                            getSuccessor().handleRequest(callBack);
                        }
                    }
                }
            }
        };

        if (DateUtil.today() <= savedUpdateDate) {
            List<DryGoods> dryGoodses = DaoWrapper.query(DateUtil.parse(savedUpdateDate));
            if (dryGoodses != null && dryGoodses.size() > 0) {
                callBack.onNext(TodayResult.build(dryGoodses));
            } else {
                getSuccessor().handleRequest(callBack);
            }
        } else {
            mHistoryDateBiz.loadHistoryDate(historyCallBack);
        }

    }

}
