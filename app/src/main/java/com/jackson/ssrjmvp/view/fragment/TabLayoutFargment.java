package com.jackson.ssrjmvp.view.fragment; /**
 * TabLayoutFargment  2017-07-28
 * Copyright (c) 2017 SSRJ Co.Ltd. All right reserved.
 */

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jackson.ssrjmvp.R;
import com.jackson.ssrjmvp.adapter.RecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * class description here
 *
 * @author Borje
 * @version 1.0.0
 *          since 2017 07 28
 */
public class TabLayoutFargment extends LazyFragment {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private View view;


    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_tab_layout_layout, null);
            ButterKnife.bind(this, view);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        }

        return view;
    }

    @Override
    protected void initData() {
        mRecyclerView.setAdapter(new RecyclerViewAdapter(getActivity()));
    }

    @Override
    protected void setDefaultFragmentTitle(String title) {

    }


    /**
     * 提供Fragment实例
     *
     * @return
     */
    public static TabLayoutFargment newInstance() {
        TabLayoutFargment fragment = new TabLayoutFargment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


}