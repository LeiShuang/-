package com.zfsoft.zfsoft_product.modules.report;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 创建日期：2019/1/7 on 16:10
 * 描述:ViewPager适配器
 * 作者:Ls
 */
public class ReportViewPagerAdapter extends FragmentPagerAdapter {
    private String [] titles;
    private List<Fragment> fragments;

    public ReportViewPagerAdapter(FragmentManager fm, String[] titles, List<Fragment> fragments) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
