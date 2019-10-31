package com.zfsoft.zfsoft_product.modules.personal.accout_safe;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseActivity;
import com.zfsoft.zfsoft_product.utils.ActivityUtils;

import javax.inject.Inject;

/**
 * Created by ckw
 * on 2019/1/18.
 * 账户安全
 */
public class AccountSafeActivity extends BaseActivity {

    @Inject
    AccountSafeFragment accountSafeFragment;

    private FragmentManager manager;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_common;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        manager = getSupportFragmentManager();
        ActivityUtils.addFragmentToActivity(manager,accountSafeFragment,R.id.fragment_container);
    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {

    }

    @Override
    protected void initListener() {

    }
}