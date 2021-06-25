package com.longface.flyermodel.splash;

import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.View;

import com.longface.flyermodel.Constant;
import com.longface.flyermodel.R;
import com.longface.flyermodel.base.BaseActivity;
import com.longface.flyermodel.databinding.ActivitySplashBinding;
import com.longface.flyermodel.utils.Edit;

import java.io.IOException;

public class SplashActivity extends BaseActivity<ActivitySplashBinding> implements SurfaceHolder.Callback {

    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);  //无title
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);  //全屏
        super.onCreate(savedInstanceState);

//        if (!Edit.getBoolean(Constant.isFirstOpen, false)) {
//            startActivity(GuideActivity.class);
//            return;
//        }
        setClick(v.buttion);
        settingSufaceView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player.isPlaying()) {
            player.stop();
        }
        player.release();
    }

    @Override
    public void onClick(View v) {
        startActivity(GuideActivity.class);
        Edit.save(Constant.isFirstOpen, false);
        finish();
    }

    private void settingSufaceView() {
        SurfaceHolder holder = v.video.getHolder();
        holder.addCallback(this);
        holder.setKeepScreenOn(true);
        player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (!player.isPlaying()) {
                    player.start();
                }
            }
        });
        try {
            AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.start_video);
            player.setDataSource(file.getFileDescriptor(), file.getStartOffset(),
                    file.getLength());
            player.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
            player.setLooping(true);
            player.prepare();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {}
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        player.setDisplay(holder);
    }




//    SplashVM splashVM1 = ViewModelProviders.of(this).get(SplashVM.class);
//        splashVM1.splashInit().observe(this, integer -> {
//    });
//        splashVM1.splashInit().observe(this, new Observer<Integer>() {
//        @Override
//        public void onChanged(Integer integer) {
//
//        }
//    });

}