package com.dzf.http.ok.logIntercept;


import com.dzf.http.log.LLog;
import com.dzf.http.log.LLogUtil;
import com.dzf.http.ok.StateCode;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by ZZB on 2017/8/3.
 */

public class InterceptLog implements InterceptListener {
    private static final String TAG = "InterceptLog";

    @Override
    public void interceptUrlLog(String fragmentOrActivityName, String mBaseURL, String actionAPI, HashMap<String, Object> params) {
        LLogUtil.printLine(TAG, true);
        LLog.i("Url-ClassName:" + fragmentOrActivityName + "--Operate:" + params.get("operate"));
        LLog.i("Url-Url:" + mBaseURL + actionAPI);
        String jsonParams = new Gson().toJson(params);
        LLog.json(jsonParams);
        LLogUtil.printLine(TAG, false);
    }

    @Override
    public void interceptResultDataLog(String fragmentOrActivityName, String result) {
        LLogUtil.printLine(TAG, true);
        LLog.i("Result-ClassName:" + fragmentOrActivityName);
        LLog.i("Result-Json:" + result);
        LLog.json(result);
        LLogUtil.printLine(TAG, false);
    }


    @Override
    public void interceptResultErrorLog(String errorMsg, String simpleName, int statecodeErrorException) {
        LLog.i("系统异常：" + errorMsg + "， 请稍后重试" + "fragment==" + simpleName + "==errorCode==" + StateCode.STATECODE_ERROR_EXCEPTION);
    }


}
