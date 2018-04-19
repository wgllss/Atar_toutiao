package com.atar.toutiao.net;

import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;


public class NetWorkInterfaces {
    private static RxJavaOkHttpRetrofit mRxJavaOkHttpRetrofit = RxJavaOkHttpRetrofit
            .getInstance();
//
//	public static void getArticleCategory(
//			CompositeSubscription comSubscription,
//			Action1<WonderfulTopicJson> onNext, String pageNo) {
//		mRxJavaOkHttpRetrofit
//				.RxJava(comSubscription,
//						mRxJavaOkHttpRetrofit.getService(MyService.class)
//								.getArticleCategory(pageNo), onNext);
//	}
//
//	public static void apiCheckUserName(CompositeSubscription comSubscription,
//			Action1<String> onNext, String userName) {
//		mRxJavaOkHttpRetrofit
//				.RxJava(comSubscription,
//						mRxJavaOkHttpRetrofit.getService(MyService.class)
//								.apiCheckUserName(userName), onNext);
//	}
}
