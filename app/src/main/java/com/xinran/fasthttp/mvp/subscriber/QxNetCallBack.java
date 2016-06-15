package com.xinran.fasthttp.mvp.subscriber;

/**
 * Created by qixinh on 16/6/15.
 */
public interface QxNetCallBack<E> {
    void onCallSuccess(E data);
    void onCallFail(Throwable throwable,String erroMsg);
    void onCallEnd();
}
