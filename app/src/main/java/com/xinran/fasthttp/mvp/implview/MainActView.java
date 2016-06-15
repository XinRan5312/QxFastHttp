package com.xinran.fasthttp.mvp.implview;

import java.util.List;

/**
 * Created by qixinh on 16/6/15.
 */
public interface MainActView {
    void begainGetData();
    void onGetDataSuccess(List<String> list) ;
    void onGetDataFail(Throwable throwable);
}
