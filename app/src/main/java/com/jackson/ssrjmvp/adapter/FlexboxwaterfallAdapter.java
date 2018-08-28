package com.jackson.ssrjmvp.adapter; /**
 * FlexboxFlowAdapter  2018-08-27
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.jackson.ssrjmvp.R;
import com.jackson.ssrjmvp.utils.GlideUtils;
import com.jackson.ssrjmvp.utils.LogUtil;

import java.util.List;

/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 08 27
 */
public class FlexboxwaterfallAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public FlexboxwaterfallAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
      //  helper.setImageResource(R.id.img_waterfall,item);
        GlideUtils.loadUrlImage(mContext,item, (ImageView) helper.getView(R.id.img_waterfall));


        ViewGroup.LayoutParams lp = helper.getView(R.id.img_waterfall).getLayoutParams();
        LogUtil.d("=="+(lp instanceof FlexboxLayoutManager.LayoutParams));
        if (lp instanceof FlexboxLayoutManager.LayoutParams) {
            FlexboxLayoutManager.LayoutParams flexboxLp =
                    (FlexboxLayoutManager.LayoutParams) helper.getView(R.id.img_waterfall).getLayoutParams();
            flexboxLp.setFlexGrow(1.0f);
           /* flexboxLp.setFlexBasisPercent(1.0f);
            flexboxLp.setAlignSelf(AlignSelf.STRETCH);*/

        }

    }
}

