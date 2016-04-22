package com.xinran.fastokhttp.requests;

import com.xinran.fastokhttp.utils.Exceptions;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by qixinh on 16/4/1.
 */
public class QxPostStringRequest extends QxBaseOkRequest {
    private static MediaType MEDIA_TYPE_PLAIN = MediaType.parse("text/plain;charset=utf-8");

    private String content;
    private MediaType mediaType;


    public QxPostStringRequest(String url, Object tag, String content)
    {
        super(url, tag, null, null);



        if (this.content == null)
        {
            Exceptions.illegalArgument("the content can not be null !");
        }else{
            this.content = content;
        }

            this.mediaType = MEDIA_TYPE_PLAIN;


    }
    public QxPostStringRequest(String url, Object tag,String content, MediaType mediaType)
    {
        super(url, tag, null, null);



        if (this.content == null)
        {
            Exceptions.illegalArgument("the content can not be null !");
        }else{
            this.content = content;
        }
        if (this.mediaType == null)
        {
            this.mediaType = MEDIA_TYPE_PLAIN;
        }else{
            this.mediaType = mediaType;
        }

    }

    @Override
    protected RequestBody buildRequestBody() {
        return RequestBody.create(mediaType, content);
    }

    @Override
    protected Request buildRequest(RequestBody requestBody) {
        return requestBody==null?null:builder.post(requestBody).build();
    }
}
