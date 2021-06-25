package com.dzf.http.ok;

import org.json.JSONObject;

/**
 * Created by ZZB on 2017/2/24.
 * 结果返回的JsonObject基类
 */

public class ResultInfo<T> {
    private String message;   //返回消息说明
    private T datas;//业务接口返回的json数据
    private int code;
    private boolean success;  //判断数据是否返回成功
    private JSONObject object;    //所有的返回信息

    public String getMsg() {
        return message;
    }

    public void setMsg(String message) {
        this.message = message;
    }

    public T getDatas() {
        return datas;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public JSONObject getObject() {
        return object;
    }

    public void setObject(JSONObject object) {
        this.object = object;
    }


    @Override
    public String toString() {
        return "ResultInfo{" +
                "message='" + message + '\'' +
                ", datas=" + datas +
                ", code=" + code +
                ", success=" + success +
                ", object=" + object +
                '}';
    }
}
