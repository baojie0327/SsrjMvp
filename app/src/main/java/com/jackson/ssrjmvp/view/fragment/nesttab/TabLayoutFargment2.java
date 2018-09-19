package com.jackson.ssrjmvp.view.fragment.nesttab; /**
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
import com.jackson.ssrjmvp.bean.TabBean;
import com.jackson.ssrjmvp.view.fragment.LazyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * class description here
 *
 * @author Borje
 * @version 1.0.0
 * since 2017 07 28
 */

public class TabLayoutFargment2 extends LazyFragment {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private View view;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private List<TabBean> dataList=new ArrayList<>();





    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_tab_layout_layout, null);
            ButterKnife.bind(this, view);

        }

        return view;
    }

    private void getData(){
        for (int i = 0; i <=8 ; i++) {
            TabBean tabBean=new TabBean();
            tabBean.setUrl("https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2532008868.webp");
            tabBean.setTerxt("我奔跑在我孤傲的路上，使然看不见终点和希望，有太多火焰冷却我的理想，我依然燃烧我仍在信仰。");
            dataList.add(tabBean);
        }
    }

    @Override
    protected void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getData();
        mRecyclerViewAdapter=new RecyclerViewAdapter(R.layout.item_recycle_layout,dataList);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

    }



    @Override
    protected void setDefaultFragmentTitle(String title) {

    }


    /**
     * 提供Fragment实例
     *
     * @return
     */
    public static TabLayoutFargment2 newInstance() {
        TabLayoutFargment2 fragment = new TabLayoutFargment2();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


}