package com.zfsoft.zfsoft_product.modules.home.notice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseActivity;
import com.zfsoft.zfsoft_product.utils.ActivityUtils;

import javax.inject.Inject;

/**
 * Created by ckw
 * on 2019/4/12.
 */
public class NoticeActivity extends BaseActivity {

    @Inject
    NoticeFragment mNoticeFragment;

    private String mNoticeId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_common;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        FragmentManager manager = getSupportFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putString("noticeId",mNoticeId);
        mNoticeFragment.setArguments(bundle);
        ActivityUtils.addFragmentToActivity(manager,mNoticeFragment,R.id.fragment_container);
    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {
        mNoticeId = bundle.getString("noticeId");
    }

    @Override
    protected void initListener() {

    }
}
