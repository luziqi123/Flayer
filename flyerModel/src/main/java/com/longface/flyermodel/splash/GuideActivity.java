package com.longface.flyermodel.splash;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.longface.flyermodel.base.BaseActivity;
import com.longface.flyermodel.databinding.ActivityGuideBinding;

public class GuideActivity extends BaseActivity<ActivityGuideBinding> {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setClick(v.tipAnim);
    }

    int i = 0;
    @Override
    public void onClick(View v) {
        this.v.tipAnim.skipTo(i++ % 4 + 1);
    }

    static class FragmentAdapter extends FragmentStateAdapter {
        public FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        public FragmentAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }

        public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return null;
        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}