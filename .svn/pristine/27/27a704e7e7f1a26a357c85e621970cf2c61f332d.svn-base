package com.zfsoft.zfsoft_product.modules.personal.change_pwd;

import com.zfsoft.zfsoft_product.di.ActivityScoped;
import com.zfsoft.zfsoft_product.di.FragmentScoped;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.HttpManager;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ckw
 * on 2019/1/18.
 */
@Module
public abstract class ChangePwdModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract ChangePwdFragment changePwdFragment();

    @ActivityScoped
    @Provides
    ChangePwdContract.Presenter presenter(HttpManager httpManager, ApiService apiService){
        return new ChangePwdPresenter(httpManager,apiService);
    }
}
