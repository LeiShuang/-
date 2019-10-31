package com.zfsoft.zfsoft_product.modules.personal.change_pwd;

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
 * 账号安全 修改手机号或者密码的第二个界面
 */
public class ChangePwdActivity extends BaseActivity {

    @Inject
    ChangePwdFragment changePwdFragment;

    private int mType;
    private FragmentManager manager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_common;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        manager = getSupportFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putInt("type",mType);
        changePwdFragment.setArguments(bundle);
        ActivityUtils.addFragmentToActivity(manager,changePwdFragment,R.id.fragment_container);
    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {
        mType = bundle.getInt("type");
    }

    @Override
    protected void initListener() {

    }
}
