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
public class OneFragment extends Fragment {


    public static OneFragment newInstance(String title, String cs) {
        OneFragment fragment = new OneFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

}
