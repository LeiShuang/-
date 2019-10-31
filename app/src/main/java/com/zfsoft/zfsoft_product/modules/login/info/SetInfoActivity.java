package com.zfsoft.zfsoft_product.modules.login.info;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseActivity;
import com.zfsoft.zfsoft_product.utils.ActivityUtils;

import javax.inject.Inject;

/**
 * Created by ckw
 * on 2019/1/17.
 * 个人信息设置
 */
public class SetInfoActivity extends BaseActivity {

    @Inject
    SetInfoFragment mSetInfoFragment;

    private FragmentManager manager;

//    private int mJumpType;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_common;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        manager = getSupportFragmentManager();
//        Bundle bundle = new Bundle();
//        bundle.putInt("jumpFrom",mJumpType);
//        mSetInfoFragment.setArguments(bundle);
        ActivityUtils.addFragmentToActivity(manager,mSetInfoFragment,R.id.fragment_container);
    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {
//        mJumpType = bundle.getInt("jumpFrom",0);
    }

    @Override
    protected void initListener() {

    }

}