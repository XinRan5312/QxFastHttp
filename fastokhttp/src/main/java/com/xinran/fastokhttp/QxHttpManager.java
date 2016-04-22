package com.xinran.fastokhttp;

import android.app.FragmentTransaction;
import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by qixinh on 16/3/30.
 */
public class QxHttpManager {
    public static final String TAG = QxHttpManager.class.getSimpleName();
    private static Handler mRunOnUIThread = new Handler(Looper.getMainLooper());
    private static OkHttpClient mOkClient = QxHttpClient.getDefaultClient();

    private QxHttpManager() {

    }

    public static void requestNet(String url, QxBaseCallBack qxBaseCallBack) {

    }

    public static void requestNet(final Request request, final QxBaseCallBack qxBaseCallBack) {

        requestNet(null, request, qxBaseCallBack);
    }

    public static void requestNet(OkHttpClient client, final Request request, final QxBaseCallBack qxBaseCallBack) {
        if (request == null || qxBaseCallBack == null) throw new NullPointerException(TAG);
        Call call = null;
        if (client == null) {
            call = mOkClient.newCall(request);
        } else {
            call = client.newCall(request);
        }
        qxBaseCallBack.onBeforeCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onCallNetFail(request, e, qxBaseCallBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() >= 400 && response.code() <= 599) {
                    onCallNetFail(request, new IOException(response.body().string()), qxBaseCallBack);
                } else {
                    try {
                        onCallNetSuccess(qxBaseCallBack, qxBaseCallBack.parseResponse(response));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public static void cancelTag(Object tag) {
        for (Call call : mOkClient.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : mOkClient.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

    private static void onCallNetFail(final Request request, final IOException e, final QxBaseCallBack qxBaseCallBack) {
        mRunOnUIThread.post(new Runnable() {
            @Override
            public void run() {

                qxBaseCallBack.onError(request, e);
            }
        });
    }

    private static void onCallNetSuccess(final QxBaseCallBack qxBaseCallBack, final Object obj) {
        mRunOnUIThread.post(new Runnable() {
            @Override
            public void run() {

                qxBaseCallBack.onResponse(obj);

            }
        });
    }
}
