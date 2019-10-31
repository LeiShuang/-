package com.zfsoft.zfsoft_product.modules.report.other_info;

import com.zfsoft.zfsoft_product.di.ActivityScoped;
import com.zfsoft.zfsoft_product.di.FragmentScoped;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.HttpManager;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * 创建日期：2019/1/17 on 16:56
 * 描述:
 * 作者:Ls
 */
@Module
public abstract class OtherInformationModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract OtherInformationFragment mOtherInformationFragment();

    @ActivityScoped
    @Provides
    OtherInformationContract.Presenter presenter(HttpManager httpManager, ApiService apiService){
        return new OtherInformationPresenter(httpManager,apiService);
    }
}
