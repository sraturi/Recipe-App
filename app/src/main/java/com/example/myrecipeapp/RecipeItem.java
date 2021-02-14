package com.example.myrecipeapp;

/**
 * A simple model class to hold a single recipe item for the recyclerview.
 */
public class RecipeItem {

    String title, imageUrl;
    int id;

    public RecipeItem(String title, String imageUrl, int id) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.id = id;
    }

    @Override
    public String toString() {
        return "RecipeItem{" +
                "title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", id=" + id +
                '}';
    }
}
