package com.xinran.fasthttp;

import com.xinran.fastokhttp.requests.QxCommonCallBack;

import okhttp3.Request;

/**
 * Created by qixinh on 16/4/7.
 */
public class TestStringCallBack<String> extends QxCommonCallBack<String> {
    public TestStringCallBack(Class<String> cls) {
        super(cls);
    }

    @Override
    public void onError(Request request, Exception e) {

    }

    @Override
    public void onResponse(String response) {

    }
}
