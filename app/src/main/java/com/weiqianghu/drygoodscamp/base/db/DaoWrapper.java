package com.weiqianghu.drygoodscamp.base.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.weiqianghu.drygoodscamp.base.view.App;
import com.weiqianghu.drygoodscamp.entity.DryGoods;
import com.weiqianghu.drygoodscamp.utils.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by huweiqiang on 2016/7/7.
 */
public class DaoWrapper {
    private static OrderDao mOrderDao = new OrderDao(App.getContext());

    public static void insert(List<DryGoods> dryGoodses) {
        List<ContentValues> contentValueses = new ArrayList<>();

        for (DryGoods dryGoods : dryGoodses) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("_id", dryGoods._id);
            contentValues.put("createdAt", DateUtil.format(DateUtil.parse(dryGoods.createdAt)));
            contentValues.put("desc", dryGoods.desc);
            contentValues.put("publishedAt", DateUtil.format(DateUtil.parse(dryGoods.publishedAt)));
            contentValues.put("type", dryGoods.type);
            contentValues.put("url", dryGoods.url);
            contentValues.put("used", dryGoods.used);
            contentValues.put("who", dryGoods.who);
            contentValueses.add(contentValues);
        }

        mOrderDao.insert(OrderDBHelper.TABLE_NAME, contentValueses);
    }

    public static List<DryGoods> query(Date date) {
        Cursor cursor = mOrderDao.query(OrderDBHelper.TABLE_NAME, null,
                "publishedAt = ?", new String[]{DateUtil.format(date)}
                , null, null, null, null);

        if (cursor.getCount() > 0) {
            List<DryGoods> dryGoodses = new ArrayList<DryGoods>(cursor.getCount());
            while (cursor.moveToNext()) {
                DryGoods dryGoods = parseOrder(cursor);
                dryGoodses.add(dryGoods);
            }
            cursor.close();
            return dryGoodses;
        }
        cursor.close();
        return new ArrayList<>();
    }

    private static DryGoods parseOrder(Cursor cursor) {
        DryGoods dryGoods = new DryGoods();
        dryGoods._id = cursor.getString(0);
        dryGoods.createdAt = cursor.getString(1);
        dryGoods.desc = cursor.getString(2);
        dryGoods.publishedAt = cursor.getString(3);
        dryGoods.type = cursor.getString(4);
        dryGoods.url = cursor.getString(5);
        dryGoods.used = cursor.getString(6);
        dryGoods.who = cursor.getString(7);

        return dryGoods;
    }
}
