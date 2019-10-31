package com.zfsoft.zfsoft_product.modules.personal.account_address.add;

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
public abstract class NewAddAddressModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract NewAddAddressFragment newAddAddressFragment();

    @ActivityScoped
    @Provides
    NewAddAddressContract.Presenter presenter(HttpManager httpManager, ApiService apiService){
        return new NewAddAddressPresenter(httpManager,apiService);
    }
}
