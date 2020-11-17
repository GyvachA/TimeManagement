package com.management.timemanagement.utils;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTest {
    Task test = new Task(1, "abc", "description", 0);
    Task test2 = new Task(0, "0", "0", 0);
    @Test
    public void setId() {
        test2.setId(1);
        Assert.assertEquals(1, test2.getId());

    }

    @Test
    public void setTask() {
        test2.setTask("test");
        Assert.assertEquals("test", test2.getTask());
    }

    @Test
    public void setDesc() {
        test2.setDesc("desc");
        Assert.assertEquals("desc", test2.getDesc());
    }

    @Test
    public void getId() {
        Assert.assertEquals(1, test.getId());
    }

    @Test
    public void getTask() {
        Assert.assertEquals("abc", test.getTask());
    }

    @Test
    public void getDesc() {
        Assert.assertEquals("description", test.getDesc());
    }
}