package com.zfsoft.zfsoft_product.modules.personal.my_collection;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseFragment;
import com.zfsoft.zfsoft_product.modules.personal.my_try.CommonFragmentAdapter;
import com.zfsoft.zfsoft_product.modules.personal.my_try.MyTryFragment;
import com.zfsoft.zfsoft_product.modules.personal.my_try.MyTrySingleFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ckw
 * on 2019/1/21.
 */
public class MyCollectionFragment extends BaseFragment {

    @Inject
    public MyCollectionFragment() {
    }

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.try_view_pager)
    ViewPager mViewPager;

    private List<Fragment> mFragmentList;

    private String[] tabTexts = new String[]{"试用商品","试用报告"};
    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_my_try;
    }

    @Override
    protected void initVariables() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(MyCollectionSingleFragment.newInstance(1));
        mFragmentList.add(MyCollectionReportFragment.newInstance(2));
    }

    @Override
    protected void handleBundle(Bundle bundle) {

    }

    @Override
    protected void operateViews(View view) {
        FragmentManager fm = MyCollectionFragment.this.getChildFragmentManager();
        CommonFragmentAdapter adapter = new CommonFragmentAdapter<Fragment>(fm,tabTexts,mFragmentList);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected boolean immersionEnabled() {
        return true;
    }

    @Override
    protected void immersionInit() {
        super.immersionInit();
        if (immersionBar == null) {
            return;
        }
        immersionBar.statusBarDarkFont(true);
        immersionBar.statusBarColor(R.color.colorWhite)
                .init();
    }
}
