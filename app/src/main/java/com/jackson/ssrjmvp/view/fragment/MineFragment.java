package com.jackson.ssrjmvp.view.fragment; /**
 * MineFragment  2017-09-14
 * Copyright (c) 2017 KL Co.Ltd. All right reserved.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jackson.ssrjmvp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2017 09 14
 */
public class MineFragment extends Fragment{
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view==null){
            view=inflater.inflate(R.layout.fragment_mine_layout,null);
        }
        Log.d("hbj--",""+getDaysInterval("2019-10-10","2092-10-10"));
        return view;
    }


    public static int getDaysInterval(String startDate, String endDate) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        int days = 0;
        try {
            Date date1 = format.parse(startDate);
            Date date2 = format.parse(endDate);
            days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return days;

    }


    /**
     *提供Fragment实例
     * @return
     */
    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
}

