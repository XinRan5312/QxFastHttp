package com.xinran.fastokhttp.requests;

import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by qixinh on 16/4/10.
 */
public class QxOKGetRequest extends QxBaseOkRequest {
    public QxOKGetRequest(String url, Object tag) {
        super(url, tag, null, null);
    }
    public QxOKGetRequest(String url, Object tag, Map<String, String> headers) {
        super(url, tag, null, headers);
    }

    @Override
    protected RequestBody buildRequestBody() {
        return null;
    }

    @Override
    protected Request buildRequest(RequestBody requestBody) {
        return builder.get().build();
    }
    public static class Builder{
        String url;
        Object tag;
        Map<String, String> headers;
        public Builder(String url, Object tag){
            this.url=url;
            this.tag=tag;
        }
      public Builder addHeaders(Map<String, String> headers){
          this.headers=headers;
          return this;
      }
        public QxOKGetRequest build(Builder builder){
            return new QxOKGetRequest(builder.url,builder.tag,builder.headers==null?null:builder.headers);
        }
    }
}
