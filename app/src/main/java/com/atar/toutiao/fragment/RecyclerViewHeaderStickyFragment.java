package com.atar.toutiao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atar.toutiao.R;
import com.atar.toutiao.adapter.RecyclerQuickAdapter;
import com.atar.toutiao.widget.TipView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :Atar
 * @createTime:2018/5/18-10:00
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: *
 * ***************************************************************************************************************************************************************************
 **/
public class RecyclerViewHeaderStickyFragment extends AtarRefreshFragment {

    private List<String> list = new ArrayList<String>();
    private RecyclerQuickAdapter mRecyclerQuickAdapter = new RecyclerQuickAdapter(list);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_recycler_sticky_header, container, false);
            setRefreshRecyclerUI((RefreshLayout)rootView.findViewById(R.id.refreshLayout),(RecyclerView)rootView.findViewById(R.id.recyclerview),(TipView)rootView.findViewById(R.id.tip_view));
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
        for(int i=0;i<20;i++){
            list.add("----"+i);
        }
        setLayoutManager(new GridLayoutManager(getActivity(), 1));
        setAdapter(mRecyclerQuickAdapter);
        refreshLayout.setEnableScrollContentWhenLoaded(true);
        refreshLayout.setEnableScrollContentWhenRefreshed(true);
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
