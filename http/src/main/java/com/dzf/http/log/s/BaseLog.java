package com.dzf.http.log.s;

import android.util.Log;

import com.dzf.http.log.LLog;


public class BaseLog {

    private static final int MAX_LENGTH = 4000;

    public static void printDefault(int type, String tag, String msg) {

        int index = 0;
        int length = msg.length();
        int countOfSub = length / MAX_LENGTH;

        if (countOfSub > 0) {
            for (int i = 0; i < countOfSub; i++) {
                String sub = msg.substring(index, index + MAX_LENGTH);
                printSub(type, tag, sub);
                index += MAX_LENGTH;
            }
            printSub(type, tag, msg.substring(index, length));
        } else {
            printSub(type, tag, msg);
        }
    }

    private static void printSub(int type, String tag, String sub) {
        switch (type) {
            case LLog.V:
                Log.v(tag, sub);
                break;
            case LLog.D:
                Log.d(tag, sub);
                break;
            case LLog.I:
                Log.i(tag, sub);
                break;
            case LLog.W:
                Log.w(tag, sub);
                break;
            case LLog.E:
                Log.e(tag, sub);
                break;
            case LLog.A:
                Log.wtf(tag, sub);
                break;
        }
    }

}
