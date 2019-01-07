package com.zfsoft.zfsoft_product.base;

import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.gyf.barlibrary.ImmersionBar;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.modules.home.HomeFragment;
import com.zfsoft.zfsoft_product.modules.report.ReportFragment;
import com.zfsoft.zfsoft_product.modules.try_use.TryUseFragment;
import com.zfsoft.zfsoft_product.modules.personal.PersonalFragment;
import com.zfsoft.zfsoft_product.utils.FragmentUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import devlight.io.library.ntb.NavigationTabBar;

/**
 * 主页Activity,通过FragmentTransaction来管理Fragment
 * */
public class HomeActivity extends BaseActivity implements NavigationTabBar.OnTabBarSelectedIndexListener {
    @Inject
    HomeFragment mGetHomeFragment;

    @Inject
    TryUseFragment mGetTryUseFragment;

    @Inject
    PersonalFragment mGetPersonalFragment;

    @Inject
    ReportFragment mGetReportFragment;

    private int mCurrentIndex = 0;
    private FragmentManager mFragmentManager;

    public static final String TAG_HOME_FRAGMENT = "HomeFragment";
    public static final String TAG_PERSONAL_FRAGMENT = "PersonalFragment";
    public static final String TAG_NEWS_FRAGMENT = "TryUseFragment";
    public static final String TAG_SHOPPING_FRAGMENT = "ReportFragment";
    private HomeFragment mHomeFragment;
    private TryUseFragment mTryUseFragment;
    private PersonalFragment mPersonalFragment;
    private ReportFragment mReportFragment;
    private NavigationTabBar mNavigationTabBar;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("currentIndex",mCurrentIndex);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).init();
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        mNavigationTabBar = (NavigationTabBar) findViewById(R.id.ntb);
        mFragmentManager = getSupportFragmentManager();
        initNavigationTabBar();
        initSaveInstanceState(savedInstanceState);
        showFragment(mCurrentIndex);

    }

    private void showFragment(int currentIndex) {
        hideAllFragment();
        switch (currentIndex){
            case 0:
                FragmentUtils.show(mHomeFragment);
                ImmersionBar.with(this).reset().transparentStatusBar().init();
                break;

            case 1:
                FragmentUtils.show(mTryUseFragment);
                break;

            case 2:
                FragmentUtils.show(mReportFragment);
                break;

            case 3:
                FragmentUtils.show(mPersonalFragment);
                break;
        }
    }

    private void hideAllFragment() {
        FragmentUtils.hide(mHomeFragment);
        FragmentUtils.hide(mReportFragment);
        FragmentUtils.hide(mTryUseFragment);
        FragmentUtils.hide(mPersonalFragment);
    }

    private void initSaveInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null){
            mCurrentIndex  = savedInstanceState.getInt("currentIndex");
            mHomeFragment = (HomeFragment) FragmentUtils.findFragment(mFragmentManager,HomeFragment.class);
            mTryUseFragment = (TryUseFragment)mFragmentManager.findFragmentByTag(TAG_NEWS_FRAGMENT);
            mReportFragment = (ReportFragment) mFragmentManager.findFragmentByTag(TAG_SHOPPING_FRAGMENT);
            mPersonalFragment = (PersonalFragment)mFragmentManager.findFragmentByTag(TAG_PERSONAL_FRAGMENT);
        }else{
            mHomeFragment = mGetHomeFragment;
            mTryUseFragment = mGetTryUseFragment;
            mReportFragment = mGetReportFragment;
            mPersonalFragment = mGetPersonalFragment;
        }
        addAllFragment();
    }

    private void addAllFragment() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        FragmentUtils.add(mFragmentManager,mHomeFragment,R.id.rl_home_container);
        FragmentUtils.add(mFragmentManager, mTryUseFragment,R.id.rl_home_container);
        FragmentUtils.add(mFragmentManager, mReportFragment,R.id.rl_home_container);
        FragmentUtils.add(mFragmentManager,mPersonalFragment,R.id.rl_home_container);
    }

    /**
     * 初始化底部Tab栏
     * */
    private void initNavigationTabBar() {
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
       models.add(
               new NavigationTabBar.Model.Builder(getResources().getDrawable(R.mipmap.ico_home),getResources().getColor(R.color.colorWhite))
               .title("首页")
               .build()
       );

        models.add(
                new NavigationTabBar.Model.Builder(getResources().getDrawable(R.mipmap.ico_alive),getResources().getColor(R.color.colorWhite))
                        .title("试用")
                        .build()
        );

        models.add(
                new NavigationTabBar.Model.Builder(getResources().getDrawable(R.mipmap.ico_home_speak),getResources().getColor(R.color.colorWhite))
                        .title("报告")
                        .build()
        );

        models.add(
                new NavigationTabBar.Model.Builder(getResources().getDrawable(R.mipmap.ico_home_mine),getResources().getColor(R.color.colorWhite))
                        .title("我的")
                        .build()
        );

        mNavigationTabBar.setModels(models);
        //默认第一个Fragment
        mNavigationTabBar.setModelIndex(0);


    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {

    }

    @Override
    protected void initListener() {
        mNavigationTabBar.setOnTabBarSelectedIndexListener(this);
    }


    @Override
    public void onStartTabSelected(NavigationTabBar.Model model, int index) {

    }

    @Override
    public void onEndTabSelected(NavigationTabBar.Model model, int index) {
        switch (index){
            case 0:
                mCurrentIndex = 0;
                showFragment(0);
               ImmersionBar.with(this).reset().transparentStatusBar().init();
                break;
            case 1:
                mCurrentIndex = 1;
                showFragment(1);
                ImmersionBar.with(this).reset().statusBarColor(R.color.try_use_top_color).init();
                break;
            case 2:
                mCurrentIndex = 2;
                showFragment(2);
                ImmersionBar.with(this).reset().statusBarColor(R.color.try_use_top_color).fitsSystemWindows(true).init();
                break;
            case 3:
                mCurrentIndex = 3;
                showFragment(3);
                ImmersionBar.with(this).statusBarDarkFont(true, 0.2f).init();
                break;
        }
    }
}
