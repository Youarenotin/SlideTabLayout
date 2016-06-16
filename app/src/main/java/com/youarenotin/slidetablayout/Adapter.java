package com.youarenotin.slidetablayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 作者：lubo on 6/2 0002 09:24
 * 邮箱：lubo_wen@126.com
 */
public class Adapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<Integer> fragBgs;
    public Adapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getArguments().getString("title");
    }

    @Override
    public int getCount() {
        return fragments.size();
    }



}
