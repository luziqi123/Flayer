package com.dzf.http.ok.params;


import com.dzf.http.BuildConfig;

import java.util.HashMap;
import java.util.Map;


/**
 * 网络请求头信息，业务基础头信息
 * Created by yqh on 2016/5/9.
 */
public class HttpParams extends AbsHttpParams {

    @Override
    public Map initHttpHeader() {
        // TODO temp 这里也需要抽出去
        return null;
//        HashMap<String, String> header = new HashMap<String, String>();
//        String timestamp = String.valueOf(System.currentTimeMillis());
//        if (!TextUtils.isEmpty(SPFUtil.getToken())) {
//            header.put("token", SPFUtil.getToken());
//        }
//
//        header.put("appKey", APPKEY);
//        header.put("version", AppUtil.getVersionName(BaseApp.mContext));
//        header.put("timestamp", timestamp);
//        header.put("deviceID", AppUtil.getDeviceOnlyId());
//        header.put("sign",getSignStr(timestamp));
//
//        LLog.a("header",new Gson().toJson(header));
//        return header;
    }


    /**
     * 静态基类参数，只会赋值一次
     * @return
     */
    @Override
    public Map initBasicsParams() {

        Map<String, String> requestParams = new HashMap<>();


        return requestParams;
    }


    /**
     * 每次都会重新获取的动态参数
     * @return
     */
    @Override
    public Map initDynamicParams() {

        Map<String, String> requestParams = new HashMap<>();

        return requestParams;
    }


    @Override
    public String initDomain() {
        return BuildConfig.API;
    }


    // TODO temp 这里也需要抽出去
//    private String getSignStr(String timestamp) {
//        Map<String, Object> map = new HashMap<>();
//
//        if (!TextUtils.isEmpty(SPFUtil.getToken())) {
//            map.put("token", SPFUtil.getToken());
//        }
//        map.put("appKey",APPKEY);
//        map.put("version", AppUtil.getVersionName(BaseApp.mContext));
//        map.put("timestamp", timestamp);
//        map.put("deviceID", AppUtil.getDeviceOnlyId());
//
//        //   LLog.i("Header", new Gson().toJson(map));
//        return HeaderUtil.createSign(map, APPSECRET);
//    }
}