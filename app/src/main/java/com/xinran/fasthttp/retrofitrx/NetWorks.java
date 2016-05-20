package com.xinran.fasthttp.retrofitrx;

import com.xinran.fasthttp.db.Repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
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
import retrofit.http.PartMap;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;
import rx.Observable;

/**
 * Created by qixinh on 16/4/18.
 */
public interface NetWorks {
    /**
     * 但是需要注意的是在Android中，Callback是在主线程中调用执行的。
     * 总之下面，带返回值形式为同步，带Callback参数形式为异步请求
     */
    String BASE_URL = "";

    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })
    @GET("/users/{user}/{id}/wr")
    Call<List<String>> getNamesResultCall(@Path("user") String user, @Path("id") String id);

    @GET("/users/{user}/{id}/wr")
    Observable<List<String>> getNamesResultObserver(@Path("user") String user, @Path("id") String id);

    @GET("/repos/{user}/{id}/wr")
    Observable<ArrayList<Repo>> getReposResultObserver(@Path("user") String user, @Path("id") String id);

    @GET("/users/{user}/{id}/wr")
    void getNamesResultCallBack(@Path("user") String user, @Path("id") String id, Callback<List<String>> callback);

    @GET("/users/{user}/wr")
    Observable<List<Integer>> getAges(@Path("user") String user, @Query("key") String key);

    //更加复杂的查询可以用QueryMap
    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })
    @GET("/users/wr")
    Observable<List<String>> getNamesResultObserver(@QueryMap Map<String, String> querys);


    //Post Request body 请求体
    @Headers("Cache-Control: max-age=640000")
    @POST("/users/wr")
    Observable<List<String>> postNamesResultObserver(@Body User user);

    //Header也可以这样添加
    Observable<List<String>> postNamesResultOtherHeader(@Header("Cache-Control") String cache, @Body User user);

    @POST("/users/wr")
    Call<List<String>> postNamesResultCall(@Body User user);


    @Headers("Cache-Control: max-age=640000")
    @POST("/users/wr")
    void postNamesResultCallBack(@Body User user, Callback<List<String>> callback);


    /**
     * 注意采用一个POJO作为请求体，它会被RestAdapter进行转换。同时POST方式可以传入回调。
     * FORM ENCODED AND　MULTIPART表单域与文件上传
     *
     * @FormUrlEncoded修饰表单域，每个表单域子件key-value采用@Field修饰
     */
    @FormUrlEncoded
    @POST("/user/edit")
    User updateUser(@Field("name") String first, @Field("age") String last);


    /**
     * @Multipart修饰用于文件上传，每个Part元素用@Part修饰:
     * @Part(“fileDes”) String des 可以加一些描述信息(可以不加)
     * @Part(“file\”; filename=\”1.txt”) 格式不变，只需将1.text 对应的替换为你想在服务器生成的文件名称
     * 如果想传多个文件，多次请求，当然，也可以像表单一样(还没弄好)
     * 当然，上面这种办法的灵活性差了点，我们可以选择下面这种写法
     */
    @Multipart
    @POST("/fileabout.php")
    Call<String> uploadFile(@Part("fileName") String des,
                            @Part("file\"; filename=\"1.txt") RequestBody file);

    @Multipart
    @POST("/fileabout.php")
    Call<String> uploadFile(@PartMap Map<String, RequestBody> params);

}
