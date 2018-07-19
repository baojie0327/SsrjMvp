package com.jackson.ssrjmvp.view.fragment; /**
 * MineFragment  2017-09-14
 * Copyright (c) 2017 KL Co.Ltd. All right reserved.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jackson.ssrjmvp.R;

/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2017 09 14
 */
public class MineFragment extends Fragment{
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view==null){
            view=inflater.inflate(R.layout.fragment_mine_layout,null);
        }
        return view;
    }


    /**
     *提供Fragment实例
     * @return
     */
    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
}

