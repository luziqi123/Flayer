package com.longface.flyer;

import com.longface.jhttp.HttpHelper;
import com.longface.jhttp.Request;

public class JavaTest {

    public static void main(String[] args) {

        HttpHelper.init(null, null);
        HttpHelper.addHeaderPar(new Request.Parameter("as", "saf"));
        System.out.println(HttpHelper.config.headers);
    }

}
