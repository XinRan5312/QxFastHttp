package com.xinran.fasthttp.mvp.retrofit;
import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by qixinh on 16/6/15.
 */
public interface QxNetApiService {
    @GET("/users/{user}/{id}/wr")
    Observable<List<String>> getNamesResultObserver(@Path("user") String user, @Path("id") String id);
}
