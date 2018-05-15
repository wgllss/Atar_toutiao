package com.atar.toutiao.presenter;

/**
 * Created by tuotuodi on 2018/5/15.
 */

public class BasePresenter<V> {
    protected V mView;
    public void attach(V view){
        mView = view;
    }

    public void deattach(){
        mView = null;
    }
}
