package com.zfsoft.zfsoft_product.modules.personal.account_address;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseActivity;
import com.zfsoft.zfsoft_product.utils.ActivityUtils;

import javax.inject.Inject;

/**
 * Created by ckw
 * on 2019/1/19.
 */
public class AccountAddressActivity extends BaseActivity {
    @Inject
    AccountAddressFragment accountAddressFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_common;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        FragmentManager manager = getSupportFragmentManager();
        ActivityUtils.addFragmentToActivity(manager,accountAddressFragment,R.id.fragment_container);
    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {

    }

    @Override
    protected void initListener() {

    }
}
