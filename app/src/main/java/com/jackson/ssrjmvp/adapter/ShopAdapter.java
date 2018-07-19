package com.jackson.ssrjmvp.adapter; /**
 * ShopAdapter  2018-02-06
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jackson.ssrjmvp.R;
import com.jackson.ssrjmvp.bean.ShopBean;

import java.util.List;

/**
 * 资讯界面的适配器
 *
 * @author Jackson
 * @version 1.0.0
 *          since 2018 02 06
 */
public class ShopAdapter extends BaseQuickAdapter<ShopBean.ShopDetails, BaseViewHolder> {

    private String type;

    public ShopAdapter(@LayoutRes int layoutResId, @Nullable List<ShopBean.ShopDetails> data) {
        super(layoutResId, data);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopBean.ShopDetails item) {
        helper.setText(R.id.tv_item_shop_name, item.getMerchantName());
        helper.setText(R.id.tv_shop_landmark, "[" + item.getLandMark() + "]");
        String disflag = item.getDiscountMerFlag();//1--添加惠字 0--不添加

        /**
         * 折扣
         */
        if ("1".equals(disflag)) {//惠买单用户
            String discoun = item.getDiscount();
            helper.setGone(R.id.tv_discount_content, true);
            if ("10".equals(item.getDiscount())) {
                helper.setText(R.id.tv_discount_content, "当前时段无折扣");
            } else {
                helper.setText(R.id.tv_discount_content, item.getDiscount());
            }
            helper.setText(R.id.tx_Sold, "");
        } else if ("0".equals(item.getIsDiscountMer())) {
            helper.setGone(R.id.tv_discount_content, true);
            if ("10".equals(item.getDiscount())) {
                helper.setText(R.id.tv_discount_content, "无折扣");
            } else {
                helper.setText(R.id.tv_discount_content, item.getDiscount() + "折");
            }
            /**
             * 已售为0的话，要隐藏掉已售和折扣右边的竖线
             */
            String saleCount = TextUtils.isEmpty(item.getSalesCount()) ? "" : item.getSalesCount();
            if (!TextUtils.isEmpty(saleCount) && !"0".equals(saleCount)) {
                helper.setGone(R.id.tx_Sold, true);
                helper.setText(R.id.tx_Sold, item.getSalesCount());
            } else {
                helper.setGone(R.id.tx_Sold, false);
            }

        } else {
            helper.setGone(R.id.iv_benefits_bill, false);
            helper.setGone(R.id.tv_discount_content, false);
            helper.setGone(R.id.tx_Sold, false);
            helper.setText(R.id.tv_discount_content, "");
        }

        //行业类型
        String industryType = (item.getIndustryName() == null) ? "" : item.getIndustryName();
        try {
            helper.setText(R.id.tv_item_shop_introduce1, industryType);
        } catch (Exception e) {
        }
        String goodComment = item.getGoodComment();
        if (goodComment == null || goodComment.equals("0%") || "".equals(goodComment)) {//好评度为0时不显示
            helper.setVisible(R.id.tx_comment, false);
        } else {
            helper.setText(R.id.tx_comment, item.getGoodComment() + "好评");
        }

        // 距离
        String distance = item.getDistance();
        int dist = Integer.parseInt(distance);
        if (dist > 1000) {
            double doubledist = Double.parseDouble(distance);
            String d = String.format("%.1f", doubledist * 1.0 / 1000);
            helper.setText(R.id.tv_item_shop_distance,d + "km");
        } else {
            helper.setText(R.id.tv_item_shop_distance,item.getDistance() + "m");
        }

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.default_square_four)
                //  .transform(new GlideCircleTransform(mContext))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);


        if (item.getHeadImg() != null) {
            String imgUrl = item.getHeadImg();
            Glide.with(mContext)
                    .load(imgUrl)
                    .apply(options)
                    .into((ImageView) helper.getView(R.id.iv_item_shop_head));
        } else {
            Glide.with(mContext)
                    .load("assets://loading_100.png")
                    .apply(options)
                    .into((ImageView) helper.getView(R.id.iv_item_shop_head));
        }


        int couponFlag = item.getCouponFlag();
        switch (couponFlag) {
            case 0:
                helper.setGone(R.id.iv_coupons_voucher,false);
                helper.setGone(R.id.iv_coupons_free,false);
                break;
            case 1:
                helper.setGone(R.id.iv_coupons_voucher,false);
                helper.setGone(R.id.iv_coupons_free,false);
                break;
            case 2:
                helper.setGone(R.id.iv_coupons_voucher,false);
                helper.setGone(R.id.iv_coupons_free,false);
                break;
            case 3:
                helper.setGone(R.id.iv_coupons_voucher,false);
                helper.setGone(R.id.iv_coupons_free,false);
                break;
            default:
                break;
        }
        String actID = item.getActID();
        if ("1".equals(type)) {  //type=0,惠买单，type=1，热门商户
            if (!"".equals(actID)) {
                Glide.with(mContext)
                        .load("assets://iv_hui_activity.png")
                        .apply(options)
                        .into((ImageView) helper.getView(R.id.iv_hui_activity));
                helper.setGone(R.id.iv_hui_activity,true);

            } else {
                helper.setGone(R.id.iv_hui_activity,false);
            }
        } else {
            if (!"0".equals(actID)) {
                Glide.with(mContext)
                        .load("assets://iv_hui_activity.png")
                        .apply(options)
                        .into((ImageView) helper.getView(R.id.iv_hui_activity));
                helper.setGone(R.id.iv_hui_activity,true);
            } else {
                helper.setGone(R.id.iv_hui_activity,false);
            }
        }


    }
}

