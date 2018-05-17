package com.atar.toutiao.activity;

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
import com.atar.toutiao.fragment.SecondFragment;
import com.atar.toutiao.fragment.ThirdFragment;
import com.atar.toutiao.fragment.ViewpageTopFragment;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout lablayout_buttom;
    private ViewPager vp_main;
    private String[] mTitles = {"首页", "视频", "微头条", "我的"};
    private List<Fragment> mList = new ArrayList<Fragment>();
    private TextView txt_title;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        StatusBarUtils.translucentStatusBar(this, toolbar);
        txt_title = findViewById(R.id.txt_title);

        lablayout_buttom = findViewById(R.id.lablayout_buttom);

        vp_main = findViewById(R.id.vp_main);

        mList.add(OneFragment.newInstance());
        mList.add(SecondFragment.newInstance());
        mList.add(ThirdFragment.newInstance());
        mList.add(ViewpageTopFragment.newInstance());
//        mList.add(OneFragment.newInstance());

        vp_main.setAdapter(new ViewPagerFragmentAdapter(getSupportFragmentManager(), mList, Arrays.asList(mTitles)));
//        tl_main.addTab(tl_main.newTab().setText(mTitles[0]));
//        tl_main.addTab(tl_main.newTab().setText(mTitles[1]));
//        tl_main.addTab(tl_main.newTab().setText(mTitles[2]));
//        tl_main.addTab(tl_main.newTab().setText(mTitles[3]));

        // 跟随ViewPager的页面切换
        lablayout_buttom.setupWithViewPager(vp_main);
        vp_main.setOffscreenPageLimit(mTitles.length);

//        tl_main.getTabAt(0).setIcon(R.drawable.selector_ico01);
//        tl_main.getTabAt(1).setIcon(R.drawable.selector_ico02);
//        tl_main.getTabAt(2).setIcon(R.drawable.selector_ico03);
//        tl_main.getTabAt(3).setIcon(R.drawable.selector_ico04);


        lablayout_buttom.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
                }
                // 设置ViewPager的页面切换
                vp_main.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
//                int position = tab.getPosition();
//                if (position == 0) {
//
//                } else if (position == 1) {
//
//                } else if (position == 2) {
//
//                } else if (position == 3) {
//
//                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        RxBus.get().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }

    @Subscribe(tags = {@Tag("TAG")})
    public void orderSwitch(TestEvent event) {
        CommonToast.show("165198158888888888888888");

    }
}
