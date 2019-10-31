package com.zfsoft.zfsoft_product.modules.try_use.try_use_detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.gyf.barlibrary.ImmersionBar;
import com.vondear.rxtool.view.RxToast;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseActivity;
import com.zfsoft.zfsoft_product.base.BaseFragment;
import com.zfsoft.zfsoft_product.modules.try_use.TryUseFragmentAdapter;
import com.zfsoft.zfsoft_product.modules.try_use.viewpager_modules.TryUseViewPagerFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建日期：2019/1/11 on 17:30
 * 描述:试用详情activity
 * 作者:Ls
 */
public class TryUseDetailActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mIvReportDetailBack;

    private TabLayout mTryDetailTabLayout;

    private ViewPager mTryUserDetailViewPager;

    private List<Fragment> mFragmentList;
    private List<String> tabTexts;
    private String mId;

    @Override
    protected int getLayoutId() {
        return R.layout.actitity_try_use_detail;
    }

    @Override
    public void initVariables() {

        tabTexts = new ArrayList<>();
        tabTexts.add("产品详情");
        tabTexts.add("试用报告");
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mIvReportDetailBack = findViewById(R.id.iv_report_detail_back);
        mTryDetailTabLayout = findViewById(R.id.try_detail_tabLayout);
        mTryUserDetailViewPager = findViewById(R.id.try_user_detail_viewPager);

        mFragmentList = new ArrayList<>();
        mFragmentList.add(ProductDetailFragment.newInstance(mId));
        mFragmentList.add(TryUseReportFragment.newInstance(mId));
        FragmentManager manager = getSupportFragmentManager();
        TryUseDetailPagerAdapter adapter = new TryUseDetailPagerAdapter(manager,tabTexts, mFragmentList);
        mTryUserDetailViewPager.setAdapter(adapter);
        mTryDetailTabLayout.setupWithViewPager(mTryUserDetailViewPager);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).statusBarColor(R.color.colorWhite).statusBarDarkFont(true,0.2f).init();
    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {
        mId = bundle.getString("thingsId","");
    }

    @Override
    protected void initListener() {
        mIvReportDetailBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int key = v.getId();
        if (key == R.id.iv_report_detail_back) {
            setResult(100);
            this.finish();
        }
    }

    @Override
    public void onBackPressed() {
        setResult(100);
        finish();
    }

}