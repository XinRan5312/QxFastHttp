package com.xinran.fastokhttp.requestcall;


import com.xinran.fastokhttp.QxBaseCallBack;
import com.xinran.fastokhttp.QxCall;
import com.xinran.fastokhttp.requests.QxUpFileRequest;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by qixinh on 16/4/11.
 */
public class QxUpFileCall extends QxBaseRequestCall
{
    private File file;
    private MediaType mediaType;


    public QxUpFileCall file(File file)
    {
        this.file = file;
        return this;
    }

    public QxUpFileCall mediaType(MediaType mediaType)
    {
        this.mediaType = mediaType;
        return this;
    }

    @Override
    public QxUpFileCall url(String url)
    {
        this.url = url;
        return this;
    }

    @Override
    public QxUpFileCall tag(Object tag)
    {
        this.tag = tag;
        return this;
    }

    @Override
    public QxUpFileCall headers(Map<String, String> headers)
    {
        this.headers = headers;
        return this;
    }

    @Override
    public QxUpFileCall addHeader(String key, String val)
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
        QxCall.excute(new QxUpFileRequest(url, tag, file, mediaType==null?null:mediaType,headers==null?null:headers).requet(),qxBaseCallBack);
    }

    @Override
    public void call(OkHttpClient client, QxBaseCallBack qxBaseCallBack) {
        QxCall.excute(client,new QxUpFileRequest(url, tag, file, mediaType==null?null:mediaType,headers==null?null:headers).requet(),qxBaseCallBack);
    }
}
