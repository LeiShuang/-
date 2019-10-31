package com.zfsoft.zfsoft_product.modules.home.search;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseFragment;
import com.zfsoft.zfsoft_product.modules.personal.my_collection.MyCollectionFragment;
import com.zfsoft.zfsoft_product.modules.personal.my_try.CommonFragmentAdapter;
import com.zfsoft.zfsoft_product.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ckw
 * on 2019/4/6.
 */
public class HomeSearchFragment extends BaseFragment {

    @Inject
    public HomeSearchFragment(){}

    private String[] tabTexts = new String[]{"试用商品","试用报告"};

    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.search_tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.search_view_pager)
    ViewPager mViewPager;

    private int mPosition = 0;//当前选中的位置
    private List<Fragment> mFragmentList;
    private  SearchTryUseFragment mSearchTryUseFragment;
    private SearchReportFragment mSearchReportFragment;

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_home_search;
    }

    @Override
    protected void initVariables() {
        mFragmentList = new ArrayList<>();
        mSearchTryUseFragment = SearchTryUseFragment.newInstance();
        mSearchReportFragment = SearchReportFragment.newInstance();
        mFragmentList.add(mSearchTryUseFragment);
        mFragmentList.add(mSearchReportFragment);
    }

    @Override
    protected void handleBundle(Bundle bundle) {

    }

    @Override
    protected void operateViews(View view) {
        FragmentManager fm = HomeSearchFragment.this.getChildFragmentManager();
        CommonFragmentAdapter adapter = new CommonFragmentAdapter<Fragment>(fm,tabTexts,mFragmentList);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initListener() {

        mEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    int selectedTabPosition = mTabLayout.getSelectedTabPosition();
                    if(selectedTabPosition == 0){
                        mSearchTryUseFragment.setSearchTitle(mEtSearch.getText().toString().trim());
                    }else {
                        mSearchReportFragment.setSearchTitle(mEtSearch.getText().toString().trim());
                    }
                    return true;
                }
                return false;
            }
        });
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