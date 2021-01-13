package com.management.timemanagement.utils;

public class QandA {
    private int id;
    private String userName;
    private String userAnswer;
    private String moderName;
    private String moderAnswer;
    private int status;

    public QandA(int id, String userName, String userAnswer, String moderName, String moderAnswer, int status) {
        this.id = id;
        this.userName = userName;
        this.userAnswer = userAnswer;
        this.moderName = moderName;
        this.moderAnswer = moderAnswer;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getModerName() {
        return moderName;
    }

    public void setModerName(String moderName) {
        this.moderName = moderName;
    }

    public String getModerAnswer() {
        return moderAnswer;
    }

    public void setModerAnswer(String moderAnswer) {
        this.moderAnswer = moderAnswer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
