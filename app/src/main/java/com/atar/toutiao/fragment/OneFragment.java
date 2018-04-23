package com.atar.toutiao.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atar.toutiao.R;
import com.atar.toutiao.adapter.VideoListAdapter;
import com.atar.toutiao.modles.News;
import com.atar.toutiao.modles.NewsData;
import com.atar.toutiao.modles.NewsResponse;
import com.atar.toutiao.net.NetWorkInterfaces;
import com.atar.toutiao.widget.TipView;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerManager;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

import static fm.jiecao.jcvideoplayer_lib.JCVideoPlayer.CURRENT_STATE_PLAYING;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener {

    private View rootView;
    @Bind(R.id.tip_view)
    TipView mTipView;
    @Bind(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @Bind(R.id.rv_recyclerview_data)
    RecyclerView recyclerView;

    private List<News> list = new ArrayList<News>();
    private VideoListAdapter mRecyclerAdapter = new VideoListAdapter(list);

    protected CompositeSubscription mCompositeSubscription = new CompositeSubscription();
    ;

    public static OneFragment newInstance() {
        OneFragment fragment = new OneFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_one, container, false);
            ButterKnife.bind(this, rootView);
        } else {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        mRecyclerAdapter.setmCompositeSubscription(mCompositeSubscription);
        recyclerView.setAdapter(mRecyclerAdapter);

        refreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);
        refreshLayout.autoRefresh();
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
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
    public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
        long time = System.currentTimeMillis() / 1000;
        NetWorkInterfaces.getNewsList(mCompositeSubscription, new Action1<NewsResponse>() {
            @Override
            public void call(NewsResponse newsResponse) {
                List<NewsData> data = newsResponse.data;
                if (data != null && data.size() > 0) {
                    list.clear();
                    for (NewsData newsData : data) {
                        News news = new Gson().fromJson(newsData.content, News.class);
                        list.add(news);
                    }
                }
                mRecyclerAdapter.notifyDataSetChanged();
                mTipView.show(newsResponse.tips.display_info);
                refreshLayout.finishRefresh();
            }
        }, "video", time, time);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.finishLoadMore();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
