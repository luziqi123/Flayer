package com.dzf.http.ok.helper;


import com.dzf.http.ok.api.AbsBaseAPIRequest;
import com.dzf.http.ok.params.AbsHttpParams;

/**
 * API助手类
 * Created by yangqinghai on 2017/2/27.
 */

public class APIBasicsHelper {
    /**用来获取静态参数*/
    private static AbsHttpParams mHttpParams;
    /**用来获取动态参数*/
    private static AbsHttpParams mDynamicHttpParams;

    private static APIBasicsHelper sInstance;

    private APIBasicsHelper(AbsHttpParams absHttpParams) {
        mHttpParams = absHttpParams;
    }

    public static APIBasicsHelper getInstance(AbsHttpParams absHttpParams){
        mDynamicHttpParams = absHttpParams;
        if (sInstance == null) {
            synchronized (APIBasicsHelper.class) {
                if (sInstance == null) {
                    sInstance = new APIBasicsHelper(absHttpParams);
                }
            }
        }
        return sInstance;
    }

    void getAPIRequest(AbsBaseAPIRequest apiRequest){
        AbsBasicHttpHelper.getInstance(mHttpParams)
                .addDynamicParams(mDynamicHttpParams.initDynamicParams())
                .get(apiRequest);

    }

    void postAPIRequest(AbsBaseAPIRequest apiRequest){
        AbsBasicHttpHelper.getInstance(mHttpParams)
                .addDynamicParams(mDynamicHttpParams.initDynamicParams())
                .post(apiRequest);

    }

    void postJsonAPIRequest(AbsBaseAPIRequest apiRequest){
        AbsBasicHttpHelper.getInstance(mHttpParams)
                .addDynamicParams(mDynamicHttpParams.initDynamicParams())
                .postJson(apiRequest);

    }

    /**
     * 清除API助手缓存信息
     */
    public void clean(){
        AbsBasicHttpHelper.getInstance(mHttpParams).clean();
        sInstance = null;
        mHttpParams = null;

    }
}
