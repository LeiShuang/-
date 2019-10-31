package com.zfsoft.zfsoft_product.modules.try_use.try_use_detail;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.List;

/**
 * 创建日期：2019/1/15 on 19:47
 * 描述:试用详情界面viewpager适配器（实在Acvtivity中使用）
 * @extends PagerAdapter
 * 作者:Ls
 */
public class TryUseDetailPagerAdapter extends FragmentPagerAdapter{
    private List<String> titleLists ;
    private List<Fragment> fragmentLists;

    public TryUseDetailPagerAdapter(FragmentManager fm,List<String> titleLists, List<Fragment> fragmentLists) {
        super(fm);
        this.titleLists = titleLists;
        this.fragmentLists = fragmentLists;
    }

    @Override
    public int getCount() {
        return titleLists.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentLists.get(position);
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleLists.get(position);
    }
}
