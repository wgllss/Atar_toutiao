package com.atar.toutiao.fragment;

import android.common.CommonHandler;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.atar.toutiao.R;
import com.atar.toutiao.widget.TipView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import butterknife.Bind;
import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;

/**
 * ****************************************************************************************************************************************************************************
 * 公共处理刷新recyclerview
 * @author :Atar
 * @createTime:2018/5/17-15:14
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: *
 * ***************************************************************************************************************************************************************************
 **/
public class AtarRefreshFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener {
        protected View rootView;
        protected RefreshLayout refreshLayout;
        private TipView tip_view;
        protected RecyclerView recyclerview;
        private boolean isFirstLoad = true;
        protected CompositeSubscription mCompositeSubscription ;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mCompositeSubscription = new CompositeSubscription();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            if (rootView == null) {
                rootView = inflater.inflate(R.layout.common_refresh_recyclerview, container, false);
                setRefreshRecyclerUI((RefreshLayout)rootView.findViewById(R.id.refreshLayout),(RecyclerView)rootView.findViewById(R.id.recyclerview),(TipView)rootView.findViewById(R.id.tip_view));
            } else {
                ViewGroup parent = (ViewGroup) rootView.getParent();
                if (parent != null) {
                    parent.removeView(rootView);
                }
            }
            return rootView;
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            if(refreshLayout!=null){
                refreshLayout.setOnRefreshListener(this);
                refreshLayout.setOnLoadMoreListener(this);
            }
        }

        @Override
        public void setUserVisibleHint(boolean isVisibleToUser) {
            initUI(isVisibleToUser);
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            if (mCompositeSubscription != null) {
                mCompositeSubscription.unsubscribe();
            }
        }

        protected void initUI(final boolean isVisibleToUser) {
            CommonHandler.getInstatnce().getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (refreshLayout == null || getActivity() == null || !isVisibleToUser) {
                        initUI(isVisibleToUser);
                    } else {
                        if (isFirstLoad()) {
                            refreshLayout.autoRefresh();
                            setIsFirstLoad(false);
                        }
                    }
                }
            }, 100);
        }

        @Override
        public void onLoadMore(RefreshLayout refreshLayout) {

        }

        @Override
        public void onRefresh(RefreshLayout refreshLayout) {

        }

        /**
        * @author :Atar
        * @createTime: 2018/5/17 16:14
        * @version:1.0.0
        * @modifyTime:
        * @modifyAuthor:
        * @description: 是否第一次加载
        */
        public boolean isFirstLoad() {
            return isFirstLoad;
        }

        public void setIsFirstLoad(boolean isFirstLoad){
            this.isFirstLoad=isFirstLoad;
        }

        /**
         * RecyclerView 适配器
         * @param adapter
         * @author :Atar
         * @createTime:2015-6-3上午10:44:38
         * @version:1.0.0
         * @modifyTime:
         * @modifyAuthor:
         * @description:
         */
        protected void setAdapter(RecyclerView.Adapter adapter) {
            if (recyclerview != null) {
                recyclerview.setAdapter(adapter);
            }
        }

        /**
        * @author :Atar 
        * @createTime: 2018/5/17 15:47
        * @param
        * @version:1.0.0
        * @modifyTime:
        * @modifyAuthor:
        * @description: 设置recyclerview.setLayoutManager
        */
        protected void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
            if (recyclerview != null) {
                recyclerview.setLayoutManager(layoutManager);
            }
        }

        /**
        * @author :Atar
        * @createTime: 2018/5/17 16:04
        * @param itemDecoration
        * @version:1.0.0
        * @modifyTime:
        * @modifyAuthor:
        * @description: recyclerview.addItemDecoration
        */
        protected void addItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
            if (recyclerview != null) {
                recyclerview.addItemDecoration(itemDecoration);
            }
        }

        /**
        * @author :Atar
        * @createTime: 2018/5/17 16:10
        * @param strTip :提示内容
        * @version:1.0.0
        * @modifyTime:
        * @modifyAuthor:
        * @description: 设置提示
        */
        protected void  tipViewShow(String strTip){
            if(tip_view!=null){
                tip_view.show(strTip);
            }
        }

        protected void setRefreshRecyclerUI(RefreshLayout refreshLayout,RecyclerView recyclerview,TipView tip_view){
            this.refreshLayout = refreshLayout;
            this.tip_view = tip_view;
            this.recyclerview = recyclerview;
        }
}
