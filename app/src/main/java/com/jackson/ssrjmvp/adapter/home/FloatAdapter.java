package com.jackson.ssrjmvp.adapter.home; /**
 * BannerAdapter  2018-07-19
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.LayoutHelper;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jackson.ssrjmvp.R;
import com.jackson.ssrjmvp.adapter.BaseDelegateAdapter;

import java.util.List;

/**
 * 浮动布局,FloatLayoutHelper
 *
 * @author Jackson
 * @version 1.0.0
 *          since 2018 07 19
 */
public class FloatAdapter extends BaseDelegateAdapter<String, BaseViewHolder> {


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
    public FloatAdapter(Context context, List<String> list, LayoutHelper layoutHelper, int lauoutId, int count, int viewTypeItem) {
        super(context, list, layoutHelper, lauoutId, count, viewTypeItem);
    }

    @Override
    protected void convert(final BaseViewHolder helper, String item, final int pos) {
       ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(200,200);
        helper.itemView.setLayoutParams(layoutParams);
        helper.getView(R.id.ll_listener).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(view,pos);
            }
        });

    }

}

