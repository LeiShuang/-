package com.zfsoft.zfsoft_product.di;

import com.google.gson.Gson;
import com.zfsoft.zfsoft_product.common.Config;
import com.zfsoft.zfsoft_product.net.ApiService;
import com.zfsoft.zfsoft_product.net.HttpManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 创建日期：2018/12/24 on 10:31
 * 描述:
 * 作者:Ls
 */
@Module
public class NetModule {
    @Singleton
    @Provides
    Gson provideGson() {
        return new Gson();
    }

    @Singleton
    @Provides
    ApiService provideApiService(Retrofit retrofit){
        return  retrofit.create(ApiService.class);
    }

    @Named("base_url")
    @Provides
    String bindBaseUrl(){
        return Config.BASE_URL;
    }

    @Singleton
    @Provides
    HttpManager provideHttpHelper() {
        return new HttpManager();
    }

    /**
     * 获取Retrofit的实例
     *
     * @param baseUrl 公共的url
     * @param client  OkHttpClient的实例
     * @return Retrofit的实例 --- 单例的
     */
    @Singleton
    @Provides
    Retrofit provideRetrofit(@Named("base_url") String baseUrl, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * 获取OkHttpClient的实例
     *
     * @param loggingInterceptor   日志拦截 debug模式打印日志 release模式不打印日志
     * @return OkHttpClient的实例
     */
    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    /**
     * OKHttp3日志拦截 debug版本打印日志 release版本不打印日志
     *
     * @return HttpLoggingInterceptor的实例
     */
    @Singleton
    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(Config.DUBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return loggingInterceptor;
    }
}
