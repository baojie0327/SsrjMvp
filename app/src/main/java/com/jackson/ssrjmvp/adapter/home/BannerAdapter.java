package com.jackson.ssrjmvp.adapter.home; /**
 * BannerAdapter  2018-07-19
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.android.vlayout.LayoutHelper;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jackson.ssrjmvp.R;
import com.jackson.ssrjmvp.adapter.BaseDelegateAdapter;
import com.jackson.ssrjmvp.utils.CommonMethod;
import com.jackson.ssrjmvp.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * banner的适配器,栏布局（SingleLayoutHelper）
 *
 * @author Jackson
 * @version 1.0.0
 *          since 2018 07 19
 */
public class BannerAdapter extends BaseDelegateAdapter<String, BaseViewHolder> {

    private List<String> imgUrl = new ArrayList<>();

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
    public BannerAdapter(Context context, List<String> list, LayoutHelper layoutHelper, int lauoutId, int count, int viewTypeItem) {
        super(context, list, layoutHelper, lauoutId, count, viewTypeItem);
        imgUrl = list;
    }


    @Override
    protected void convert(final BaseViewHolder helper, String item, final int pos) {

        Banner mBanner = helper.getView(R.id.banner);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.height = CommonMethod.convertDpToPixel(mContext, 200);
        mBanner.setLayoutParams(layoutParams);
        mBanner.setImages(imgUrl);
        mBanner.start();
       // Banner监听
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                mOnItemClickListener.onItemClick(helper.itemView,position);
            }
        });
    }

}

