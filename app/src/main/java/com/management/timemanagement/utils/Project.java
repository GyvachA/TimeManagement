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

    public void setId(int id) {
        this.id = id;
    }

    public void setCardColor(int cardColor) {
        this.cardColor = cardColor;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", cardColor=" + cardColor +
                ", title='" + title + '\'' +
                ", status=" + status +
                '}';
    }
}
