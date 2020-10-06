package com.management.timemanagement.utils;

public class Project {
    private int id;
    private int cardColor;
    private String title;
    private int status;

    public Project(int id, int cardColor, String title, int status) {
        this.id = id;
        this.cardColor = cardColor;
        this.title = title;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getCardColor() {
        return cardColor;
    }

    public String getTitle() {
        return title;
    }

    public int getStatus() {
        return status;
    }
}
