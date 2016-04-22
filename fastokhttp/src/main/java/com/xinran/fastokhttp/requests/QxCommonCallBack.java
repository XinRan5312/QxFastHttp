package com.xinran.fastokhttp.requests;



import com.xinran.fastokhttp.QxBaseCallBack;
import com.xinran.fastokhttp.QxFastJsonUtils;

import okhttp3.Response;

/**
 * Created by qixinh on 16/4/7.
 */
public  abstract class QxCommonCallBack<T> extends QxBaseCallBack<T> {
    public QxCommonCallBack(Class<T> cls) {
        super(cls);
    }

    @Override
    public T parseResponse(Response response) throws Exception {

        return QxFastJsonUtils.parseJsonStringToAnyObjNoList(response.message(),mCls);
    }

}
