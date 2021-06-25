package com.dzf.http;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;

import com.dzf.http.ok.helper.APIHelper;
import com.pnikosis.materialishprogress.BuildConfig;

import org.json.JSONArray;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by hongliang on 2016/7/6.
 */
public class Tools {


    public static View inflate(int layoutId) {

        return View.inflate(APIHelper.application, layoutId, null);
    }

    /**
     * 检测网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetWorkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }

        return false;
    }

    //检查网络类型
    public static boolean networkType(Context context) {
        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        int netType = info.getType();
        if (null != info) {
            if (ConnectivityManager.TYPE_WIFI == netType) {
                return true;
            } else if (ConnectivityManager.TYPE_MOBILE == netType) {
                return false;
            }
        }
        return false;
    }


    public static boolean isNewVersion(String nowVerStr1, String newStr2) {

        if (!TextUtils.isEmpty(nowVerStr1) && !TextUtils.isEmpty(newStr2)) {
            nowVerStr1 = nowVerStr1.replace(".", "");
            newStr2 = newStr2.replace(".", "");

            //版本2.0与2.0.0相同
            int len = nowVerStr1.length() - newStr2.length();
            if (len > 0) {
                for (int i = 0; i < len; i++) {
                    newStr2 = newStr2 + "0";
                }
            } else {
                for (int i = 0; i < -len; i++) {
                    nowVerStr1 = nowVerStr1 + "0";
                }
            }
            int version1 = Integer.parseInt(nowVerStr1);
            int version2 = Integer.parseInt(newStr2);
            if (version1 == version2) {
                return false;
            } else if (version1 < version2) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    /**
     * 获取屏幕的宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        Resources res = context.getResources();
        return res.getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        Resources res = context.getResources();
        return res.getDisplayMetrics().heightPixels;
    }

    /**
     * 判断应用是否已经启动
     *
     * @param context     一个context
     * @param packageName 要判断应用的包名
     * @return boolean
     */
    public static boolean isAppAlive(Context context, String packageName) {
        ActivityManager activityManager =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfos
                = activityManager.getRunningAppProcesses();
        for (int i = 0; i < processInfos.size(); i++) {
            if (processInfos.get(i).processName.equals(packageName)) {
                Log.i("NotificationLaunch",
                        String.format("the %s is running, isAppAlive return true", packageName));
                return true;
            }
        }
        Log.i("NotificationLaunch",
                String.format("the %s is not running, isAppAlive return false", packageName));
        return false;
    }


    /**
     * dip转为 px
     */
    public static int dip2px(float dipValue) {
        final float scale = APIHelper.application.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    public static Handler getHandler() {
        return new Handler(Looper.getMainLooper());
    }

    public static int getMainThreadId() {
        return android.os.Process.myTid();
    }

    public static boolean isRunInMainThread() {
        //运行在主线程中
        return getMainThreadId() == android.os.Process.myTid();
    }

    public static void runInMainThread(Runnable runnable) {
        //运行在主线程
        if (isRunInMainThread()) {
            runnable.run();
        } else {
            getHandler().post(runnable);
        }
    }

    public static void postDelayed(Runnable runnable, int delayed) {
        //延时做任务方法
        getHandler().postDelayed(runnable, delayed);
    }

    //移除指定任务的操作
    public static void removeCallBack(Runnable runnable) {
        getHandler().removeCallbacks(runnable);
    }


    /**
     * 判断某个界面是否在前台
     *
     * @param context
     * @param className 某个界面名称
     */
    public static boolean isForeground(Context context, String className) {
        if (context == null || TextUtils.isEmpty(className)) {
            return false;
        }

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (className.equals(cpn.getClassName())) {
                return true;
            }
        }

        return false;
    }

    public static boolean isAppRunningForeground(Context var0) {
        ActivityManager var1 = (ActivityManager) var0.getSystemService(var0.ACTIVITY_SERVICE);
        List var2 = var1.getRunningTasks(1);
        boolean var3 = var0.getPackageName().equalsIgnoreCase(((ActivityManager.RunningTaskInfo) var2.get(0)).baseActivity.getPackageName());
        return var3;
    }


