package com.zfsoft.zfsoft_product.modules.login.info;

import com.zfsoft.zfsoft_product.di.ActivityScoped;
import com.zfsoft.zfsoft_product.di.FragmentScoped;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.HttpManager;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ckw
 * on 2019/1/17.
 */
@Module
public abstract class SetInfoModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract SetInfoFragment setInfoFragment();

    @ActivityScoped
    @Provides
    SetInfoContract.Presenter presenter(HttpManager httpManager, ApiService apiService){
        return new SetInfoPresenter(httpManager,apiService);
    }
}
