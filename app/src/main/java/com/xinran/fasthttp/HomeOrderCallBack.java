package com.xinran.fasthttp;

import com.xinran.fastokhttp.requests.QxCommonCallBack;

import okhttp3.Request;

/**
 * Created by qixinh on 16/4/7.
 */
public class HomeOrderCallBack<Student> extends QxCommonCallBack<Student> {
    public HomeOrderCallBack(Class<Student> cls) {
        super(cls);
    }

    @Override
    public void onError(Request request, Exception e) {

    }

    @Override
    public void onResponse(Student response) {

    }
}
