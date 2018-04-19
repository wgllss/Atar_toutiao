package com.atar.toutiao.fragment;


import android.common.CommonHandler;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atar.toutiao.R;
import com.atar.toutiao.adapter.RecyclerAdapter;
import com.atar.toutiao.widget.TipView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate {

    private View rootView;
    @Bind(R.id.tip_view)
    TipView mTipView;
    @Bind(R.id.refresh_layout)
    BGARefreshLayout refreshLayout;
    @Bind(R.id.rv_recyclerview_data)
    RecyclerView recyclerView;

    private List<String> list = new ArrayList<String>();
    private RecyclerAdapter mRecyclerAdapter = new RecyclerAdapter(list);

    public static OneFragment newInstance() {
        OneFragment fragment = new OneFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_one, container, false);
            ButterKnife.bind(this, rootView);
        } else {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }
        refreshLayout.setDelegate(this);
        BGAMoocStyleRefreshViewHolder moocStyleRefreshViewHolder = new BGAMoocStyleRefreshViewHolder(getActivity(), true);
        moocStyleRefreshViewHolder.setUltimateColor(R.color.custom_imoocstyle);
        moocStyleRefreshViewHolder.setOriginalImage(R.mipmap.custom_mooc_icon);
        moocStyleRefreshViewHolder.setLoadMoreBackgroundColorRes(R.color.custom_imoocstyle);
        moocStyleRefreshViewHolder.setSpringDistanceScale(0.2f);
        moocStyleRefreshViewHolder.setRefreshViewBackgroundColorRes(R.color.custom_imoocstyle);
        refreshLayout.setRefreshViewHolder(moocStyleRefreshViewHolder);


        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mRecyclerAdapter);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(final BGARefreshLayout refreshLayout) {
        CommonHandler.getInstatnce().getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.endRefreshing();
            }
        }, 2000);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(final BGARefreshLayout refreshLayout) {
        CommonHandler.getInstatnce().getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.endLoadingMore();
            }
        }, 2000);
        return true;
    }
}
