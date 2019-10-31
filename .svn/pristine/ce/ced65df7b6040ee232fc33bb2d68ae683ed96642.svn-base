package com.zfsoft.zfsoft_product.modules.personal.my_concern;

import com.zfsoft.zfsoft_product.di.ActivityScoped;
import com.zfsoft.zfsoft_product.di.FragmentScoped;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.HttpManager;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ckw
 * on 2019/2/22.
 */
@Module
public abstract class MyConcernModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract MyConcernFragment myConcernFragment();

    @ActivityScoped
    @Provides
    MyConcernContract.Presenter presenter(HttpManager httpManager, ApiService apiService){
        return new MyConcernPresenter(apiService,httpManager);
    }
}
