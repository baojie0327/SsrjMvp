package com.jackson.ssrjmvp.view.fragment; /**
 * DisCountFragment  2017-09-14
 * Copyright (c) 2017 KL Co.Ltd. All right reserved.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jackson.ssrjmvp.R;
import com.jackson.ssrjmvp.adapter.ShopAdapter;
import com.jackson.ssrjmvp.bean.ShopBean;
import com.jackson.ssrjmvp.dagger.component.DaggerTopicComponent;
import com.jackson.ssrjmvp.dagger.module.TopicModule;
import com.jackson.ssrjmvp.presenter.TopicPresenter;
import com.jackson.ssrjmvp.view.IView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

/**
 * 资讯界面，hrt的数据
 *
 * @author Jackson
 * @version 1.0.0
 *          since 2017 09 14
 */
public class TopicFragment extends Fragment implements IView.ITopicView{
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_head_title)
    TextView mTvHeadTitle;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    private View view;

    private List<ShopBean.ShopDetails> mDataList;
    private ShopAdapter mShopAdapter;

    @Inject
    TopicPresenter mTopicPresenter;

  //  private DisCountPresenter mDisCountPresenter=new DisCountPresenter(this);

    /**
     * 点击事件的监听
     * @param view
     */
    @OnClick({R.id.ll_back})
    public void onClick(View view){
        switch (view.getId()){

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_discount_layout, null);
            unbinder = ButterKnife.bind(this, view);
            inject();
            initView();
            mTopicPresenter.getData();
        }

        return view;
    }

    /**
     * inject
     */
    private void inject(){
        DaggerTopicComponent.builder()
                .topicModule(new TopicModule(this))
                .build()
                .inject(this);
    }

    /**
     * 初始化界面
     */
    private void initView(){
        mLlBack.setVisibility(View.INVISIBLE);
        mTvHeadTitle.setText("资讯");
        // 设置RecyclerView
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mDataList=new ArrayList<>();

    }

    @Override
    public void setData(List<ShopBean.ShopDetails> dataList) {
        this.mDataList=dataList;
        mShopAdapter=new ShopAdapter(R.layout.item_shop2,mDataList);
        mShopAdapter.setType("0");
        mRecyclerView.setAdapter(mShopAdapter);
     //   mShopAdapter.setNewData(mDataList);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    @Override
    public void closeDispose(Disposable disposable) {

    }


    /**
     *提供Fragment实例
     * @return
     */
    public static TopicFragment newInstance() {
        TopicFragment fragment = new TopicFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


}

