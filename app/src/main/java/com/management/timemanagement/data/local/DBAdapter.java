package com.management.timemanagement.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBAdapter {

    protected SQLiteDatabase db;
    private DBHelper helper;
    private Context c;

    public DBAdapter(Context context) {
        c = context;
        helper = new DBHelper(c);
    }

    public DBAdapter openDB() {
        db = helper.getWritableDatabase();
        return this;
    }

    public void closeDB() {
        helper.close();
    }

    public void add(String task, String desc, int status) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COLUMN_TASK, task);
        cv.put(DBHelper.COLUMN_DESCRIPTION, desc);
        cv.put(DBHelper.COLUMN_STATUS, status);

        db.insert(DBHelper.TABLE_NAME, DBHelper.COLUMN_ID, cv);
    }

    public void addProject(String title, String color, int status) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.PROJECT_COLOR, color);
        cv.put(DBHelper.PROJECT_TITLE, title);
        cv.put(DBHelper.PROJECT_STATUS, status);

        db.insert(DBHelper.TABLE_PROJECT, DBHelper.COLUMN_ID, cv);
    }

    public int delete(int pos) {
        int check = db.delete(DBHelper.TABLE_NAME, "_id = ?", new String[]{String.valueOf(pos)});
        if(check != 0) {
            return check;
        } else {
            return -1;
        }
    }

    public Cursor getTaskDetailsNotReady() {
        String[] columns = {DBHelper.COLUMN_ID, DBHelper.COLUMN_TASK, DBHelper.COLUMN_DESCRIPTION};

        return db.query(DBHelper.TABLE_NAME, columns,
                "status=?", new String[] {"0"}, null, null, null);
    }
    public Cursor getTaskDetailsReady() {
        String[] columns = {DBHelper.COLUMN_ID, DBHelper.COLUMN_TASK, DBHelper.COLUMN_DESCRIPTION};

        return db.query(DBHelper.TABLE_NAME, columns,
                "status=?", new String[] {"1"}, null, null, null);
    }

    public Cursor getProjectDetails() {
        String[] columns = {DBHelper.COLUMN_ID, DBHelper.PROJECT_COLOR, DBHelper.PROJECT_TITLE,
        DBHelper.PROJECT_STATUS};

        return db.query(DBHelper.TABLE_PROJECT, columns, null, null, null,
                null, null);
    }

    public void upgrade(int id, String task, String desc, int status) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COLUMN_TASK, task);
        cv.put(DBHelper.COLUMN_DESCRIPTION, desc);
        cv.put(DBHelper.COLUMN_STATUS, status);

        db.update(DBHelper.TABLE_NAME, cv, DBHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

}