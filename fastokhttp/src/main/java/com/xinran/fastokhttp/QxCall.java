package com.xinran.fastokhttp;


import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by qixinh on 16/4/7.
 */
public class QxCall {

    public static void excute(Request request,QxBaseCallBack callBack){
         QxHttpManager.requestNet(request,callBack);
    }
    public static void excute(OkHttpClient client,Request request,QxBaseCallBack callBack){
        QxHttpManager.requestNet(client,request,callBack);
    }
}
