package com.example.arpit.jioassignment.model;

/**
 * Created by arpit on 15/6/18.
 */

public class DataModel {
    private int id;
    private int UserId;
    private String title;
    private boolean completed;

    public DataModel() {
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return UserId;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted() {
        this.completed = true;
    }


    public String getTitle() {
        return title;
    }
}
