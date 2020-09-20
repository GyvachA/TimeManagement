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
    public void getTaskDetails() {
        adapter.openDB();
        Assert.assertNotNull(adapter.getTaskDetails());
        adapter.closeDB();
    }
    @Test
    public void add() {
        adapter.openDB();
        adapter.add("test_task", "test_desc");
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