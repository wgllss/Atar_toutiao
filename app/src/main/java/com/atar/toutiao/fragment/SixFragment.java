package com.atar.toutiao.fragment;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.utils.StatusBarUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atar.toutiao.R;
import com.atar.toutiao.adapter.RecyclerQuickAdapter;
import com.atar.toutiao.widget.TipView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SixFragment extends AtarRefreshFragment implements NestedScrollView.OnScrollChangeListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.coordinator)
    CoordinatorLayout coordinator;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;
//    @Bind(R.id.scrollView)
//    NestedScrollView scrollView;
    @Bind(R.id.buttonBarLayout)
    ButtonBarLayout buttonBarLayout;

    private int lastScrollY = 0;
    private int h = DensityUtil.dp2px(170);
    private int color  ;
    private int mOffset = 0;
    private int mScrollY = 0;

    private List<String> list = new ArrayList<String>();
    private RecyclerQuickAdapter mRecyclerQuickAdapter = new RecyclerQuickAdapter(list);

    public SixFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_six, container, false);
            setRefreshRecyclerUI((RefreshLayout) rootView.findViewById(R.id.refreshLayout), (RecyclerView) rootView.findViewById(R.id.vertical_recyclerView), (TipView) rootView.findViewById(R.id.tip_view));
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        for (int i = 0; i < 20; i++) {
            list.add("----" + i);
        }
        setLayoutManager(new GridLayoutManager(getActivity(), 1));
        setAdapter(mRecyclerQuickAdapter);
//        scrollView.setOnScrollChangeListener(this);
        int color = ContextCompat.getColor(getActivity(), R.color.colorPrimary)& 0x00ffffff ;
//        collapsing_toolbar.setStatusBarScrim(new ColorDrawable(color));
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        super.onRefresh(refreshLayout);
        refreshLayout.finishRefresh();
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        super.onLoadMore(refreshLayout);
        refreshLayout.finishLoadMore();
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (lastScrollY < h) {
            scrollY = Math.min(h, scrollY);
            mScrollY = scrollY > h ? h : scrollY;
            buttonBarLayout.setAlpha(1f * mScrollY / h);
            toolbar.setBackgroundColor(((255 * mScrollY / h) << 24) | color);
//            parallax.setTranslationY(mOffset - mScrollY);
        }
        lastScrollY = scrollY;
    }
}
