package com.xinran.fastokhttp;


import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by qixinh on 16/3/30.
 */
public class QxCallUtils {
    public static Call createCall(OkHttpClient okHttpClient, Request request){
        if(okHttpClient==null||request==null)return null;
        return okHttpClient.newCall(request);
    }
}
