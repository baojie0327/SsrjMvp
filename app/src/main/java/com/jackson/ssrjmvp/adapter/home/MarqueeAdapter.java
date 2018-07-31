package com.jackson.ssrjmvp.adapter.home; /**
 * MarqueeAdapter  2018-07-24
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */

import android.content.Context;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jackson.ssrjmvp.R;
import com.jackson.ssrjmvp.adapter.BaseDelegateAdapter;
import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

/**
 * 公告,LinearLayoutHelper
 * @author Jackson
 * @version 1.0.0
 * since 2018 07 24
 */
public class MarqueeAdapter extends BaseDelegateAdapter<String,BaseViewHolder>{

    private List<String> infoList = new ArrayList<>();

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
    public MarqueeAdapter(Context context, List<String> list, LayoutHelper layoutHelper, int lauoutId, int count, int viewTypeItem) {
        super(context, list, layoutHelper, lauoutId, count, viewTypeItem);
        infoList=list;
    }


    @Override
    protected void convert(BaseViewHolder helper, String item, int position) {
        MarqueeView marqueeView=helper.getView(R.id.marqueeView);
        marqueeView.startWithList(infoList);
        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                mOnItemClickListener.onItemClick(textView,position);
            }
        });
    }


}

