package com.zfsoft.zfsoft_product.modules.personal.my_report;

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
public abstract class MyReportModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract MyReportFragment myTryReportFragment();

    @ActivityScoped
    @Provides
    MyReportContract.Presenter presenter(HttpManager httpManager, ApiService apiService){
        return new MyReportPresenter(apiService,httpManager);
    }
}
