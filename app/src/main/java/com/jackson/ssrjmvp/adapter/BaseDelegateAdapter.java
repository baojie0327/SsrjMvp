package com.jackson.ssrjmvp.adapter; /**
 * BaseDelegateAdapter  2018-07-19
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * class description here
 *
 * @author Jackson
 * @version 1.0.0
 *          since 2018 07 19
 */
public abstract class BaseDelegateAdapter<T, K extends BaseViewHolder> extends DelegateAdapter.Adapter<K> {

    private LayoutHelper mLayoutHelper;
    private int mCount = -1;
    private int mLayoutId = -1;
    protected Context mContext;
    private List<T> mDataList;
    private int mViewTypeItem = -1;

    protected OnItemClickListener mOnItemClickListener;   // item点击监听
    protected OnItemChildClickListener mOnItemChildClickListener; // child item 点击监听

    /**
     * 构造方法
     *
     * @param context
     * @param layoutHelper
     * @param lauoutId
     * @param
     */
    public BaseDelegateAdapter(Context context, List<T> list, LayoutHelper layoutHelper, int lauoutId, int count,int viewTypeItem) {
        this.mContext = context;
        this.mDataList = list;
        this.mLayoutHelper = layoutHelper;
        this.mLayoutId = lauoutId;
        this.mCount = count;
        this.mViewTypeItem = viewTypeItem;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public K onCreateViewHolder(ViewGroup parent, int viewType) {
     //   this.mContext = parent.getContext();
        if (viewType == mViewTypeItem){
            return (K) new BaseViewHolder(LayoutInflater.from(mContext).inflate(mLayoutId, parent, false));
        }
        return null;

    }

    @Override
    public void onBindViewHolder(K holder, int position) {
        convert(holder, getItem(position),position);
    }


    @Override
    public int getItemCount() {
        return mCount;
    }

    /**
     * 必须重写不然会出现滑动不流畅的情况
     */
    @Override
    public int getItemViewType(int position) {
        return mViewTypeItem;
    }

    protected abstract void convert(K helper, T item,int position);

    @Nullable
    public T getItem(@IntRange(from = 0) int position) {
        if (position < mDataList.size())
            return mDataList.get(position);
        else
            return null;
    }

    /**
     * 监听接口
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemChildClickListener {
        void onItemChildClick(View view, int position);
    }

    public void setOnItemClickListener(@Nullable OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public void setOnItemChildClickListener(OnItemChildClickListener listener) {
        mOnItemChildClickListener = listener;
    }

}

