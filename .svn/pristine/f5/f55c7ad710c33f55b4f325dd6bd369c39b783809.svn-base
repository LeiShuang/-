package com.zfsoft.zfsoft_product.modules.home.notice;

import com.zfsoft.zfsoft_product.di.ActivityScoped;
import com.zfsoft.zfsoft_product.di.FragmentScoped;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.HttpManager;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ckw
 * on 2019/4/12.
 */
@Module
public abstract class NoticeModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract NoticeFragment noticeFragment();

    @ActivityScoped
    @Provides
    NoticeContract.Presenter presenter(HttpManager httpManager, ApiService apiService){
        return new NoticePresenter(httpManager,apiService);
    }
}
