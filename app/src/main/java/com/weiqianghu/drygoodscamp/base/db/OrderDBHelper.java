package com.weiqianghu.drygoodscamp.base.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by huweiqiang on 2016/7/7.
 */
public class OrderDBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "dry_goods_camp.db";
    public static final String TABLE_NAME = "dryGoodses";

    public OrderDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists " + TABLE_NAME
                + " (_id char(24) primary key, " +
                "createdAt varchar(100), " +
                "desc varchar(1000), " +
                "publishedAt varchar(100)," +
                "type varchar(50)," +
                "url varchar(500)," +
                "used varchar(20)," +
                "who varchar(100))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }
}
