package com.xinran.fasthttp.mvp;

/**
 * Created by qixinh on 16/6/15.
 */
public interface QxBasePresenter<K> {
    void attachAct(K act);
    void detachAct();

}
