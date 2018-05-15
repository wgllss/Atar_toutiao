package com.atar.toutiao.presenter;

import com.atar.toutiao.contract.NewsContract;
import com.atar.toutiao.modles.News;
import com.atar.toutiao.modles.NewsData;
import com.atar.toutiao.modles.NewsResponse;
import com.atar.toutiao.net.NetWorkInterfaces;
import com.google.gson.Gson;

import java.util.List;

import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by tuotuodi on 2018/5/15.
 */

public class ImpNewsPresenper extends  BasePresenter<NewsContract.IView> implements NewsContract.IPresenter {
    @Override
    public void getList(CompositeSubscription mCompositeSubscription) {
        long time = System.currentTimeMillis() / 1000;
        NetWorkInterfaces.getNewsList(mCompositeSubscription, new Action1<NewsResponse>() {
            @Override
            public void call(NewsResponse newsResponse) {
                mView.setViewList(newsResponse);
            }
        }, "video", time, time);
    }
}
