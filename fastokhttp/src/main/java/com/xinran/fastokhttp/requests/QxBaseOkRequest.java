package com.xinran.fastokhttp.requests;


import com.xinran.fastokhttp.utils.Exceptions;

import java.util.Map;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by qixinh on 16/4/10.
 */
public abstract class QxBaseOkRequest {
    protected String url;
    protected Object tag;
    protected Map<String, String> params;
    protected Map<String, String> headers;
    protected Request.Builder builder = new Request.Builder();

    protected QxBaseOkRequest(String url, Object tag,
                              Map<String, String> params, Map<String, String> headers) {
        this.url = url;
        this.tag = tag;
        this.params = params;
        this.headers = headers;

        if (url == null) {
            Exceptions.illegalArgument("url can not be null.");
        }

        initBuilder();
    }


    /**
     * 初始化一些基本参数 url , tag , headers
     */
    private void initBuilder() {
        builder.url(url).tag(tag);
        appendHeaders();
    }

    protected abstract RequestBody buildRequestBody();


    protected abstract Request buildRequest(RequestBody requestBody);
    public Request requet()
    {
        RequestBody requestBody = buildRequestBody();

       return buildRequest(requestBody);

    }

    private void appendHeaders() {
        if (headers != null && !headers.isEmpty()) {
            Headers.Builder headerBuilder = new Headers.Builder();


            for (String key : headers.keySet()) {
                headerBuilder.add(key, headers.get(key));
            }
            builder.headers(headerBuilder.build());
        }
    }


}
