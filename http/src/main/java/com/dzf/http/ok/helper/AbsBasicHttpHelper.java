package com.dzf.http.ok.helper;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.dzf.http.Tools;
import com.dzf.http.log.LLog;
import com.dzf.http.log.ToastUtils;
import com.dzf.http.ok.BasicsRequestBody;
import com.dzf.http.ok.CommonMediaType;
import com.dzf.http.ok.ErrorPrompt;
import com.dzf.http.ok.ResultInfo;
import com.dzf.http.ok.StateCode;
import com.dzf.http.ok.TrustSSLFactory;
import com.dzf.http.ok.api.AbsBaseAPIRequest;
import com.dzf.http.ok.logIntercept.InterceptListener;
import com.dzf.http.ok.logIntercept.InterceptLog;
import com.dzf.http.ok.params.AbsHttpParams;
import com.dzf.http.ok.params.PostParams;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.dzf.http.ok.TrustSSLFactory.DO_NOT_VERIFY;


/**
 * Created by yangqinghai on 2017/2/27.
 */

public class AbsBasicHttpHelper {

    private String TAG = AbsBasicHttpHelper.class.getSimpleName();
    private OkHttpClient okhttp;
    private OkHttpClient.Builder builder;
    private HashMap<String, String> headerMap = new HashMap<>();
    private Map baseParams = new HashMap<>();
    private Map dynamicParams = new HashMap<>();
    private Handler handler = new Handler(Looper.getMainLooper());
    private static AbsBasicHttpHelper mInstance;

    private static String mBaseURL = "";

    private static AbsHttpParams mAbsHttpParams;
    private InterceptListener mInterceptListener;

    private AbsBasicHttpHelper(AbsHttpParams absHttpParams) {
        mAbsHttpParams = absHttpParams;
        //默认配置，已提供方法修改默认配置
        builder = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .sslSocketFactory(TrustSSLFactory.getSocketFactory()) //忽略证书
                .hostnameVerifier(DO_NOT_VERIFY);
        ;
        if (mAbsHttpParams != null) {

            mBaseURL = mAbsHttpParams.initDomain();
            baseParams = mAbsHttpParams.initBasicsParams();
        }
        addInterceptListener(new InterceptLog());  //添加一个自定义Log日志拦截监听器
    }


    private void addInterceptListener(InterceptListener interceptListener) {
        this.mInterceptListener = interceptListener;
    }

    /**
     * 创建单例
     *
     * @return
     */
    public static AbsBasicHttpHelper getInstance(AbsHttpParams absHttpParams) {
        if (null == mInstance) {
            synchronized (AbsBasicHttpHelper.class) {
                if (null == mInstance) {
                    mInstance = new AbsBasicHttpHelper(absHttpParams);
                }
            }
        }
        return mInstance;
    }

    /**
     * 手动设置连接超时时间
     */
    public AbsBasicHttpHelper connectTimeout(int timeout, TimeUnit unit) {
        builder.connectTimeout(timeout, unit);
        return mInstance;
    }

    /**
     * 手动设置读取超时时间
     */
    public AbsBasicHttpHelper readTimeout(int timeout, TimeUnit unit) {
        builder.readTimeout(timeout, unit);
        return mInstance;
    }

    /**
     * 手动设置写入超时时间
     */
    public AbsBasicHttpHelper writeTimeout(int timeout, TimeUnit unit) {
        builder.writeTimeout(timeout, unit);
        return mInstance;
    }


    /**
     * 自定义HttpClient
     *
     * @param client
     */
//    public void setOkhttpClient(OkHttpClient client) {
//        okhttp = client;
//    }

    /**
     * 创建okHttpClint
     */
    private void createOkhttpClient() {
        if (okhttp == null) {
            okhttp = builder.build();
        }
    }

    /**
     * 添加请求头参数
     *
     * @param key
     * @param value
     * @return
     */
    public AbsBasicHttpHelper addHeader(String key, String value) {
        headerMap.put(key, value);
        return mInstance;
    }

    public AbsBasicHttpHelper setHeaders(Map headers) {
        if (headers != null) {
            headerMap.clear();
            headerMap.putAll(headers);
        }
        return mInstance;
    }

    /**
     * 缓存配置
     *
     * @param cacheDir
     * @param cacheSize
     * @return
     */
    public AbsBasicHttpHelper cache(File cacheDir, long cacheSize) {
        builder.cache(new Cache(cacheDir, cacheSize));
        return mInstance;
    }


    private String reSetBaseURL(AbsBaseAPIRequest apiRequest) {
        if (apiRequest != null && !TextUtils.isEmpty(apiRequest.getDomain())) {
            mBaseURL = apiRequest.getDomain();
        }
        return mBaseURL;
    }


