package com.zfsoft.zfsoft_product.modules.try_use;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 创建日期：2019/1/5 on 14:02
 * 描述:fragment viewpager 的适配器
 * 作者:Ls
 */
public class TryUseFragmentAdapter extends FragmentPagerAdapter {
    private String [] titles;
    private List<Fragment> fragments;

    public TryUseFragmentAdapter(FragmentManager fm,String[] titleList,List<Fragment> fragmentList) {
        super(fm);
        this.titles = titleList;
        this.fragments = fragmentList;
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
