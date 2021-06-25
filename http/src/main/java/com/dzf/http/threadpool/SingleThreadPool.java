package com.dzf.http.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName PictureIdentifyActivity
 * @Author dingmingzhong
 * @Date 2020-04-14
 * @Description: 线程池管理类
 */
public class SingleThreadPool extends ThreadPoolExecutor {
    public SingleThreadPool() {
        super(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }
}
