package com.zfsoft.zfsoft_product.modules.personal.my_report;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.vondear.rxtool.view.RxToast;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseActivity;
import com.zfsoft.zfsoft_product.utils.ActivityUtils;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ckw
 * on 2019/1/21.
 * 我的报告列表
 */
public class MyReportActivity extends BaseActivity implements View.OnClickListener {

    @Inject
    MyReportFragment myReportFragment;

    private RelativeLayout mRlBack;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_report;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mRlBack = findViewById(R.id.rl_report_back);
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), myReportFragment,R.id.fragment_container);
    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {

    }

    @Override
    protected void initListener() {
        mRlBack.setOnClickListener(this);

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).statusBarColor(R.color.colorWhite).statusBarDarkFont(true,0.2f).init();
    }

    @Override
    public void onClick(View v) {
        int key = v.getId();
        switch (key){
            case R.id.rl_report_back:
                finish();
                break;

            default:
                break;
        }
    }

}
