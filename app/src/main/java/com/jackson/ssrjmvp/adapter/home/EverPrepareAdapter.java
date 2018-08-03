package com.jackson.ssrjmvp.adapter.home; /**
 * GridMenuAdapter  2018-07-23
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.vlayout.LayoutHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jackson.ssrjmvp.R;
import com.jackson.ssrjmvp.adapter.BaseDelegateAdapter;
import com.jackson.ssrjmvp.bean.HomeBean;

import java.util.List;

/**
 * 常备好药, 线性布局（SingleLayoutHelper）
 *
 * @author Jackson
 * @version 1.0.0
 *          since 2018 07 23
 */
public class EverPrepareAdapter extends BaseDelegateAdapter<HomeBean.DataBean.ItemsBean, BaseViewHolder> {

    private List<HomeBean.DataBean.ItemsBean> mDataList;

    /**
     * 构造方法
     *
     * @param context
     * @param list
     * @param layoutHelper
     * @param lauoutId
     * @param count
     * @param viewTypeItem
     */
    public EverPrepareAdapter(Context context, List<HomeBean.DataBean.ItemsBean> list, LayoutHelper layoutHelper, int lauoutId, int count, int viewTypeItem) {
        super(context, list, layoutHelper, lauoutId, count, viewTypeItem);
        this.mDataList = list;
    }


    @Override
    protected void convert(final BaseViewHolder helper, HomeBean.DataBean.ItemsBean item, final int position) {

        RecyclerView recyclerView=helper.getView(R.id.prepareRecyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        ItemPrepareAdapter itemPrepareAdapter=new ItemPrepareAdapter(R.layout.item_item_home_everprepare_layout,mDataList);
        recyclerView.setAdapter(itemPrepareAdapter);

        itemPrepareAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
             //   LogUtil.d("onitem-"+position);
                mOnItemClickListener.onItemClick(view,position);
            }
        });

//        itemPrepareAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                mOnItemChildClickListener.onItemChildClick(view,position);
//            }
//        });


    }


}

