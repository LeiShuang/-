package com.zfsoft.zfsoft_product.modules.report.submit_report;

import com.zfsoft.zfsoft_product.di.ActivityScoped;
import com.zfsoft.zfsoft_product.di.FragmentScoped;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.HttpManager;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * 创建日期：2019/1/23 on 14:50
 * 描述:
 * 作者:Ls
 */
@Module
public abstract class SubmitReportModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract SubmitReportFragment mSubmitReportFragment();


    @ActivityScoped
    @Provides
    SubmitReportContract.Presenter mPresenter(HttpManager manager, ApiService apiService){
        return new SubmitReportPresenter(manager,apiService);
    }
}
