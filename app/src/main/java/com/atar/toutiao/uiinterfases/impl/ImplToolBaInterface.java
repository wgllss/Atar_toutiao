package com.atar.toutiao.uiinterfases.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atar.toutiao.R;
import com.atar.toutiao.uiinterfases.ToolBarInterface;

/**
 * Created by Atar on 2018/6/13.
 */

public class ImplToolBaInterface implements ToolBarInterface {

    private Context context;
    private int resIDToolbarLayout;

    protected ImageView imgCommonTopLeft;
    protected ImageView imgCommonTopRight;
    protected TextView txtCommonTopTitle;
    protected RelativeLayout topTitleBarBg;

    public static ImplToolBaInterface newInstance(Context context, int resIDToolbarLayout) {
        ImplToolBaInterface mImplToolBatInterface = new ImplToolBaInterface();
        mImplToolBatInterface.context = context;
        mImplToolBatInterface.resIDToolbarLayout = resIDToolbarLayout;
        return mImplToolBatInterface;
    }

    @Override
    public View getView() {
        View view = LayoutInflater.from(context).inflate(resIDToolbarLayout, null);
        imgCommonTopLeft = (ImageView) view.findViewById(R.id.img_common_top_left);
        imgCommonTopRight = (ImageView) view.findViewById(R.id.img_common_top_right);
        txtCommonTopTitle = (TextView) view.findViewById(R.id.txt_common_top_title);
        topTitleBarBg = (RelativeLayout) view.findViewById(R.id.common_top_title_bar);
        return view;
    }

    @Override
    public ViewGroup getToolBarLayout() {
        return topTitleBarBg;
    }

    @Override
    public void setActivityTitle(String strActivityTitle) {
        if (txtCommonTopTitle != null) {
            txtCommonTopTitle.setText(strActivityTitle);
        }
    }

    @Override
    public void setActivityTitle(int intResIDStringTitle) {
        if (txtCommonTopTitle != null) {
            txtCommonTopTitle.setText(context.getResources().getString(intResIDStringTitle));
        }
    }


    @Override
    public void setToolbarLeftImageDrawable(Drawable drawable) {
        if (imgCommonTopLeft != null) {
            imgCommonTopLeft.setImageDrawable(drawable);

        }
    }

    @Override
    public void setToolbarLeftImageResource(int resID) {
        if (imgCommonTopLeft != null) {
            imgCommonTopLeft.setImageResource(resID);
        }
    }
}
