package com.management.timemanagement.utils;

public class Task {

    private int id;
    private String task;
    private String desc;
    private int status;
    private long deadline;

    public Task(int id, String task, String desc, int status, long deadline) {
        this.id = id;
        this.task = task;
        this.desc = desc;
        this.status = status;
        this.deadline = deadline;
    }

    public Task(int id, String task, String desc, int status) {
        this(id, task, desc, status, 0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }
}