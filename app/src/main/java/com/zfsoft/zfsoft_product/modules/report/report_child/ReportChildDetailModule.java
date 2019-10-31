package com.zfsoft.zfsoft_product.modules.report.report_child;

import com.zfsoft.zfsoft_product.di.ActivityScoped;
import com.zfsoft.zfsoft_product.di.FragmentScoped;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.HttpManager;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * 创建日期：2019/1/25 on 17:26
 * 描述:
 * 作者:Ls
 */
@Module
public abstract class ReportChildDetailModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract ReportViewPagerFragment mReportViewPagerFragment();

    @ActivityScoped
    @Provides
    ReportChildDetailContract.Presenter mReportChildDetailPresenter(HttpManager manager, ApiService apiService){
        return  new ReportChildDetailPresenter(manager,apiService);
    }
}
