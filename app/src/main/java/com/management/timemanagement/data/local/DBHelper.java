package com.management.timemanagement.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "TimeManage.db";
    public static final int SCHEMA = 1;
    public static final String TABLE_TASK = "Tasks";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TASK = "task";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_STATUS = "status";

    public static final String TABLE_PROJECT = "Projects";
    public static final String PROJECT_COLOR = "color";
    public static final String PROJECT_TITLE = "title";
    public static final String PROJECT_STATUS = "status";

    public static final String TABLE_PROJECT_TASK = "ProjectTasks";
    public static final String PROJECT_TASK = "title";
    public static final String PROJECT_TASK_DESC = "description";
    public static final String PROJECT_TASK_STATUS = "status";
    public static final String PROJECT_TASK_ID = "project_id";


    DBHelper(Context c) {
        super(c, DB_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_TASK +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TASK +
                " TEXT, " + COLUMN_DESCRIPTION + " TEXT, " + COLUMN_STATUS + " INTEGER NOT NULL);");
        db.execSQL("CREATE TABLE " + TABLE_PROJECT +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PROJECT_COLOR +
                " INTEGER, " + PROJECT_TITLE + " TEXT, " + PROJECT_STATUS + " INTEGER NOT NULL);");
        db.execSQL("CREATE TABLE " + TABLE_PROJECT_TASK +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PROJECT_TASK +
                " TEXT, " + PROJECT_TASK_ID + " INTEGER, "
                + PROJECT_TASK_DESC + " TEXT, " + PROJECT_TASK_STATUS + " INTEGER NOT NULL, " +
                "FOREIGN KEY " + "(" + PROJECT_TASK_ID + ")" + " REFERENCES " + TABLE_PROJECT +
                " (" + COLUMN_ID + "));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECT + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECT_TASK + ";");
        onCreate(db);
    }

}