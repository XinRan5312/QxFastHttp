package com.xinran.fasthttp.mvp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinran.fasthttp.mvp.presenters.QxBasePresenterImp;

/**
 * Created by qixinh on 16/6/15.
 */
public abstract class QxBaseMvpFragment<P extends QxBasePresenterImp> extends QxBaseFragment {
    protected P presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract P initPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachAct();
    }
}
