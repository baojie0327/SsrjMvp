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
 * 七月爆品,GridLayoutHelper
 *
 * @author Jackson
 * @version 1.0.0
 *          since 2018 07 23
 */
public class HotItemAdapter extends BaseDelegateAdapter<HomeBean.DataBean.ItemsBean, BaseViewHolder> {


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
    public HotItemAdapter(Context context, List<HomeBean.DataBean.ItemsBean> list, LayoutHelper layoutHelper, int lauoutId, int count, int viewTypeItem) {
        super(context, list, layoutHelper, lauoutId, count, viewTypeItem);
    }

    @Override
    protected void convert(final BaseViewHolder helper, HomeBean.DataBean.ItemsBean item, final int position) {
    //    LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    //    helper.getView(R.id.img_hotitem_menu).setLayoutParams(layoutParams);
        // 图片
     //   LogUtil.d("hotpos=="+position);
        GlideUtils.loadUrlImage(mContext,
                "http:" + item.getItem().getImg(),
                (ImageView) helper.getView(R.id.img_hotitem_menu));


        helper.getView(R.id.ll_hot_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(view,position);
            }
        });


    }




}

