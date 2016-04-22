package com.xinran.fastokhttp.requests;

import com.xinran.fastokhttp.utils.Exceptions;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by qixinh on 16/4/11.
 */
public class QxUpFileRequest extends QxBaseOkRequest {

    private static MediaType MEDIA_TYPE_STREAM = MediaType.parse("application/octet-stream");

    private File file;
    private MediaType mediaType;
    public QxUpFileRequest(String url, Object tag, File file){
        super(url, tag, null, null);
        if (this.file == null) {
            Exceptions.illegalArgument("the file can not be null !");
        } else {
            this.file = file;
        }
        this.mediaType = MEDIA_TYPE_STREAM;

    }
    public QxUpFileRequest(String url, Object tag, File file, MediaType mediaType){
        super(url, tag, null, null);
        if (this.file == null) {
            Exceptions.illegalArgument("the file can not be null !");
        } else {
            this.file = file;
        }
        if (this.mediaType == null) {
            this.mediaType = MEDIA_TYPE_STREAM;
        } else {
            this.mediaType = mediaType;
        }
    }
    public QxUpFileRequest(String url, Object tag, File file, MediaType mediaType, Map<String, String> headers) {
        super(url, tag, null, headers);
        if (this.file == null) {
            Exceptions.illegalArgument("the file can not be null !");
        } else {
            this.file = file;
        }
        if (this.mediaType == null) {
            this.mediaType = MEDIA_TYPE_STREAM;
        } else {
            this.mediaType = mediaType;
        }


    }
    @Override
    protected RequestBody buildRequestBody() {
        return RequestBody.create(mediaType, file);
    }

    @Override
    protected Request buildRequest(RequestBody requestBody) {
        return requestBody == null ? null : builder.post(requestBody).build();
    }
}
