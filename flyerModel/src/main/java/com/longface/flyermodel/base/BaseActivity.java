package com.longface.flyermodel.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.gyf.immersionbar.ImmersionBar;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

import me.yokeyword.fragmentation.SupportActivity;

public abstract class BaseActivity<T extends ViewBinding> extends SupportActivity implements View.OnClickListener {

    protected T v;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
            Class<T> cls = (Class<T>) parameterizedType.getActualTypeArguments()[0];
            Method inflate = cls.getDeclaredMethod("inflate", LayoutInflater.class);
            v = (T) inflate.invoke(null, getLayoutInflater());
            assert v != null;
            setContentView(v.getRoot());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //在BaseActivity里初始化
        ImmersionBar.with(this).fitsSystemWindows(false).transparentStatusBar().init();
        getSupportActionBar().hide();
    }

    public void setClick(View... views) {
        for (View view : views) {
            view.setOnClickListener(this);
        }
    }

    public  <A extends AppCompatActivity> void startActivity(Class<A> acyClass) {
        startActivity(acyClass, Integer.MIN_VALUE);
    }

    public <A extends AppCompatActivity> void startActivity(Class<A> acyClass, int requestCode) {
        startActivity(acyClass, requestCode, null);
    }

    public <A extends AppCompatActivity> void startActivity(Class<A> acyClass, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, acyClass);
        if (bundle != null) {
            intent.putExtra("bundleData", bundle);
        }
        if (requestCode == Integer.MIN_VALUE) {
            startActivity(intent);
        } else {
            startActivityForResult(intent, requestCode);
        }
    }
}
