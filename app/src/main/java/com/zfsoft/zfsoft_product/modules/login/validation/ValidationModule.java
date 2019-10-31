package com.zfsoft.zfsoft_product.modules.login.validation;

import com.zfsoft.zfsoft_product.di.ActivityScoped;
import com.zfsoft.zfsoft_product.di.FragmentScoped;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.HttpManager;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ckw
 * on 2019/1/14.
 */
@Module
public abstract class ValidationModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract ValidationFragment validationFragment();

    @ActivityScoped
    @Provides
    ValidationContract.Presenter presenter(HttpManager httpManager, ApiService apiService){
        return new ValidationPresenter(httpManager,apiService);
    }
}
