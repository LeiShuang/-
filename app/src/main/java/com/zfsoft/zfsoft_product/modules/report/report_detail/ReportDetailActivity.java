package com.zfsoft.zfsoft_product.modules.report.report_detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseActivity;
import com.zfsoft.zfsoft_product.utils.ActivityUtils;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 创建日期：2019/1/8 on 17:27
 * 描述:报告详情Activity --->接收参数  报告id  和 写报告的人id  分别用reportId，reportUserId接收
 * 作者:Ls
 */
public class ReportDetailActivity extends BaseActivity {
    @Inject
    ReportDetailFragment mDetailFragment;
    private FragmentManager mManager;
    private ReportDetailFragment mFragment;
    private int mReportId;
    private String mReporterId;
    @Override
    public void initVariables() {
        mManager = getSupportFragmentManager();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_report_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mFragment = (ReportDetailFragment) mManager.findFragmentById(R.id.fragment_report_detail);
        if (mFragment == null){
            mFragment = ReportDetailFragment.newInstance(mReportId,mReporterId);
            ImmersionBar.with(this).reset().transparentStatusBar().init();
            ActivityUtils.addFragmentToActivity(mManager,mFragment,R.id.fragment_report_detail);
        }
    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {
        mReportId = bundle.getInt("reportId",1);
        mReporterId = bundle.getString("reportUserId","1");
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onBackPressed() {
        setResult(100);
        finish();
    }


}