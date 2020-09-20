package com.management.timemanagement.data.local;

import androidx.test.platform.app.InstrumentationRegistry;

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
}