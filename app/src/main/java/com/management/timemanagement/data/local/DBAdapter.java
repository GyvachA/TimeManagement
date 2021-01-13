package com.management.timemanagement.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.management.timemanagement.utils.QandA;

import java.util.Calendar;
import java.util.Date;

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

    public void add(String task, String desc, int status, long deadline) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COLUMN_TASK, task);
        cv.put(DBHelper.COLUMN_DESCRIPTION, desc);
        cv.put(DBHelper.COLUMN_STATUS, status);
        cv.put(DBHelper.COLUMN_DEADLINE, deadline);

        db.insert(DBHelper.TABLE_TASK, DBHelper.COLUMN_ID, cv);
    }

    public void add(String task, String desc, int status) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COLUMN_TASK, task);
        cv.put(DBHelper.COLUMN_DESCRIPTION, desc);
        cv.put(DBHelper.COLUMN_STATUS, status);
        cv.put(DBHelper.COLUMN_DEADLINE, "null");

        db.insert(DBHelper.TABLE_TASK, DBHelper.COLUMN_ID, cv);
    }

    public void addQA(QandA qandA) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.QA_USER_NAME, qandA.getUserName());
        cv.put(DBHelper.QA_USER_ANSWER, qandA.getUserAnswer());
        cv.put(DBHelper.QA_MODER_NAME, qandA.getModerName());
        cv.put(DBHelper.QA_MODER_ANSWER, qandA.getModerAnswer());
        cv.put(DBHelper.QA_STATUS, qandA.getStatus());

        db.insert(DBHelper.QA_TABLE, DBHelper.COLUMN_ID, cv);
    }

    public void addProjectTask(String task, String desc, int status, int proj_id) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.PROJECT_TASK, task);
        cv.put(DBHelper.PROJECT_TASK_DESC, desc);
        cv.put(DBHelper.PROJECT_TASK_STATUS, status);
        cv.put(DBHelper.PROJECT_TASK_ID, proj_id);

        db.insert(DBHelper.TABLE_PROJECT_TASK, DBHelper.COLUMN_ID, cv);
    }

    public void addProject(String title, int color, int status) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.PROJECT_COLOR, color);
        cv.put(DBHelper.PROJECT_TITLE, title);
        cv.put(DBHelper.PROJECT_STATUS, status);

        db.insert(DBHelper.TABLE_PROJECT, DBHelper.COLUMN_ID, cv);
    }

    public void addUser(String login, String password) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.USER_LOGIN, login);
        cv.put(DBHelper.USER_PASSWORD, password);

        db.insert(DBHelper.USER_TABLE, DBHelper.COLUMN_ID, cv);
    }

    public int deleteTask(int pos) {
        int check = db.delete(DBHelper.TABLE_TASK, "_id = ?", new String[]{String.valueOf(pos)});
        if (check != 0) {
            return check;
        } else {
            return -1;
        }
    }

    public int deleteProjectTask(int pos) {
        int check = db.delete(DBHelper.TABLE_PROJECT_TASK, "_id = ?", new String[]{String.valueOf(pos)});
        if (check != 0) {
            return check;
        } else {
            return -1;
        }
    }

    public int deleteProject(int pos) {
        int check = db.delete(DBHelper.TABLE_PROJECT, "_id = ?", new String[]{String.valueOf(pos)});
        if (check != 0) {
            return check;
        } else {
            return -1;
        }
    }

    public Cursor getTaskDetailsNotReady() {
        String[] columns = {DBHelper.COLUMN_ID, DBHelper.COLUMN_TASK, DBHelper.COLUMN_DESCRIPTION,
                DBHelper.COLUMN_DEADLINE};

        return db.query(DBHelper.TABLE_TASK, columns,
                "status=?", new String[]{"0"}, null, null, null);
    }

    public Cursor getQuestionByUserName(String name) {
        String[] columns = {DBHelper.COLUMN_ID, DBHelper.QA_USER_NAME, DBHelper.QA_USER_ANSWER,
                DBHelper.QA_MODER_NAME, DBHelper.QA_MODER_ANSWER, DBHelper.QA_STATUS};
        return db.query(DBHelper.QA_TABLE, columns, DBHelper.QA_USER_NAME + "=?",
                new String[]{name}, null, null, null);
    }

    public Cursor getQuestionByStatusZero() {
        String[] columns = {DBHelper.COLUMN_ID, DBHelper.QA_USER_NAME, DBHelper.QA_USER_ANSWER,
                DBHelper.QA_MODER_NAME, DBHelper.QA_MODER_ANSWER, DBHelper.QA_STATUS};
        return db.query(DBHelper.QA_TABLE, columns, DBHelper.QA_STATUS + "=?",
                new String[]{String.valueOf(0)}, null, null, null);
    }

    public Cursor getTaskDetailsReady() {
        String[] columns = {DBHelper.COLUMN_ID, DBHelper.COLUMN_TASK, DBHelper.COLUMN_DESCRIPTION,
                DBHelper.COLUMN_DEADLINE};

        return db.query(DBHelper.TABLE_TASK, columns,
                "status=?", new String[]{"1"}, null, null, null);
    }

    public Cursor getProjectTaskDetails(int id) {
        String[] columns = {DBHelper.COLUMN_ID, DBHelper.PROJECT_TASK,
                DBHelper.PROJECT_TASK_DESC, DBHelper.PROJECT_TASK_STATUS,
                DBHelper.PROJECT_TASK_ID};

        return db.query(DBHelper.TABLE_PROJECT_TASK, columns,
                DBHelper.PROJECT_TASK_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null);
    }

    public Cursor getUserByLogin(String login) {
        String[] columns = {DBHelper.COLUMN_ID, DBHelper.USER_LOGIN, DBHelper.USER_PASSWORD};

        return db.query(DBHelper.USER_TABLE, columns, DBHelper.USER_LOGIN + "=?",
                new String[]{String.valueOf(login)}, null, null, null);
    }

    public Cursor getUserByLoginAndPassword(String login, String password) {
        String[] columns = {DBHelper.COLUMN_ID, DBHelper.USER_LOGIN, DBHelper.USER_PASSWORD};

        return db.query(DBHelper.USER_TABLE, columns,
                DBHelper.USER_LOGIN + "=?" + " AND " + DBHelper.USER_PASSWORD + "=?",
                new String[]{String.valueOf(login), password}, null, null, null);
    }

    public Cursor getProjectDetails() {
        String[] columns = {DBHelper.COLUMN_ID, DBHelper.PROJECT_COLOR, DBHelper.PROJECT_TITLE,
                DBHelper.PROJECT_STATUS};

        return db.query(DBHelper.TABLE_PROJECT, columns, null, null, null,
                null, null);
    }

    public void upgradeTask(int id, String task, String desc, int status, long deadline) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COLUMN_TASK, task);
        cv.put(DBHelper.COLUMN_DESCRIPTION, desc);
        cv.put(DBHelper.COLUMN_STATUS, status);
        cv.put(DBHelper.COLUMN_DEADLINE, deadline);

        db.update(DBHelper.TABLE_TASK, cv, DBHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void upgradeQuestion(int id, String moderName, String moderAnswer) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.QA_MODER_NAME, moderName);
        cv.put(DBHelper.QA_MODER_ANSWER, moderAnswer);
        cv.put(DBHelper.QA_STATUS, 1);

        db.update(DBHelper.QA_TABLE, cv, DBHelper.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)});
    }

    public void upgradeProjectTask(int id, String task, String desc, int status, int proj_id) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.PROJECT_TASK, task);
        cv.put(DBHelper.PROJECT_TASK_DESC, desc);
        cv.put(DBHelper.PROJECT_TASK_STATUS, status);
        cv.put(DBHelper.PROJECT_TASK_ID, proj_id);

        db.update(DBHelper.TABLE_PROJECT_TASK, cv, DBHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void upgradeProjectTask(int id, String task, String desc, int status) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.PROJECT_TASK, task);
        cv.put(DBHelper.PROJECT_TASK_DESC, desc);
        cv.put(DBHelper.PROJECT_TASK_STATUS, status);

        db.update(DBHelper.TABLE_PROJECT_TASK, cv, DBHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void upgradeProject(int id, int color, String title, int status) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.PROJECT_COLOR, color);
        cv.put(DBHelper.PROJECT_TITLE, title);
        cv.put(DBHelper.PROJECT_STATUS, status);

        db.update(DBHelper.TABLE_PROJECT, cv, DBHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

}