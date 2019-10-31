package com.zfsoft.zfsoft_product.modules.report.other_info;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseActivity;
import com.zfsoft.zfsoft_product.entity.InfoServer;
import com.zfsoft.zfsoft_product.entity.UserInfo;
import com.zfsoft.zfsoft_product.utils.ActivityUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建日期：2019/1/17 on 16:49
 * 描述:他人个人信息界面Activity
 * 作者:Ls
 */
public class OtherInformationActivity extends BaseActivity implements View.OnClickListener {
    @Inject
    OtherInformationFragment mOtherInformationFragment;


    private FragmentManager mManager;
    private OtherInformationFragment mFragment;
    private ImageView mIvBack;
    private String mOtherId;
    private String mOtherName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_other_information;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mIvBack = (ImageView) findViewById(R.id.iv_other_info_back);
        TextView tvTitle = findViewById(R.id.tv_info_title);
        tvTitle.setText(mOtherName != null?mOtherName + "的主页": "用户主页" );
        Bundle bundle = new Bundle();
        bundle.putString("otherUserId",mOtherId);
        mOtherInformationFragment.setArguments(bundle);
        ActivityUtils.addFragmentToActivity(mManager, mOtherInformationFragment, R.id.frame_other_info);
    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {
        mOtherId = bundle.getString("otherUserId");
        mOtherName = bundle.getString("otherUserName");
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).statusBarColor(R.color.colorWhite).statusBarDarkFont(true,0.2f).init();
    }

    @Override
    public void initVariables() {
        mManager = getSupportFragmentManager();
    }

    @Override
    protected void initListener() {
        mIvBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int key = v.getId();
        if (key == R.id.iv_other_info_back){
            this.finish();
        }
    }
}