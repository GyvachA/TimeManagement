package com.management.timemanagement.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "TimeManage.db";
    public static final int SCHEMA = 7;
    public static final String TABLE_TASK = "Tasks";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TASK = "task";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_DEADLINE = "deadline";

    public static final String TABLE_PROJECT = "Projects";
    public static final String PROJECT_COLOR = "color";
    public static final String PROJECT_TITLE = "title";
    public static final String PROJECT_STATUS = "status";

    public static final String TABLE_PROJECT_TASK = "ProjectTasks";
    public static final String PROJECT_TASK = "title";
    public static final String PROJECT_TASK_DESC = "description";
    public static final String PROJECT_TASK_STATUS = "status";
    public static final String PROJECT_TASK_ID = "project_id";

    public static final String USER_TABLE = "Users";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";

    public static final String QA_TABLE = "questionAs";
    public static final String QA_USER_NAME = "username";
    public static final String QA_USER_ANSWER = "userAnswer";
    public static final String QA_MODER_NAME = "moderName";
    public static final String QA_MODER_ANSWER = "moderAnswer";
    public static final String QA_STATUS = "status";



    DBHelper(Context c) {
        super(c, DB_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s INTEGER NOT NULL, %s INTEGER);", TABLE_TASK, COLUMN_ID, COLUMN_TASK, COLUMN_DESCRIPTION, COLUMN_STATUS, COLUMN_DEADLINE));
        db.execSQL(String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER, %s TEXT, %s INTEGER NOT NULL);", TABLE_PROJECT, COLUMN_ID, PROJECT_COLOR, PROJECT_TITLE, PROJECT_STATUS));
        db.execSQL(String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s INTEGER, %s TEXT, %s INTEGER NOT NULL, FOREIGN KEY (%s) REFERENCES %s (%s));", TABLE_PROJECT_TASK, COLUMN_ID, PROJECT_TASK, PROJECT_TASK_ID, PROJECT_TASK_DESC, PROJECT_TASK_STATUS, PROJECT_TASK_ID, TABLE_PROJECT, COLUMN_ID));
        db.execSQL(String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s VARCHAR(50) UNIQUE, %s VARCHAR(20));", USER_TABLE, COLUMN_ID, USER_LOGIN, USER_PASSWORD));
        db.execSQL(String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s VARCHAR(50), %s TEXT, %s VARCHAR(50), %s TEXT, %s INTEGER)", QA_TABLE, COLUMN_ID, QA_USER_NAME, QA_USER_ANSWER, QA_MODER_NAME, QA_MODER_ANSWER, QA_STATUS));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECT + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECT_TASK + ";");
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE + ";");
        db.execSQL("DROP TABLE IF EXISTS " + QA_TABLE + ";");
        onCreate(db);
    }

}