    /**
     * 发送get请求
     *
     * @return
     */
    public Call get(AbsBaseAPIRequest apiRequest) {
        setHeaders(mAbsHttpParams.initHttpHeader());
        String mUrl = mBaseURL + apiRequest.getAPI() ;
        if (!Tools.isNetWorkConnected(APIHelper.application)) {
            apiRequest.onFinished();
            apiRequest.onFail(apiRequest.getTag(), StateCode.STATECODE_NONETWORK, null, ErrorPrompt.ERROR_NONETWORK);
            return null;
        }
        createOkhttpClient();
        //1.创建请求
        reSetBaseURL(apiRequest);
        apiRequest.getPostParams().putAll(baseParams).putAll(dynamicParams);
        mInterceptListener.interceptUrlLog(apiRequest.getTagName(), mBaseURL, apiRequest.getAPI(), apiRequest.getPostParams().getParams());

        if (apiRequest.getPostParams() != null) {
            mUrl = mBaseURL + apiRequest.getAPI() + "?";

            Iterator<Map.Entry<String, Object>> iterator = apiRequest.getPostParams().getParams().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                if (entry != null && entry.getValue() != null) {

                    mUrl = mUrl + entry.getKey() + "=" +(String) entry.getValue() + "&";

                }
            }
            mUrl = mUrl.substring(0, mUrl.length() - 1);

        }
        Request.Builder builder = createRequestBuilder(mUrl);

