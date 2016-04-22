package com.xinran.fastokhttp.requestcall;


import com.xinran.fastokhttp.QxBaseCallBack;
import com.xinran.fastokhttp.QxCall;
import com.xinran.fastokhttp.requests.QxOKGetRequest;

import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.OkHttpClient;

/**
 * Created by qixinh on 16/4/1.
 */
public class QxGetReqCall extends QxBaseRequestCall {


    @Override
    public QxGetReqCall url(String url) {
        this.url = url;
        return this;
    }

    @Override
    public QxGetReqCall tag(Object tag) {
        if(tag==null) throw new NullPointerException("request tag can not be null");
        this.tag = tag;
        return this;
    }

    @Override
    public QxGetReqCall headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    @Override
    public QxGetReqCall addHeader(String key, String val) {
        if (this.headers == null) {
            headers = new LinkedHashMap<>();
        }
        headers.put(key, val);
        return this;
    }

    @Override
    public QxGetReqCall params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    @Override
    public QxGetReqCall addParams(String key, String val) {
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }

    private String urlParams(String url, Map<String, String> params)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(url + "?");
        if (params != null && !params.isEmpty())
        {
            for (String key : params.keySet())
            {
                sb.append(key).append("=").append(params.get(key)).append("&");
            }
        }

        sb = sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    @Override
    public void call(QxBaseCallBack qxBaseCallBack) {
        if(params!=null&&!params.isEmpty()){
            url=urlParams(url,params);
        }
       QxCall.excute(new QxOKGetRequest(url, tag, headers == null ? null : headers).requet(), qxBaseCallBack);
    }

    @Override
    public void call(OkHttpClient client, QxBaseCallBack qxBaseCallBack) {
        if(params!=null&&!params.isEmpty()){
            url=urlParams(url,params);
        }
         QxCall.excute(client==null?null:client,new QxOKGetRequest(url,tag,headers==null?null:headers).requet(),qxBaseCallBack);
    }

}
