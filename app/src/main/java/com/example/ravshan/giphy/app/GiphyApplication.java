package com.example.ravshan.giphy.app;

import androidx.multidex.MultiDexApplication;

import io.realm.Realm;

public class GiphyApplication extends MultiDexApplication {
    private static GiphyApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        mContext = this;
    }

}
