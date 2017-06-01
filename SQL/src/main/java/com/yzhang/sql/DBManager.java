package com.yzhang.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * 数据库方法集合，可以将数据库的方法全部写在这里
 * Created by yzhang on 2017/2/9.
 */

public class DBManager {
    private DBOpenHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        System.out.println("open db");
        helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();
    }

    public void addPerson(Object obj) {
        db.beginTransaction();  //开始事务
        try {
            db.execSQL("INSERT INTO person VALUES(null, ?)",
                    new Object[]{});
            db.setTransactionSuccessful();  //设置事务成功完成
        } finally {
            db.endTransaction();    //结束事务
        }
    }
}