    /**
     * 去除特殊字符或将所有中文标号替换为英文标号
     *
     * @param str
     * @returnSisSis
     */
    public static String stringPattern(String str) {
        str = str.replaceAll("【", "[").replaceAll("】", "]")
                .replaceAll("！", "!").replaceAll("：", ":");// 替换中文标号
        String regEx = "[『』]"; // 清除掉符
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    public static String stringFilter(String str) {
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return stringPattern(new String(c));
    }

    /**
     * 获取颜色兼容
     *
     * @param context
     * @param color
     * @return
     */
    @ColorInt
    public static int getColor(Context context, @ColorRes int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getResources().getColor(color, null);
        } else {
            return context.getResources().getColor(color);
        }
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha 0.5置灰    1.0恢复
     */
    public static void backgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND); // 解决华为手机中置灰失效的问题
        activity.getWindow().setAttributes(lp);
    }


    /**
     * @param c
     * @param pn 手机号
     * @return
     */
    public static void phoneCall(Context c, String pn) {
        if (TextUtils.isEmpty(pn)) {

            return;
        }
        Intent intent1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + pn));
        c.startActivity(intent1);
    }

    public static void phoneCallNoMsg(Context c, String pn) {
        if (!TextUtils.isEmpty(pn)) {
            Intent intent1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + pn));
            c.startActivity(intent1);
        }
    }

    /**
     * 把字符串变为 数组格式
     *
     * @param v ts
     * @return ["ts"]
     */
    public static String strToArr(String v) {
        JSONArray array = new JSONArray();
        array.put(v);
        return array.toString();
    }

    public static String moneyResult2Bit(String value) {


        if (TextUtils.isEmpty(value)) return "0.00";
        if (value.contains(",")) return value;
        BigDecimal b = new BigDecimal(value);
        return new DecimalFormat("#,##0.00").format(b);


    }

    /**
     * 金额保留两位小数
     *
     * @param d
     * @return
     */
    public static String doubleTo2Bit(double d) {
        DecimalFormat df = null;
        try {
            df = new DecimalFormat("0.##");
            return df.format(d);
        } catch (Exception e) {
        }
        return "";
    }

    public static String getMoneyTwoSpotStr(double s) {
        return String.format("%.2f", s);
    }

    public static <T> boolean isEmpty(List<T> list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }

    public static void toAppMarketRate(Context context) {
        Uri uri = Uri.parse("market://details?id=" + BuildConfig.APPLICATION_ID);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }


    /**
     * Is fast click boolean.
     *
     * @return the boolean
     */

    private static final int MIN_DELAY_TIME = 1000;
    // 两次点击间隔不能少于1000ms
    private static long lastClickTime;

    public static boolean isFastClick() {

        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (timeD >= 0 && timeD <= MIN_DELAY_TIME) {
            return true;
        } else {
            lastClickTime = time;
            return false;
        }


    }

    /**
     * meizu status textcolor
     *
     * @param dark
     * @param window
     * @return
     */
    public static boolean setMeizuStatusBarDark(boolean dark, Window window) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {

            }
        }
        return result;
    }


    /**
     * Sets status bar dark font.
     * 设置状态栏字体颜色，android6.0以上
     */
    private static boolean setStatusBar23DarkFont(boolean dark, Window window) {
        boolean flag = false;
        try {
            int uiFlags;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && dark) {
                uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            }
            window.getDecorView().setSystemUiVisibility(uiFlags);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;

    }

    /**
     * xiaomi status textcolor
     *
     * @param darkmode 是否设置为黑色
     * @param activity 页面
     */
    public static void setStatusBarDarkMode(boolean darkmode, Activity activity) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getTime(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parse = format.parse(time);
            return new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(parse);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String getString(int stringId) {
        return APIHelper.application.getResources().getString(stringId);
    }
}
