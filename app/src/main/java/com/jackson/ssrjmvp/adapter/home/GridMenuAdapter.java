package com.jackson.ssrjmvp.adapter.home; /**
 * GridMenuAdapter  2018-07-23
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jackson.ssrjmvp.R;
import com.jackson.ssrjmvp.adapter.BaseDelegateAdapter;
import com.jackson.ssrjmvp.bean.HomeBean;
import com.jackson.ssrjmvp.utils.GlideUtils;

import java.util.List;

/**
 * GridLayoutHelper
 *
 * @author Jackson
 * @version 1.0.0
 *          since 2018 07 23
 */
public class GridMenuAdapter extends BaseDelegateAdapter<HomeBean.DataBean.ItemsBean, BaseViewHolder> {


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
    public GridMenuAdapter(Context context, List<HomeBean.DataBean.ItemsBean> list, LayoutHelper layoutHelper, int lauoutId, int count, int viewTypeItem) {
        super(context, list, layoutHelper, lauoutId, count, viewTypeItem);
    }

    @Override
    protected void convert(final BaseViewHolder helper, HomeBean.DataBean.ItemsBean item, final int position) {

        // 图片
        GlideUtils.loadUrlImage(mContext,
                "http:" + item.getItem().getImg(),
                (ImageView) helper.getView(R.id.img_grid_menu));
        // 加载文字
        helper.setText(R.id.tv_grid_menu, item.getName());

        helper.getView(R.id.ll_grid_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(view,position);
            }
        });

/*
        helper.getView(R.id.img_grid_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemChildClickListener.onItemChildClick(view,position);
            }
        });

        helper.getView(R.id.tv_grid_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemChildClickListener.onItemChildClick(view,position);
            }
        });
zhangyife*/


    }




}

