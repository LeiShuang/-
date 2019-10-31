package com.zfsoft.zfsoft_product.modules.personal.integral_mall.integral_detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseActivity;
import com.zfsoft.zfsoft_product.utils.ActivityUtils;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ckw
 * on 2019/1/23.
 */
public class IntegralDetailActivity extends BaseActivity {
    @Inject
    IntegralDetailFragment integralDetailFragment;

    private RelativeLayout mRlBack;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_integral_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mRlBack = findViewById(R.id.rl_integral_detail);
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),integralDetailFragment,R.id.fragment_container);
    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {

    }

    @Override
    protected void initListener() {
        mRlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).statusBarColor(R.color.colorWhite).statusBarDarkFont(true,0.2f).init();
    }
}