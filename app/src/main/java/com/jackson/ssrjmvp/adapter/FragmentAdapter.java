package com.example.ahuang.myframe.adapter.wrapper.viewpager; /**
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

    private List<Fragment> mFragments;
    private List<String> mTitles;

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);
        this.mFragments = fragments;
        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    //此方法用来显示tab上的名字
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}