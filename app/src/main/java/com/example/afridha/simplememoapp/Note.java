package com.example.afridha.simplememoapp;

import android.database.Cursor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Note {
    private String title, content, dateCreated, dateModified;
    private int id;

    public Note(){

    }


//public static Note getNotefromCursor(Cursor cursor){
//        Note note = new Note();
//        note.setId(cursor.getInt(cursor.getColumnIndex("id")));
//        note.setTitle(cursor.getString(cursor.getColumnIndex("title")));
//        note.setContent(cursor.getString(cursor.getColumnIndex("content")));
//
//        //get Calendar instance
//        Calendar calendar = GregorianCalendar.getInstance();
//
//        //set the calendar time to date created
//        calendar.setTimeInMillis(cursor.getLong(cursor.getColumnIndex("created")));
//        note.setDateCreated(calendar);
//
//        //set the calendar time to date modified
//        calendar.setTimeInMillis(cursor.getLong(cursor.getColumnIndex("modified")));
//        note.setDateModified(calendar);
//        return note;
//    }

    public Note(String judul, String note, String dateCreated, String dateModified){
        this.title = judul;
        this.content = note;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }
}
