package com.zfsoft.zfsoft_product.modules.personal.my_try;

import com.zfsoft.zfsoft_product.di.ActivityScoped;
import com.zfsoft.zfsoft_product.di.FragmentScoped;
import com.zfsoft.zfsoft_product.modules.home.HomePresenter;
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
public abstract class MyTryModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract MyTryFragment myTryFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract MyTrySingleFragment myTrySingleFragment();

    @ActivityScoped
    @Provides
    MyTryContract.Presenter myTryPresenter(HttpManager httpManager,ApiService apiService){
        MyTryContract.Presenter presenter= new MyTryPresenter(apiService,httpManager);
        return presenter;
    }
}
