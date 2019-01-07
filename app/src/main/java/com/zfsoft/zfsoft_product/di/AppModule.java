package com.zfsoft.zfsoft_product.di;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * 创建日期：2018/12/24 on 10:14
 * 描述:
 * 作者:Ls
 */
@Module
public abstract class AppModule {
    @Binds
    abstract Context bindContext(Application application);
}
