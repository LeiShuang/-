package com.zfsoft.zfsoft_product.modules.personal.my_platform;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseActivity;
import com.zfsoft.zfsoft_product.utils.ActivityUtils;

import javax.inject.Inject;

/**
 * Created by ckw
 * on 2019/3/15.
 */
public class MyPlatformActivity extends BaseActivity {

    @Inject
    MyPlatformFragment myPlatformFragment;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_common;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),myPlatformFragment,R.id.fragment_container);
    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {

    }

    @Override
    protected void initListener() {

    }
}