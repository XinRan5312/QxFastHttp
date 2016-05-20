package com.xinran.fasthttp.retrofitrx;

import com.xinran.fasthttp.db.Repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;
import rx.Observable;

/**
 * Created by qixinh on 16/4/18.
 */
public interface IRequestMethods {

    /**
     * 但是需要注意的是在Android中，Callback是在主线程中调用执行的。
     * 总之下面，带返回值形式为同步，带Callback参数形式为异步请求
     */

    Call<List<String>> getNamesResultCall(@Path("user") String user, @Path("id") String id);

    Observable<ArrayList<Repo>> getReposResultObserver(@Path("user") String user, @Path("id") String id);

    Observable<List<String>> getNamesResultObserver( String user, String id);


    void getNamesResultCallBack(String user,String id, Callback<List<String>> callback);


    Observable<List<Integer>> getAges(String user,String key);

    //更加复杂的查询可以用QueryMap

    Observable<List<String>> getNamesResultObserver(Map<String, String> querys);


    Observable<List<String>> postNamesResultObserver(User userBody);

    //Header也可以这样添加
    Observable<List<String>> postNamesResultOtherHeader(String cacheHeader,@Body User userBody);


    Call<List<String>> postNamesResultCall(User userBody);


    void postNamesResultCallBack(User userBody, Callback<List<String>> callback);


    /**
     * 注意采用一个POJO作为请求体，它会被RestAdapter进行转换。同时POST方式可以传入回调。
     * FORM ENCODED AND　MULTIPART表单域与文件上传
     *
     * @FormUrlEncoded修饰表单域，每个表单域子件key-value采用@Field修饰
     *
     */

    User updateUser(String nameField,String ageField);


    /**
     *  @Multipart修饰用于文件上传，每个Part元素用@Part修饰:
     */

//    @Multipart
//    @PUT("/user/photo")
//    User updateUser(@Part("photo") TypedFile photo, @Part("description") TypedString desc);
}
