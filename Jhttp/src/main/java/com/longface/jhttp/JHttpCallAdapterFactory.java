package com.longface.jhttp;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public class JHttpCallAdapterFactory extends CallAdapter.Factory {

    @Override
    public CallAdapter<?, ?> get(@NotNull Type returnType, Annotation @NotNull [] annotations, @NotNull Retrofit retrofit) {
        if (getRawType(returnType) != Request.class) {
            return null;
        }
        return new JHttpCallAdapter(returnType , annotations , retrofit);
    }

    /**
     * Adapter类
     */
    public static class JHttpCallAdapter<R> implements CallAdapter<R , Request> {

        Type returnType;
        Annotation [] annotations;
        Retrofit retrofit;

        public JHttpCallAdapter(Type returnType, Annotation[] annotations, Retrofit retrofit) {
            this.returnType = returnType;
            this.annotations = annotations;
            this.retrofit = retrofit;
        }

        @Override
        public Type responseType() {
            return returnType;
        }

        @Override
        public Request adapt(Call<R> call) {
            Request<R> request = new Request<>();
            request.adapt(call);
            return request;
        }
    }
}
