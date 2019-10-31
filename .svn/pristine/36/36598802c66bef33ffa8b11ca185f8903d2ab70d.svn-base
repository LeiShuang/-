package com.zfsoft.zfsoft_product.modules.try_use.viewpager_modules;

import com.zfsoft.zfsoft_product.di.ActivityScoped;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.HttpManager;

import dagger.Module;
import dagger.Provides;

/**
 * 创建日期：2019/1/21 on 9:34
 * 描述:
 * 作者:Ls
 */
@Module
public abstract  class TryUseChildFragmentModule {
    @ActivityScoped
    @Provides
    TryUseChildFragmentContract.Presenter tryUseChildPresenter(ApiService apiService,HttpManager manager){
        TryUseChildFragmentPresenter presenter = new TryUseChildFragmentPresenter(apiService,manager);
        return presenter;
    }

}
