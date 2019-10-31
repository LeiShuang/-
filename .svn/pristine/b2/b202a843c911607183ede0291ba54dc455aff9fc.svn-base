package com.zfsoft.zfsoft_product.modules.home.search;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.EditText;

import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseActivity;
import com.zfsoft.zfsoft_product.modules.personal.my_collection.MyCollectionReportFragment;
import com.zfsoft.zfsoft_product.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by ckw
 * on 2019/4/6.
 */
public class HomeSearchActivity extends BaseActivity{


    @Inject
    HomeSearchFragment homeSearchFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_common;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),homeSearchFragment,R.id.fragment_container);
    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {
    }

    @Override
    protected void initListener() {

    }
}
