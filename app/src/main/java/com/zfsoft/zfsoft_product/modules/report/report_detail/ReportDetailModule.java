package com.zfsoft.zfsoft_product.modules.report.report_detail;

import com.zfsoft.zfsoft_product.di.ActivityScoped;
import com.zfsoft.zfsoft_product.di.FragmentScoped;
import com.zfsoft.zfsoft_product.modules.report.report_child.ReportViewPagerFragment;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.HttpManager;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * 创建日期：2019/1/8 on 19:28
 * 描述:
 * 作者:Ls
 */
@Module
public abstract class ReportDetailModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract ReportDetailFragment mReportDetailFragment();

    @ActivityScoped
    @Provides
    ReportDetailContract.Presenter mReportDetailPresenter(HttpManager manager, ApiService apiService){
        return  new ReportDetailPresenter(manager,apiService);
    }
}
