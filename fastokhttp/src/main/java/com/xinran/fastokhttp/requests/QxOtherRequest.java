package com.xinran.fastokhttp.requests;

import android.text.TextUtils;

import com.xinran.fastokhttp.utils.Exceptions;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.internal.http.HttpMethod;

/**
 * Created by qixinh on 16/4/11.
 */
public class QxOtherRequest extends QxBaseOkRequest
{
    private static MediaType MEDIA_TYPE_PLAIN = MediaType.parse("text/plain;charset=utf-8");

    private RequestBody requestBody;
    private String method;
    private String content;
    public static final String HEAD = "HEAD";
    public static final String DELETE = "DELETE";
    public static final String PUT = "PUT";
    public static final String PATCH = "PATCH";
    public QxOtherRequest(RequestBody requestBody, String content, String method, String url, Object tag)
    {
        super(url, tag, null, null);
        this.requestBody = requestBody;
        this.method = method;
        this.content = content;

    }
    public QxOtherRequest(RequestBody requestBody, String content, String method, String url, Object tag, Map<String, String> params)
    {
        super(url, tag, params, null);
        this.requestBody = requestBody;
        this.method = method;
        this.content = content;

    }
    public QxOtherRequest(RequestBody requestBody, String content, String method, String url, Object tag, Map<String, String> params,Map<String, String> headers)
    {
        super(url, tag, params, headers);
        this.requestBody = requestBody;
        this.method = method;
        this.content = content;

    }
    @Override
    protected RequestBody buildRequestBody()
    {
        if (requestBody == null && TextUtils.isEmpty(content) && HttpMethod.requiresRequestBody(method))
        {
            Exceptions.illegalArgument("requestBody and content can not be null in method:" + method);
        }

        if (requestBody == null && !TextUtils.isEmpty(content))
        {
            requestBody = RequestBody.create(MEDIA_TYPE_PLAIN, content);
        }

        return requestBody;
    }

    @Override
    protected Request buildRequest(RequestBody requestBody)
    {
        if (method.equals(PUT))
        {
            builder.put(requestBody);
        } else if (method.equals(DELETE))
        {
            if (requestBody == null)
                builder.delete();
            else
                builder.delete(requestBody);
        } else if (method.equals(HEAD))
        {
            builder.head();
        } else if (method.equals(PATCH))
        {
            builder.patch(requestBody);
        }

        return builder.build();
    }

}
