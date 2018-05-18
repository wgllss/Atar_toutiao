package com.atar.toutiao.adapter;

import android.support.annotation.Nullable;

import com.atar.toutiao.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :Atar
 * @createTime:2018/5/18-10:11
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: *
 * ***************************************************************************************************************************************************************************
 **/
public class RecyclerQuickAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public RecyclerQuickAdapter(@Nullable List<String> data) {
        super( R.layout.adapter_abs_item,data);
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        baseViewHolder.setText(R.id.txt_item,s);
    }
}
