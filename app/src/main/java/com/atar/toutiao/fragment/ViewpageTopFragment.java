package com.atar.toutiao.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atar.toutiao.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewpageTopFragment extends Fragment {


    public static ViewpageTopFragment newInstance() {
        ViewpageTopFragment fragment = new ViewpageTopFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_viewpage_top, container, false);
    }

}
