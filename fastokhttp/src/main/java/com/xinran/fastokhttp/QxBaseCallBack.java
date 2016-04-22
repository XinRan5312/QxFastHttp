package com.xinran.fastokhttp;


import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by qixinh on 16/3/30.
 */
public abstract class QxBaseCallBack<T>  {
   private QxBaseCallBack() {

    }

    /**
     * UI Thread
     *
     * @param request
     */
    public void onBeforeCall(Request request)
    {
    }

    /**
     * UI Thread
     *
     * @param
     */
    public void onAfterCall()
    {
    }

    /**
     * UI Thread
     *
     * @param progress
     */
    public void inProgress(int progress)
    {

    }
    /**
     * Thread Pool Thread
     *
     * @param response
     */
    public abstract T parseResponse(Response response) throws Exception;

    public abstract void onError(Request request, Exception e);

    public abstract void onResponse(T response);

    protected Class<T> mCls;
    public QxBaseCallBack(Class<T> cls){
        if(cls==null) throw new NullPointerException("class can not be null");
        this.mCls=cls;

    }
    public static QxBaseCallBack CALLBACK_DEFAULT = new QxBaseCallBack()
    {

        @Override
        public Object parseResponse(Response response) throws Exception
        {
            return null;
        }

        @Override
        public void onError(Request request, Exception e)
        {

        }

        @Override
        public void onResponse(Object response)
        {

        }
    };


}
