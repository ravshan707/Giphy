package com.example.ravshan.giphy.models;

import io.realm.RealmObject;

public class Image extends RealmObject {
    String url;

    public String getUrl() {
        return url;
    }
}
