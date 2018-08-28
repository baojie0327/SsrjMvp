package com.jackson.ssrjmvp; /**
 * FlexboxFlowAdapter  2018-08-27
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.flexbox.FlexboxLayoutManager;
import com.jackson.ssrjmvp.utils.GlideUtils;
import com.jackson.ssrjmvp.utils.LogUtil;

import java.util.List;

/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 08 27
 */
public class Flexboxwaterfall1Adapter extends RecyclerView.Adapter<Flexboxwaterfall1Adapter.MyViewHolder> {

    private List<String> mDatas;
    private Context mContext;

    public Flexboxwaterfall1Adapter(Context context,List<String> data){
        this.mContext=context;
        this.mDatas=data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_flow_waterfall_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
     //   holder.img.setImageResource(mDatas.get(position));
        GlideUtils.loadUrlImage(mContext,mDatas.get(position), holder.img);
        ViewGroup.LayoutParams lp = holder.img.getLayoutParams();
        LogUtil.d("=="+(lp instanceof FlexboxLayoutManager.LayoutParams));
        if (lp instanceof FlexboxLayoutManager.LayoutParams) {
            FlexboxLayoutManager.LayoutParams flexboxLp =
                    (FlexboxLayoutManager.LayoutParams)  holder.img.getLayoutParams();
            flexboxLp.setFlexGrow(1.0f);

        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }



   /* @Override
    protected void convert(BaseViewHolder helper, Integer item) {

        GlideUtils.loadUrlImage(mContext,item, (ImageView) helper.getView(R.id.img_waterfall));


        ViewGroup.LayoutParams lp = helper.getView(R.id.img_waterfall).getLayoutParams();
        LogUtil.d("=="+(lp instanceof FlexboxLayoutManager.LayoutParams));
        if (lp instanceof FlexboxLayoutManager.LayoutParams) {
            FlexboxLayoutManager.LayoutParams flexboxLp =
                    (FlexboxLayoutManager.LayoutParams) helper.getView(R.id.img_waterfall).getLayoutParams();
            flexboxLp.setFlexGrow(1.0f);
           *//* flexboxLp.setFlexBasisPercent(1.0f);
            flexboxLp.setAlignSelf(AlignSelf.STRETCH);*//*

        }

    }*/

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img_waterfall);
        }

    }
}

