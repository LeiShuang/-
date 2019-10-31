package com.zfsoft.zfsoft_product.modules.personal.integral_mall.integral_detail;

import com.zfsoft.zfsoft_product.di.ActivityScoped;
import com.zfsoft.zfsoft_product.di.FragmentScoped;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.HttpManager;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ckw
 * on 2019/1/23.
 */
@Module
public abstract class IntegralDetailModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract IntegralDetailFragment integralDetailFragment();

    @ActivityScoped
    @Provides
    IntegralDetailContract.Presenter presenter(HttpManager httpManager, ApiService apiService){
        return new IntegralDetailPresenter(httpManager,apiService);
    }
}