        Request request = builder.build();
        builder.get();
        //2.发送请求
        return executeCall(apiRequest, request);
    }


    /**
     * 发送post请求
     *
     * @return
     */
    public Call post(AbsBaseAPIRequest apiRequest) {
        setHeaders(mAbsHttpParams.initHttpHeader());
        if (!Tools.isNetWorkConnected(APIHelper.application)) {
            apiRequest.onFinished();
            apiRequest.onFail(apiRequest.getTag(), StateCode.STATECODE_NONETWORK, null, ErrorPrompt.ERROR_NONETWORK);
            return null;
        }
        createOkhttpClient();
        //1.创建请求
        reSetBaseURL(apiRequest);
        apiRequest.getPostParams().putAll(baseParams).putAll(dynamicParams);
        mInterceptListener.interceptUrlLog(apiRequest.getTagName(), mBaseURL, apiRequest.getAPI(), apiRequest.getPostParams().getParams());
        Request.Builder builder = createRequestBuilder(mBaseURL + apiRequest.getAPI());
        if (apiRequest.getPostParams() != null) {
            RequestBody requestBody = createFormBody(apiRequest.getPostParams());
            if (apiRequest.getPostParams().isMultipart()) {
                builder.post(new BasicsRequestBody(requestBody, apiRequest));
            } else {
                builder.post(requestBody);
            }
        }
        Request request = builder.build();
        //2.发送请求
        return executeCall(apiRequest, request);
    }

    /**
     * 发送postjson请求
     *
     * @return
     */
    public Call postJson(AbsBaseAPIRequest apiRequest) {
        setHeaders(mAbsHttpParams.initHttpHeader());
        if (!Tools.isNetWorkConnected(APIHelper.application)) {
            apiRequest.onFinished();
            apiRequest.onFail(apiRequest.getTag(), StateCode.STATECODE_NONETWORK, null, ErrorPrompt.ERROR_NONETWORK);
            return null;
        }
        createOkhttpClient();
        //1.创建请求
        reSetBaseURL(apiRequest);
        apiRequest.getPostParams().putAll(baseParams).putAll(dynamicParams);
        mInterceptListener.interceptUrlLog(apiRequest.getTagName(), mBaseURL, apiRequest.getAPI(), apiRequest.getPostParams().getParams());
        Request.Builder builder = createRequestBuilder(mBaseURL + apiRequest.getAPI());
        addHeader("content-type", "application/json;charset:utf-8");
        if (!TextUtils.isEmpty(apiRequest.getPostParamsJson())) {

            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody requestBody = RequestBody.create(JSON, apiRequest.getPostParamsJson());
            builder.post(requestBody);

        }
        Request request = builder.build();
        //2.发送请求
        return executeCall(apiRequest, request);
    }


    private Request.Builder createRequestBuilder(String url) {
        Request.Builder builder = new Request.Builder();
        //1.addHeader
        Iterator<Map.Entry<String, String>> iterator = headerMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            builder.addHeader(entry.getKey(), entry.getValue());
        }

        builder.url(url);
        return builder;
    }

    /**
     * 执行请求
     *
     * @param request
     * @param
     */
    private Call executeCall(final AbsBaseAPIRequest apiRequest, final Request request) {
        Call call = okhttp.newCall(request);
        apiRequest.onStart();
        LLog.d("HttpTag", "Request----\n" + request);
        call.enqueue(new Callback() {
                         @Override
                         public void onFailure(final Call call, final IOException e) {
                             handler.post(new Runnable() {
                                 @Override
                                 public void run() {
                                     mInterceptListener.interceptResultErrorLog(e.getClass().toString(), apiRequest.getTagName(), StateCode.STATECODE_ERROR_EXCEPTION);
                                     e.printStackTrace();
                                     if (apiRequest.isTagDestroyed()) return;
                                     apiRequest.onFinished();
                                     apiRequest.onFail(apiRequest.getTag(), StateCode.STATECODE_ERROR_EXCEPTION, null, ErrorPrompt.RESPONE_ERROR_EXCEPTION);
                                 }
                             });
                         }

                         @Override
                         public void onResponse(final Call call, final Response response) {
                             try {
                                 if (response.isSuccessful()) {
                                     final String result = response.body().string();
                                     LLog.d("HttpTag", "Result----\n" + result);
                                     mInterceptListener.interceptResultDataLog(apiRequest.getTagName(), result);
                                     //post data to UI Thread
//                                         final ResultInfo<String> resultInfo = (callback.mParse).parseResponse(result);


                                     final ResultInfo resultInfo = apiRequest.parseResponse(result);

                                     handler.post(new Runnable() {
                                         @Override
                                         public void run() {
                                             if (apiRequest.isTagDestroyed()) return;
                                             apiRequest.onFinished();

                                             if (resultInfo.getCode() == StateCode.STATECODE_SERVER_SUCCESS) {
                                                 apiRequest.onSuccess(apiRequest.getTag(), resultInfo.getCode(), resultInfo.getDatas(), resultInfo.getMsg(), resultInfo.getObject());
                                             } else {

                                                 if (resultInfo.getCode() == StateCode.TOKEN_TIME_OUT){
                                                     ToastUtils.showToast(resultInfo.getMsg());
                                                     // TODO temp 这里要抽出去
//                                                     apiRequest. open(LoginActivity.class);
//                                                     AppManager.finishAllActivity();

                                                 }else{
                                                     apiRequest.onFail(apiRequest.getTag(), resultInfo.getCode(), resultInfo.getDatas(), resultInfo.getMsg());
                                                 }

                                             }
                                         }
                                     });
                                 } else {
                                     mInterceptListener.interceptResultErrorLog("Response Failed", apiRequest.getTagName(), response.code());
                                     handler.post(new Runnable() {
                                         @Override
                                         public void run() {
                                             if (apiRequest.isTagDestroyed()) return;
                                             apiRequest.onFinished();
                                             apiRequest.onFail(apiRequest.getTag(), response.code(), null, ErrorPrompt.RESPONE_ERROR_SERVICE_EXCEPTION);
                                         }
                                     });
                                 }
                             } catch (Exception e) {
                                 mInterceptListener.interceptResultErrorLog(e.getClass().toString(), apiRequest.getTagName(), response.code());
                                 e.printStackTrace();
                                 handler.post(new Runnable() {
                                     @Override
                                     public void run() {
                                         if (apiRequest.isTagDestroyed()) return;
                                         apiRequest.onFinished();
                                         apiRequest.onFail(apiRequest.getTag(), response.code(), null, ErrorPrompt.RESPONE_ERROR_EXCEPTION);
                                     }
                                 });
                             }
                         }
                     }
        );
        return call;
    }


    /**
     * 创建PostBody
     *
     * @param params
     * @return
     */
    private RequestBody createFormBody(PostParams params) {
        RequestBody requestBody;
        Iterator<Map.Entry<String, Object>> iterator = params.getParams().entrySet().iterator();
        if (params.isMultipart()) {
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM); //type一定要设置，否则无法上传
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                if (entry.getValue() instanceof String) {
                    builder.addFormDataPart(entry.getKey(), (String) entry.getValue());
                } else if (entry.getValue() instanceof File) {
                    File file = (File) entry.getValue();
                    builder.addFormDataPart(entry.getKey(), file.getName(),
                            RequestBody.create(CommonMediaType.getMediaType(file), file));
                } else if (entry.getValue() instanceof List && ((List) entry.getValue()).get(0) instanceof File) {
                    //多文件同一参数时
                    List<File> fileList = (List<File>) entry.getValue();
                    for (File file : fileList) {
                        builder.addFormDataPart(entry.getKey(), file.getName(),
                                RequestBody.create(CommonMediaType.getMediaType(file), file));
                    }
                }
            }
            requestBody = builder.build();
        } else {


            FormBody.Builder builder = new FormBody.Builder();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                if (entry != null && entry.getValue() != null) {
                    builder.add(entry.getKey(), (String) entry.getValue());
                }
            }
            requestBody = builder.build();
        }
        return requestBody;
    }

    /**
     * 清除API助手缓存信息
     */
    public void clean() {
        mInstance = null;
        mAbsHttpParams = null;
    }


    /**
     * 添加动态参数
     *
     * @param map
     * @return
     */
    AbsBasicHttpHelper addDynamicParams(Map map) {
        dynamicParams = map;
        return this;
    }

}
