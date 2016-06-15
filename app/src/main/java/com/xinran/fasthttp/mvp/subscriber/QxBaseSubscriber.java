package com.xinran.fasthttp.mvp.subscriber;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by qixinh on 16/6/15.
 */
public class QxBaseSubscriber<T> extends Subscriber<T> {
    private QxNetCallBack<T> callback;

    public void setCallback(QxNetCallBack<T> callback) {
        this.callback = callback;
    }

    public QxBaseSubscriber(QxNetCallBack<T> callback) {
        this.callback = callback;
    }

    @Override
    public void onCompleted() {
        callback.onCallEnd();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            //httpException.response().errorBody().string()
            int code = httpException.code();
            String msg = httpException.getMessage();
            if (code == 504) {
                msg = "网络不给力";
            }
            callback.onCallFail(e, msg);
        } else {
            callback.onCallFail(e, e.getMessage());
        }
       callback.onCallEnd();
    }

    @Override
    public void onNext(T t) {

    }
}
