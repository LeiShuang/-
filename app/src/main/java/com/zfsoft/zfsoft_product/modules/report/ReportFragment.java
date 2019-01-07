package com.zfsoft.zfsoft_product.modules.report;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseFragment;
import com.zfsoft.zfsoft_product.di.ActivityScoped;
import com.zfsoft.zfsoft_product.modules.try_use.viewpager_modules.ChildPagerFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 创建日期：2018/12/24 on 10:55
 * 描述:
 * 作者:Ls
 */
@ActivityScoped
public class ReportFragment extends BaseFragment {


    @BindView(R.id.report_add)
    ImageView mReportAdd;
    @BindView(R.id.report_search)
    SearchView mReportSearch;
    @BindView(R.id.report_tabLayout)
    TabLayout mReportTabLayout;
    @BindView(R.id.report_view_pager)
    ViewPager mReportViewPager;
    private List<Fragment> fragmentLists;
    private String[] tabTexts = new String[]{"热门测评","美妆","个护家清","零食"};

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_report;
    }

    @Inject
    public ReportFragment() {

    }

    @Override
    protected void initVariables() {
        fragmentLists = new ArrayList<>();
        for (int i = 0; i < tabTexts.length; i++) {
            fragmentLists.add(ChildPagerFragment.newInstance("" + i));
        }
    }

    @Override
    protected void handleBundle(Bundle bundle) {

    }

    @Override
    protected void operateViews(View view) {
        FragmentManager fm = ReportFragment.this.getChildFragmentManager();
        ReportViewPagerAdapter adapter = new ReportViewPagerAdapter(fm,tabTexts,fragmentLists);
        mReportViewPager.setAdapter(adapter);
        mReportTabLayout.setupWithViewPager(mReportViewPager);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void initPresenter() {

    }


}
