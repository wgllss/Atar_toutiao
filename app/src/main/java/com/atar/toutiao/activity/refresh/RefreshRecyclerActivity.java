package com.atar.toutiao.activity.refresh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.atar.toutiao.R;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class RefreshRecyclerActivity extends AppCompatActivity {
//    private NormalRecyclerViewAdapter mAdapter;
    private BGARefreshLayout mRefreshLayout;
    private RecyclerView mDataRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_recycler);
    }
}
