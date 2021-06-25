package com.longface.flyermodel;

import android.content.Context;

import com.longface.flyermodel.utils.Edit;

public class App {

    public static Context context;

    public static void init(Context context) {
        App.context = context;
        Edit.init();

    }
}
