package com.jackson.ssrjmvp.adapter; /**
 * RecyclerViewAdapter  2017-08-02
 * Copyright (c) 2017 SSRJ Co.Ltd. All right reserved.
 */

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jackson.ssrjmvp.R;
import com.jackson.ssrjmvp.bean.TabBean;

import java.util.List;


/**
 * class description here
 * @author Borje
 * @version 1.0.0
 * since 2017 08 02
 */
public class RecyclerViewAdapter extends BaseQuickAdapter<TabBean,BaseViewHolder> {



    public RecyclerViewAdapter(int layoutResId, @Nullable List<TabBean> data) {
        super(layoutResId, data);
    }



    @Override
    protected void convert(BaseViewHolder helper, TabBean item) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.default_square_four)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(mContext)
                .load(item.getUrl())
                .apply(options)
                .into((ImageView) helper.getView(R.id.image));

        helper.setText(R.id.text,item.getTerxt());

    }





}