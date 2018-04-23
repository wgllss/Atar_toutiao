package com.atar.toutiao.adapter;

import android.adapter.CommonRecyclerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atar.toutiao.R;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */

public class RecyclerAdapter extends CommonRecyclerAdapter<String> {
    public RecyclerAdapter(List<?> list) {
        super(list);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_abs_item, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
