package com.xinran.fastokhttp.requestcall;

import com.xinran.fastokhttp.QxBaseCallBack;
import com.xinran.fastokhttp.QxCall;
import com.xinran.fastokhttp.requests.QxPostFormRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by qixinh on 16/3/29.
 */
public class QxPostFormCall extends QxBaseRequestCall
{
    private List<FileInput> files = new ArrayList<>();

    public QxPostFormCall addFile(String key, String filename, File file)
    {
        files.add(new FileInput(key, filename, file));
        return this;
    }

    public static class FileInput
    {
        public String key;
        public String filename;
        public File file;

        public FileInput(String name, String filename, File file)
        {
            this.key = name;
            this.filename = filename;
            this.file = file;
        }

        @Override
        public String toString()
        {
            return "FileInput{" +
                    "key='" + key + '\'' +
                    ", filename='" + filename + '\'' +
                    ", file=" + file +
                    '}';
        }
    }

    //
    @Override
    public QxPostFormCall url(String url)
    {
        this.url = url;
        return this;
    }

    @Override
    public QxPostFormCall tag(Object tag)
    {
        this.tag = tag;
        return this;
    }

    @Override
    public QxPostFormCall params(Map<String, String> params)
    {
        this.params = params;
        return this;
    }

    @Override
    public QxPostFormCall addParams(String key, String val)
    {
        if (this.params == null)
        {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }

    @Override
    public void call(QxBaseCallBack qxBaseCallBack) {
        QxCall.excute(new QxPostFormRequest(url, tag, params==null?null:params, headers==null?null:headers, files).requet(),qxBaseCallBack);
    }

    @Override
    public void call(OkHttpClient client, QxBaseCallBack qxBaseCallBack) {

       QxCall.excute(client==null?null:client,new QxPostFormRequest(url, tag, params==null?null:params, headers==null?null:headers, files).requet(),qxBaseCallBack);
    }

    @Override
    public QxPostFormCall headers(Map<String, String> headers)
    {
        this.headers = headers;
        return this;
    }


    @Override
    public QxPostFormCall addHeader(String key, String val)
    {
        if (this.headers == null)
        {
            headers = new LinkedHashMap<>();
        }
        headers.put(key, val);
        return this;
    }


}
