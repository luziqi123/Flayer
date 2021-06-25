package com.dzf.http.ok.helper;


import android.app.Application;

import com.dzf.http.ok.api.AbsBaseAPIRequest;
import com.dzf.http.ok.params.HttpParams;

/**
 * API助手类
 * Created by yqh on 2016/5/9.
 * ---------------------------
 * 增加取消请求方法
 * Updated by JunyiZhou on 2016/6/23
 */
public class APIHelper {

    public static Application application;
    /**
     * http头信息实例
     */
    private static HttpParams mHttpParams;

    /**
     * 默认POST方式发送API请求
     *
     * @param absAPIRequestNew
     */
    public static void postAPI(AbsBaseAPIRequest absAPIRequestNew) {
        if (mHttpParams == null) {
            mHttpParams = new HttpParams();
        }
        APIBasicsHelper.getInstance(mHttpParams).postAPIRequest(absAPIRequestNew);
    }


    /**
     * get方式发送API请求
     *
     * @param absAPIRequestNew
     */
    public static void getAPI(AbsBaseAPIRequest absAPIRequestNew) {
        if (mHttpParams == null) {
            mHttpParams = new HttpParams();
        }
        APIBasicsHelper.getInstance(mHttpParams).getAPIRequest(absAPIRequestNew);
    }


    /**
     * json方式发送API请求
     *
     * @param absAPIRequestNew
     */
    public static void jsonAPI(AbsBaseAPIRequest absAPIRequestNew) {
        if (mHttpParams == null) {
            mHttpParams = new HttpParams();
        }
        APIBasicsHelper.getInstance(mHttpParams).postJsonAPIRequest(absAPIRequestNew);
    }


    /**
     * 清空网络助手缓存信息
     */
    public static void cleanHttpParams() {
        APIBasicsHelper.getInstance(mHttpParams).clean();
        mHttpParams = null;
    }


}
