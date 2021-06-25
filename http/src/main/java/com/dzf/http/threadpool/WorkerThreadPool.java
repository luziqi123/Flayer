package com.dzf.http.threadpool;

import android.annotation.TargetApi;
import android.os.Build;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName PictureIdentifyActivity
 * @Author dingmingzhong
 * @Date 2020-04-14
 * @Description: 线程池管理类
 */
public class WorkerThreadPool extends ThreadPoolExecutor {
    public static final String TAG = "WorkerThreadPool";
    private final static int MAX_THREAD_SIZE = 4;
    private final static Long THREAD_LIFETIME = 10L;
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public WorkerThreadPool() {
        super(1, MAX_THREAD_SIZE,
                THREAD_LIFETIME, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>()
        );
    }


}
