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

    public void add(String task, String desc) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COLUMN_TASK, task);
        cv.put(DBHelper.COLUMN_DESCRIPTION, desc);

        db.insert(DBHelper.TABLE_NAME, DBHelper.COLUMN_ID, cv);
    }

    public int delete(int pos) {
        int check = db.delete(DBHelper.TABLE_NAME, "_id = ?", new String[]{String.valueOf(pos)});
        if(check != 0) {
            return check;
        } else {
            return -1;
        }
    }

    public Cursor getTaskDetails() {
        String[] columns = {DBHelper.COLUMN_ID, DBHelper.COLUMN_TASK, DBHelper.COLUMN_DESCRIPTION};

        return db.query(DBHelper.TABLE_NAME, columns,
                null, null, null, null, null);
    }

    void upgrade(int id, String task, String desc) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COLUMN_TASK, task);
        cv.put(DBHelper.COLUMN_DESCRIPTION, desc);

        db.update(DBHelper.TABLE_NAME, cv, DBHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

}