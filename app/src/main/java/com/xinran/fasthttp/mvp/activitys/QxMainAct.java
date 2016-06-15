package com.xinran.fasthttp.mvp.activitys;

import android.os.Bundle;

import com.xinran.fasthttp.mvp.implview.MainActView;
import com.xinran.fasthttp.mvp.presenters.QxMainActPrensenter;

import java.util.List;

/**
 * Created by qixinh on 16/6/15.
 */
public class QxMainAct extends QxBaseMvpActvity<QxMainActPrensenter> implements MainActView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter.loadDataFromUrl("qx", "8888888");
    }

    @Override
    protected QxMainActPrensenter initPresenter() {
        return new QxMainActPrensenter(this);
    }

    @Override
    public void begainGetData() {

    }

    @Override
    public void onGetDataSuccess(List<String> list) {

    }

    @Override
    public void onGetDataFail(Throwable throwable) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachAct();
    }
}
