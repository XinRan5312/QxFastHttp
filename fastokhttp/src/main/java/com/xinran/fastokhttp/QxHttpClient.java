package com.xinran.fastokhttp;

import android.os.Environment;


import com.xinran.fastokhttp.cookie.SimpleCookieJar;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Cache;
import okhttp3.CookieJar;
import okhttp3.OkHttpClient;

/**
 * Created by qixinh on 16/3/30.
 */
public class QxHttpClient {
    public static OkHttpClient getDefaultClient(){
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.connectTimeout(5,TimeUnit.SECONDS);
        if(createHttpCache()!=null) {
          builder.cache(createHttpCache());
        }
        /**
         * Sets the handler that can accept cookies from incoming HTTP responses and provides cookies to
         * outgoing HTTP requests.
         *
         * <p>If unset, {@linkplain CookieJar#NO_COOKIES no cookies} will be accepted nor provided.
         */
        builder.cookieJar(new SimpleCookieJar());
        /**
         * Sets the verifier used to confirm that response certificates apply to requested hostnames for
         * HTTPS connections.
         *
         * <p>If unset, a default hostname verifier will be used.
         */
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        return builder.build();
    }
    public static Cache createHttpCache(){
        Cache cache=null;
        if(Environment.isExternalStorageEmulated()){
            File file=new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"qx");
            if(!file.exists()){
                file.mkdirs();
            }
            cache=new Cache(file,24*1024);
        }
        return cache;
    }
}
