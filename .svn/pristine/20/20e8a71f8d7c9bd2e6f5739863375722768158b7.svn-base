package com.zfsoft.zfsoft_product.modules.login;

import com.zfsoft.zfsoft_product.di.ActivityScoped;
import com.zfsoft.zfsoft_product.di.FragmentScoped;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.HttpManager;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ckw
 * on 2019/1/12.
 */
@Module
public abstract class LoginModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract NewLoginFragment loginFragment();

    @ActivityScoped
    @Provides
    LoginContract.Presenter presenter(HttpManager httpManager, ApiService apiService){
        return new LoginPresenter(httpManager,apiService);
    }
}
