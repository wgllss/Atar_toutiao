package com.atar.toutiao.net;

import com.atar.toutiao.modles.NewsResponse;
import com.atar.toutiao.modles.VideoPathResponse;

import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class NetWorkInterfaces {
    private static RxJavaOkHttpRetrofit mRxJavaOkHttpRetrofit = RxJavaOkHttpRetrofit
            .getInstance();

    public static void getNewsList(CompositeSubscription comSubscription,
                                   Action1<NewsResponse> onNext, String category, long lastTime, long currentTime) {
        mRxJavaOkHttpRetrofit
                .RxJava(comSubscription,
                        mRxJavaOkHttpRetrofit.getService(APIService.class)
                                .getNewsList(category, lastTime, currentTime), onNext);
    }

    public static void getVidioDetal(CompositeSubscription comSubscription,
                                     Action1<VideoPathResponse> onNext, String link, String r, String s) {
        mRxJavaOkHttpRetrofit
                .RxJava(comSubscription,
                        mRxJavaOkHttpRetrofit.getService(APIService.class)
                                .getVideoPath(link, r, s), onNext);
    }

    public static void getVidioDetal2(CompositeSubscription comSubscription,
                                     Action1<VideoPathResponse> onNext, String link, String r, String s) {
        mRxJavaOkHttpRetrofit
                .RxJava(comSubscription,
                        mRxJavaOkHttpRetrofit.getService(MyService.class)
                                .getVideoPath(link, r, s), onNext);
    }
}
