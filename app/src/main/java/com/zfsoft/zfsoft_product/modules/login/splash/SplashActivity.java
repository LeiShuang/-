package com.zfsoft.zfsoft_product.modules.login.splash;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.gyf.barlibrary.ImmersionBar;
import com.vondear.rxtool.RxActivityTool;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseActivity;
import com.zfsoft.zfsoft_product.utils.ActivityUtils;

import javax.inject.Inject;

/**
 * Created by ckw
 * on 2019/1/23.
 */
public class SplashActivity extends BaseActivity {


    @Inject
    SplashFragment splashFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_common;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),splashFragment,R.id.fragment_container);
    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).statusBarColor(R.color.login_bar_color).init();
    }

}
