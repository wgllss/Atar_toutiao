package com.atar.toutiao.adapter;

import android.adapter.CommonAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atar.toutiao.R;

import java.util.List;

/**
 * Created by Atar on 2018/4/17.
 */

public class AbsAdapter extends CommonAdapter<String> {
    public AbsAdapter(List<?> list) {
        super(list);
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_abs_item, null);
        }
        return convertView;
    }
}
