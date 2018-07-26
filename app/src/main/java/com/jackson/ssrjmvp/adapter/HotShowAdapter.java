package com.jackson.ssrjmvp.adapter; /**
 * HotShowAdapter  2018-07-13
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jackson.ssrjmvp.R;
import com.jackson.ssrjmvp.bean.HotSHowBean;
import com.jackson.ssrjmvp.utils.GlideUtils;

import java.text.DecimalFormat;
import java.util.List;

/**
 * class description here
 *
 * @author Jackson
 * @version 1.0.0
 *          since 2018 07 13
 */
public class HotShowAdapter extends BaseQuickAdapter<HotSHowBean.SubjectsBean, BaseViewHolder> {

    public HotShowAdapter(@LayoutRes int layoutResId, @Nullable List<HotSHowBean.SubjectsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotSHowBean.SubjectsBean item) {

        // 加载图片
        GlideUtils.loadUrlImage(mContext, item.getImages().getMedium(), (ImageView) helper.getView(R.id.img_moive));

        // 电影名
        helper.setText(R.id.tv_moive_name, item.getTitle());

        // 评分
        helper.setRating(R.id.rating_moive, item.getRating().getAverage() / 2);

        // 导演
        helper.setText(R.id.tv_director, "导演：" + item.getDirectors().get(0).getName());

        // 主演
        if (item.getCasts() != null) {
            helper.setText(R.id.tv_leading_role, "主演：" + getActor(item.getCasts()));
        }


        // 看过
        helper.setText(R.id.tv_has_see, formatLargeNum(item.getCollect_count()) + "人看过");

     //   helper.addOnClickListener(R.id.ll_listener);
    }

    /**
     * 获取主演
     *
     * @param castList
     * @return
     */
    private String getActor(List<HotSHowBean.SubjectsBean.CastsBean> castList) {
        if (castList.size() > 0) {
            StringBuilder strBuilder = new StringBuilder();
            for (HotSHowBean.SubjectsBean.CastsBean castsBean : castList) {
                strBuilder.append(castsBean.getName() + " / ");
            }
            strBuilder.delete(strBuilder.length() - 2, strBuilder.length());
            return strBuilder.toString();
        } else {
            return "";
        }

    }

    /**
     * 格式化大数字
     *
     * @param number
     * @return
     */
    private String formatLargeNum(int number) {
        String ns = number + "";
        if (ns.length() < 5) {
            return ns;
        }
        double a = (number / 10000f);
        DecimalFormat df = new DecimalFormat(".0");
        return df.format(a) + "万";
    }


}

