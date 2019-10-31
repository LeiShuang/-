package com.zfsoft.zfsoft_product.modules.login.splash;

import com.zfsoft.zfsoft_product.di.FragmentScoped;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ckw
 * on 2019/1/23.
 */
@Module
public abstract class SplashModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SplashFragment splashFragment();
}
