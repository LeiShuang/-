package com.zfsoft.zfsoft_product.modules.personal.setting;

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
 */
public class SettingActivity extends BaseActivity {

    @Inject
    SettingFragment mSettingFragment;

    private FragmentManager manager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_common;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        manager = getSupportFragmentManager();
        ActivityUtils.addFragmentToActivity(manager,mSettingFragment,R.id.fragment_container);
    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onBackPressed() {
        setResult(10);
        finish();
    }
}
