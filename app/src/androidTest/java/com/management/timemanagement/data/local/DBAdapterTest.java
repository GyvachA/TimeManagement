package com.management.timemanagement.data.local;

import android.database.Cursor;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DBAdapterTest {
    DBAdapter adapter = new DBAdapter(InstrumentationRegistry.getInstrumentation().getTargetContext());
    @Test
    public void openDB() {
        Assert.assertNotNull(adapter.openDB());
        adapter.closeDB();
    }

    @Test
    public void getTaskDetailsNotReady() {
        adapter.openDB();
        Assert.assertNotNull(adapter.getTaskDetailsNotReady());
        adapter.closeDB();
    }
    @Test
    public void getTaskDetailsReady() {
        adapter.openDB();
        Assert.assertNotNull(adapter.getTaskDetailsReady());
        adapter.closeDB();
    }
    @Test
    public void getProjectDetails() {
        adapter.openDB();
        Assert.assertNotNull(adapter.getProjectDetails());
        adapter.closeDB();
    }
    @Test
    public void add() {
        adapter.openDB();
        adapter.add("test_task", "test_desc", 0);
        Cursor cursor = adapter.db.query("Tasks", new String[]{"_id"},
                "task=?", new String[] {"test_task"},
                null, null, null);
        cursor.moveToNext();
        int id = cursor.getInt(0);
        Assert.assertNotNull(cursor);
        adapter.delete(id);
        adapter.closeDB();
    }
    @Test
    public void delete() {
        adapter.openDB();
        Assert.assertNotEquals(adapter.delete(1234), 0);
        adapter.closeDB();
    }

}