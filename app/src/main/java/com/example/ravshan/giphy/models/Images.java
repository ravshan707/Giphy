package com.example.ravshan.giphy.models;

import io.realm.RealmObject;

public class Images extends RealmObject {
    Image fixedHeight;
    Image fixedWidth;

    public Image getFixedHeight() {
        return fixedHeight;
    }

    public Image getFixedWidth() {
        return fixedWidth;
    }

}
