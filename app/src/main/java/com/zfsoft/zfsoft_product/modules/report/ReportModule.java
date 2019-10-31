package com.zfsoft.zfsoft_product.modules.report;

import com.zfsoft.zfsoft_product.di.ActivityScoped;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.HttpManager;

import dagger.Module;
import dagger.Provides;

/**
 * 创建日期：2019/2/15 on 17:47
 * 描述:
 * 作者:Ls
 */
@Module
public class ReportModule {
    @ActivityScoped
    @Provides
    ReportContract.Presenter reportPresenter(HttpManager httpManager, ApiService apiService) {
        ReportPresenter reportPresenter = new ReportPresenter(apiService, httpManager);
        return reportPresenter;
    }
}
