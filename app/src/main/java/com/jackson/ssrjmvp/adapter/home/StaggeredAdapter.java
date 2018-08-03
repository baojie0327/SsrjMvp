package com.jackson.ssrjmvp.adapter.home; /**
 * GridMenuAdapter  2018-07-23
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jackson.ssrjmvp.R;
import com.jackson.ssrjmvp.adapter.BaseDelegateAdapter;
import com.jackson.ssrjmvp.bean.HomeBean;
import com.jackson.ssrjmvp.utils.GlideUtils;

import java.util.List;

/**
 * 瀑布流（StaggeredGridLayoutHelper）
 *
 * @author Jackson
 * @version 1.0.0
 *          since 2018 07 23
 */
public class StaggeredAdapter extends BaseDelegateAdapter<HomeBean.DataBean.ItemsBean, BaseViewHolder> {

    private List<HomeBean.DataBean.ItemsBean> mDataList;

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
    public StaggeredAdapter(Context context, List<HomeBean.DataBean.ItemsBean> list, LayoutHelper layoutHelper, int lauoutId, int count, int viewTypeItem) {
        super(context, list, layoutHelper, lauoutId, count, viewTypeItem);
        this.mDataList = list;
    }


    @Override
    protected void convert(final BaseViewHolder helper, HomeBean.DataBean.ItemsBean item, final int position) {
        helper.getView(R.id.ll_staggered_item).post(new Runnable() {
            @Override
            public void run() {
           //     LogUtil.d(helper.getView(R.id.ll_staggered_item).getWidth()+"  "+helper.getView(R.id.ll_staggered_item).getHeight());
            }
        });

        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 750);
     //   LogUtil.d("width==" + layoutParams.width + " height==" + layoutParams.height);
        layoutParams.height = 750 + position % 3 * 30;
        /*if (position % 2 == 0) {
            layoutParams.height = 750 + position % 3 * 30;
        } else {
            layoutParams.height = 750 + position % 2 * 5;
        }*/
        helper.itemView.setLayoutParams(layoutParams);
        // 图片
        GlideUtils.loadUrlImage(mContext,
                "http:" + item.getItem().getMainImg(),
                (ImageView) helper.getView(R.id.img_staggered_menu));
        // 加载文字
        helper.setText(R.id.tv_staggered_menu, item.getItem().getSkuName());
        helper.setText(R.id.tv_staggered_factory, item.getItem().getFactoryName());
        helper.setText(R.id.tv_staggered_shop, item.getItem().getShopName());

        helper.getView(R.id.ll_staggered_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(view,position);
            }
        });

    }


}

