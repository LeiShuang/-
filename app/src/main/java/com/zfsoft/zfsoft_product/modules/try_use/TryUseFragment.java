package com.zfsoft.zfsoft_product.modules.try_use;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TableLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseFragment;
import com.zfsoft.zfsoft_product.di.ActivityScoped;
import com.zfsoft.zfsoft_product.modules.try_use.viewpager_modules.ChildPagerFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 创建日期：2018/12/24 on 10:55
 * 描述:
 * 作者:Ls
 */
@ActivityScoped
public class TryUseFragment extends BaseFragment {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.try_view_pager)
    ViewPager mViewPager;
    private List<Fragment> fragmentLists;
    private String[] tabTexts = new String[]{"新品","进行中","即将截止","即将开始"};

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_try_use;
    }

    @Inject
    public TryUseFragment() {

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
        mToolbar.setTitle("");
        ImmersionBar.setTitleBar(getActivity(), mToolbar);
        FragmentManager fm = TryUseFragment.this.getChildFragmentManager();
        TryUseFragmentAdapter adapter = new TryUseFragmentAdapter(fm,tabTexts,fragmentLists);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void initPresenter() {

    }

}