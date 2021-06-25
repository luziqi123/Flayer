package com.longface.flyer;

import android.app.Application;

import com.longface.flyermodel.App;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        App.init(this);
    }
}