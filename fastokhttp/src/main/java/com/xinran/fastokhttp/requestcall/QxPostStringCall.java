package com.xinran.fastokhttp.requestcall;


import com.xinran.fastokhttp.QxBaseCallBack;
import com.xinran.fastokhttp.QxCall;
import com.xinran.fastokhttp.requests.QxPostStringRequest;

import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by qixinh on 16/4/12.
 */
public class QxPostStringCall extends QxBaseRequestCall {
    private String content;
    private MediaType mediaType;


    @Override
    public QxPostStringCall url(String url)
    {
        this.url = url;
        return this;
    }

    @Override
    public QxPostStringCall tag(Object tag)
    {
        this.tag = tag;
        return this;
    }


    @Override
    public QxPostStringCall headers(Map<String, String> headers)
    {
        this.headers = headers;
        return this;
    }

    @Override
    public QxPostStringCall addHeader(String key, String val)
    {
        if (this.headers == null)
        {
            headers = new LinkedHashMap<>();
        }
        headers.put(key, val);
        return this;
    }

    @Override
    public QxBaseRequestCall params(Map<String, String> params) {
        return null;
    }

    @Override
    public QxBaseRequestCall addParams(String key, String val) {
        return null;
    }

    @Override
    public void call(QxBaseCallBack qxBaseCallBack) {
        QxCall.excute(new QxPostStringRequest(url, tag, content, mediaType==null?null:mediaType).requet(),qxBaseCallBack);
    }

    @Override
    public void call(OkHttpClient client, QxBaseCallBack qxBaseCallBack) {
        QxCall.excute(client==null?null:client,new QxPostStringRequest(url, tag, content, mediaType==null?null:mediaType).requet(),qxBaseCallBack);
    }

    public QxPostStringCall content(String content)
    {
        this.content = content;
        return this;
    }

    public QxPostStringCall mediaType(MediaType mediaType)
    {
        this.mediaType = mediaType;
        return this;
    }
}
