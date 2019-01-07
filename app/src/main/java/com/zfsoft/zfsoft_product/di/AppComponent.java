package com.zfsoft.zfsoft_product.di;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.zfsoft.zfsoft_product.base.BaseApplication;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.HttpManager;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import retrofit2.Retrofit;

/**
 * 创建日期：2018/12/24 on 10:10
 * 描述:
 * 作者:Ls
 */
@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
        ActivityBindingModule.class,
        AppModule.class,
        NetModule.class
})
public interface AppComponent extends AndroidInjector<BaseApplication> {

    Context getContext();

    Gson getGson();

    ApiService getApiService();

    HttpManager getHttpManager();

    Retrofit getRetrofit();

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
