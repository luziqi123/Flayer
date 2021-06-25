package com.dzf.http.baseparse;

/**
 * @author ZhuZiBo
 * @date 2017/10/30
 */
public interface HttpStateCode {

    /**
     * 成功状态码
     */
    int OK_CODE = 0;
    /**
     * 成功状态码
     */
    int OK_CODE_NEW = 200;
    /**
     * 服务器数据正常，返回的错误状态码
     */
    int ERROR_CODE = 1;


    /**
     * token
     */
    int TOKEN_CODE = 30102;

    /**
     * 其他
     */
    int OTHER_CODE = 203;

    /**
     * 异常
     */
    int ERROR_EXCEPTION = 999;

    /**
     * 数据解析异常，json解析出错
     */
    int ERROR_DATA_EXCEPTION = 10006;
    /**
     * 无网络
     */
    int STATECODE_NONETWORK = 100000;

}
