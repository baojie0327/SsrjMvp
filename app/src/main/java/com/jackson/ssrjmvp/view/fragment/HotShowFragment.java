package com.jackson.ssrjmvp.view.fragment; /**
 * 2017-09-12
 * Copyright (c) 2017 KL Co.Ltd. All right reserved.
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jackson.ssrjmvp.R;
import com.jackson.ssrjmvp.adapter.HotShowAdapter;
import com.jackson.ssrjmvp.bean.HotSHowBean;
import com.jackson.ssrjmvp.dagger.component.DaggerHotShowComponent;
import com.jackson.ssrjmvp.dagger.module.HotShowModule;
import com.jackson.ssrjmvp.presenter.HotShowPresenter;
import com.jackson.ssrjmvp.utils.CommonMethod;
import com.jackson.ssrjmvp.utils.Constant;
import com.jackson.ssrjmvp.utils.LogUtil;
import com.jackson.ssrjmvp.view.IView;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

import static com.jackson.ssrjmvp.R.id.refreshLayout;

/**
 * 第二个模块，热映，提供SmartRefresh刷新
 *
 * @author Jackson
 * @version 1.0.0
 *          since 2017 09 12
 */
public class HotShowFragment extends Fragment implements IView.IHotShowView {

    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_head_title)
    TextView mTvHeadTitle;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    @BindView(refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    Unbinder unbinder1;
    private View view;

    private List<HotSHowBean.SubjectsBean> mDataList;  // 数据源

    @Inject
    HotShowPresenter mHotShowPresenter;
    private HotShowAdapter mHotShowAdapter;
    private int start = 0; // 起始位置

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_nearby_layout, null);
            unbinder = ButterKnife.bind(this, view);
            inject();
            initView();
            mHotShowPresenter.getData(0, start);  // 请求数据
            refreshListen();  // 刷新，加载监听
        }

        unbinder1 = ButterKnife.bind(this, view);
        return view;
    }

    /**
     * 注入Presenter
     */
    private void inject() {
        DaggerHotShowComponent.builder()
                .hotShowModule(new HotShowModule(this))
                .build()
                .inject(this);
    }

    /**
     * 初始化界面
     */
    private void initView() {
        mLlBack.setVisibility(View.INVISIBLE);
        mTvHeadTitle.setText("热映");
        mTvHeadTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        // 设置RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mDataList = new ArrayList<>();

        // 刷新设置
        mRefreshLayout.autoRefresh();  // 开启自动刷新
        mRefreshLayout.setEnableAutoLoadMore(true); // 开启自动加载功能
        // mRefreshLayout.setEnableAutoLoadMore(false);//使上拉加载具有弹性效果
        //  mRefreshLayout.setEnableOverScrollDrag(false);//禁止越界拖动（1.0.4以上版本）
        // mRefreshLayout.setEnableOverScrollBounce(false);//关闭越界回弹功能

        // 设置官方刷新主题
        mRefreshLayout.setRefreshHeader(new MaterialHeader(getActivity()));
        mRefreshLayout.setEnableHeaderTranslationContent(false);

    }


    /**
     * 刷新加载监听
     */
    private void refreshListen() {
        // refresh
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mRefreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        start = 0;
                        mHotShowPresenter.getData(1, start);
                    }
                }, 2000);
            }
        });

        // loadMore
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mRefreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        start += Constant.PAGE_COUNT;
                        mHotShowPresenter.getData(2, start);
                    }
                }, 2000);
            }
        });
    }

    /**
     * 请求成功后，设置数据
     *
     * @param mDataList
     */
    @Override
    public void setData(List<HotSHowBean.SubjectsBean> mDataList) {
        LogUtil.d("size==" + mDataList.size());
        mHotShowAdapter = new HotShowAdapter(R.layout.item_hotshow_layout, mDataList);
        mRecyclerView.setAdapter(mHotShowAdapter);
        mHotShowAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CommonMethod.showToast(getActivity(),"click--"+position,false);
            }
        });
    }

    /**
     * 刷新成功后，更新数据
     *
     * @param mDataList
     */
    @Override
    public void onRefresh(List<HotSHowBean.SubjectsBean> mDataList) {
        mHotShowAdapter.setNewData(mDataList);
        mRefreshLayout.finishRefresh();
        mRefreshLayout.setNoMoreData(false);
    }

    /**
     * 加载成功后，更新数据
     *
     * @param mDataList
     */
    @Override
    public void onLoadMore(List<HotSHowBean.SubjectsBean> mDataList) {
        mHotShowAdapter.addData(mDataList);
        if (mDataList.size() < Constant.PAGE_COUNT) {
            mRefreshLayout.finishLoadMoreWithNoMoreData();  //将不会再次触发加载更多事件
        }
        mRefreshLayout.finishLoadMore();
    }

    @Override
    public void closeDispose(Disposable disposable) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /**
     * 提供Fragment实例
     *
     * @return
     */
    public static HotShowFragment newInstance() {
        HotShowFragment fragment = new HotShowFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


}

