package com.dzf.http.log.s;

import android.util.Log;

import com.dzf.http.log.LLog;
import com.dzf.http.log.LLogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonLog {

    public static void printJson(String tag, String msg, String headString) {

        String message;

        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(LLog.JSON_INDENT);
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(LLog.JSON_INDENT);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }

        LLogUtil.printLine(tag, true);
        message = headString + LLog.LINE_SEPARATOR + message;
        String[] lines = message.split(LLog.LINE_SEPARATOR);
        for (String line : lines) {
            Log.d(tag, "║ " + line);
        }
        LLogUtil.printLine(tag, false);
    }
}
