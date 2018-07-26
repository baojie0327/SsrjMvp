package com.jackson.ssrjmvp.adapter.home; /**
 * ItemPrepareAdapter  2018-07-26
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jackson.ssrjmvp.R;
import com.jackson.ssrjmvp.bean.HomeBean;
import com.jackson.ssrjmvp.utils.GlideUtils;

import java.util.List;

/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 07 26
 */
public class ItemPrepareAdapter extends BaseQuickAdapter<HomeBean.DataBean.ItemsBean,BaseViewHolder>{

    public ItemPrepareAdapter(@LayoutRes int layoutResId, @Nullable List<HomeBean.DataBean.ItemsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.DataBean.ItemsBean item) {

        GlideUtils.loadUrlImage(mContext,
                "http:" + item.getItem().getMainImg(),
                (ImageView) helper.getView(R.id.img_prepare_menu));
        helper.setText(R.id.tv_prepare_menu,item.getItem().getSkuName());
     //   helper.addOnClickListener(R.id.img_prepare_menu);
    //    helper.addOnClickListener(R.id.img_prepare_menu).addOnClickListener(R.id.tv_prepare_menu);

    }
}

