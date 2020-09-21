package com.management.timemanagement.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "TimeManage.db";
    private static final int SCHEMA = 1;
    static final String TABLE_NAME = "Tasks";
    static final String COLUMN_ID = "_id";
    static final String COLUMN_TASK = "task";
    static final String COLUMN_DESCRIPTION = "description";
    static final String COLUMN_STATUS = "status";

    DBHelper(Context c) {
        super(c, DB_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TASK +
                " TEXT, " + COLUMN_DESCRIPTION + " TEXT, " + COLUMN_STATUS + " INTEGER NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
        onCreate(db);
    }

}