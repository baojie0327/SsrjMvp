package com.jackson.ssrjmvp.adapter; /**
 * FragmentAdapter  2017-07-28
 * Copyright (c) 2017 SSRJ Co.Ltd. All right reserved.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * class description here
 *
 * @author Borje
 * @version 1.0.0
 *          since 2017 07 28
 */
public class FragmentAdapter extends FragmentStatePagerAdapter {

    public List<Fragment> mFragments;


    public FragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;

    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }



    @Override
    public int getCount() {
        return mFragments.size();
    }




}