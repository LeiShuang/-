package com.zfsoft.zfsoft_product.modules.personal.integral_mall;

import com.zfsoft.zfsoft_product.di.ActivityScoped;
import com.zfsoft.zfsoft_product.di.FragmentScoped;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.HttpManager;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ckw
 * on 2019/1/21.
 */
@Module
public abstract class IntegralMallModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract IntegralMallFragment integralMallFragment();

    @ActivityScoped
    @Provides
    IntegralMallContract.Presenter presenter(HttpManager httpManager, ApiService apiService){
        return new IntegralMallPresenter(httpManager,apiService);
    }
}
