package com.weiqianghu.drygoodscamp.base.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by huweiqiang on 2016/7/7.
 */
public class OrderDao {
    private Context mContext;
    private OrderDBHelper mOrderDBHelper;

    public OrderDao(Context context) {
        this.mContext = context;
        mOrderDBHelper = new OrderDBHelper(context);
    }

    public void insert(String tableName, ContentValues contentValues) {
        SQLiteDatabase db = mOrderDBHelper.getWritableDatabase();
        db.beginTransaction();

        db.insertOrThrow(tableName, null, contentValues);
        db.setTransactionSuccessful();

        db.close();
    }

    public void insert(String tableName, List<ContentValues> contentValueses) {
        SQLiteDatabase db = mOrderDBHelper.getWritableDatabase();
        db.beginTransaction();

        for (ContentValues contentValues : contentValueses) {
            db.insertOrThrow(tableName, null, contentValues);
        }

        db.setTransactionSuccessful();
        db.endTransaction();

        db.close();
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        SQLiteDatabase readableDatabase = mOrderDBHelper.getReadableDatabase();
        return readableDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
    }
}
