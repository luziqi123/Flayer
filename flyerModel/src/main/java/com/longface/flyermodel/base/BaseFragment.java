package com.longface.flyermodel.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

import me.yokeyword.fragmentation.SupportFragment;

public abstract class BaseFragment<T extends ViewBinding> extends SupportFragment implements View.OnClickListener {

    protected T v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        try {
            ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
            Class<T> cls = (Class<T>) type.getActualTypeArguments()[0];
            Method inflate = cls.getDeclaredMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
            v = (T) inflate.invoke(null, inflater, container, false);
        }  catch (Exception e) {
            e.printStackTrace();
        }
        assert v != null;
        return v.getRoot();
    }

    public void setClick(View... views) {
        for (View view : views) {
            view.setOnClickListener(this);
        }
    }

}
