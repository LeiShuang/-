package com.zfsoft.zfsoft_product.modules.report.discuss_detail;

import com.zfsoft.zfsoft_product.di.ActivityScoped;
import com.zfsoft.zfsoft_product.di.FragmentScoped;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.HttpManager;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * 创建日期：2019/1/28 on 11:31
 * 描述:
 * 作者:Ls
 */
@Module
public abstract  class DiscussDetailModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract DiscussDetailFragment mDiscussDetailFragment();

    @ActivityScoped
    @Provides
    DiscussDetailContract.Presenter presenter(HttpManager httpManager, ApiService apiService){
        return new DiscussDetailPresenter(apiService,httpManager);
    }

    @ActivityScoped
    @Provides
    DiscussDetailContract.discussPresenter mDiscussPresenter(HttpManager httpManager,ApiService apiService){
        return new DiscussSubmitPresenter(httpManager,apiService);
    }
}
