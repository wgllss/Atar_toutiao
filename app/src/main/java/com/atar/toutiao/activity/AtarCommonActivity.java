package com.atar.toutiao.activity;

import android.activity.CommonActivity;
import android.app.Activity;
import android.application.CrashHandler;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.utils.ScreenUtils;
import android.utils.ShowLog;
import android.utils.StatusBarUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.atar.toutiao.R;
import com.atar.toutiao.uiinterfases.ToolBarInterface;
import com.atar.toutiao.uiinterfases.impl.ImplToolBaInterface;

/**
 * Created by Atar on 2018/6/13.
 */

public abstract class AtarCommonActivity extends CommonActivity implements ToolBarInterface {
    private String TAG = AtarCommonActivity.class.getSimpleName();

    private ToolBarInterface mToolBarInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mToolBarInterface == null && getToolBarInterface() != null) {
            mToolBarInterface = getToolBarInterface();
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.gravity = Gravity.LEFT | Gravity.TOP;
            addContentView(mToolBarInterface.getView(), lp);
            StatusBarUtils.translucentStatusBar(this, mToolBarInterface.getToolBarLayout());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mToolBarInterface != null) {
            mToolBarInterface = null;
        }
    }

    protected ToolBarInterface getToolBarInterface() {
        return ImplToolBaInterface.newInstance(this, R.layout.activity_common_toolbar_layout);
    }

    /**
     * 添加中间布局
     *
     * @param layoutResId
     * @author :Atar
     * @createTime:2014-6-13下午9:07:25
     * @version:1.0.0
     * @modifyTime:
     * @modifyAuthor:
     * @description:
     */
    protected void addContentView(int layoutResId) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View commonContentBg = inflater.inflate(layoutResId, null);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.gravity = Gravity.TOP | Gravity.LEFT;
        if (getToolBarLayout() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                int statusBarHeight = ScreenUtils.getStatusBarHeight(this);
                int layoutHeight = (statusBarHeight > 0 ? statusBarHeight : 36) + (int) ScreenUtils.getIntToDip(45);
                getToolBarLayout().setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, layoutHeight));
                getToolBarLayout().setPadding(0, statusBarHeight, 0, 0);
                lp.topMargin = layoutHeight;
            } else {
                lp.topMargin = (int) ScreenUtils.getIntToDip(45);
            }
        }
        addContentView(commonContentBg, lp);
        init();
    }

    /**
     * 初始化整个activity
     *
     * @author :Atar
     * @createTime:2014-6-13下午9:06:21
     * @version:1.0.0
     * @modifyTime:
     * @modifyAuthor:
     * @description:
     */
    protected void init() {
        try {
            initControl();
            initValue();
            bindEvent();
        } catch (Exception e) {
            ShowLog.e(TAG, e);
        }
    }

    /**
     * 装载控件
     *
     * @author :Atar
     * @createTime:2014-6-13上午11:35:38
     * @version:1.0.0
     * @modifyTime:
     * @modifyAuthor:
     * @description: 如:findViewById(R.id.xxx)
     */
    protected abstract void initControl();

    /**
     * 绑定监听事件
     *
     * @author :Atar
     * @createTime:2014-6-13上午11:36:31
     * @version:1.0.0
     * @modifyTime:
     * @modifyAuthor:
     * @description: 如:setOnClickListener(this)等
     */
    protected abstract void bindEvent();

    /**
     * 初始化值:
     *
     * @author :Atar
     * @createTime:2014-6-13上午11:37:53
     * @version:1.0.0
     * @modifyTime:
     * @modifyAuthor:
     * @description: 如:setText("XXXX")
     */
    protected abstract void initValue();

    @Override
    public View getView() {
        return mToolBarInterface != null ? mToolBarInterface.getView() : null;
    }

    @Override
    public ViewGroup getToolBarLayout() {
        return mToolBarInterface != null ? mToolBarInterface.getToolBarLayout() : null;
    }

    @Override
    public void setActivityTitle(String strActivityTitle) {
        if (mToolBarInterface != null) {
            mToolBarInterface.setActivityTitle(strActivityTitle);
        }
    }

    @Override
    public void setActivityTitle(int intResIDStringTitle) {
        if (mToolBarInterface != null) {
            mToolBarInterface.setActivityTitle(intResIDStringTitle);
        }
    }

    @Override
    public void setToolbarLeftImageDrawable(Drawable drawable) {
        if (mToolBarInterface != null) {
            mToolBarInterface.setToolbarLeftImageDrawable(drawable);
        }
    }

    @Override
    public void setToolbarLeftImageResource(int resID) {
        if (mToolBarInterface != null) {
            mToolBarInterface.setToolbarLeftImageResource(resID);
        }
    }
}
