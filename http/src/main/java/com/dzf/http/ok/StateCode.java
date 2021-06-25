package com.dzf.http.ok;

/**
 * 请求状态码：包括接口请求返回码、http状态码、自定义状态码
 * Created by yangqinghai on 2017/2/28.
 */

public class StateCode {

    /**
     * result默认状态码
     */
    public static final int STATECODE_DEFAULT = -1;
    /**
     * 接口返回码状态码：成功
     */
    public static final int STATECODE_SERVER_SUCCESS = 200;
    /**
     * 接口返回码状态码：重新登陆
     */
    public static final int TOKEN_TIME_OUT = 30102;

    /**
     * 接口返回码状态码：重新登陆
     */
    public static final int LOGIN_AGAIN = 40001;
    /**
     * 接口返回码状态码：失败
     */
    public static final int STATECODE_SERVER_ERROR = 1;
    /**
     * 客户端网络模块捕获异常或服务端响应异常
     */
    public static final int STATECODE_ERROR_EXCEPTION = 200000;

    /**
     * 无网络
     */
    public static final int STATECODE_NONETWORK = 100000;

    /**
     * 数据解析异常，json解析出错
     */
    public static final int ERROR_DATA_EXCEPTION = 100006;

}
