package com.zfsoft.zfsoft_product.modules.personal.my_try;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseFragment;
import com.zfsoft.zfsoft_product.modules.try_use.TryUseFragment;
import com.zfsoft.zfsoft_product.modules.try_use.TryUseFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ckw
 * on 2019/1/21.
 */
public class MyTryFragment extends BaseFragment  {

    private FragmentManager fm;

    @Inject
    public MyTryFragment() {
    }

    private boolean mIsFromHome;

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.try_view_pager)
    ViewPager mViewPager;

    private List<MyTrySingleFragment> mFragmentList;

    private String[] tabTexts = new String[]{"已申请","已通过","待提交"};

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_my_try;
    }

    @Override
    protected void initVariables() {
        mFragmentList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            //commoditytype:（1：已通过，2：已申请，3：待提交）
            mFragmentList.add(MyTrySingleFragment.newInstance(i+1));
        }
    }

    @Override
    protected void handleBundle(Bundle bundle) {
        mIsFromHome = bundle.getBoolean("home",false);
    }

    @Override
    protected void operateViews(View view) {
        fm = MyTryFragment.this.getChildFragmentManager();
        FragmentAdapter adapter = new FragmentAdapter(fm,tabTexts,mFragmentList);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        if(mIsFromHome){
            mTabLayout.getTabAt(2).select();
        }
    }


     class FragmentAdapter extends FragmentPagerAdapter {

        private String[] titles;
        private List<MyTrySingleFragment> fragments;

        public FragmentAdapter(FragmentManager fm, String[] titles, List<MyTrySingleFragment> fragments) {
            super(fm);
            this.titles = titles;
            this.fragments = fragments;
        }

        @Override
        public MyTrySingleFragment getItem(int position) {
            return  fragments.get(position);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

         @Override
         public int getItemPosition(@NonNull Object object) {
             return PagerAdapter.POSITION_NONE;
         }

         @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

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
