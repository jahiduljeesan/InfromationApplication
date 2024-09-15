package com.example.rakibapplication.data_model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ItemsModel {
    @PrimaryKey (autoGenerate = true)
    int id;
    String title, writer, category,content;

    public ItemsModel() {
    }

    public ItemsModel(int id, String title, String writer, String category, String content) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.category = category;
        this.content = content;
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

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
