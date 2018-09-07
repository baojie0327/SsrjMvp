package com.example.ahuang.myframe.adapter; /**
 * RecyclerViewAdapter  2017-08-02
 * Copyright (c) 2017 SSRJ Co.Ltd. All right reserved.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ahuang.myframe.R;

/**
 * class description here
 * @author Borje
 * @version 1.0.0
 * since 2017 08 02
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{


    private Context mContext;

    public RecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_recycle_layout,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        final MyViewHolder holder= (MyViewHolder) viewHolder;
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}