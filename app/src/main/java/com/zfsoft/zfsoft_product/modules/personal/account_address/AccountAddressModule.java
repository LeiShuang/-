package com.zfsoft.zfsoft_product.modules.personal.account_address;

import com.zfsoft.zfsoft_product.di.ActivityScoped;
import com.zfsoft.zfsoft_product.di.FragmentScoped;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.HttpManager;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ckw
 * on 2019/1/19.
 */
@Module
public abstract class AccountAddressModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract AccountAddressFragment accountAddressFragment();

    @ActivityScoped
    @Provides
    AccountAddressContract.Presenter presenter(HttpManager httpManager, ApiService apiService){
        return new AccountAddressPresenter(httpManager,apiService);
    }
}
