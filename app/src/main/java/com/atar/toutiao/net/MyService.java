package com.atar.toutiao.net;

import com.atar.toutiao.modles.VideoPathResponse;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by tuotuodi on 2018/5/14.
 */

public interface MyService {

    @POST("http://service.iiilab.com/video/toutiao")
    Observable<VideoPathResponse> getVideoPath(@Query("link") String link, @Query("r") String r, @Query("s") String s);
}
