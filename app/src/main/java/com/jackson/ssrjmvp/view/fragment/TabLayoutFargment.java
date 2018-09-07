package com.example.ahuang.myframe.fragment; /**
 * TabLayoutFargment  2017-07-28
 * Copyright (c) 2017 SSRJ Co.Ltd. All right reserved.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ahuang.myframe.R;
import com.example.ahuang.myframe.adapter.RecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * class description here
 *
 * @author Borje
 * @version 1.0.0
 *          since 2017 07 28
 */
public class TabLayoutFargment extends Fragment {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_tab_layout_layout, null);
            ButterKnife.bind(this, view);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRecyclerView.setAdapter(new RecyclerViewAdapter(getActivity()));
        }

        return view;
    }
}