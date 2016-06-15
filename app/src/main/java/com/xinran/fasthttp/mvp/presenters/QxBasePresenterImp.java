package com.xinran.fasthttp.mvp.presenters;

import com.xinran.fasthttp.mvp.QxBasePresenter;

/**
 * Created by qixinh on 16/6/15.
 */
public abstract class QxBasePresenterImp<K> implements QxBasePresenter<K> {
    public K act;
    @Override
    public void attachAct(K act) {
        this.act=act;
        bindPresenter();
    }

    @Override
    public void detachAct() {
         this.act=null;
        unbindPresenter();
    }
    public abstract void bindPresenter();
   public  abstract void unbindPresenter();
}
