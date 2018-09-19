package com.jackson.ssrjmvp.adapter; /**
 * RecyclerViewAdapter  2017-08-02
 * Copyright (c) 2017 SSRJ Co.Ltd. All right reserved.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jackson.ssrjmvp.R;


/**
 * class description here
 * @author Borje
 * @version 1.0.0
 * since 2017 08 02
 */
public class RecyclerViewAdapter1 extends RecyclerView.Adapter<RecyclerViewAdapter1.MyViewHolder>{


    private Context mContext;
    private int mCount;

    public RecyclerViewAdapter1(Context mContext,int count) {
        this.mContext = mContext;
        this.mCount=count;
    }

    public void addData(int count){
        this.mCount=count;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item1_recycle_layout,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        final MyViewHolder holder= (MyViewHolder) viewHolder;
    }

    @Override
    public int getItemCount() {
        return mCount;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}