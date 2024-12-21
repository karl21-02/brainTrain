package com.example.braintrain.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static String NAME = "user.db";
    public static int version = 1;

    public DataBaseHelper(Context context) {
        super(context, NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table if not exists user(id integer primary key autoincrement, email text, password text);";
        sqLiteDatabase.execSQL(sql);
    }

    /**
     * 테이블이 수정되거나 구조가 변경될 경우 onUpgrade 수행
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(newVersion > 1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS user");
        }
    }
}
