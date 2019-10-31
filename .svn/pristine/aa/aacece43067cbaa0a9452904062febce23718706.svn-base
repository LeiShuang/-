package com.zfsoft.zfsoft_product.modules.personal.accout_safe.modify;

import com.zfsoft.zfsoft_product.di.ActivityScoped;
import com.zfsoft.zfsoft_product.di.FragmentScoped;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.HttpManager;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ckw
 * on 2019/1/25.
 */
@Module
public abstract class ModifyPhoneModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract ModifyPhoneFragment modifyPhoneFragment();

    @ActivityScoped
    @Provides
    ModifyContract.Presenter presenter(HttpManager httpManager, ApiService apiService){
        return new ModifyPresenter(httpManager,apiService);
    }
}
