package com.xinran.fasthttp.retrofitrx;

import com.xinran.fasthttp.db.Repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Converter;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.Body;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by qixinh on 16/4/18.
 */
public class NetWorkFactory implements IRequestMethods {

    private NetWorks mNetWork;

    private NetWorkFactory(Converter.Factory converter) {
        init(converter);
    }

    public static class NetWorkFactoryHelper {
        public static NetWorkFactory newNettWorkFactory() {
            return new NetWorkFactory(null);
        }
    }

    public void changeConverterFactory(Converter.Factory converter) {
        init(converter);
    }

    private void init(Converter.Factory converter) {
        mNetWork = new Retrofit.Builder()
                .baseUrl(NetWorks.BASE_URL)
                .addConverterFactory(converter == null ? GsonConverterFactory.create() : converter)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(NetWorks.class);//使用了动态代理的知识
    }

    @Override
    public Call<List<String>> getNamesResultCall(@Path("user") String user, @Path("id") String id) {
        return mNetWork.getNamesResultCall(user, id);
    }

    @Override
    public Observable<ArrayList<Repo>> getReposResultObserver(@Path("user") String user, @Path("id") String id) {
        return null;
    }

    @Override
    public Observable<List<String>> getNamesResultObserver(String user, String id) {
        return null;
    }

    @Override
    public void getNamesResultCallBack(String user, String id, Callback<List<String>> callback) {

    }

    @Override
    public Observable<List<Integer>> getAges(String user, String key) {
        return null;
    }

    @Override
    public Observable<List<String>> getNamesResultObserver(Map<String, String> querys) {
        return null;
    }

    @Override
    public Observable<List<String>> postNamesResultObserver(User userBody) {
        return null;
    }

    @Override
    public Observable<List<String>> postNamesResultOtherHeader(String cacheHeader, @Body User userBody) {
        return null;
    }

    @Override
    public Call<List<String>> postNamesResultCall(User userBody) {
        return null;
    }

    @Override
    public void postNamesResultCallBack(User userBody, Callback<List<String>> callback) {

    }

    @Override
    public User updateUser(String nameField, String ageField) {
        return null;
    }
}
