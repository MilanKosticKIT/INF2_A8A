package com.uni.admin.aufgabe_8_x;

/**
 * Created by MK on 24.01.18.
 */

public class Tasks {

    private String id;
    private String title;
    private String note;


    public Tasks(String id, String title, String note) {
        this.id = id;
        this.title = title;
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getNote() {
        return note;
    }
}