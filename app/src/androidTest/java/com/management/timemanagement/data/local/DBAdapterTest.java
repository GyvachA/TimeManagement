package com.management.timemanagement.data.local;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.espresso.internal.inject.InstrumentationContext;
import androidx.test.platform.app.InstrumentationRegistry;

import com.management.timemanagement.ui.to_do.To_DoFragment;
import com.management.timemanagement.ui.to_do.To_DoViewModel;

import org.junit.Assert;
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
    public void add() {
        adapter.openDB();
        adapter.add("task ", "desc 1", 0);
        Assert.assertNotNull(adapter.db.query("Tasks", new String[] {"task"},
                "task=?", new String[] {"task 1"},
                null, null, null));
        adapter.closeDB();
    }

    @Test
    public void getTaskDetails() {
        adapter.openDB();
        Assert.assertNotNull(adapter.getTaskDetailsNotReady());
        adapter.closeDB();
    }
}