package com.zfsoft.zfsoft_product.di;

import com.zfsoft.zfsoft_product.base.HomeActivity;
import com.zfsoft.zfsoft_product.base.HomeModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * 创建日期：2018/12/24 on 10:11
 * 描述:
 * 作者:Ls
 */
@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeActivity homeActivity();
}
