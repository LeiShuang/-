package com.zfsoft.zfsoft_product.modules.personal.accout_safe.modify;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseActivity;
import com.zfsoft.zfsoft_product.utils.ActivityUtils;

import javax.inject.Inject;

/**
 * Created by ckw
 * on 2019/1/25.
 * 修改手机号或者密码的第一个界面
 */
public class ModifyPhoneActivity extends BaseActivity{
    @Inject
    ModifyPhoneFragment modifyPhoneFragment;

    private int mType;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_common;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Bundle bundle = new Bundle();
        bundle.putInt("type",mType);
        modifyPhoneFragment.setArguments(bundle);
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),modifyPhoneFragment,R.id.fragment_container);
    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {
        mType = bundle.getInt("type");
    }

    @Override
    protected void initListener() {

    }
}
