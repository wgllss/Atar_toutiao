package com.atar.toutiao.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.Toolbar;
import android.utils.StatusBarUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.atar.toutiao.R;
import com.atar.toutiao.interfaces.SimpleMultiPurposeListener;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ****************************************************************************************************************************************************************************
 * @author :Atar
 * @createTime:2018/5/17-11:14
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: *
 * ***************************************************************************************************************************************************************************
 **/
public class SecondFragment extends Fragment implements NestedScrollView.OnScrollChangeListener {
    private View rootView;
    @Bind(R.id.scrollView)
    NestedScrollView scrollView;
    @Bind(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @Bind(R.id.buttonBarLayout)
    ButtonBarLayout buttonBarLayout;
    @Bind(R.id.parallax)
    ImageView parallax;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private int lastScrollY = 0;
    private int h = DensityUtil.dp2px(170);
    private int color  ;
    private int mOffset = 0;
    private int mScrollY = 0;

    public static SecondFragment newInstance() {
        SecondFragment fragment = new SecondFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_two, container, false);
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
        color = ContextCompat.getColor(getActivity(), R.color.colorPrimary)& 0x00ffffff ;
        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
                mOffset = offset / 2;
                parallax.setTranslationY(mOffset - mScrollY);
                toolbar.setAlpha(1 - Math.min(percent, 1));
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(3000);
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(3000);
            }
        });
        scrollView.setOnScrollChangeListener(this);
        buttonBarLayout.setAlpha(0);
        toolbar.setBackgroundColor(0);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                finish();
            }
        });
    }

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
}
