package com.zfsoft.zfsoft_product.modules.personal.my_platform;

import com.zfsoft.zfsoft_product.di.ActivityScoped;
import com.zfsoft.zfsoft_product.di.FragmentScoped;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.HttpManager;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ckw
 * on 2019/3/15.
 */
@Module
public abstract class MyPlatformModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract MyPlatformFragment myPlatformFragment();

    @ActivityScoped
    @Provides
    MyPlatformContract.Presenter presenter(HttpManager httpManager, ApiService apiService){
        return new MyPlatformPresenter(apiService,httpManager);
    }
}
