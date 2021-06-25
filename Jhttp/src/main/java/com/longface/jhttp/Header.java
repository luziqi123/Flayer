package com.longface.jhttp;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Interceptor;
import okhttp3.Response;

class Header implements Interceptor {

    ArrayList<Request.Parameter> headerList;

    public Header(ArrayList<Request.Parameter> headerList) {
        this.headerList = headerList;
    }

    @Override
    @NotNull
    public Response intercept(Chain chain) throws IOException {
        okhttp3.Request request = chain.request();
        okhttp3.Request.Builder builder = request.newBuilder();
        for (Request.Parameter parameter : headerList) {
            builder.addHeader(parameter.key, (String) parameter.value);
        }
        okhttp3.Request r = builder.build();
        return chain.proceed(r);
    }
}
