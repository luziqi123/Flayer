package com.longface.flyermodel.activity;

import android.os.Bundle;
import android.view.View;

import com.longface.flyermodel.R;
import com.longface.flyermodel.base.BaseActivity;
import com.longface.flyermodel.databinding.ActivityHomeBinding;

public class HomeActivity extends BaseActivity<ActivityHomeBinding> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v.pla.setContentId(R.id.btn1);
        v.logo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.v.logo.startAnim();
    }
}