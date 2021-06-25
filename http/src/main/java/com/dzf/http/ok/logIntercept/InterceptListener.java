package com.dzf.http.ok.logIntercept;

import java.util.HashMap;

/**
 * Created by ZZB on 2017/8/3.
 */

public interface InterceptListener {
    void interceptUrlLog(String fragmentOrActivityName, String mBaseURL, String actionAPI, HashMap<String, Object> params);
    void interceptResultDataLog(String fragmentOrActivityName, String result);
    void interceptResultErrorLog(String errorMsg, String simpleName, int statecodeErrorException);
}
