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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jackson.ssrjmvp.R;
import com.jackson.ssrjmvp.adapter.RecyclerViewAdapter;
import com.jackson.ssrjmvp.bean.TabBean;
import com.jackson.ssrjmvp.utils.CustomLoadMoreView;
import com.jackson.ssrjmvp.view.fragment.LazyFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * class description here
 *
 * @author Borje
 * @version 1.0.0
 * since 2017 07 28
 */

public class TabLayoutFargment extends LazyFragment {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private View view;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private List<TabBean> dataList = new ArrayList<>();


    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_tab_layout_layout, null);
            ButterKnife.bind(this, view);
        }

        return view;
    }

    private void getData() {
        for (int i = 0; i <= 8; i++) {
            TabBean tabBean = new TabBean();
            tabBean.setUrl("https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2531887203.webp");
            tabBean.setTerxt("我奔跑在我孤傲的路上，使然看不见终点和希望，有太多火焰冷却我的理想，我依然燃烧我仍在信仰。");
            dataList.add(tabBean);
        }
    }

    private List<TabBean> addData() {
        List<TabBean> dataList1 = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            TabBean tabBean = new TabBean();
            tabBean.setUrl("https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2531887203.webp");
            tabBean.setTerxt("我奔跑在我孤傲的路上，使然看不见终点和希望，有太多火焰冷却我的理想，我依然燃烧我仍在信仰。");
            dataList1.add(tabBean);
        }
        return dataList1;
    }

    @Override
    protected void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getData();
        mRecyclerViewAdapter = new RecyclerViewAdapter(R.layout.item_recycle_layout, dataList);
     //   mRecyclerViewAdapter.setNewData(dataList);
        mRecyclerViewAdapter.setLoadMoreView(new CustomLoadMoreView()); //设置加载视图

        mRecyclerViewAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                Observable.create(new ObservableOnSubscribe<List<TabBean>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<TabBean>> e) {
                        e.onNext(addData());
                    }
                })
                        .delay(2, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<List<TabBean>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(List<TabBean> tabBeans) {
                                mRecyclerViewAdapter.addData(addData());
                                mRecyclerViewAdapter.loadMoreComplete();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });

        mRecyclerView.setAdapter(mRecyclerViewAdapter);


    }

    public void refreshData(){
        dataList.clear();
        getData();
        mRecyclerViewAdapter.setNewData(dataList);
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