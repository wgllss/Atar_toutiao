package android.utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.support.v7.widget.Toolbar;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :Atar
 * @createTime:2018/5/17-10:27
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: 设置透明状态栏工具
 * ***************************************************************************************************************************************************************************
 **/
public class StatusBarUtils {

    public static void translucentStatusBar(Activity activity, Toolbar toolbar) {
        try {
            if (activity == null) {
                return;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(Color.TRANSPARENT);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
                ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
                View mChildView = mContentView.getChildAt(0);
                if (mChildView != null) {
                    ViewCompat.setFitsSystemWindows(mChildView, false);
                    ViewCompat.requestApplyInsets(mChildView);
                }
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                // 透明状态栏
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && toolbar != null) {
                int statusBarHeight = ScreenUtils.getStatusBarHeight(activity);
                int layoutHeight = (statusBarHeight > 0 ? statusBarHeight : 36) + (int) ScreenUtils.getIntToDip(45);
                ViewGroup.LayoutParams lp = toolbar.getLayoutParams();
                lp.height = layoutHeight;
                toolbar.setPadding(0, statusBarHeight, 0, 0);
            }
        } catch (Exception e) {

        }
    }
}
