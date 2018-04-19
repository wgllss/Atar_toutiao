package com.atar.toutiao.adapter;

import android.adapter.FragmentAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */

public class ViewPagerFragmentAdapter extends FragmentAdapter {
    private List<String> list;

    public ViewPagerFragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> list) {
        super(fm, fragments);
        this.list = list;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}
