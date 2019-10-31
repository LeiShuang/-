package com.zfsoft.zfsoft_product.modules.personal.my_try;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ckw
 * on 2019/1/21.
 */
public class CommonFragmentAdapter<T> extends FragmentPagerAdapter {

    private String[] titles;
    private List<T> fragments;

    public CommonFragmentAdapter(FragmentManager fm,String[] titles,List<T> fragments) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return (Fragment) fragments.get(position);
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
