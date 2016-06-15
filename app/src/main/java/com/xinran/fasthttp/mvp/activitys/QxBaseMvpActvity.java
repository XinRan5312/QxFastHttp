package com.xinran.fasthttp.mvp.activitys;

import android.os.Bundle;

import com.xinran.fasthttp.mvp.presenters.QxBasePresenterImp;

/**
 * Created by qixinh on 16/6/15.
 */
public abstract class QxBaseMvpActvity<P extends QxBasePresenterImp> extends QxBaseActivity {
    /**
     * 之所以有个反映P是为了方便继承QxBaseMvpActvity的子类在继承的时候随时确定P的类型，此时P的类型就变成了当时指定的类型
     */
    protected P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=initPresenter();
    }

    protected abstract P initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.detachAct();
            presenter=null;
        }
    }
}
