package com.zfsoft.zfsoft_product.base;

import com.zfsoft.zfsoft_product.di.FragmentScoped;
import com.zfsoft.zfsoft_product.modules.home.HomeFragment;
import com.zfsoft.zfsoft_product.modules.try_use.TryUseFragment;
import com.zfsoft.zfsoft_product.modules.personal.PersonalFragment;
import com.zfsoft.zfsoft_product.modules.report.ReportFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * 创建日期：2018/12/25 on 13:58
 * 描述:
 * 作者:Ls
 */
@Module
public abstract  class HomeModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract HomeFragment mHomeFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract ReportFragment mShoppingFragment();


    @FragmentScoped
    @ContributesAndroidInjector
    abstract TryUseFragment mNewsFragment();


    @FragmentScoped
    @ContributesAndroidInjector
    abstract PersonalFragment mPersonalFragment();


}
