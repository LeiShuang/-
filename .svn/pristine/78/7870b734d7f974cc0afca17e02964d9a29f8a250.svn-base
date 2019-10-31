package com.zfsoft.zfsoft_product.modules.try_use.share_redbook;

import com.zfsoft.zfsoft_product.di.ActivityScoped;
import com.zfsoft.zfsoft_product.di.FragmentScoped;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.HttpManager;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * 创建日期：2019/2/26 on 18:34
 * 描述:
 * 作者:Ls
 */
@Module
public abstract class ShareRedBookModule {
    @ActivityScoped
    @Provides
    ShareRedBookContract.Presenter shareRedBookPresenter(HttpManager httpManager, ApiService apiService){
        ShareRedBookPresenter shareRedBookPresenter =   new ShareRedBookPresenter(apiService,httpManager);
        return shareRedBookPresenter;
    }

    @FragmentScoped
    @ContributesAndroidInjector
    abstract ShareRedBookFragment mShareRedBookFragment();
}
