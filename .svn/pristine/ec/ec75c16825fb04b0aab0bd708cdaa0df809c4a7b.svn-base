package com.zfsoft.zfsoft_product.modules.personal.my_try;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseActivity;
import com.zfsoft.zfsoft_product.utils.ActivityUtils;

import javax.inject.Inject;

/**
 * Created by ckw
 * on 2019/1/21.
 */
public class MyTryActivity extends BaseActivity{
    @Inject
    MyTryFragment myTryFragment;

    @Inject
    MyTrySingleFragment myTrySingleFragment;

    private boolean mIsFromHome = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_common;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Bundle temp = new Bundle();
        temp.putBoolean("home",mIsFromHome);
        myTryFragment.setArguments(temp);
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),myTryFragment,R.id.fragment_container);
    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {
       mIsFromHome =  bundle.getBoolean("home",false);
    }

    @Override
    protected void initListener() {

    }
}
