package com.atar.toutiao.utils;

import android.application.CommonApplication;
import android.common.CommonHandler;
import android.util.Log;
import android.utils.ShowLog;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.atar.toutiao.modles.VideoPathResponse;
import com.atar.toutiao.net.NetWorkInterfaces;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/2/9 0009.
 */

public abstract class VideoPathDecoder {

    private static String TAG = VideoPathDecoder.class.getSimpleName();

    private static final String NICK = "chaychan";

    public void decodePath(final CompositeSubscription mCompositeSubscription, final String srcUrl) {
        final WebView webView = new WebView(CommonApplication.getContext());

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);//设置JS可用
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        ParseRelation relation = new ParseRelation(new IGetParamsListener() {
            @Override
            public void onGetParams(String r, String s) {
                ShowLog.e(TAG, "r-->" + r + "--s-->" + s);
                sendRequest(mCompositeSubscription, srcUrl, r, s);
            }

            @Override
            public void onGetPath(String path) {
                ShowLog.e(TAG, "onGetPath--path--" + path);
                onSuccess(path);
            }
        });

        webView.addJavascriptInterface(relation, NICK);//绑定JS和Java的联系类，以及使用到的昵称

//        webView.loadUrl("file:///android_asset/parse.html");
        webView.loadUrl(srcUrl);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
//                webView.loadUrl("javascript:getParseParam('" + srcUrl + "')");
                CommonHandler.getInstatnce().getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        addJs(webView);
                    }
                }, 0);

            }
        });
    }

    private void addJs(WebView webView) {
        webView.loadUrl("javascript:(function  getVideoPath(){" +
                "var videos = document.getElementsByTagName(\"video\");" +
                "var path = videos[0].src;" +
                "window.chaychan.onGetPath(path);" +
                "})()");

    }

    private void sendRequest(final CompositeSubscription mCompositeSubscription, final String srcUrl, String r, String s) {
        NetWorkInterfaces.getVidioDetal(mCompositeSubscription, new Action1<VideoPathResponse>() {
            @Override
            public void call(VideoPathResponse videoPathResponse) {
                switch (videoPathResponse.retCode) {
                    case 200:
                        //请求成功
                        List<VideoPathResponse.DataBean.VideoBean.DownloadBean> downloadList = videoPathResponse.data.video.download;
                        if (downloadList != null && downloadList.size() > 0) {
                            String url = downloadList.get(downloadList.size() - 1).url;//获取下载地址中最后一个地址，即超清
                            ShowLog.e(TAG, "url-->" + url);
                            onSuccess(url);
                        }
                        break;
                    case 400:
                        decodePath(mCompositeSubscription, srcUrl);//如果请求失败，可能是生成的r s请求参数不正确，重新再调用
                        break;
                }
            }
        }, srcUrl, r, s);
    }


    public abstract void onSuccess(String url);

    public abstract void onDecodeError();


    private class ParseRelation {

        IGetParamsListener mListener;

        public ParseRelation(IGetParamsListener listener) {
            mListener = listener;
        }

        @JavascriptInterface
        public void onReceiveParams(String r, String s) {
            mListener.onGetParams(r, s);
        }

        @JavascriptInterface
        public void onGetPath(String path) {
            mListener.onGetPath(path);
        }
    }

    public interface IGetParamsListener {
        void onGetParams(String r, String s);

        void onGetPath(String path);
    }

}
