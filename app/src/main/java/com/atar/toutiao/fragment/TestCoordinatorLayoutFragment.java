package com.atar.toutiao.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.atar.toutiao.R;
import com.atar.toutiao.adapter.RecyclerQuickAdapter;
import com.atar.toutiao.interfaces.SimpleMultiPurposeListener;
import com.atar.toutiao.widget.TipView;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestCoordinatorLayoutFragment extends AtarRefreshFragment {

//    @Bind(R.id.parallax)
//    ImageView parallax;
//    @Bind(R.id.buttonBarLayout)
//    ButtonBarLayout buttonBarLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
//    @Bind(R.id.coordinator)
//    CoordinatorLayout coordinator;
//    @Bind(R.id.collapsing_toolbar)
//    CollapsingToolbarLayout collapsing_toolbar;

    private List<String> list = new ArrayList<String>();
    private RecyclerQuickAdapter mRecyclerQuickAdapter = new RecyclerQuickAdapter(list);

    public TestCoordinatorLayoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_test_coordinator_layout, container, false);
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
}
