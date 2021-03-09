package com.management.timemanagement.utils;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProjectTest {
    Project project = new Project(34, 112, "Test", 0);

    @Test
    public void getId() {
        Assert.assertEquals(34, project.getId());
    }

    @Test
    public void getCardColor() {
        Assert.assertEquals(112, project.getCardColor());
    }

    @Test
    public void getTitle() {
        Assert.assertEquals("Test", project.getTitle());
    }

    @Test
    public void getStatus() {
        Assert.assertEquals(0, project.getStatus());
    }
}