package com.example.ravshan.giphy.models;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Gif extends RealmObject {
    @PrimaryKey
    String id;
    Images images;

    //local key
    String query;

    public Images getImages() {
        return images;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
