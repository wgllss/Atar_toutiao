package com.atar.toutiao.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.atar.toutiao.adapter.VideoListAdapter;
import com.atar.toutiao.contract.NewsContract;
import com.atar.toutiao.modles.News;
import com.atar.toutiao.modles.NewsData;
import com.atar.toutiao.modles.NewsResponse;
import com.atar.toutiao.presenter.ImpNewsPresenper;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerManager;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

import static fm.jiecao.jcvideoplayer_lib.JCVideoPlayer.CURRENT_STATE_PLAYING;
/**
* @author :Atar
* @createTime: 2018/5/17 16:36
* @version:1.0.0
* @modifyTime:
* @modifyAuthor:
* @description:
*/
public class OneFragment extends AtarRefreshFragment implements  NewsContract.IView {

    private String TAG = OneFragment.class.getSimpleName();

    private List<News> list = new ArrayList<News>();
    private VideoListAdapter mRecyclerAdapter = new VideoListAdapter(list);
    private ImpNewsPresenper mImpNewsPresenper;

    public static OneFragment newInstance() {
        OneFragment fragment = new OneFragment();
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImpNewsPresenper = new ImpNewsPresenper();
        mImpNewsPresenper.attach(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mImpNewsPresenper.deattach();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setLayoutManager(new GridLayoutManager(getActivity(), 1));
        mRecyclerAdapter.setmCompositeSubscription(mCompositeSubscription);
        setAdapter(mRecyclerAdapter);
        recyclerview.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (JCVideoPlayerManager.getCurrentJcvd() != null) {
                    JCVideoPlayerStandard videoPlayer = (JCVideoPlayerStandard) JCVideoPlayerManager.getCurrentJcvd();
                    if (videoPlayer.currentState == CURRENT_STATE_PLAYING) {
                        //如果正在播放
                        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                        int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                        int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();

                        if (firstVisibleItemPosition > videoPlayer.getPosition() || lastVisibleItemPosition < videoPlayer.getPosition()) {
                            //如果第一个可见的条目位置大于当前播放videoPlayer的位置
                            //或最后一个可见的条目位置小于当前播放videoPlayer的位置，释放资源
                            JCVideoPlayer.releaseAllVideos();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onRefresh( final RefreshLayout refreshLayout) {
        mImpNewsPresenper.getList(mCompositeSubscription);
    }

    @Override
    public void onLoadMore( RefreshLayout refreshLayout) {
        refreshLayout.finishLoadMore();
    }

    @Override
    public void setViewList(NewsResponse newsResponse) {
        List<NewsData> data = newsResponse.data;
        if (data != null && data.size() > 0) {
            list.clear();
            for (NewsData newsData : data) {
                News news = new Gson().fromJson(newsData.content, News.class);
                list.add(news);
            }
        }
        mRecyclerAdapter.notifyDataSetChanged();
        tipViewShow(newsResponse.tips.display_info);
        refreshLayout.finishRefresh();
    }
}
