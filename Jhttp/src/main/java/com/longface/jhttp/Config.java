package com.longface.jhttp;

import android.app.Application;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Config {

    Application application;

    String baseUrl;

    public ArrayList<Request.Parameter> headers;

    public Retrofit retrofit;

    protected void init() {
        headers = new ArrayList<Request.Parameter>();
        Header header = new Header(headers);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(header);
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new JHttpCallAdapterFactory())
                .client(builder.build())
                .build();
    }

}
