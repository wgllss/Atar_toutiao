package com.atar.toutiao.contract;

import com.atar.toutiao.modles.NewsData;
import com.atar.toutiao.modles.NewsResponse;

import java.util.List;

import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by tuotuodi on 2018/5/15.
 */

public class NewsContract {
    public interface IView{
        void setViewList( NewsResponse newsResponse);
    }

    public interface  IPresenter{
        void getList(CompositeSubscription comSubscription);
    }
}
