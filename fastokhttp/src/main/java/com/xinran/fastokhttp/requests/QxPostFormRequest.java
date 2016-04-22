package com.xinran.fastokhttp.requests;


import com.xinran.fastokhttp.requestcall.QxPostFormCall;

import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by qixinh on 16/4/1.
 */
public class QxPostFormRequest extends QxBaseOkRequest {
    private List<QxPostFormCall.FileInput> files;
    public QxPostFormRequest(String url, Object tag, List<QxPostFormCall.FileInput> files) {
        super(url, tag, null, null);
        this.files = files;
    }
    public QxPostFormRequest(String url, Object tag, Map<String, String> params, List<QxPostFormCall.FileInput> files) {
        super(url, tag, params, null);
        this.files = files;
    }
    public QxPostFormRequest(String url, Object tag, Map<String, String> params, Map<String, String> headers, List<QxPostFormCall.FileInput> files) {
        super(url, tag, params, headers);
        this.files = files;
    }

    @Override
    protected RequestBody buildRequestBody() {
        if (files == null || files.isEmpty()) {
            FormBody.Builder builder = new FormBody.Builder();
            addParams(builder);
            return builder.build();
        } else {
            MultipartBody.Builder builder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM);
            addParams(builder);

            for (int i = 0; i < files.size(); i++) {
                QxPostFormCall.FileInput fileInput = files.get(i);
                RequestBody fileBody = RequestBody.create(MediaType.parse(getMimeTypeWithFilePath(fileInput.filename)), fileInput.file);
                builder.addFormDataPart(fileInput.key, fileInput.filename, fileBody);
            }
            return builder.build();
        }
    }

    @Override
    protected Request buildRequest(RequestBody requestBody) {
        return requestBody==null?null:builder.post(requestBody).build();
    }

    private void addParams(MultipartBody.Builder builder) {
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""),
                        RequestBody.create(null, params.get(key)));
            }
        }
    }

    private void addParams(FormBody.Builder builder) {
        if (params != null) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
    }

    private String getMimeTypeWithFilePath(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }
}
