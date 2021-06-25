package com.dzf.http.ok.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.dzf.http.ok.ProgressView;
import com.dzf.http.ok.ResultInfo;
import com.dzf.http.ok.params.PostParams;
import com.dzf.http.ok.parse.Parse;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * 数据接口请求基类
 * Created by ZZB on 2017/12/14.
 * T传入的 Tag为任意对象，最好为当前Activity或Fragment或View，只有为Activity或Fragment或view时，才能使用Loading
 * K传入ResultInfo中的datas类型,即结果Bean对象或bean集合
 */

public abstract class AbsBaseAPIRequest<T, K> {

    private PostParams mPostParams = new PostParams();
    private WeakReference<T> mReference;
    private ProgressView mDialog;
    private String mDialogMsg;
    /**
     * 是否显示处理中loading
     */
    private boolean mShowProcessing = false;

    public AbsBaseAPIRequest(T reference) {
        if (reference != null) {
            mReference = new WeakReference<T>(reference);
        }
    }

    public AbsBaseAPIRequest(T reference, boolean showProcessing) {
        if (reference != null) {
            mReference = new WeakReference<T>(reference);
        }
        mShowProcessing = showProcessing;
    }

    public AbsBaseAPIRequest(T reference, boolean showProcessing, String dialogMsg) {
        if (reference != null) {
            mReference = new WeakReference<T>(reference);
        }
        mShowProcessing = showProcessing;
        this.mDialogMsg = dialogMsg;
    }
    public String getDomain() {
        return null;
    }


    /**
     * 只有Activity与Fragment、View才能获取到当前activity引用
     * @return
     */
    private Activity getActivity() {
        if (getTag() != null) {
            if (mReference.get() instanceof Fragment) {
                return ((Fragment) mReference.get()).getActivity();
            }
            if (mReference.get() instanceof Activity) {
                return (Activity) mReference.get();
            }
            if (mReference.get() instanceof View) {
                Context context = ((View) mReference.get()).getContext();
                if (context instanceof Activity) {
                    return (Activity) context;
                }
            }
        }
        return null;
    }


    public T getTag() {
        if (mReference != null && mReference.get() != null) {
            return mReference.get();
        }
        return null;
    }

    public String getTagName() {
        if (mReference != null && mReference.get() != null) {
            return mReference.get().getClass().getSimpleName();
        }
        return "此API未绑定相应TAG";
    }

    public void addParams(String key, String value) {
        mPostParams.add(key, value);
    }
    public void addParams(String key, boolean value ) {
        mPostParams.add(key, value);
    }


   public void addParams(String key, List<File> files) {
        mPostParams.add(key, files);
    }
    public void addParams(String key, Object o) {
        mPostParams.add(key, o);
    }


    public void addParams(String key, File value) {
        mPostParams.add(key, value);
    }

    public void addParams(String key, int value) {
        mPostParams.add(key, value);
    }
    public void addParams(String key, long value) {
        mPostParams.add(key, value);
    }

    public PostParams getPostParams() {
        return mPostParams;
    }
    public String getPostParamsJson() {

        return new Gson().toJson(mPostParams.getParams());
    }
    public boolean ismShowProcessing() {
        return mShowProcessing;
    }

    public void setShowProcessing(boolean showProcessing) {
        this.mShowProcessing = showProcessing;
    }

    public abstract String getAPI();

    public abstract void onSuccess(T fragment, int resCode, K datas, String msg, JSONObject resultObject);

    public abstract void onFail(T fragment, int resCode, K datas, String msg);

    public abstract void onProgress(T fragment, long currentBytes, long totalBytes);


    /**
     * 请求开始时做的
     * 定义显示dialog
     */
    public void onStart() {
        if (mShowProcessing && mDialog == null && getActivity() != null) {
            if (TextUtils.isEmpty(mDialogMsg)){
                mDialog = ProgressView.showNoMessage(getActivity());
            }else{

                mDialog = ProgressView.showMessage(getActivity(),mDialogMsg);
            }

        }
    }

    /**
     * 请求结束做的事情
     * 不管成功失败，都会执行
     */
    public void onFinished() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    /**
     * 判断当前Tag引用是否已经销毁，或者将要销毁
     * @return
     */
    public Boolean isTagDestroyed() {
        if (getTag() == null) {
            return true;
        }
        if (getTag() instanceof Fragment) {
            return ((Fragment) getTag()).getActivity() == null || ((Fragment) getTag()).getActivity().isFinishing();
        }
        return getTag() instanceof Activity && ((Activity) getTag()).isFinishing();

    }


    /**
     * 根据项目需求，封装基类
     */
    public ResultInfo<K> parseResponse(String result) {
        ResultInfo<K> kResultInfo = new Parse<K>(getClass()).parseResponse(result);
        return kResultInfo;
    }

    /**
     * 基础库会在得到重新登录返回码的时候调用这个方法
     */
    public void exitToLogin() {
        // todo 退出到登录页面并且关闭其他页面
    }

    public void open(Class<?> cls) {
        Intent intent = new Intent(getActivity(), cls);
        getActivity().startActivity(intent);
    }
}
