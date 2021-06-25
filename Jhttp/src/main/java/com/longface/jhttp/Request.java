package com.longface.jhttp;

import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Request<T> extends LiveData<T> {

    private String method;
    private ArrayList<Parameter> parameters;
    private Object jsonBean;

    private AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    private Call<T> call;
    @Override
    protected void onActive() {
        super.onActive();
        if (atomicBoolean.compareAndSet(false , true)) {
            if (call != null) {
                call.enqueue(new Callback<T>() {
                    @Override
                    public void onResponse(@NotNull Call<T> call, @NotNull Response<T> response) {
                        postValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<T> call, Throwable t) {
                    }
                });
            }
        }
    }

    protected void adapt(Call<T> call) {
        this.call = call;
    }

    /**
     * 新增参数
     * @param key
     * @param value
     * @return
     */
    public Request addParameter(String key, Object value){
        if (parameters == null) {
            parameters = new ArrayList<>();
        }
        parameters.add(new Parameter(key, value));
        return this;
    }

    /**
     * 添加一个jsonBean
     * @param jsonBean
     * @return
     */
    public Request addJsonBean(Object jsonBean) {
        this.jsonBean = jsonBean;
        return this;
    }

//    public <T> Request next(NextRequest<T> nextRequest) {
//        // todo 添加一个链式调用
//        if (parameters != null) {
//
//        } else if (jsonBean != null) {
//
//        }
//        return this;
//    }

    /**
     * 用于存储键值对参数的类
     */
    public static class Parameter{
        String key;
        Object value;
        public Parameter(String key, Object value) {
            this.key = key;
            this.value = value;
        }
    }
}
