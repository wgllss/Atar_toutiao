package com.atar.toutiao.uiinterfases;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Atar on 2018/6/13.
 */

public interface ToolBarInterface {

    View getView();

    ViewGroup getToolBarLayout();

    void setActivityTitle(String strActivityTitle);

    void setActivityTitle(int intResIDStringTitle);

    void setToolbarLeftImageDrawable(Drawable drawable);

    void setToolbarLeftImageResource(int resID);
}
