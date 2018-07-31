package com.jackson.ssrjmvp.adapter.home; /**
 * BannerAdapter  2018-07-19
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */

import android.content.Context;
import android.view.View;

import com.alibaba.android.vlayout.LayoutHelper;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jackson.ssrjmvp.R;
import com.jackson.ssrjmvp.adapter.BaseDelegateAdapter;

import java.util.List;

/**
 * 可选固定布局,ScrollFixLayoutHelper
 *
 * @author Jackson
 * @version 1.0.0
 *          since 2018 07 19
 */
public class ScrollFixAdapter extends BaseDelegateAdapter<String, BaseViewHolder> {


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
    public ScrollFixAdapter(Context context, List<String> list, LayoutHelper layoutHelper, int lauoutId, int count, int viewTypeItem) {
        super(context, list, layoutHelper, lauoutId, count, viewTypeItem);
    }

    @Override
    protected void convert(final BaseViewHolder helper, String item, final int pos) {
        helper.getView(R.id.img_fix).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(view,pos);
            }
        });
    }

}

