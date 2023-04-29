package com.naiple.culinary_social_network.data.model;

import java.util.UUID;

public class Recipe {
    private String id;
    private String title;
    private String author;
    private int imageId;

    public Recipe(String title, String author, int imageId) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.author = author;
        this.imageId = imageId;
    }

    public Recipe(String title) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getImageId() {
        return imageId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthorId(String author) {
        this.author = author;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
