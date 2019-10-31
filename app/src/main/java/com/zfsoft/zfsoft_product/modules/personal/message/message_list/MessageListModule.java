package com.zfsoft.zfsoft_product.modules.personal.message.message_list;

import com.zfsoft.zfsoft_product.di.FragmentScoped;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ckw
 * on 2019/1/23.
 */
@Module
public abstract class MessageListModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract MessageListFragment messageListFragment();
}
