package com.atar.toutiao.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.utils.StatusBarUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.atar.toutiao.R;
import com.atar.toutiao.adapter.VideoListAdapter;
import com.atar.toutiao.contract.NewsContract;
import com.atar.toutiao.interfaces.SimpleMultiPurposeListener;
import com.atar.toutiao.modles.News;
import com.atar.toutiao.modles.NewsData;
import com.atar.toutiao.modles.NewsResponse;
import com.atar.toutiao.presenter.ImpNewsPresenper;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerManager;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import rx.subscriptions.CompositeSubscription;

import static fm.jiecao.jcvideoplayer_lib.JCVideoPlayer.CURRENT_STATE_PLAYING;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment implements NewsContract.IView {
    private View rootView;
    @Bind(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rv_recyclerview_data)
    RecyclerView recyclerView;
    @Bind(R.id.parallax)
    ImageView parallax;
    @Bind(R.id.scrollView)
    NestedScrollView scrollView;
    @Bind(R.id.buttonBarLayout)
    ButtonBarLayout buttonBarLayout;

    private List<News> list = new ArrayList<News>();
    private VideoListAdapter mRecyclerAdapter = new VideoListAdapter(list);

    protected CompositeSubscription mCompositeSubscription = new CompositeSubscription();
    private ImpNewsPresenper mImpNewsPresenper;
    private int mOffset = 0;
    private int mScrollY = 0;


    public static ThirdFragment newInstance() {
        ThirdFragment fragment = new ThirdFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_third, container, false);
            ButterKnife.bind(this, rootView);
            StatusBarUtils.translucentStatusBar(getActivity(), toolbar);
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
        // 设置为 true
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置为 false
        recyclerView.setNestedScrollingEnabled(true);
        mRecyclerAdapter.setmCompositeSubscription(mCompositeSubscription);
        recyclerView.setAdapter(mRecyclerAdapter);

        refreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）
        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
                mOffset = offset / 2;
                parallax.setTranslationY(mOffset - mScrollY);
                toolbar.setAlpha(1 - Math.min(percent, 1));
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mImpNewsPresenper.getList(mCompositeSubscription);
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(3000);
            }
        });
        refreshLayout.autoRefresh();
//        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                if (JCVideoPlayerManager.getCurrentJcvd() != null) {
//                    JCVideoPlayerStandard videoPlayer = (JCVideoPlayerStandard) JCVideoPlayerManager.getCurrentJcvd();
//                    if (videoPlayer.currentState == CURRENT_STATE_PLAYING) {
//                        //如果正在播放
//                        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                        int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
//                        int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
//
//                        if (firstVisibleItemPosition > videoPlayer.getPosition() || lastVisibleItemPosition < videoPlayer.getPosition()) {
//                            //如果第一个可见的条目位置大于当前播放videoPlayer的位置
//                            //或最后一个可见的条目位置小于当前播放videoPlayer的位置，释放资源
//                            JCVideoPlayer.releaseAllVideos();
//                        }
//                    }
//                }
//            }
//        });

        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            private int lastScrollY = 0;
            private int h = DensityUtil.dp2px(170);
            private int color = ContextCompat.getColor(getActivity(), R.color.colorAccent) & 0x00ffffff;

            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (lastScrollY < h) {
                    scrollY = Math.min(h, scrollY);
                    mScrollY = scrollY > h ? h : scrollY;
                    buttonBarLayout.setAlpha(1f * mScrollY / h);
                    toolbar.setBackgroundColor(((255 * mScrollY / h) << 24) | color);
                    parallax.setTranslationY(mOffset - mScrollY);
                }
                lastScrollY = scrollY;
            }
        });
        buttonBarLayout.setAlpha(0);
        toolbar.setBackgroundColor(0);
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
//        mTipView.show(newsResponse.tips.display_info);
        refreshLayout.finishRefresh();
    }
}
