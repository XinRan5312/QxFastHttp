package com.xinran.fasthttp.mvp.fragments;

import com.xinran.fasthttp.mvp.implview.MainFragmentView;
import com.xinran.fasthttp.mvp.presenters.QxMainActPrensenter;

import java.util.List;

/**
 * Created by qixinh on 16/6/15.
 */
public class QxMainFragment extends QxBaseMvpFragment<MainFragmentPresenter> implements MainFragmentView{
    @Override
    protected MainFragmentPresenter initPresenter() {
        return new MainFragmentPresenter(this);
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
}
