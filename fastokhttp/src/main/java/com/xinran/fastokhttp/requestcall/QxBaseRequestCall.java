package com.xinran.fastokhttp.requestcall;




import com.xinran.fastokhttp.QxBaseCallBack;

import java.util.Map;

import okhttp3.OkHttpClient;


/**
 * Created by qixinh on 16/4/8.
 */
public abstract class QxBaseRequestCall
{
    protected String url;
    protected Object tag;
    protected Map<String, String> headers;
    protected Map<String, String> params;

    public abstract QxBaseRequestCall url(String url);

    public abstract QxBaseRequestCall tag(Object tag);

    public abstract QxBaseRequestCall headers(Map<String, String> headers);

    public abstract QxBaseRequestCall addHeader(String key, String val);
    public abstract QxBaseRequestCall params(Map<String, String> params);

    public abstract QxBaseRequestCall addParams(String key, String val);

    public abstract void call(QxBaseCallBack qxBaseCallBack);
    public abstract void call(OkHttpClient client,QxBaseCallBack qxBaseCallBack);

}
