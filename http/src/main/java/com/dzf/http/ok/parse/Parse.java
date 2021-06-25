package com.dzf.http.ok.parse;

import android.text.TextUtils;

import com.dzf.http.ok.ResultInfo;
import com.dzf.http.ok.StateCode;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by yangqinghai on 2017/2/28.
 * 数据解析类
 */

public class Parse<T> {

    private Class<T> entityClass;
    private Type mType;

    public Parse(Class clazz) {
        Type genType = clazz.getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (params.length == 2) { //AbsBaseAPIRequest
            if (params[1] instanceof Class) {
                entityClass = (Class) params[1];
            } else {
                mType = params[1];
            }
        } else { //BaseHttpCallback
            if (params[0] instanceof Class) {
                entityClass = (Class) params[0];
            } else {
                mType = params[0];
            }
        }
    }

    /**
     * 根据项目需求，封装基类,初步解析
     */
    public ResultInfo<T> parseResponse(String result) {
        ResultInfo<T> resultInfo = new ResultInfo<>();
        try {
            JSONObject object = new JSONObject(result);
            boolean success = object.optBoolean("succ");
            String msg = object.optString("msg");
            String datas = object.optString("data");
            int code = object.optInt("code");

            if ( !TextUtils.isEmpty(datas)&& code == StateCode.STATECODE_SERVER_SUCCESS) {  //数据返回成功，并且msg不为空时，解析bean对象
                T dataBean = parseDatas(datas);
                resultInfo.setDatas(dataBean);
            }
            resultInfo.setCode(code);
            resultInfo.setMsg(msg);
            resultInfo.setSuccess(success);
            resultInfo.setObject(object);
        } catch (Exception e) {
            resultInfo.setCode(StateCode.STATECODE_DEFAULT);
        }
        return resultInfo;
    }


    /**
     * 封装datas为bean对象
     */
    private T parseDatas(String datas) {
        try {
            if (entityClass != null) {
                String classType= entityClass.getSimpleName();
                switch (classType) {
                    case "String":
                        return  (T)datas;
                    default:  //bean类
                        return new Gson().fromJson(datas, entityClass);
                }
            } else {
                return new Gson().fromJson(datas, mType);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
