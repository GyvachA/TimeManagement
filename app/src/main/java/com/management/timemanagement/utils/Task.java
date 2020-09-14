package com.management.timemanagement.utils;

public class Task {

    private int id;
    private String task;
    private String desc;

    public Task(int _id, String _task, String _desc) {
        id = _id;
        task = _task;
        desc = _desc;
    }

    public void setId(int _id) {
        id = _id;
    }
    public void setTask(String _task) {
        task = _task;
    }
    public void setDesc(String _desc) {
        desc = _desc;
    }

    public int getId() {
        return id;
    }
    public String getTask() {
        return task;
    }
    public String getDesc() {
        return desc;
    }

}