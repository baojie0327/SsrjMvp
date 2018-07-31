package com.jackson.ssrjmvp.adapter.home; /**
 * StickyAdapter  2018-07-31
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */

import android.content.Context;

import com.alibaba.android.vlayout.LayoutHelper;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jackson.ssrjmvp.adapter.BaseDelegateAdapter;

import java.util.List;

/**
 * 吸边布局（StickyLayoutHelper）
 * @author Jackson
 * @version 1.0.0
 * since 2018 07 31
 */
public class StickyAdapter extends BaseDelegateAdapter<String,BaseViewHolder>{

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
    public StickyAdapter(Context context, List<String> list, LayoutHelper layoutHelper, int lauoutId, int count, int viewTypeItem) {
        super(context, list, layoutHelper, lauoutId, count, viewTypeItem);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item, int position) {

    }
}

