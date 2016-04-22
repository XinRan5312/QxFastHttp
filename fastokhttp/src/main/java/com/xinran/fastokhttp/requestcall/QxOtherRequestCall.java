package com.xinran.fastokhttp.requestcall;


import com.xinran.fastokhttp.QxBaseCallBack;
import com.xinran.fastokhttp.QxCall;
import com.xinran.fastokhttp.requests.QxOtherRequest;

import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by qixinh on 16/4/18.
 * for HEAD、DELETE、PUT等其他方法
 */
public class QxOtherRequestCall extends QxBaseRequestCall {
    private RequestBody requestBody;
    private String method;
    private String content;

    public QxOtherRequestCall(String method) {
        super();
        this.method = method;
    }

    private String appendParams(String url, Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append(url + "?");
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                sb.append(key).append("=").append(params.get(key)).append("&");
            }
        }

        sb = sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public QxOtherRequestCall requestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
        return this;
    }


    @Override
    public QxOtherRequestCall params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    @Override
    public QxOtherRequestCall addParams(String key, String val) {
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }

    @Override
    public void call(QxBaseCallBack qxBaseCallBack) {
        //TODO check other method can use params
        if (params != null && method.equals(QxOtherRequest.DELETE)) {
            url = appendParams(url, params);
        }
        QxCall.excute(new QxOtherRequest(requestBody, content, method, url, tag, params == null ? null : params, headers == null ? null : headers).requet(),qxBaseCallBack);

    }

    @Override
    public void call(OkHttpClient client, QxBaseCallBack qxBaseCallBack) {
        //TODO check other method can use params
        if (params != null && method.equals(QxOtherRequest.DELETE)) {
            url = appendParams(url, params);
        }
        QxCall.excute(client==null?null:client,new QxOtherRequest(requestBody, content, method, url, tag, params == null ? null : params, headers == null ? null : headers).requet(),qxBaseCallBack);
    }

    @Override
    public QxOtherRequestCall headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    @Override
    public QxOtherRequestCall addHeader(String key, String val) {
        if (this.headers == null) {
            headers = new LinkedHashMap<>();
        }
        headers.put(key, val);
        return this;
    }

    public QxOtherRequestCall requestBody(String content) {
        this.content = content;
        return this;
    }

    @Override
    public QxOtherRequestCall url(String url) {
        this.url = url;
        return this;
    }

    @Override
    public QxOtherRequestCall tag(Object tag) {
        this.tag = tag;
        return this;
    }
}
