package com.zfsoft.zfsoft_product.modules.try_use.try_use_detail;

import com.zfsoft.zfsoft_product.di.ActivityScoped;
import com.zfsoft.zfsoft_product.di.FragmentScoped;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.HttpManager;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * 创建日期：2019/1/12 on 16:26
 * 描述:
 * 作者:Ls
 */
@Module
public abstract class TryUseDetailModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract ProductDetailFragment mProductDetailFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract TryUseReportFragment mTryUseReportFragment();
    /**
     * 提供试用详情Presenter
     * */
   @ActivityScoped
   @Provides
   TryUseDetailContract.UseDetailPresenter mUseDetailPresenter(ApiService mApiService, HttpManager manager){

       TryUseDetailPresenter mPresenter = new TryUseDetailPresenter(manager,mApiService);
       return mPresenter;
   }

    /**
     * 提供试用列表Presenter
     * */
    @ActivityScoped
    @Provides
    TryUseDetailContract.TryUseReportPresenter mTryUseReportPresenter(ApiService mApiService, HttpManager manager){

       TryUseReportPresenter mPresenter = new TryUseReportPresenter(manager,mApiService);
        return mPresenter;
    }
}
