package com.xinran.fasthttp.mvp.fragments;

import com.xinran.fasthttp.mvp.implview.MainFragmentView;
import com.xinran.fasthttp.mvp.presenters.QxBasePresenterImp;

/**
 * Created by qixinh on 16/6/15.
 */
public  class MainFragmentPresenter extends QxBasePresenterImp<MainFragmentView> {

    public MainFragmentPresenter(MainFragmentView view) {
        attachAct(view);
    }

    @Override
    public void bindPresenter() {

    }

    @Override
    public void unbindPresenter() {

    }
}
