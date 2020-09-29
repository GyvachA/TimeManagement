package com.management.timemanagement.utils;

public class Project {
    private int id;
    private String cardColor;
    private String title;
    private int status;

    public Project(int id, String cardColor, String title, int status) {
        this.id = id;
        this.cardColor = cardColor;
        this.title = title;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getCardColor() {
        return cardColor;
    }

    public String getTitle() {
        return title;
    }

    public int getStatus() {
        return status;
    }
}
