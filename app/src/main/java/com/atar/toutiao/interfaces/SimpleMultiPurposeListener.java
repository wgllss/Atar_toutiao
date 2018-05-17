package com.atar.toutiao.interfaces;

import android.support.annotation.NonNull;

import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :Atar
 * @createTime:2018/5/17-13:18
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: *
 * ***************************************************************************************************************************************************************************
 **/
public class SimpleMultiPurposeListener implements OnMultiPurposeListener {

//    @Override
//    public void onHeaderPulling(RefreshHeader header, float percent, int offset, int headerHeight, int maxDragHeight) {
//
//    }

    @Override
    public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {

    }

    @Override
    public void onHeaderReleased(RefreshHeader header, int headerHeight, int maxDragHeight) {

    }

//    @Override
//    public void onHeaderReleasing(RefreshHeader header, float percent, int offset, int footerHeight, int maxDragHeight) {
//
//    }

    @Override
    public void onHeaderStartAnimator(RefreshHeader header, int footerHeight, int maxDragHeight) {

    }

    @Override
    public void onHeaderFinish(RefreshHeader header, boolean success) {

    }

    @Override
    public void onFooterMoving(RefreshFooter footer, boolean isDragging, float percent, int offset, int footerHeight, int maxDragHeight) {

    }

//    @Override
//    public void onFooterPulling(RefreshFooter footer, float percent, int offset, int footerHeight, int maxDragHeight) {
//
//    }

    @Override
    public void onFooterReleased(RefreshFooter footer, int footerHeight, int maxDragHeight) {

    }

//    @Override
//    public void onFooterReleasing(RefreshFooter footer, float percent, int offset, int footerHeight, int maxDragHeight) {
//
//    }

    @Override
    public void onFooterStartAnimator(RefreshFooter footer, int headerHeight, int maxDragHeight) {

    }

    @Override
    public void onFooterFinish(RefreshFooter footer, boolean success) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {

    }
}
