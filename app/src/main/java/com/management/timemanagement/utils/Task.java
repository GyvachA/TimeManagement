package com.management.timemanagement.utils;

public class Task {

    private int id;
    private String task;
    private String desc;

    Task(int _id, String _task, String _desc) {
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

    int getId() {
        return id;
    }
    String getTask() {
        return task;
    }
    String getDesc() {
        return desc;
    }

}