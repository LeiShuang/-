package com.zfsoft.zfsoft_product.modules.login.third_phone;

import com.zfsoft.zfsoft_product.di.ActivityScoped;
import com.zfsoft.zfsoft_product.di.FragmentScoped;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.HttpManager;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ckw
 * on 2019/3/19.
 */
@Module
public abstract class ThirdPhoneModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract ThirdPhoneFragment thirdPhoneFragment();

    @ActivityScoped
    @Provides
    ThirdPhoneContract.Presenter presenter(HttpManager httpManager, ApiService apiService){
        return new ThirdPhonePresenter(httpManager,apiService);
    }
}
