package com.atar.toutiao.net;

import android.util.Log;
import android.utils.ShowLog;

import com.atar.toutiao.net.retrofitutils.HttpLoggingInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class RxJavaOkHttpRetrofit {

    private String TAG = RxJavaOkHttpRetrofit.class.getSimpleName();

    private static final String API_HOST = "";//MyService.baseUrl;

    private Retrofit retrofit;
    private final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(
            new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    ShowLog.i(TAG, message);
                }
            });
    private final OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(interceptor).build();
    private static RxJavaOkHttpRetrofit instance;

    public static RxJavaOkHttpRetrofit getInstance() {
        if (instance == null) {
            instance = new RxJavaOkHttpRetrofit();
        }
        return instance;
    }

    public <T> T getService(Class<T> cls) {
        T t = (T) getRetrofit(API_HOST).create(cls);
        return t;
    }

    public <T> T getService(String baseUrl, Class<T> cls) {
        T t = (T) getRetrofit(baseUrl).create(cls);
        return t;
    }

    private Retrofit getRetrofit(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Builder().client(client).baseUrl(baseUrl)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//支持RxJava
                    .build();
        }
        return retrofit;
    }

    public <T> void RxJava(CompositeSubscription comSubscription,
                           Observable<T> observable, Action1<T> onNext) {
        comSubscription.add(observable.compose(this.<T>applySchedulers())
                .subscribe(newSubscriber(comSubscription, onNext)));
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) transformer;
    }

    @SuppressWarnings("rawtypes")
    final Observable.Transformer transformer = new Observable.Transformer() {
        @Override
        public Object call(Object observable) {
            return ((Observable) observable).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    private <T> Subscriber<T> newSubscriber(
            final CompositeSubscription mCompositeSubscription,
            final Action1<? super T> onNext) {
        return new Subscriber<T>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError--" + String.valueOf(e.getMessage()));
            }

            @Override
            public void onNext(T t) {
                if (!mCompositeSubscription.isUnsubscribed()) {
                    onNext.call(t);
                }
            }
        };
    }
}
