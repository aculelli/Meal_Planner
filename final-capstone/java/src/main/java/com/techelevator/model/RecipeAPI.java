package com.techelevator.model;

public class RecipeAPI {
    private long id;
    private String title;

    public RecipeAPI(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "RecipeAPI{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
