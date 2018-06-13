package com.atar.toutiao.activity;

import android.activity.CommonActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.utils.StatusBarUtils;
import android.view.View;
import android.widget.CommonToast;
import android.widget.TextView;

import com.atar.toutiao.R;
import com.atar.toutiao.adapter.ViewPagerFragmentAdapter;
import com.atar.toutiao.event.TestEvent;
import com.atar.toutiao.fragment.OneFragment;
import com.atar.toutiao.fragment.RecyclerViewHeaderStickyFragment;
import com.atar.toutiao.fragment.SecondFragment;
import com.atar.toutiao.fragment.SixFragment;
import com.atar.toutiao.fragment.TestCoordinatorLayoutFragment;
import com.atar.toutiao.fragment.ThirdFragment;
import com.atar.toutiao.fragment.ViewpageTopFragment;
import com.atar.toutiao.uiinterfases.ToolBarInterface;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AtarCommonTabActivity implements TabLayout.OnTabSelectedListener {


    private String[] mTitles = {"首页", "视频", "微头条", "我的", "5", "6"};
    private TextView txt_title;
    private Toolbar toolbar;
    private TabLayout lablayout_buttom;


    @Override
    protected ToolBarInterface getToolBarInterface() {
        return null;
    }

    @Override
    protected int getResLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void bindEvent() {
        lablayout_buttom.addOnTabSelectedListener(this);
    }

    @Override
    protected void initControl() {
        super.initControl();
        txt_title = findViewById(R.id.txt_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        lablayout_buttom = findViewById(R.id.lablayout_buttom);
        lablayout_buttom.setupWithViewPager(getViewPager());
    }

    @Override
    protected void initValue() {
        StatusBarUtils.translucentStatusBar(this, toolbar);
        setTextTab(mTitles, false, true);
        addFragmentToList(OneFragment.newInstance());
        addFragmentToList(SecondFragment.newInstance());
        addFragmentToList(ThirdFragment.newInstance());
        addFragmentToList(new RecyclerViewHeaderStickyFragment());
        addFragmentToList(new RecyclerViewHeaderStickyFragment());
        addFragmentToList(new SixFragment());
        setViewPagerAdapter();
        setOnDrawerBackEnabled(false);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        txt_title.setText(mTitles[position]);
        if (position == 0) {
            toolbar.setVisibility(View.VISIBLE);
        } else if (position == 1) {
            toolbar.setVisibility(View.GONE);
        } else if (position == 2) {
            toolbar.setVisibility(View.GONE);
        } else if (position == 3) {
            toolbar.setVisibility(View.GONE);
        } else if (position == 4) {
            toolbar.setVisibility(View.GONE);
        } else if (position == 5) {
            toolbar.setVisibility(View.GONE);
        }
        // 设置ViewPager的页面切换
        getViewPager().setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        RxBus.get().unregister(this);
    }

    @Subscribe(tags = {@Tag("TAG")})
    public void orderSwitch(TestEvent event) {
        CommonToast.show("165198158888888888888888");

    }
}
