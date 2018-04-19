package com.atar.toutiao.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.atar.toutiao.R;
import com.atar.toutiao.adapter.ViewPagerFragmentAdapter;
import com.atar.toutiao.fragment.OneFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout lablayout_buttom;
    private ViewPager vp_main;
    private String[] mTitles = {"首页", "视频", "微头条", "我的"};
    private List<Fragment> mList = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lablayout_buttom = (TabLayout) findViewById(R.id.lablayout_buttom);

        vp_main = (ViewPager) findViewById(R.id.vp_main);

        mList.add(OneFragment.newInstance());
        mList.add(OneFragment.newInstance());
        mList.add(OneFragment.newInstance());
        mList.add(OneFragment.newInstance());

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

//                if (position == 0) {

//
//                } else if (position == 1) {
//
//                } else if (position == 2) {
//
//                } else if (position == 3) {
//
//                }
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
    }
}
