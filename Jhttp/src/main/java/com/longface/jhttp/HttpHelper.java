package com.longface.jhttp;

import android.app.Application;

import java.util.Arrays;

public class HttpHelper {

    public static Config config = new Config();

    public static void init(Application application  , String baseUrl) {
        config.application = application;
        config.baseUrl = baseUrl;
        config.init();
    }


    public static <I> I getServer(Class<I> clz) {
        return config.retrofit.create(clz);
    }

    public static void addHeaderPar(Request.Parameter...parameter) {
        config.headers.addAll(Arrays.asList(parameter));
    }

//    public static Request post() {
//
//    }
//
//    public static Request get() {
//
//    }
//
//    public static Request delete() {
//
//    }
//
//    public static Request put() {
//
//    }

}
