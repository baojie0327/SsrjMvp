package com.jackson.ssrjmvp.adapter; /**
 * FlexboxFlowAdapter  2018-08-27
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jackson.ssrjmvp.R;

import java.util.List;

/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 08 27
 */
public class FlexboxFlowAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public FlexboxFlowAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_content,item);
    }
}

